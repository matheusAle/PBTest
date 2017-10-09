package controller;

import model.Usuario;
import model.UsuarioDOA;

import java.util.Collection;

public class UsuarioController {

    private static Collection<UsuarioMetaData> listaDeUsuario = UsuarioDOA.buscarTodosOSUsuarios();
    private static Usuario usuarioAtivo;


    public static Collection<UsuarioMetaData> getListaDeUsuarios(){
        return listaDeUsuario;
    }

    public static Usuario fazerLogin (String email, String senha){
        return UsuarioDOA.fazerLogin(email, senha);
    }

    static void setUsuario(Usuario u){
        usuarioAtivo = u;
    }

    public static String getEmailUsuarioLogado(){
        return usuarioAtivo.getEmail();
    }


}
