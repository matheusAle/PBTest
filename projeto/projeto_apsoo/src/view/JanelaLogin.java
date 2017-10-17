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
            JOptionPane.showMessageDialog(null, "senha ou usuario invalido.\n" + usuarioException.getMessage(),"Falha na autenticação", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e ){
            JOptionPane.showMessageDialog(null, e.getMessage(), "falha ao iniciar o programa", JOptionPane.INFORMATION_MESSAGE);
        }
        SistemaController.abrir();
        SistemaController.setPainelDeTrabalho("USUARIOS");
        SistemaController.JANELA.setVisible(true);
    }
}
