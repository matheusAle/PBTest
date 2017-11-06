package controller;

import model.CasoDeTeste;
import model.Factorys.RoteiroDeTesteFactory;
import model.RoteiroDeTestes;
import model.TestesPool;

import java.util.LinkedList;

public class RoteiroDeTesteController {
    static RoteiroDeTesteFactory dao = new RoteiroDeTesteFactory();
    private static LinkedList<RoteiroDeTestes> listaDeRoteiros = new LinkedList<>();
    
    public static String salvarNovoRoteiro(String nomeText, String descricaoText, LinkedList<CasoDeTeste> casosDeTesteDoRoteiro) {
        String codigo = ProjetoController.gerarCodigo("roteiro de teste");
        LinkedList<String> codigos = new LinkedList<>();
        for (CasoDeTeste e : casosDeTesteDoRoteiro){
            codigos.add(e.getCodigo());
        }

        dao.salvar(codigo, nomeText, descricaoText, 0, ProjetoController.getCodigoProjetoAtivo(), UsuarioController.getEmailUsuarioLogado(), codigos);

        return codigo;
    }

    public static LinkedList<RoteiroDeTestes> getListaDeRoteiros() {
        return listaDeRoteiros;
    }

    public static void carregarRoteirosDoProjetoAtivo() {
        listaDeRoteiros = dao.listarRoteiros(ProjetoController.getCodigoProjetoAtivo());
    }

    /**
     * Cerrega os casos de teste dos roteiro.
     * @param roteiroDeTestes
     */
    public static void carregarCasosDeTesteDoRoteiro(RoteiroDeTestes roteiroDeTestes) {
        LinkedList<String> casosDeTesteCodigo = dao.listarCasosDeTesteDoRoteiro(roteiroDeTestes.getCodigo(), roteiroDeTestes.getProjetoID());
        roteiroDeTestes.setCasosDeTeste(TestesPool.buscarCasosDeTeste(casosDeTesteCodigo));
    }

    public static void salvarAlteracao(RoteiroDeTestes roteiroDeTestes, String nome, String descricaoText, LinkedList<CasoDeTeste> casosDeTesteDoRoteiro) {
        LinkedList<String> codigos = new LinkedList<>();
        for (CasoDeTeste e : casosDeTesteDoRoteiro){
            codigos.add(e.getCodigo());
        }
        dao.atualizar(roteiroDeTestes.getCodigo(), roteiroDeTestes.getProjetoID(), nome, descricaoText, codigos);
        for (RoteiroDeTestes roteiro : listaDeRoteiros){
            if (roteiro.getCodigo().equals(roteiroDeTestes.getCodigo())){
                roteiro.setNome(nome);
                roteiro.setCasosDeTeste(casosDeTesteDoRoteiro);
                roteiroDeTestes.setDescricao(descricaoText);
                break;
            }
        }
    }

    /**
     * Deleta o roteiro de teste do banco de dados e da lista de roteiros de testes.
     * @param roteiroDeTestes roteiro a ser deletado.
     */
    public static void deletarRoteiriDeTestes(RoteiroDeTestes roteiroDeTestes) {
        dao.deletar(roteiroDeTestes.getCodigo(), roteiroDeTestes.getProjetoID());
        listaDeRoteiros.removeIf((r) -> r.getCodigo().equals(roteiroDeTestes.getCodigo()));
    }
}
