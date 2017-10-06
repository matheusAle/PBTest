package model;

import javax.swing.*;

/**
 * Essa classe que modela a entidade Usuario.
 */
public class Usuario {
   private String nome;
   private String email;
   private Cargo cargo;
   private String telefone;
   private String biografia;
   private ImageIcon imgPerfil;

    public Usuario(String nome, String email, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    /**
     * Metodo para realizar altenticar as informações do usuario que deseja <br/> acessar sua conta
     * no sistema
     * @return retorma uma instancia da classe Usuario
     */
    public static Usuario fazerLogin(String email, String senha){
        UsuarioDOA.logar(email, senha);
        //TODO terminar a implemeentação do metodo de logar!
        return null;
    }


    /**
     * letodo chamado quando o usuario for fazer o logOut do sistema
     * @return retorna sim se for bem sucedido.
     */
    public boolean fazerLogOut(){
        return UsuarioDOA.logout();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public ImageIcon getImgPerfil() {
        return imgPerfil;
    }

    public static enum Cargo {
        GERENTE_DE_PROJETO,
        TESTADOR,
        GERENTE_DE_TESTES,
   }
}
