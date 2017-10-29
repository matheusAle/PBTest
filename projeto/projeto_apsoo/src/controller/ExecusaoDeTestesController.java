package controller;

import controller.exceptions.ExecusaoExeption;
import model.CasoDeTeste;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

public class ExecusaoDeTestesController {

    private static LinkedList<CasoDeTeste> sequenciaDeExecusao;
    String classPath;
    String srcJunit;
    String srcExtraLibs;
    String srcClasses;
    String srcCasoDeTeste;
    String javaArgs;
    String junitArgs;

    public static void execultarTestes(LinkedList<CasoDeTeste> seq){
        sequenciaDeExecusao = seq;
        
    }


    private static void carregarPropriedadesDoProjeto(){
        String src = ProjetoController.getSrcProjetoAtivo();
        Properties propriedade = new Properties();
        try {
            propriedade.load(new FileReader(src.concat("/props_pbtest.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExecusaoExeption("Erro ao ler o arquivo de propriedades");
        }



    }




    public static void main(String[] args) throws IOException {
        StringBuilder classpath = new StringBuilder();
        String src = "C:\\Users\\matheus\\Google Drive\\java\\exercicio do URI\\src"; // TODO mudar


        System.out.println(classpath.toString().replaceAll("\\\\", "/"));

        Process p = Runtime.getRuntime().exec("java -version");
        Runtime.getRuntime().exec("c:\\Windows\\System32\\calc.exe");
        BufferedInputStream inputStream = new BufferedInputStream(p.getInputStream());
        BufferedInputStream erroStream = new BufferedInputStream(p.getErrorStream());
        BufferedOutputStream outStream = new BufferedOutputStream(p.getOutputStream());

        System.out.println(inputStream.read());
    }
}

interface inteface {
    int atributo = 1;

    public void soma(int i);

    default int  subtrai(int i){
        return i -atributo;
    }
}


abstract class abustrata extends SistemaController{

    int var;
    static int index;
    final int max = 10;

    protected int getVar(){
        return var;
    }

    abstract void incremetarIndex();
}













