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

    public Usuario(String email, String nome, Cargo cargo, Object o) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.telefone = telefone;
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
                ", IMG_PERFIL_PADRAO=" + imgPerfil +
                '}';
    } 
}
