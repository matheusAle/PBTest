package controller.adapters;

import model.Usuario;

import javax.swing.*;

public class UsuarioAdapter {

    private String email;
    private String nome;
    private String cargo;
    private ImageIcon imgPerfil;


    public UsuarioAdapter(String email, String nome, Usuario.Cargo cargo, ImageIcon imgPerfil) {
        this.email = email;
        this.nome = nome;
        this.cargo = cargo.toString().replaceAll("_", " ");
        this.imgPerfil = imgPerfil;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public ImageIcon getImgPerfil() {
        return imgPerfil;
    }

    public String getEmail() {
        return email;
    }
}
