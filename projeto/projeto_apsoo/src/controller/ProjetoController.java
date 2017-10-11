package controller;

import model.Projeto;
import model.ProjetoDAO;

import java.util.Collection;
import java.util.LinkedList;

public class ProjetoController {
    private static ProjetoDAO dao = new ProjetoDAO();
    private Projeto projetoAtivo;
    public static LinkedList<ProjetoAdapter> listaDeprojetos  = listarProjetos();


    /**
     * Persiste as informações do projeto passadas como paramentro.
     * @param nome
     * @param src
     * @param CASO_DE_TESTE prefixo dos casos de teste
     * @param CASO_DE_USO  prefixo dos casos de uso
     * @param ROTEIRO_DE_TESTE prefixo dos casos roteiros de teste
     * @param descricao descrição do projeto
     * @return true se bem sicedido
     */
    public static boolean cadastrarProjeto (
            String nome,
            String src,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String CASO_DE_TESTE,
            String descricao
            ){
        return ProjetoDAO.salvar(
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
        listaDeprojetos = listarProjetos();
    }
}
