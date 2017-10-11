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

    private static Painel estacaoAtiva = new Painel("");
    public static Janela JANELA;

    public static void abrir() throws IOException, FontFormatException {
        JANELA = new Janela();
        JANELA.iniciarNavegacao(UsuarioController.getNomeUsuarioLogado(), UsuarioController.getCargoUsuarioLogado());
    }

    public static synchronized void setPainelDeTrabalho (String painel){
        Painel p = null;
        switch (painel){
            case "USUARIOS":
                p = PaineisDeTabalho.USUARIOS;
                break;
            case "PROJETOS":
                p = PaineisDeTabalho.PROJETOS;
                break;
            case "CRIAR_PROJETO":
                p = PaineisDeTabalho.CRIAR_PROJETO;
                break;
            case "CASOS_DE_USO":
                break;
            case "CASOS_DE_TESTE":
                p = PaineisDeTabalho.CASOS_TESTE;
                break;
            case "ROTEIROS_DE_TESTE":
                p = PaineisDeTabalho.MATRIZ_DE_RASTREABIBLIDADE;
                break;
        }

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
    private static class PaineisDeTabalho {
        public static final Painel USUARIOS = new UsuariosPainel();
        public static final Painel PROJETOS = new ProjetosPainel();
        public static final Painel CRIAR_PROJETO = new CriarProjetoPainel();
        public static final Painel CASOS_TESTE = new CasosDeTestePainel();
        public static final Painel ROTEIROS_TESTE = new RoteirosDeTestesPainel();
        public static final Painel MATRIZ_DE_RASTREABIBLIDADE = new MatrizDeRastreabilidadePainel();
    }




}
