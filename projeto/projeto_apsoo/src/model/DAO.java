package model;

import java.sql.*;
import java.util.Collection;

/**
 * classe responsavel pela conexão com o banco de dados da aplicação.
 */
public abstract class DAO {

    private static String url = "jdbc:mysql://localhost/pbtest";
    private static String usuario = "root";
    private static String senha = "";


    /**
     * Execulta a query passada por paramentro
     * @param query query de busca no banco de dados
     * @return retorna um ReultSet com o resultado da busca for bem sucedida. Caso java uma falha retorna null
     */
    protected synchronized static ResultSet execultarSELECT (String query){
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement pesquisa = conexao.prepareStatement(query);
            return pesquisa.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected synchronized static int execultarINSERT (String query){
        try {
            Connection cone = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement inserir = cone.prepareStatement(query);
            inserir.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }


    /**
     * Busca no banco e retorna um coleção de items da relação.
     * @return
     */
    public abstract Collection listar();

    /**
     * Buscar por um elemento ou mais da relação
     * @return
     */
    public abstract Collection buscar(String restricao);


}
