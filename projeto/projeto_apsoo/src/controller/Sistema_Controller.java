package controller;

import model.Usuario;
import sun.swing.SwingUtilities2;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Classe resposavel por manter informações sobre o estado do sistema.<br/>
 *
 */
public class Sistema_Controller {

    public static Janela JANELA;
    private static JScrollPane estacaoAtiva = new JScrollPane();
    private static Usuario usuarioAtivo;


    public static void abrir(Usuario usuario) throws IOException, FontFormatException {
        usuarioAtivo = usuario;

        JANELA = new Janela().iniciarNav(usuario.getNome(), usuario.getCargo().toString().replaceAll("_", " "));
    }

    public static synchronized void setPainelDeTrabalho (JScrollPane p){
        JANELA.remove(estacaoAtiva);
        estacaoAtiva = p;
        JANELA.add(p, BorderLayout.CENTER);
        JANELA.revalidate();
        JANELA.repaint();
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        abrir(UsuarioController.listaUsuario.get(0));
        setPainelDeTrabalho(Estacoes_de_Tabalho.PROJETOS);
        JANELA.setVisible(true);
    }

    /**
     * Mantem referencias para cada uma das "estações" de trabalho do programa.<br/>
     * Todas as referencias são estaticas e finais.
     */
    public static class Estacoes_de_Tabalho {
        public static final JScrollPane USUARIOS = new Painel_Usuarios().getPainel();
        public static final JScrollPane PROJETOS = new Painel_Projetos().getPainel();
        public static final JScrollPane CASOS_TESTE = new Painel_Casos_de_Teste();
        public static final JScrollPane ROTEIROS_TESTE = new Painel_Roteiros_de_Teste();
        public static final JScrollPane MATRIZ_RASTREABIBLIDADE = new Painel_Matriz_de_Rastreabilidade();
    }


}
