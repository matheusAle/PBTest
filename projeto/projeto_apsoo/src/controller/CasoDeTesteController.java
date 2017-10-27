package controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil;
import controller.exceptions.CasoDeTesteException;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;
import model.CasoDeUso;
import model.TestesPool;
import model.Factorys.CasoDeTesteFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
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
     * Carrega os artefatos de teste do banco de dados para a pool de testes.
     * @param pacote pacote do artefato de teste
     * @param prjID ID do projeto atual
     * @param nomeArquivo Nome do arquivo do artefato de teste.
     * @return lista encadeada de Casos De Teste
     */
    public synchronized static LinkedList<CasoDeTeste> carregarCasosDeTesteDoArtefato(ArtefatoDeTeste artefato){
        LinkedList<ArtefatoDeTeste> lista = mapaDeArtefatos.get(artefato.getPakage());
        LinkedList<model.CasoDeTeste> casosDeTeste = null;
        for(ArtefatoDeTeste artefato1 : lista){
            if (artefato1.getNomeArquivo().equals(artefato.getNomeArquivo())){
                casosDeTeste =  (LinkedList<model.CasoDeTeste>) dao.listar(artefato.getProjetoId(), artefato.getCaminhoRelativoAoProjeto());
                TestesPool.setCasosDeTesteDoArtefato(casosDeTeste, artefato1.getNomeArquivo());
            }
        }
        LinkedList<CasoDeTeste> listaAdapters = new LinkedList<CasoDeTeste>();
        for (model.CasoDeTeste casoDeTeste : casosDeTeste){
            listaAdapters.add(casoDeTeste);
        }
        return listaAdapters;
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
        String codigo = ProjetoController.gerarPrefixo("caso de teste");
        CasoDeTeste casoDeTeste = new CasoDeTeste(
                codigo
                , nome
                , srcCasoDeTeste.replace(ProjetoController.getInformacoesDoProjetoAtivo().getSrc().concat("\\"), "")
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
                , srcCasoDeTeste.replace(ProjetoController.getInformacoesDoProjetoAtivo().getSrc().concat("\\"), "")
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

    public static void salvarMudancasNoCasoDeTeste(CasoDeTeste casoDeTeste, String nome, String descricao, String srcCasoDeTeste, String codigoCasoDeUsoSelecionado) {
        TestesPool.alterarCasoDeTeste(casoDeTeste, nome, descricao, srcCasoDeTeste, codigoCasoDeUsoSelecionado);
        dao.atualizar(casoDeTeste.getCodigo(), casoDeTeste.getArtefatoDeTeste(), casoDeTeste.getProjetoId(), nome, srcCasoDeTeste, descricao, codigoCasoDeUsoSelecionado);
    }
}
