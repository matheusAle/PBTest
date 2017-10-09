package model;


import controller.UsuarioMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

// TODO implementar o doa do usuario!
public final class UsuarioDOA {
    static boolean logar (String email, String senha){
        return true;
    }


    public Usuario buscarUsuario (String email){
        //TODO implementar a recuperação dos dados de um usuario no banco de dados
        return null;
    }

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

    /**
     * Retorna uma <link>Collection</link> com objetos <link>UsuarioMetaData</link> em seu conteudo.
     * @return Collection<UsuarioMetaData>. caso haja algum erro retorna null.
     */
    public static Collection<UsuarioMetaData> buscarTodosOSUsuarios (){
        String query  = "SELECT email, nome, cargo FROM usuario";
        try {
            ResultSet resultSet = DOA.execultarSELECT(query);
            if (resultSet != null){
                LinkedList<UsuarioMetaData> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new UsuarioMetaData(
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
     * Retorna um objeto do tipo <link>Usuario</linK> caso a senha e o email passado exista na tabela
     * de usuarios no banco de dados.
     * @param email
     * @param senha
     * @return objeto Usuario ou null caso o usuario com esse email e senha não exista;
     */
    public static Usuario fazerLogin(String email, String senha) {
        String query  = "SELECT * FROM usuario WHERE email = '" + email + "' AND senha = '" + senha + "'";
        try {
            ResultSet resultSet = DOA.execultarSELECT(query);
            if (resultSet != null){
                while (resultSet.next()){
                    return new Usuario(
                            resultSet.getString("nome"),
                            resultSet.getString("email"),
                            getCargo(resultSet.getInt("cargo")),
                            resultSet.getString("telefone"),
                            resultSet.getString("biografia"),
                            null
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
