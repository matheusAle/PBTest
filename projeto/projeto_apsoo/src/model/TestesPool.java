package model;

import controller.ProjetoController;
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
            if (Files.exists(Paths.get(path))){
                LinkedList<File> lista = new LinkedList<>();
                File[] files = (new File(path)).listFiles();
                gerarListaDeArtefatos(files, "", ProjetoController.getInformacoesDoProjetoAtivo().getCodigo());
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
                    listaDeArtefatos.add(new ArtefatoDeTeste(pakageNome, f.getName(), f.getAbsolutePath(), prjID));
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

    public synchronized static void setCasosDeTesteDoArtefato(LinkedList<CasoDeTeste> lista, String nomeArquivo) {
        listaDeArtefatos.forEach((artefato)->{
            if (artefato.getNomeArquivo().equals(nomeArquivo)){
                artefato.setCasosDeTeste(lista);
            }
        });

    }
}
