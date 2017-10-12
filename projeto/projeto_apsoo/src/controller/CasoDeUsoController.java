package controller;

import controller.adapters.CasoDeUsoAdapter;
import controller.exceptions.ProjetoException;
import model.CasoDeUso;
import model.daos.CasosDeUsoDAO;

import java.util.Collection;
import java.util.LinkedList;

public final class CasoDeUsoController {
    private static CasosDeUsoDAO dao = new CasosDeUsoDAO();

    private static LinkedList<CasoDeUsoAdapter> listaDeCasosDeUso;

    /**
     * Busca no banco de dados todos casos de uso vinculados ao projeto ativo.
     * @return retorna um <link>LinkedList</link> de <link>CAsoDeUsoAdapter</link>
     * @throws ProjetoException Lançada quando não existir um projeto ativo
     */
    public static LinkedList<CasoDeUsoAdapter> carregarLista() throws ProjetoException {
        LinkedList<CasoDeUsoAdapter> l = (LinkedList<CasoDeUsoAdapter>) dao.listar();
        return l != null ? l : new LinkedList<CasoDeUsoAdapter>();
    }

    /**
     * @return retorna um <link>Collection</link> de <link>CAsoDeUsoAdapter</link>
     * @throws ProjetoException Lançada quando não existir um projeto ativo
     */
    public static Collection<CasoDeUsoAdapter> getListaDeCasosDeUso () throws ProjetoException {
        if (listaDeCasosDeUso == null)
            listaDeCasosDeUso = carregarLista();
        return listaDeCasosDeUso;
    }


    /**
     * Tenta recarregar a lista de casos de uso vinculados ao projeto ativo.
     * Alista não sera recarregado se não existir um projeto ativo.
     */
    public static void recarregarLista(){
        try {
            listaDeCasosDeUso = carregarLista();
        } catch (ProjetoException e) {
            e.printStackTrace();
        }
    }

    public static boolean salvarCasoDeUso(CasoDeUsoAdapter c) {
        CasoDeUso  cu = new CasoDeUso(
                c.getCodigo(),
                c.getNome(),
                c.getObjetivo(),
                c.getAtores(),
                c.getDescricao(),
                UsuarioController.getEmailUsuarioLogado(),
                ProjetoController.getInformacoesDoProjetoAtivo().getCodigo()
        );
        boolean r = dao.salvar(cu);
        //TODO impementar a atualização dos contadores do banco na tabela projetos
        return r;

    }
}
