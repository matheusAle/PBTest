package controller;

import controller.exceptions.UsuarioException;
import model.Factorys.UsuarioFactory;
import model.Usuario;

import javax.swing.*;
import java.util.Collection;
import java.util.HashSet;

public class UsuarioController {

    private static UsuarioFactory dao = new UsuarioFactory();
    private static Collection<Usuario> listaDeUsuario = buscarTodosOSUsuarios();
    private static model.Usuario usuarioLogado;


    private static Collection<Usuario> buscarTodosOSUsuarios() {
        return dao.listar();
    }

    /**
     *
     * @return Retorna uma <link>Collection</link> de <link>Usuario</link> dos usuarios cadastrados
     * no banco de dados.
     */
    public static Collection<Usuario> getListaDeUsuarios(){
        return listaDeUsuario;
    }


    public static void cadastrarUsuario(){
        //TODO implementar o cadastro de usuarios.
    }
    /**
     * usa os paramentros para tentar realizar o login do usuario na istancia do sistema.
     * @param email email do usuario
     * @param senha senha do usuario
     * @throws UsuarioException Apenas se o usuario não for encontrado no banco de dados.
     */
    public static void fazerLogin (String email, String senha) throws UsuarioException {
        String restricao = "email = '" + email + "' AND senha = '" + senha+ "' ";
        Usuario usuario = dao.buscar(restricao);
        usuarioLogado = usuario;

    }


    public static String getEmailUsuarioLogado(){
        return usuarioLogado.getEmail();
    }

    public static String getCargoUsuarioLogado(){
        return usuarioLogado.getCargo();
    }

    public static String getNomeUsuarioLogado(){
        return usuarioLogado.getNome();
    }

    public static ImageIcon getfotoUsuarioLogado(){
        return usuarioLogado.getImgPerfil();
    }


}
