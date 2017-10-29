package controller;

import controller.exceptions.ProjetoException;
import model.Factorys.ProjetoFactory;
import model.Projeto;
import view.Componetes.MeuLabel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;

public class ProjetoController {
    private static ProjetoFactory dao = new ProjetoFactory();
    private static model.Projeto projetoAtivo;
    private static LinkedList<Projeto> listaDeProjetos  = listarProjetos();
    private static Projeto projetoParaEditar;

    /**
     * Persiste as informações do projeto passadas como paramentro.
     * @param nome nome do projeto
     * @param src path do diretorio raiz do projeto
     * @param CASO_DE_TESTE prefixo dos casos de teste
     * @param CASO_DE_USO  prefixo dos casos de uso
     * @param ROTEIRO_DE_TESTE prefixo dos casos roteiros de teste
     * @param descricao descrição do projeto
     * @return true se bem sucedido
     */
    public synchronized static boolean cadastrarProjeto (
            String nome,
            String src,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String CASO_DE_TESTE,
            String descricao
            ){
        String srcP = src.replaceAll("\\\\", "/");
        Boolean retorno = dao.salvar(
                nome,
                srcP,
                CASO_DE_USO,
                ROTEIRO_DE_TESTE,
                CASO_DE_TESTE,
                descricao,
                UsuarioController.getEmailUsuarioLogado()
        );

        File file = new File (src + "/testes");
        file.setWritable(true);
        file.setReadable(true);
        file.mkdir();

        file = new File (src + "/testes/pbtest");
        file.setWritable(true);
        file.setReadable(true);
        file.mkdir();
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
    public synchronized static boolean ativarProjeto(String codigo) {
        try {
            projetoAtivo = dao.buscar("id = " + codigo);
            CasoDeTesteController.carregarArtefatos();
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }

    /**
     * Constroi um <link>Projeto</link> com todas as imformações de um projeto.
     * @param codigo codigo do projeto que sera atualizado.
     */
    public static void setProjetoAtualizavel(String codigo){
        Projeto projeto = dao.buscar("id = " + codigo);
        projetoParaEditar = projeto;
    }

    /**
     * Persiste as mudanças no dos valores passados como parametro no projeto semdo editado.
     * @return true caso seja bem sucedido.
     */
    public synchronized static boolean atualizarProjeto(String nome, String descicao, String src) {
        src = Utils.srcToStorage(src);
        boolean b =  dao.atualizar(nome, descicao, src, projetoParaEditar.getCodigo());
        projetoParaEditar = null;
        return b;
    }

    /**
     * @return retorna objeto <link>Projeto</link> contento o projeto semdo editado atualmente.
     */
    static Projeto getProjetoParaEditar(){
        return projetoParaEditar;
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
        String s = "  ";
        switch (tipo){
            case "caso de uso":
                System.out.println(projetoAtivo);
                s = projetoAtivo.getPrefixoCU().concat(dao.buscarContador("contadorCU", projetoAtivo.getCodigo()));
                break;
            case "caso de teste":
                s = projetoAtivo.getPrefixoCT().concat(dao.buscarContador("contadorCT", projetoAtivo.getCodigo()));
                break;
            case "roteiro de teste":
                s = projetoAtivo.getPrefixoCT().concat(dao.buscarContador("contadorRT", projetoAtivo.getCodigo()));
                break;
        }
        return s;
    }

    /**
     * @return Retorna uma referencia para o projeto ativo.
     */
    public static model.Projeto getProjetoAtivo() {
        return projetoAtivo;
    }

    /**
     * @return retorna verdadeiro de o projeto ativo não for nulo
     */
    static synchronized boolean temProjetoAtivo() {
        return projetoAtivo == null;
    }



    /**
     * Deleta o projeto com o codigo informado do banco de dados. E o remove da lista de projetos.
     * @param codigo
     */
    public synchronized static void deletarProjetoDeCodigo(String codigo) {
        dao.deletar("id = " + codigo);
        listaDeProjetos.removeIf((p) -> {
            return p.getCodigo() == codigo;
        });
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
    public synchronized static String getSrcProjetoAtivo(){
        return projetoAtivo.getSrc();
    }

    public synchronized static  String getNomeProjetoAtivo(){
        return projetoAtivo.getNome();
    }
}
