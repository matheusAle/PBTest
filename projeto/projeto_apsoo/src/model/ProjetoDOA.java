package model;

import controller.ProjetoMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Classe para reealizar operaçoes realacionada a projetos no banco de dados
 */
public class ProjetoDOA {

    /**
     * busca no banco de dados todos os dados de todos os projetos cadastrado.
     * @return retorna uma coleção de <Link>ProjetoMetaData</Link>
     */
    public static Collection<ProjetoMetaData> buscarTodosOsProjetos (){
        String query = "SELECT id, srcRaiz, descricao FROM projeto";
        ResultSet resultSet = DOA.execultar(query);
        if (resultSet != null){
            try {
                LinkedList<ProjetoMetaData> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new ProjetoMetaData(
                            resultSet.getString("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("descricao"),
                            resultSet.getString("srcRaiz")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Projeto pegarProjetoDeCodigo(String codigo){
        try {
            String query = "SELECT * FROM projeto WHERE id = " + codigo;
            ResultSet resultSet = DOA.execultar(query);
            return new Projeto(
                    resultSet.getString("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao"),
                    resultSet.getString("srcRaiz"),
                    resultSet.getString("prefixoCU"),
                    resultSet.getString("prefixoCT"),
                    resultSet.getString("prefixoRT"),
                    resultSet.getInt("contadorCU"),
                    resultSet.getInt("contadorCT"),
                    resultSet.getInt("contadorRT")
            );
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
