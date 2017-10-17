package controller;

import controller.adapters.ProjetoAdapter;
import model.ArtefatoDeTeste;
import model.Projeto;
import model.daos.ProjetoDAO;

import java.util.LinkedList;

public class ProjetoController {
    private static ProjetoDAO dao = new ProjetoDAO();
    private static Projeto projetoAtivo;
    private static LinkedList<ProjetoAdapter> listaDeProjetos  = listarProjetos();
    private static ProjetoAdapter projetoParaEditar;

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

    private static LinkedList<ProjetoAdapter> listarProjetos(){
        return (LinkedList<ProjetoAdapter>) dao.listar();
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
            projetoAtivo = (Projeto) dao.buscar("id = " + codigo).iterator().next();
            CasoDeTesteController.carregarArtefatos(); //TODO este invocação deve ser por thread futuramente!
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }

    /**
     * Constroi um <link>ProjetoAdapter</link> com todas as imformações de um projeto.
     * @param codigo codigo do projeto que sera atualizado.
     */
    public static void setProjetoAtualizavel(String codigo){
        Projeto projeto = (Projeto) dao.buscar("id = " + codigo).iterator().next();
        ProjetoAdapter adapter= new ProjetoAdapter(projeto.getCodigo(), projeto.getNome(), projeto.getDescricao(), projeto.getSrc());
        adapter.setPrefixoCT(projeto.getPrefixoCT());
        adapter.setPrefixoRT(projeto.getPrefixoRT());
        adapter.setPrefixoCU(projeto.getPrefixoCU());
        projetoParaEditar = adapter;
    }


    /**
     * @return Retorna um objeto do tipo <link>ProjetoAdapter</link> com, codigo, nome, src e descrição do projeto ativo.
     */
    public static ProjetoAdapter getInformacoesDoProjetoAtivo(){
        try {
            return new ProjetoAdapter(projetoAtivo.getCodigo(), projetoAtivo.getNome(), projetoAtivo.getDescricao(), projetoAtivo.getSrc());
        }catch (Exception e ){}
        return null;
    }

    /**
     * Persiste as mudanças no projeto passado por paramentro.
     * @param projetoSendoEditado
     * @return true caso seja bem sucedido.
     */
    public static boolean atualizarProjeto(ProjetoAdapter projetoSendoEditado) {
        projetoSendoEditado.setSrc(projetoSendoEditado.getSrc().replaceAll("\\\\", "/"));
        boolean b =  dao.atualizar(projetoSendoEditado);
        return b;
    }

    /**
     * @return retorna objeto <link>ProjetoAdapter</link> contento o projeto semdo editado atualmente.
     */
    static ProjetoAdapter getProjetoParaEditar(){
        return projetoParaEditar;
    }

    /**
     * @return Retorna a uma <link>LinkedList</link> de <link>ProjetoAdapter</link> com as informações persistidas no banco de dados.
     */
    public static LinkedList<ProjetoAdapter> getListaDeProjetos() {
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

    static Projeto getProjetoAtivo() {
        return projetoAtivo;
    }
}
