package controller;

import controller.exceptions.CasoDeTesteException;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;
import model.TestesPool;
import model.Factorys.CasoDeTesteFactory;

import java.util.*;

public final class CasoDeTesteController {

    private static CasoDeTesteFactory dao = new CasoDeTesteFactory();
    private static HashMap<String, LinkedList<ArtefatoDeTeste>> mapaDeArtefatos = new HashMap<>();
    private static ArtefatoDeTeste artefatoDeTesteDendoEditado;

    /**
     * Carrega os artefatos de teste do projeto ativo em uma TestesPool
     * @throws CasoDeTesteException lançada quando o diretorio raiz não existir.
     */
    public synchronized static void carregarArtefatos() throws CasoDeTesteException {
        TestesPool.limparPool();
        TestesPool.carregarPool(ProjetoController.getProjetoAtivo());
        mapaDeArtefatos = new HashMap<>();
        gerarMapaDeArtefatos();
    }

    /**
     * mapeia os artefatos do projeto de acordo com o pacote o qual pertence.
     */
    private synchronized static void gerarMapaDeArtefatos() {
        TestesPool.getListaDeArtefatos().forEach((e) -> {
            if (mapaDeArtefatos.containsKey(e.getPakage())){
                mapaDeArtefatos.get(e.getPakage()).add(e);
            }else {
                LinkedList<ArtefatoDeTeste> l = new LinkedList<>();
                l.add(e);
                mapaDeArtefatos.put(e.getPakage(), l);
            }
        });
    }

    /**
     * Retorna os artefatos de teste mapeados em seus pacotes.
     * @return
     */
    public synchronized static HashMap<String, LinkedList<ArtefatoDeTeste>> getMapaDeArtefatos() {
        return mapaDeArtefatos;
    }

    /**
     * Persiste um novo caso de teste para o artefato passado como parametro.
     * @param artefato objeto do artefato.
     * @param nome nome do novo caso de teste
     * @param descricao descrição do caso de teste
     * @param srcCasoDeTeste src do arquivo que contem o codigo junit para reaizar os testes
     * @param codCU codigo do caso de uso vinculado ao caso de teste.
     * @return retorna o codigo do caso de uso.
     */
    public synchronized static String salvarCasoDeTeste(ArtefatoDeTeste artefato, String nome, String descricao, String srcCasoDeTeste, String codCU){
        String codigo = ProjetoController.gerarCodigo("caso de teste");
        CasoDeTeste casoDeTeste = new CasoDeTeste(
                codigo
                , nome
                , Utils.srcToStorage(Utils.resumeSrcCasoDeTeste(srcCasoDeTeste))
                , artefato.getCaminhoRelativoAoProjeto()
                , descricao
                , artefato.getProjetoId()
                , codCU
                , null
                , UsuarioController.getEmailUsuarioLogado()
        );
        boolean retorno = dao.salvar(
                codigo
                , nome
                , Utils.srcToStorage(Utils.resumeSrcCasoDeTeste(srcCasoDeTeste))
                , artefato.getCaminhoRelativoAoProjeto()
                , descricao
                , artefato.getProjetoId()
                , codCU
                , UsuarioController.getEmailUsuarioLogado()
        );
        artefato.addCasoDeTeste(casoDeTeste);
        return codigo;
    }

    /**
     * Seta o artefato de teste que está sendo editado.
     * @param artefato
     */
    public static void novoCasoDeTestePara(ArtefatoDeTeste artefato) {
        artefatoDeTesteDendoEditado = artefato;
    }

    /**
     * Salva as auteraçoes tanto na pool de testes quanto no banco de daso
     * @param casoDeTeste caso de teste
     * @param nome novo nome
     * @param descricao nova descrição
     * @param srcCasoDeTeste novo src
     * @param codigoCasoDeUsoSelecionado novo codigo de caso de uso vinculado
     */
    public static void salvarMudancasNoCasoDeTeste(CasoDeTeste casoDeTeste, String nome, String descricao, String srcCasoDeTeste, String codigoCasoDeUsoSelecionado) {
        dao.atualizar(casoDeTeste.getCodigo(), casoDeTeste.getArtefatoDeTeste(), casoDeTeste.getProjetoId(), nome, srcCasoDeTeste, descricao, codigoCasoDeUsoSelecionado);
        TestesPool.alterarCasoDeTeste(casoDeTeste, nome, descricao, srcCasoDeTeste, codigoCasoDeUsoSelecionado);
    }

    /**
     * Deleta o casos de teste tando da pool de testes quando do banco de dados
     * @param casoDeTeste
     */
    public static void deletarCasoDeTeste(CasoDeTeste casoDeTeste) {
        dao.deletar(casoDeTeste.getCodigo(), casoDeTeste.getArtefatoDeTeste().getCaminhoRelativoAoProjeto(), casoDeTeste.getProjetoId());
        TestesPool.deletarCasoDeTesteDoArtefato(casoDeTeste, casoDeTeste.getArtefatoDeTeste());
    }

    public static void salvarResultado(CasoDeTeste e, String resultado) {
        TestesPool.setResultadoDoTeste(e, resultado);
        dao.setResultado(e.getCodigo(), e.getProjetoId(), e.getArtefatoDeTeste().getCaminhoRelativoAoProjeto(), resultado);
    }
}
