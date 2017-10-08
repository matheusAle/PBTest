package model;

import java.sql.*;

/**
 * classe responsavel pela conexão com o banco de dados da aplicação.
 */
public class DOA {

    private static String url = "jdbc:mysql://localhost/pbtest";
    private static String usuario = "root";
    private static String senha = "";

    /**
     * Execulta a query passada por paramentro
     * @param query query de busca no banco de dados
     * @return retorna um ReultSet com o resultado da busca for bem sucedida. Caso java uma falha retorna null
     */
    protected synchronized static ResultSet execultar (String query){
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement pesquisa = conexao.prepareStatement(query);
            return pesquisa.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
