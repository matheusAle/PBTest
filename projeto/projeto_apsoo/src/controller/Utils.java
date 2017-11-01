package controller;

import view.Componetes.Botao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    /**
     * Substitui as '\' por '/'
     * @param src string com o caminho original
     * @return String refeita
     */
    public static String srcToStorage(String src){
        return src.replaceAll("\\\\", "/");
    }


    /**
     * Substitui as '/' por '\'
     * @param src string com o caminho original
     * @return String refeita
     */
    public static String srcToObject(String src){
        return src.replaceAll( "/","\\\\");
    }

    /**
     * Junta o src do projeto e do artefato/caso de teste e verifica se o arquivo existe.
     * @param srcPrj src do projeto
     * @param srcFile src do artefato/caso de teste
     * @return true se existir.
     */
    public static boolean fileExists(String srcPrj, String srcFile){
        return Files.exists(Paths.get(srcPrj.concat((srcPrj.endsWith("\\")?  "" : "\\")).concat(srcFile)));
    }

    /**
     * remove o src do projeto ativo, do src do passado por paramentro
     * @param src src.
     * @return src resumido.
     */
    public static String resumeSrcCasoDeTeste(String src){
        return src.replace(ProjetoController.getSrcTestesProjetoAtivo(), "");
    }

    public static String srcToCommadLine(String src){
        String s = src.substring(1, src.length());
        s = s.replaceAll(".class", "");
        return s;
    }

}
