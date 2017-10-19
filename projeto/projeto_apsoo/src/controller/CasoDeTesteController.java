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
     * Carreega os artefatos de teste do banco de dados para a pool de testes.
     * @param pacote pacote do artefato de teste
     * @param prjID ID do projeto atual
     * @param nomeArquivo Nome do arquivo do artefato de teste.
     * @return lista encadeada de Casos De Teste
     */
    public synchronized static LinkedList<CasoDeTeste> carregarCasosDeTesteDoArtefato(String pacote, String prjID, String nomeArquivo){
        LinkedList<ArtefatoDeTeste> lista = mapaDeArtefatos.get(pacote);
        LinkedList<model.CasoDeTeste> casosDeTeste = null;
        for(ArtefatoDeTeste artefato : lista){
            if (artefato.getNomeArquivo().equals(nomeArquivo)){
                casosDeTeste =  (LinkedList<model.CasoDeTeste>) dao.listar(prjID, nomeArquivo);
                TestesPool.setCasosDeTesteDoArtefato(casosDeTeste, nomeArquivo);
            }
        }
        LinkedList<CasoDeTeste> listaAdapters = new LinkedList<CasoDeTeste>();
        for (model.CasoDeTeste casoDeTeste : casosDeTeste){
            listaAdapters.add(casoDeTeste);
        }
        return listaAdapters;
    }


}
