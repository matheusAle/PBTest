package model;

import controller.ProjetoMetaData;
import controller.UsuarioController;

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
        ResultSet resultSet = DOA.execultarSELECT(query);
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
            ResultSet resultSet = DOA.execultarSELECT(query);
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

    public static boolean persistirProjeto(
            String nome,
            String src,
            String CASO_DE_TESTE,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String descricao,
            String email
    ){
        String query = "INSERT INTO projeto " +
                        "(nome, srcRaiz, prefixoCT, prefixoCU, prefixoRT, descricao, usuario_dono)" +
                        "VALUES (" +
                        "'" + nome + "', " +
                        "'" + src.replaceAll("\\\\", "\\\\\\\\") + "', " +
                        "'" + CASO_DE_TESTE + "', " +
                        "'" + CASO_DE_USO + "', " +
                        "'" + ROTEIRO_DE_TESTE + "', " +
                        "'" + descricao + "', " +
                        "'" + email + "')";
        int status = DOA.execultarINSERT(query);
        return status == 1;
    }

}
