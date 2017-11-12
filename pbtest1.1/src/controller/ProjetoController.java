package controller;

import controller.exceptions.ExecusaoExeption;
import controller.exceptions.ProjetoException;
import model.Factorys.ProjetoFactory;
import model.Projeto;
import resources.Arquivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Properties;
import model.TestesPool;

public class ProjetoController {
    private static ProjetoFactory dao = new ProjetoFactory();
    private static model.Projeto projetoAtivo;
    private static LinkedList<Projeto> listaDeProjetos  = listarProjetos();

    /**
     * Persiste as informações do projeto passadas como paramentro.
     *
     * @param text
     * @param nome nome do projeto
     * @param src path do diretorio raiz do projeto
     * @param CASO_DE_USO  prefixo dos casos de uso
     * @param ROTEIRO_DE_TESTE prefixo dos casos roteiros de teste
     * @param CASO_DE_TESTE prefixo dos casos de teste
     * @param descricao descrição do projeto
     * @return true se bem sucedido
     */
    public synchronized static boolean cadastrarProjeto(
            String nome,
            String srcProducao,
            String srcTestes,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String CASO_DE_TESTE,
            String descricao
    ){
        srcProducao =  Utils.srcToStorage(srcProducao);
        srcTestes = Utils.srcToStorage(srcTestes);
        Boolean retorno = dao.salvar(
                nome,
                srcProducao,
                srcTestes,
                CASO_DE_USO,
                ROTEIRO_DE_TESTE,
                CASO_DE_TESTE,
                descricao,
                UsuarioController.getEmailUsuarioLogado()
        );
        try {
            Properties properties = Arquivos.PROPERTIES_PADRAO;
            FileWriter fileWriter = new FileWriter(new File(srcTestes+"/props_pbtest.properties"));
            properties.store(fileWriter, " Arquivo de propropriedades do projeto\n" +
                    " Estes valores são usados pelo software de testes pbtest\n\n" +
                    " variant.junit.path: caminho para um .jar junit diferente do usado pelo software\n" +
                    " variant.extra.path: caminho para bibliotecas extras usadas pelo sistema\n" +
                    " extra.args.java: argumentos extras para a execução do comando java.\n" +
                    " extra.args.junit: argumentos extras para a execução dos testes.\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExecusaoExeption("Erro ao ler o arquivo de propriedade do projeto");
        }
        atualizarListaDeProjetos();

        return retorno;

    }

    private synchronized static LinkedList<Projeto> listarProjetos(){
        return (LinkedList<Projeto>) dao.listar();
    }

    /**
     * Recarrega a lista de projetos com os projetos no banco de dados
     */
    public static void atualizarListaDeProjetos(){
        listaDeProjetos = listarProjetos();
    }

    /**
     * ativa o projeto com o codigo passado como paramentro.
     * @param codigo codigo do projeto a ser ativado.
     * @return true caso a operação seja bem sucedida.
     */
    public synchronized static boolean ativarProjeto(Projeto p) {
        try {
            projetoAtivo = p;
            CasoDeUsoController.recarregarLista();
            CasoDeTesteController.carregarArtefatos();
            RoteiroDeTesteController.carregarRoteirosDoProjetoAtivo();
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }

    /**
     * Persiste as mudanças no dos valores passados como parametro no projeto.
     * @return true caso seja bem sucedido.
     */
    public synchronized static boolean atualizarProjeto(Projeto prj, String nome, String descicao, String srcProducao, String srcTestes) {

        boolean b =  dao.atualizar(nome, descicao, Utils.srcToStorage(srcProducao), Utils.srcToStorage(srcTestes), prj.getCodigo());
        for (Projeto p : listaDeProjetos){
            if (p.getCodigo().equals(prj.getCodigo())){
                p.setSrcTestes(srcTestes);
                p.setDescricao(descicao);
                p.setNome(nome);
                p.setSrcProducao(srcProducao);
                p.setSrcTestes(srcTestes);
                break;
            }
        }
        return b;
    }

    /**
     * @return Retorna a uma <link>LinkedList</link> de <link>Projeto</link> com as informações persistidas no banco de dados.
     */
    public static LinkedList<Projeto> getListaDeProjetos() {
        return listaDeProjetos;
    }

    /**
     * Gera um prefixo do tipo solicitado.
     * @param tipo tipo de prefixo. Valores possiveis: caso de uso, caso de teste, roteiro de teste;
     * @return retorna umas string com o prefixo.
     * @throws ProjetoException pode ser disparado caso algo de errado.
     */
    public static String gerarCodigo(String tipo){
        String s = "";
        switch (tipo){
            case "caso de uso":
                s = projetoAtivo.getPrefixoCU().concat(dao.buscarContador("contadorCU", projetoAtivo.getCodigo()));
                break;
            case "caso de teste":
                s = projetoAtivo.getPrefixoCT().concat(dao.buscarContador("contadorCT", projetoAtivo.getCodigo()));
                break;
            case "roteiro de teste":
                s = projetoAtivo.getPrefixoRT().concat(dao.buscarContador("contadorRT", projetoAtivo.getCodigo()));
                break;
        }
        return s;
    }

    /**
     * @return Retorna uma referencia para o projeto ativo.
     */
    public static Projeto getProjetoAtivo() {
        return projetoAtivo;
    }

    /**
     * @return retorna verdadeiro de o projeto ativo não for nulo
     */
    public static synchronized boolean temProjetoAtivo() {
        return projetoAtivo != null;
    }



    /**
     * Deleta o projeto informado do banco de dados. E o remove da lista de projetos.
     * @param p
     */
    public synchronized static void deletarProjetoDeCodigo(Projeto p) {
        try {
            Files.deleteIfExists(Paths.get(p.getSrcProducao().concat("/").concat("props_pbtest.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (p.getCodigo().equals(projetoAtivo.getCodigo())){
                projetoAtivo = null;
                TestesPool.limparPool();
            } 
        }catch(NullPointerException e){}
        dao.deletar("id = " + p.getCodigo());
        listaDeProjetos.removeIf((x) -> p.getCodigo().equals(x.getCodigo()));
    }

    /**
     * @return retorna o codigo do projeto ativo
     */
    public synchronized static String getCodigoProjetoAtivo(){
        return projetoAtivo.getCodigo();
    }

    /**
     * @return Retorna o src do projeto ativo.
     */
    public synchronized static String getSrcPruducaoProjetoAtivo(){
        return projetoAtivo.getSrcProducao();
    }

    public synchronized static String getSrcTestesProjetoAtivo(){
        return projetoAtivo.getSrcTestes();
    }

    public synchronized static  String getNomeProjetoAtivo(){
        return projetoAtivo.getNome();
    }
    
}
