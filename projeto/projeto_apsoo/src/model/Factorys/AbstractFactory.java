package model.Factorys;

import model.ArtefatoDeTeste;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Collection;


/**
 * classe responsavel pela conexão com o banco de dados da aplicação.
 */
public abstract class AbstractFactory {

    private static String url = "jdbc:mysql://localhost/pbtest";
    private static String usuario = "root";
    private static String senha = "";

    static {
        try {
            DriverManager.setLogWriter(new PrintWriter(new File("dblog.log")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManager.setLoginTimeout(1);
    }

    /**
     * Execulta a query passada por paramentro
     * @param query query de busca no banco de dados
     * @return retorna um ReultSet com o resultado da busca for bem sucedida. Caso java uma falha retorna null
     */
    protected ResultSet execultarBusca(String query){
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement pesquisa = conexao.prepareStatement(query);
            ResultSet r = pesquisa.executeQuery();
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Tenta execultar a query passa por paramentro.
     * @param dml query do tipo que DML
     * @throws SQLException lançada caso haja quanquer erro na execulção da dml
     */
    protected void execultarAtualizacao(String dml) throws SQLException {
        Connection conexao = DriverManager.getConnection(url, usuario, senha);
        PreparedStatement p = conexao.prepareStatement(dml);
        p.executeUpdate();
    }


}
