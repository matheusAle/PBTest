package controller;

import controller.exceptions.ProjetoException;
import model.CasoDeUso;
import model.Factorys.CasosDeUsoFactory;

import java.util.Collection;
import java.util.LinkedList;

public final class CasoDeUsoController {
    private static CasosDeUsoFactory dao = new CasosDeUsoFactory();

    private static LinkedList<CasoDeUso> listaDeCasosDeUso;

    /**
     * Busca no banco de dados todos casos de uso vinculados ao projeto ativo.
     * @return retorna um <link>LinkedList</link> de <link>CAsoDeUsoAdapter</link>
     * @throws ProjetoException Lançada quando não existir um projeto ativo
     */
    public static LinkedList<CasoDeUso> carregarLista() throws ProjetoException {
        LinkedList<CasoDeUso> l = (LinkedList<CasoDeUso>) dao.listar();
        return l != null ? l : new LinkedList<CasoDeUso>();
    }

    /**
     * @return retorna um <link>Collection</link> de <link>CAsoDeUsoAdapter</link>
     * @throws ProjetoException Lançada quando não existir um projeto ativo
     */
    public static Collection<CasoDeUso> getListaDeCasosDeUso () throws ProjetoException {
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

    public static boolean salvarCasoDeUso(String codigo, String nome, String objetivo, String atores, String descricao) {

        boolean r = dao.salvar(codigo, nome, objetivo, atores, descricao);
        //TODO impementar a atualização dos contadores do banco na tabela projetos
        return r;

    }

}
