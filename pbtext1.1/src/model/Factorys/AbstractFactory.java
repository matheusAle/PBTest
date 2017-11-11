package model.Factorys;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.mainApp;


/**
 * classe responsavel pela conexão com o banco de dados da aplicação.
 */
public abstract class AbstractFactory {

    private static String url = "jdbc:mysql://localhost/pbtest";
    private static String usuario = "root";
    private static String senha = "";
    private static Connection connection;
    static {
        try {
            DriverManager.setLogWriter(new PrintWriter(new File("dblog.log")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManager.setLoginTimeout(1);
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            mainApp.relatarErroInterno(ex.getMessage());
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    AbstractFactory.connection.close();
                } catch (SQLException ex) {
                }
            }
        });
    }

    /**
     * Execulta a query passada por paramentro
     * @param query query de busca no banco de dados
     * @return retorna um ReultSet com o resultado da busca for bem sucedida. Caso java uma falha retorna null
     */
    protected ResultSet execultarBusca(String query){
        try {
            synchronized(connection){
                PreparedStatement rx = connection.prepareStatement(query);
                ResultSet r = rx.executeQuery();
                return r;
            }
        } catch (SQLException e) {
            mainApp.relatarErroInterno(e.getMessage());
            return null;
        }
    }

    /**
     * Tenta execultar a query passa por paramentro.
     * @param dml query do tipo que DML
     */
    protected void execultarAtualizacao(String dml){
        synchronized(connection){;
            PreparedStatement p;
            try {
                p = connection.prepareStatement(dml);
                p.executeUpdate();
            } catch (SQLException ex) {
                mainApp.relatarErroInterno(ex.getMessage());
            }
        }
        
    }
    
    

}
