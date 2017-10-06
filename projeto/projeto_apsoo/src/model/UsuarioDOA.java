package model;

import model.Usuario;

// TODO implementar o doa do usuario!
public final class UsuarioDOA {
    static boolean logar (String email, String senha){
        return true;
    }

    /**
     * Este metodo deve ser chamado sempre que as informações de um usuario doirem alteradas.
     * @param u usuario que teve informaçoes alteradas
     * @return
     */
    static boolean salvarAlteracoes (Usuario u){
        return true;
    }

    /**
     * Libera o login do usuario em outra maquina e desloga-o desta instancia do sistema
     * @return retorna verdadeiro se for bem sucedido.
     */
    static boolean logout(){
        return true;
    }

    public Usuario buscarUsuario (String email){
        //TODO implementar a recuperação dos dados de um usuario no banco de dados
        return null;
    }
}
