package controller;

import model.Usuario;

import javax.swing.*;

public class UsuarioMetaData {

    String nome;
    String cargo;
    ImageIcon imgPerfil;

    public UsuarioMetaData(String nome, Usuario.Cargo cargo, ImageIcon imgPerfil) {
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
}
