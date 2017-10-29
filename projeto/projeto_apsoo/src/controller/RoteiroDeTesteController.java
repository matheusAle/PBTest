package controller;

import model.CasoDeTeste;
import model.Factorys.RoteiroDeTesteFactory;
import model.Factorys.RoteiroDeTestes;

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
}
