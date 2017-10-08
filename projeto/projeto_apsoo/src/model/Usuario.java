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

    public Usuario(String nome, String email, Cargo cargo, String telefone, String bio, ImageIcon img) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.telefone = telefone;
        this.biografia = bio;
        this.imgPerfil = img;
    }

    /**
     * Metodo para realizar altenticar as informações do usuario que deseja acessar sua conta
     * no sistema
     * @return retorma uma instancia da classe Usuario
     */
    public static Usuario fazerLogin(String email, String senha){
        UsuarioDOA.logar(email, senha);
        //TODO terminar a implemeentação do metodo de logar!
        return null;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo.toString().replaceAll("_", " ");
    }

    public ImageIcon getImgPerfil() {
        return imgPerfil;
    }

    public static enum Cargo {
        GERENTE_DE_PROJETO,
        TESTADOR,
        GERENTE_DE_TESTES,
   }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cargo=" + cargo +
                ", telefone='" + telefone + '\'' +
                ", biografia='" + biografia + '\'' +
                ", imgPerfil=" + imgPerfil +
                '}';
    }
}
