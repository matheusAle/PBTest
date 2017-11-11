package model.Factorys;

import controller.Utils;
import controller.exceptions.CasoDeTesteException;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Classe resposavel pela manipulação dados persistentes dos casos de teste
 */
public class CasoDeTesteFactory extends AbstractFactory {

    /**
     * busca no banco de dados todos os casos de teste vinculados ao nome do artefato e ao projeto.
     * @param pjID codigo do projeto ativo.
     * @param classTeste nome da classe do artefato de teste.
     * @return Collerctio\<CasoDeTeste\> com os casos de teste do artefato.
     */
    public synchronized LinkedList<CasoDeTeste> listar(String pjID, String classTeste) {
        String query = String.format("SELECT * FROM caso_de_teste WHERE projetoID = %s AND nomeClasseArtefato = '%s' ", pjID, classTeste.replaceAll("\\\\","/"));
        ResultSet resultSet = super.execultarBusca(query);
        if (resultSet != null){
            try {
                LinkedList<CasoDeTeste> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new CasoDeTeste(
                            resultSet.getString("codigo"),
                            resultSet.getString("nome"),
                            resultSet.getString("nomeClasseTeste"),
                            resultSet.getString("nomeClasseArtefato"),
                            resultSet.getString("descricao"),
                            resultSet.getString("projetoID"),
                            resultSet.getString("casoDeUsoCodigo"),
                            resultSet.getString("resultado"),
                            resultSet.getString("emailUsuario")
                    ));
                }
                return lista;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * salva o  caso de teste no banco e dados.
     * @param codigo codigo do caso de teste
     * @param nome nome do caso de teste
     * @param srcClasseDeTeste src da calsse que realiza os teste que esta sendo testado
     * @param srcDoArtefato src do artefato que esta semdo testado.
     * @param descricao descrição deste caso de teste
     * @param pjID projeto do caso dde teste e do artefato
     * @param cuCOD codigo do caso de uso
     * @param email email do usuario que cadastrou o caso de teste
     * @return true se a operação dor vem sucediida.
     */
    public synchronized boolean salvar(String codigo, String nome, String srcClasseDeTeste, String srcDoArtefato, String descricao, String pjID, String cuCOD, String email){
        String dml = String.format("INSERT INTO caso_de_teste VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s', '%s', null)", codigo, nome, Utils.srcToStorage(srcClasseDeTeste), Utils.srcToStorage(srcDoArtefato), descricao, pjID, cuCOD, email);
        execultarAtualizacao(dml);
        return true;

    }

    /**
     * Atualiza os valores do caso de teste com os novos passados por paramentro
     * @param codigo codogo de caso de teste
     * @param artefatoDeTeste artefato testado
     * @param projetoId id do projeto
     * @param nome novo nome para o caso de teste
     * @param srcCasoDeTeste novo src para o caso de teste
     * @param descricao nova descição do caso de teste
     * @param codigoCasoDeUsoSelecionado novo codigo de caso de uso
     * @return true se a operação dor vem sucediida.
     */
    public boolean atualizar(String codigo, ArtefatoDeTeste artefatoDeTeste, String projetoId, String nome, String srcCasoDeTeste, String descricao, String codigoCasoDeUsoSelecionado) {
        String dml = String.format("UPDATE caso_de_teste SET nome = '%s', nomeClasseTeste= '%s', descricao = '%s', casoDeUsoCodigo = '%s' WHERE codigo = '%s' AND nomeClasseArtefato = '%s' AND projetoID = %s", nome, Utils.srcToStorage(srcCasoDeTeste), descricao, codigoCasoDeUsoSelecionado,  codigo, Utils.srcToStorage(artefatoDeTeste.getCaminhoRelativoAoProjeto()), projetoId);
        execultarAtualizacao(dml);
        return true;
    }

    /**
     * Deleta a tupla que tem as colunas com os valores correspondentes
     * @param codigo codigo do caso de teste
     * @param nomeArquivo nome do arquivo sendo testado
     * @param projetoId id do projeto
     */
    public void deletar(String codigo, String nomeArquivo, String projetoId) {
        String dml = String.format("DELETE FROM caso_de_teste WHERE codigo = '%s' AND nomeClasseArtefato = '%s' AND projetoID = %s", codigo, Utils.srcToStorage(nomeArquivo), projetoId);
        execultarAtualizacao(dml);
    }

    /**
     * Busca o caso de teste com o codigo solicitado
     * @param codigoCasoDeTeste codigo do caso de teste
     * @param codigoProjeto codigo do projeto
     * @return retorna um Caso de teste
     *@throws CasoDeTesteException caso de algum erro.
     **/
    public CasoDeTeste buscarPorCodigo(String codigoCasoDeTeste, String codigoProjeto){
        String query = String.format("SELECT * FROM caso_de_teste WHERE projetoID = %s AND codigo = '%s' ", codigoProjeto, codigoCasoDeTeste);
        ResultSet resultSet = execultarBusca(query);
        if (resultSet != null){
            try {
                while (resultSet.next()){
                    return new CasoDeTeste(
                            resultSet.getString("codigo"),
                            resultSet.getString("nome"),
                            Utils.srcToObject(resultSet.getString("nomeClasseTeste")),
                            Utils.srcToObject(resultSet.getString("nomeClasseArtefato")),
                            resultSet.getString("descricao"),
                            resultSet.getString("projetoID"),
                            resultSet.getString("casoDeUsoCodigo"),
                            resultSet.getString("resultado"),
                            resultSet.getString("emailUsuario")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CasoDeTesteException("Não foi possivel carregar o caso de teste de codigo " + codigoCasoDeTeste + "do projeto " + codigoProjeto );
            }
        }
        return null;
    }

    public boolean setResultado(String codigoCasoDeTeste, String codigoProjeto, String srcArtefato, String resultado){
        String dml = String.format("UPDATE caso_de_teste SET resultado = '%s' WHERE codigo = '%s' AND nomeClasseArtefato = '%s' AND projetoID = %s ",resultado, codigoCasoDeTeste, Utils.srcToStorage(srcArtefato), codigoProjeto);
        execultarAtualizacao(dml);
        return true;

    }
}
