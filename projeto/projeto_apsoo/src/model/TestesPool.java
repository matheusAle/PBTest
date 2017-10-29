package model;

import controller.ProjetoController;
import controller.Utils;
import controller.exceptions.CasoDeTesteException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            String path = projeto.getSrc();
            if (Utils.fileExists(path, "")){
                LinkedList<File> lista = new LinkedList<>();
                File[] files = (new File(path)).listFiles();
                gerarListaDeArtefatos(files, "", ProjetoController.getCodigoProjetoAtivo());
            }else {
                throw new CasoDeTesteException("O diretorio do projeto não foi encontrado: src: " + path);
            }
        }catch (Exception e){
            System.err.println("ERRO em TestesPool: " + e.getMessage());
            e.printStackTrace();
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
    public static void alterarCasoDeTeste(CasoDeTeste casoDeTeste, String nome, String descricao, String srcCasoDeTeste, String codigoCasoDeUsoSelecionado) {
        casoDeTeste.setNome(nome);
        casoDeTeste.setSrcCasoDeTeste(Utils.resumeSrcCasoDeTeste(srcCasoDeTeste));
        casoDeTeste.setDescricao(descricao);
        casoDeTeste.setCodigoCasoDeUso(codigoCasoDeUsoSelecionado);
    }

    /**
     * Remove o caso de teste do artefato
     * @param casoDeTeste caso de teste a ser deletado
     * @param artefatoDeTeste artefato que detem o caso de teste
     */
    public static void deletarCasoDeTesteDoArtefato(CasoDeTeste casoDeTeste, ArtefatoDeTeste artefatoDeTeste) {
        artefatoDeTeste.getCasosDeTeste().removeIf((e) -> e.getCodigo().equals(casoDeTeste.getCodigo()));
    }
}
