package model;


import controller.UsuarioAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

// TODO implementar o doa do usuario!
public final class UsuarioDAO extends DAO{

    /**
     * Transforma o codigo do atributo cargo na relação usuario em uma string com o nome do cargo.
     * @param i codigo do cargo.
     * @return String com o nome do cargo. se o codigo não existir retorna null.
     */
    private static Usuario.Cargo getCargo (int i){
        switch (i){
            case 1:
                return Usuario.Cargo.GERENTE_DE_PROJETO;
            case 2:
                return Usuario.Cargo.GERENTE_DE_TESTES;
            case 3:
                return Usuario.Cargo.TESTADOR;
            default:
                return null;
        }
    }

    @Override
    public Collection listar() {
        String query  = "SELECT * FROM usuario";
        try {
            ResultSet resultSet = DAO.execultarSELECT(query);
            if (resultSet != null){
                LinkedList<UsuarioAdapter> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new UsuarioAdapter(
                            resultSet.getString("email"),
                            resultSet.getString("nome"),
                            getCargo(resultSet.getInt("cargo")),
                            null));
                }
                return lista;
            }
        }catch (SQLException e){
        }
        return null;
    }
    /**
     * Busca na relação de usuario no banco de dados, usuario que passem pelo crivo passado como parametro
     * @param restricao restrição de busca. ex: email = "m@gmail.com' AND senha = '123'
     * @return Retorna uma <link>Collection</link> com objetos <link>Usuario</link> em seu conteudo.
     */
    @Override
    public Collection<Usuario> buscar(String restricao) {
        String query  = "SELECT * FROM usuario WHERE " + restricao;
        try {
            ResultSet resultSet = DAO.execultarSELECT(query);
            HashSet<Usuario> usuariosEncontrados = new HashSet<>();
            if (resultSet != null){
                while (resultSet.next()){
                    usuariosEncontrados.add(new Usuario(
                            resultSet.getString("nome"),
                            resultSet.getString("email"),
                            getCargo(resultSet.getInt("cargo")),
                            resultSet.getString("telefone"),
                            resultSet.getString("biografia"),
                            null
                    ));
                }
            }
            return usuariosEncontrados;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //TODO impementar a atualização dos dados do usuario.
    public Collection atualizar() {
        return null;
    }
}
