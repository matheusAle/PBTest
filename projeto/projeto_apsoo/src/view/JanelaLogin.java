package view;

import controller.SistemaController;
import controller.UsuarioController;

import java.awt.*;
import java.io.IOException;

public class JanelaLogin {
    public static void main(String[] args) throws IOException, FontFormatException {
        SistemaController.abrir(UsuarioController.fazerLogin("root@root", "root"));
        SistemaController.setPainelDeTrabalho(SistemaController.PaineisDeTabalho.CRIAR_PROJETO);
        SistemaController.JANELA.setVisible(true);
    }
}
