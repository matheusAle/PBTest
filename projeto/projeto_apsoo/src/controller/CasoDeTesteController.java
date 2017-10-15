package controller;

import controller.adapters.ArtefatoDeTesteAdapter;
import controller.exceptions.CasoDeTesteException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CasoDeTesteController {

    private static LinkedList<ArtefatoDeTesteAdapter> listaDeArtefatos = new LinkedList<>();
    private static HashMap<String, LinkedList<ArtefatoDeTesteAdapter>> mapaDeArtefatos = new HashMap<>();

    /**
     * Carrega os qrtefatos de teste do projeto ativo
     * @throws CasoDeTesteException lançada quando o diretorio raiz não existir.
     */
    public static void carregarArtefatos() throws CasoDeTesteException {
        String path = "C:\\Users\\matheus\\Dropbox\\projetos\\APSOO\\trabalho_apsoo\\projeto\\projeto_apsoo\\src";//ProjetoController.getInformacoesDoProjetoAtivo().getSrc();
        if (Files.exists(Paths.get(path))){
            LinkedList<File> lista = new LinkedList<>();
            File[] files = (new File(path)).listFiles();
            gerarListaDeArtefatos(files, "");
            gerarMapaDeArtefatos();
        }else {
            throw new CasoDeTesteException("O diretorio do projeto não foi encontrado");
        }
    }

    /**
     * mapeia os artefatos do projeto de acordo com o pacote o qual pertence.
     */
    private static void gerarMapaDeArtefatos() {
        listaDeArtefatos.forEach((e) -> {
            if (mapaDeArtefatos.containsKey(e.getPakage())){
                mapaDeArtefatos.get(e.getPakage()).add(e);
            }else {
                LinkedList<ArtefatoDeTesteAdapter> l = new LinkedList<>();
                l.add(e);
                mapaDeArtefatos.put(e.getPakage(), l);
            }
        });
    }

    /**
     * Gera uma lista com informações dos artefatos do projeto ativo.
     * Esta lista é gerada recursivamente.
     * @param files arquivos do diretorio raiz.
     * @param pakageNome nome do pacote.
     */
    private static void gerarListaDeArtefatos(File[] files, String pakageNome){
        for (File f: files){
            if(f.isDirectory()){
                gerarListaDeArtefatos(f.listFiles(), (pakageNome == "" ? "" : pakageNome+".").concat(f.getName()));
            }else {
                if (f.getName().endsWith(".class") || f.getName().endsWith(".java")) {
                    listaDeArtefatos.add(new ArtefatoDeTesteAdapter(pakageNome, f.getName(), f.getAbsolutePath()));
                }
            }
        }
    }

    public static HashMap<String, LinkedList<ArtefatoDeTesteAdapter>> getMapaDeArtefatos() {
        return mapaDeArtefatos;
    }

    //    public static void main(String[] args) {
//        File[] f = new File("C:\\Users\\matheus\\Dropbox\\projetos\\APSOO\\trabalho_apsoo\\projeto\\projeto_apsoo\\src").listFiles();
//        gerarMapaDeArtefatos(f, "");
//        System.out.println(Arrays.toString(listaDeArtefatos.toArray()));
//    }

}
