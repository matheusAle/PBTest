package controller;

import model.Factorys.ProjetoFactory;
import model.Projeto;

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
    public static boolean cadastrarProjeto (
            String nome,
            String src,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String CASO_DE_TESTE,
            String descricao
            ){
        src = src.replaceAll("\\\\", "/");
        return dao.salvar(
                nome,
                src,
                CASO_DE_USO,
                ROTEIRO_DE_TESTE,
                CASO_DE_TESTE,
                descricao,
                UsuarioController.getEmailUsuarioLogado()
        );
    }

    private static LinkedList<Projeto> listarProjetos(){
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
    public static boolean ativarProjeto(String codigo) {
        try {
            projetoAtivo = (model.Projeto) dao.buscar("id = " + codigo).iterator().next();
            CasoDeTesteController.carregarArtefatos(); //TODO este invocação deve ser por thread futuramente!
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }

    /**
     * Constroi um <link>Projeto</link> com todas as imformações de um projeto.
     * @param codigo codigo do projeto que sera atualizado.
     */
    public static void setProjetoAtualizavel(String codigo){
        Projeto projeto = dao.buscar("id = " + codigo).iterator().next();
        projetoParaEditar = projeto;
    }


    /**
     * @return Retorna um objeto do tipo <link>Projeto</link> com, codigo, nome, src e descrição do projeto ativo.
     */
    public static Projeto getInformacoesDoProjetoAtivo(){
        try {
            return new Projeto(projetoAtivo.getCodigo(), projetoAtivo.getNome(), projetoAtivo.getDescricao(), projetoAtivo.getSrc());
        }catch (Exception e ){}
        return null;
    }

    /**
     * Persiste as mudanças no dos valores passados como parametro no projeto semdo editado.
     * @return true caso seja bem sucedido.
     */
    public static boolean atualizarProjeto(String nome, String descicao, String src) {
        src = src.replaceAll("\\\\", "/");
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
     */
    public static String getPrefixoDoProjetoAtual(String tipo){
        String s = null;
        switch (tipo){
            case "caso de uso":
                s = projetoAtivo.getPrefixo("caso de uso");
                dao.atualizarContador("contadorCU", projetoAtivo.getContador("caso de uso"), projetoAtivo.getCodigo());
                break;
            case "caso de teste":
                s = projetoAtivo.getPrefixo("caso de teste");
                dao.atualizarContador("contadorCT", projetoAtivo.getContador("caso de teste"), projetoAtivo.getCodigo());
                break;
            case "roteiro de teste":
                s = projetoAtivo.getPrefixo("roteiro de teste");
                dao.atualizarContador("contadorRT", projetoAtivo.getContador("roteiro de teste"), projetoAtivo.getCodigo());
                break;
        }
        return s;
    }

    static model.Projeto getProjetoAtivo() {
        return projetoAtivo;
    }

    public static boolean temProjetoAtivo() {
        return projetoAtivo == null;
    }
}
