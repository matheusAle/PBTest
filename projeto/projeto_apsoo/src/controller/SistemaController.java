package controller;

import model.Usuario;
import view.*;
import view.Componetes.Painel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Classe resposavel por manter informações sobre o estado do sistema.<br/>
 *
 */
public class SistemaController {

    public static Janela JANELA;
    private static Painel estacaoAtiva = new Painel("");

    public static void abrir(Usuario usuario) throws IOException, FontFormatException {
        UsuarioController.setUsuario(usuario);
        JANELA = new Janela().iniciarNavegacao(usuario.getNome(), usuario.getCargo());
    }

    public static synchronized void setPainelDeTrabalho (Painel p){
        JANELA.remove(estacaoAtiva.getPainel());
        estacaoAtiva = p;
        JANELA.add(p.getPainel(), BorderLayout.CENTER);
        JANELA.setOpcoes(p.getOpcoes());
        JANELA.revalidate();
        JANELA.repaint();
    }

    /**
     * Mantem referencias para cada uma das "estações" de trabalho do programa.<br/>
     * Todas as referencias são estaticas e finais.
     */
    public static class PaineisDeTabalho {
        public static final Painel USUARIOS = new UsuariosPainel();
        public static final Painel PROJETOS = new ProjetosPainel();
        public static final Painel CRIAR_PROJETO = new CriarProjetoPainel();
        public static final JScrollPane CASOS_TESTE = new CasosDeTestePainel();
        public static final JScrollPane ROTEIROS_TESTE = new RoteirosDeTestesPainel();
        public static final JScrollPane MATRIZ_RASTREABIBLIDADE = new MatrizDeRastreabilidadePainel();
    }


}
