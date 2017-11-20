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
        LinkedList<CasoDeUso> l = (LinkedList<CasoDeUso>) dao.listar(ProjetoController.getCodigoProjetoAtivo());
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

    /**
     * Salva o novo caso de uso no banco de dados.
     * @param nome nome do caso de uso
     * @param objetivo breve descrição do caso de uso
     * @param atores atores deste caso de uso
     * @param descricao descrição do caso de uso
     * @return Retorna uma string com o codigo do novo caso de uso
     */
    public static String salvarCasoDeUso(String nome, String objetivo, String atores, String descricao) {
        String codigo = ProjetoController.gerarCodigo("caso de uso");
        dao.salvar(codigo, nome, objetivo, atores, descricao, ProjetoController.getProjetoAtivo().getCodigo(), UsuarioController.getEmailUsuarioLogado());
        recarregarLista();
        return codigo;
    }

    public synchronized static void deletarCasoDeUsoDeCodigo(String text) {
        dao.deletar("codigo = '" + text + "'");
        listaDeCasosDeUso.removeIf((p) -> p.getCodigo() == text);
    }

    public static void salvarMudancas(CasoDeUso c, String nome, String objetivo, String atore, String descicao) {
        dao.atualizar(c.getCodigo(), nome, objetivo, atore, descicao, c.getProjetoID());
        c.setNome(nome);
        c.setDescricao(descicao);
        c.setAtores(atore);
        c.setObjetovo(objetivo);
    }
}
