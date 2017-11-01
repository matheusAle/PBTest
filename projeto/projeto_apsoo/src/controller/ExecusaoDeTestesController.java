package controller;

import controller.exceptions.ExecusaoExeption;
import model.CasoDeTeste;
import model.Factorys.CasoDeTesteFactory;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

public class ExecusaoDeTestesController {

    private static LinkedList<CasoDeTeste> sequenciaDeExecusao;
    private static String classPath;
    private static String srcJunit;
    private static String srcExtraLibs;
    private static String srcClasses;
    private static String srcCasoDeTeste;
    private static String javaArgs;
    private static String junitArgs;
    private static String comando;

    /**
     * Carrega todos os parametros iniciais para a execução de um roteiro de testes
     * @param seq
     */
    public static void carregar(LinkedList<CasoDeTeste> seq){
        sequenciaDeExecusao = seq;
        srcCasoDeTeste = ProjetoController.getSrcTestesProjetoAtivo();
        srcClasses = ProjetoController.getSrcPruducaoProjetoAtivo();
        String pathextralib = ExecusaoDeTestesController.class.getResource("../resources/lib/hamcrest-core-1.3.jar").getPath();
        srcExtraLibs = pathextralib.substring(1, pathextralib.length());
        srcJunit = ExecusaoDeTestesController.class.getResource("../resources/lib/junit-4.12.jar").getPath();
        srcJunit = srcJunit.substring(1, srcJunit.length());
        javaArgs = "";
        carregarPropriedadesDoProjeto();
        construirComando();
        init();
    }


    public static void init(){
        sequenciaDeExecusao.forEach((e) -> {
            String resultado = run(comando + Utils.srcToCommadLine(e.getSrcCasoDeTeste()));
            CasoDeTesteController.salvarResultado(e, resultado);
        });
    }

    public static String run(String comando){
        String e = "";
        try {
            Process process = Runtime.getRuntime().exec(comando);
            String stdOut = getInputAsString(process.getInputStream());
            String stdErr = getInputAsString(process.getErrorStream());
            return stdOut + "\n" + stdErr;
        } catch (IOException x) {
            e = x.toString();
        }
        return "Erro ao tentar Execultar este caso de teste : " + e;
    }

    private static String getInputAsString(InputStream is)
    {
        try(java.util.Scanner s = new java.util.Scanner(is))
        {
            return s.useDelimiter("\\A").hasNext() ? s.next() : "";
        }
    }

    private static void construirComando(){
        comando = String.format("java -cp \"%s;%s;%s;%s\" %s %s ",
                srcJunit,
                srcExtraLibs,
                srcCasoDeTeste,
                srcClasses,
                javaArgs,
                junitArgs
        );
    }
    /**
     * Carrega as variaveis custoniadas do projeto setadas pelo usuario.
     */
    private static void carregarPropriedadesDoProjeto(){
        String src = ProjetoController.getSrcTestesProjetoAtivo();
        Properties propriedade = new Properties();

        try {
            propriedade.load(new FileReader(src.concat("/props_pbtest.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExecusaoExeption("Erro ao ler o arquivo de propriedades");
        }

        String extraLibs = propriedade.getProperty("variant.extra.path");
        srcExtraLibs.concat(extraLibs.equals("void") ? "" : ";".concat(extraLibs));

        String extrajavaArgs = propriedade.getProperty("extra.args.java");
        javaArgs.concat(extrajavaArgs.equals("void")? "" : " ".concat(extrajavaArgs).concat(" "));

        junitArgs = propriedade.getProperty("extra.args.junit");

        String classpathReplace = propriedade.getProperty("production.class.path");
        String classTestpathReplace = propriedade.getProperty("test.class.path");
        if (!classpathReplace.equals("void")){
            srcClasses = classpathReplace;
        }
        if (!classTestpathReplace.equals("void")) {
            srcCasoDeTeste = classTestpathReplace;
        }

        String custonJunitpath = propriedade.getProperty("variant.junit.path");
        if (!custonJunitpath.equals("void")){
            srcJunit = custonJunitpath;
        }
    }




    public static void main(String[] args) throws IOException {
        String path = ExecusaoDeTestesController.class.getResource("../resources/lib/hamcrest-core-1.3.jar").getPath();
        System.out.println(path.substring(1, path.length()));
    }
}












