package view;

import controller.ProjetoController;
import controller.SistemaController;
import controller.UsuarioController;
import controller.exceptions.UsuarioException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JanelaLogin {
    public static void main(String[] args) throws IOException, FontFormatException {
        try {
            UsuarioController.fazerLogin("root@root", "root");
        } catch (UsuarioException usuarioException) {
            JOptionPane.showMessageDialog(null, "Falha na autenticação", "senha ou usuario invalido.\n" + usuarioException.getMessage(), JOptionPane.INFORMATION_MESSAGE);
        }
        SistemaController.abrir();
        ProjetoController.setProjetoAtualizavel("1");
        SistemaController.setPainelDeTrabalho("PROJETOS");
        SistemaController.JANELA.setVisible(true);
    }
}
