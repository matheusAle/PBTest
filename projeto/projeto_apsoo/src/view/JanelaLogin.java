package view;

import controller.SistemaController;
import controller.UsuarioController;
import controller.UsuarioExeption;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JanelaLogin {
    public static void main(String[] args) throws IOException, FontFormatException {
        try {
            UsuarioController.fazerLogin("root@root", "root");
        } catch (UsuarioExeption usuarioExeption) {
            JOptionPane.showMessageDialog(null, "Falha na autenticação", "senha ou usuario invalido.\n" + usuarioExeption.getMessage(), JOptionPane.INFORMATION_MESSAGE);
        }
        SistemaController.abrir();
        SistemaController.setPainelDeTrabalho("CRIAR_PROJETO");
        SistemaController.JANELA.setVisible(true);
    }
}
