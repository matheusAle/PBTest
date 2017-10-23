package model.Factorys;

import model.CasoDeTeste;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
    public synchronized Collection<CasoDeTeste> listar(String pjID, String classTeste) {
        String query = String.format("SELECT * FROM caso_de_teste WHERE projetoID = '%s' AND nomeClasseTeste = '%s' ", pjID, classTeste );
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
                            resultSet.getString("projetiID"),
                            resultSet.getString("casoDeUsoCodigo"),
                            resultSet.getString("resultado"),
                            resultSet.getString("emailUsuario")
                    ));
                }
                return lista;
            } catch (SQLException e) {
                System.err.println("ERRO em CasoDeTesteFactory::listar -> ");
                e.printStackTrace();
            }
        }
        return null;
    }

    public Collection listar() {
        return null;
    }

    public Collection buscar(String restricao) {
        return null;
    }

    public synchronized boolean salvar(String codigo, String nome, String nomeDaClasseDeTeste, String nomeDoArtefato, String descricao, String pjID, String cuCOD, String email){
        String dml = String.format("INSERT INTO caso_de_teste VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s', '%s', null)", codigo, nome, nomeDaClasseDeTeste, nomeDoArtefato, descricao, pjID, cuCOD, email);
        System.out.println(dml);
        try {
            AbstractFactory.execultarAtualizacao(dml);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
