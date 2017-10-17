package controller;

import controller.exceptions.ProjetoException;
import view.*;
import view.CriarCasoDeUsoPainel;
import view.Componetes.Painel;

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
                p = PaineisDeTabalho.CASOS_DE_USO;
                break;
            case "CRIAR_CASO_DE_USO":
                p = PaineisDeTabalho.CRIAR_CASOS_DE_USO;
                ((CriarCasoDeUsoPainel)p).iniciarFormulario();
                break;
            case "CASOS_DE_TESTE":
                p = PaineisDeTabalho.CASOS_TESTE;
                ((CasosDeTestePainel)p).iniciarArvore();
                break;
            case "ROTEIROS_DE_TESTE":
                p = PaineisDeTabalho.MATRIZ_DE_RASTREABIBLIDADE;
                break;
            case "EDITAR_PROJETO":
                p = PaineisDeTabalho.EDITAR_PROJETO;
                ((EditarProjetoPainel) p).setProjeto(ProjetoController.getProjetoParaEditar());
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
        public static final Painel EDITAR_PROJETO = new EditarProjetoPainel();
        public static final Painel CASOS_DE_USO = new CasosDeUsoPainel();
        public static final Painel CRIAR_CASOS_DE_USO = new CriarCasoDeUsoPainel();
        public static final Painel CASOS_TESTE = new CasosDeTestePainel();
        public static final Painel ROTEIROS_TESTE = new RoteirosDeTestesPainel();
        public static final Painel MATRIZ_DE_RASTREABIBLIDADE = new MatrizDeRastreabilidadePainel();
    }




}
