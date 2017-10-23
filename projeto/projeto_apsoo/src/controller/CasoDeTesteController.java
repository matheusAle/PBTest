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


    /**
     * Gera o nome do arquivo apartir dos paremetros informados.
     * @param codigo
     * @param nomeDoArtefato
     * @return retorna ila string com o nome do arquivo. (já com o .java)
     */
    private synchronized static String gerarNomeDaClasseJava(String codigo, String nomeDoArtefato){
        codigo = codigo.replaceAll("\\W", "");

        return nomeDoArtefato.concat("_").concat(codigo).concat("_Test");
    }


    public synchronized static boolean salvarCasoDeTeste(String nome, String descricao, String codigoFonteJunit, String codCU){
        //Pega o nome da classe do artefato sendo testado
        String nomeDoArtefatoSendoTestado = artefatoDeTesteDendoEditado.getNomeArquivo();
        nomeDoArtefatoSendoTestado = nomeDoArtefatoSendoTestado.replaceAll("\\.java","");

        System.out.println(ProjetoController.getProjetoAtivo());
        System.out.println(artefatoDeTesteDendoEditado);

        //gera um codigo para este classe de teste
        String codigo = ProjetoController.gerarPrefixo("caso de teste");

        String nomeDaClasseDeTeste = gerarNomeDaClasseJava(codigo, nomeDoArtefatoSendoTestado);

        // da nome a classe de teste
        codigoFonteJunit= codigoFonteJunit.replaceAll("\\{\\$className}", nomeDaClasseDeTeste);


        FileWriter fileWriter = null;
        try {
            // cria um arquivo .java para a classe de teste, dentro do diretorio do projeto.
            fileWriter = new FileWriter(criarArquivo(ProjetoController.getSrcCasosDeTeste().concat(nomeDaClasseDeTeste).concat(".java")));
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            CasoDeTeste casoDeTeste = new CasoDeTeste(
                    codigo
                    , nome
                    , nomeDaClasseDeTeste
                    , nomeDoArtefatoSendoTestado
                    , descricao
                    , artefatoDeTesteDendoEditado.getProjetoId()
                    , artefatoDeTesteDendoEditado.getProjetoId()
                    , codCU
                    , UsuarioController.getEmailUsuarioLogado()
            );
            try {
                fileWriter.write(codigoFonteJunit);
                //Persiste as informações
                boolean retorno = dao.salvar(
                        codigo
                        , nome
                        , nomeDaClasseDeTeste
                        , nomeDoArtefatoSendoTestado
                        , descricao
                        , artefatoDeTesteDendoEditado.getProjetoId()
                        , codCU
                        , UsuarioController.getEmailUsuarioLogado()
                );

                return retorno;
            } catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }  catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private synchronized static File criarArquivo(String novoDoArquivo) throws IOException {
        File file = new File(novoDoArquivo);
        file.setWritable(true);
        file.setReadable(true);
        file.createNewFile();
        return file;
    }

    public static ArtefatoDeTeste getArtefatoDeTesteSendoEditado() {
        return artefatoDeTesteDendoEditado;
    }

    /**
     * Seta o artefato de teste que está sendo editado.
     * @param artefato
     */
    public static void novoCasoDeTestePara(ArtefatoDeTeste artefato) {
        artefatoDeTesteDendoEditado = artefato;
    }
}
