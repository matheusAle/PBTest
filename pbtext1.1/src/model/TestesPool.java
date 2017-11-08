package model;

import controller.ProjetoController;
import controller.Utils;
import controller.exceptions.CasoDeTesteException;
import model.Factorys.CasoDeTesteFactory;

import java.io.File;
import java.util.LinkedList;

/**
 * Classe que mantem referecias para todos os caso de teste e artefatos de teste do projeto ativo
 */
public final class TestesPool {

    private static LinkedList<ArtefatoDeTeste> listaDeArtefatos = new LinkedList<>();

    /**
     * Remove todas as referancias da pool.
     */
    public static synchronized void limparPool(){
        listaDeArtefatos = new LinkedList<>();
    }

    /**
     * Carrega os artefatos de teste e casos de teste dos artefatos na memoria.
     * @param projeto
     * @throws CasoDeTesteException lançada quando o diretorio raiz não existir.
     */
    public static synchronized void carregarPool(Projeto projeto) throws CasoDeTesteException {
        try {
            String path = projeto.getSrcProducao();
            if (Utils.fileExists(path, "")){
                LinkedList<File> lista = new LinkedList<>();
                File[] files = (new File(path)).listFiles();
                gerarListaDeArtefatos(files, "", ProjetoController.getCodigoProjetoAtivo());
                carregarCasosDeTesteDosArtefatos();
            }else {
                throw new CasoDeTesteException("O diretório do projeto não foi encontrado: src: " + path);
            }
        }catch (Exception e){
            System.err.println("ERRO em TestesPool: " + e.getCause());
        }

    }

    /**
     * Gera uma lista com informações dos artefatos do projeto ativo.
     * Esta lista é gerada recursivamente.
     * @param files arquivos do diretorio raiz.
     * @param pakageNome nome do pacote.
     */
    private static void gerarListaDeArtefatos(File[] files, String pakageNome, String prjID){
        for (File f: files){
            if(f.isDirectory()){
                gerarListaDeArtefatos(f.listFiles(), (pakageNome == "" ? "" : pakageNome+".").concat(f.getName()), prjID);
            }else {
                if (f.getName().endsWith(".class") || f.getName().endsWith(".java")) {
                    listaDeArtefatos.add(new ArtefatoDeTeste(pakageNome, f.getName(), prjID));
                }
            }
        }
    }

    /**
     * @return retorna a lista de artefatos de teste.
     */
    public synchronized static LinkedList<ArtefatoDeTeste> getListaDeArtefatos() {
        return listaDeArtefatos;
    }

    /**
     * Vincula ao artefato com o nome de arquivo especificado a lista de casos de teste
     * informada
     * @param lista lista de casos de teste
     * @param nomeArquivo nome do artefato
     */
    public synchronized static void setCasosDeTesteDoArtefato(LinkedList<CasoDeTeste> lista, String nomeArquivo) {
        for (ArtefatoDeTeste a : listaDeArtefatos){
            if (a.getNomeArquivo().equals(nomeArquivo)){
                a.setCasosDeTeste(lista);
                break;
            }
        }
    }

    /**
     * Altera do caso de teste para os passados por paramentro
     * @param casoDeTeste Caso de teste que recebera os novos valores
     * @param nome novo nome
     * @param descricao nova descrição
     * @param srcCasoDeTeste novo src
     * @param codigoCasoDeUsoSelecionado  novo codigo de caso de uso.
     */
    public static CasoDeTeste alterarCasoDeTeste(CasoDeTeste casoDeTeste, String nome, String descricao, String srcCasoDeTeste, String codigoCasoDeUsoSelecionado) {
        casoDeTeste.setNome(nome);
        casoDeTeste.setSrcCasoDeTeste(Utils.resumeSrcCasoDeTeste(srcCasoDeTeste));
        casoDeTeste.setDescricao(descricao);
        casoDeTeste.setCodigoCasoDeUso(codigoCasoDeUsoSelecionado);
        return casoDeTeste;
    }

    /**
     * Remove o caso de teste do artefato
     * @param casoDeTeste caso de teste a ser deletado
     * @param artefatoDeTeste artefato que detem o caso de teste
     */
    public static void deletarCasoDeTesteDoArtefato(CasoDeTeste casoDeTeste, ArtefatoDeTeste artefatoDeTeste) {
        artefatoDeTeste.getCasosDeTeste().removeIf((e) -> e.getCodigo().equals(casoDeTeste.getCodigo()));
    }


    /**
     * Dada a lista de codigos, retorna uma lista com casos de teste
     * @param listaDeCodigos codigos dos casos de teste requeridos
     * @return lista com os casos de teste de cogigo requerido.
     */
    public static LinkedList<CasoDeTeste> buscarCasosDeTeste(LinkedList<String> listaDeCodigos){
        LinkedList<CasoDeTeste> resultado = new LinkedList<>();
        for (ArtefatoDeTeste artefato : listaDeArtefatos){ //percorre os artefatos
            for (CasoDeTeste casoDeTeste : artefato.getCasosDeTeste()) { //percorre os casos de teste de cada artefato
                if (listaDeCodigos.contains(casoDeTeste.getCodigo())) // verifica se o caso de teste é um dos requeridos
                    resultado.add(casoDeTeste); // adiciona o casos de teste ao resultado da busca
            }
        }
        return resultado;
    }

    /**
     * Carrega os casos de teste de todos os artefatos.
     */
    public static void carregarCasosDeTesteDosArtefatos(){
        CasoDeTesteFactory dao = new CasoDeTesteFactory();
        listaDeArtefatos.forEach(
                (artefato) -> {
                    artefato.setCasosDeTeste(dao.listar(ProjetoController.getCodigoProjetoAtivo(), artefato.getCaminhoRelativoAoProjeto()));
                }
        );
    }

    public static void setResultadoDoTeste(CasoDeTeste e, String resultado) {
        e.setResultado(resultado);
    }
}
