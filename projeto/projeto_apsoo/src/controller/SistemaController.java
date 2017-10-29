package controller;

import controller.exceptions.CasoDeTesteExeption;
import controller.exceptions.CasoDeUsoExeption;
import controller.exceptions.RoteiroDeTesteExeption;
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

    /**
     * Carrega todas as funções do sistema ma memoria.
     * @throws IOException
     * @throws FontFormatException
     */
    public static void abrir() throws IOException, FontFormatException {
        JANELA = new Janela();
        JANELA.iniciarNavegacao(UsuarioController.getNomeUsuarioLogado(), UsuarioController.getCargoUsuarioLogado());
        JANELA.setVisible(true);
    }

    public static synchronized void setPainelDeTrabalho (String painel) throws CasoDeUsoExeption, CasoDeTesteExeption, RoteiroDeTesteExeption {
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
                if (ProjetoController.temProjetoAtivo()) throw new CasoDeUsoExeption("Não existe nem um projeto ativo!");
                p = PaineisDeTabalho.CASOS_DE_USO;
                break;
            case "CRIAR_CASO_DE_USO":
                if (ProjetoController.temProjetoAtivo()) throw new CasoDeUsoExeption("Não existe nem um projeto ativo!");
                p = PaineisDeTabalho.CRIAR_CASOS_DE_USO;
                ((CriarCasoDeUsoPainel)p).iniciarFormulario();
                break;
            case "CASOS_DE_TESTE":
                if (ProjetoController.temProjetoAtivo()) throw new CasoDeTesteExeption("Não existe nem um projeto ativo!");
                p = PaineisDeTabalho.CASOS_TESTE;
                ((CasosDeTestePainel)p).iniciarArvore();
                break;
            case "ROTEIROS_DE_TESTE":
                if (ProjetoController.temProjetoAtivo()) throw new RoteiroDeTesteExeption("Não existe nem um projeto ativo!");
                p = PaineisDeTabalho.ROTEIROS_TESTE;
                break;
            case "EDITAR_PROJETO":
                p = PaineisDeTabalho.EDITAR_PROJETO;
                ((EditarProjetoPainel) p).setProjeto(ProjetoController.getProjetoParaEditar());
                break;
            case "EDITAR_CASO_DE_USO":
                p = PaineisDeTabalho.EDITAR_CASO_DE_USO;
                ((EditarCasoDeUsoPainel)p).setCasoDeUso(CasoDeUsoController.getCasoDeUsoEditavel());
        }
        JANELA.setPainelAtivo(p);

    }

    /**
     * Mantem referencias para cada uma das "estações" de trabalho do programa.<br/>
     * Todas as referencias são estaticas e finais.
     */
    private static class PaineisDeTabalho {
        static final Painel USUARIOS = new UsuariosPainel();
        static final Painel PROJETOS = new ProjetosPainel();
        static final Painel CRIAR_PROJETO = new CriarProjetoPainel();
        static final Painel EDITAR_PROJETO = new EditarProjetoPainel();
        static final Painel CASOS_DE_USO = new CasosDeUsoPainel();
        static final Painel CRIAR_CASOS_DE_USO = new CriarCasoDeUsoPainel();
        static final Painel CASOS_TESTE = new CasosDeTestePainel();
        static final Painel ROTEIROS_TESTE = new RoteirosDeTestePainel();
        static final Painel MATRIZ_DE_RASTREABIBLIDADE = new MatrizDeRastreabilidadePainel();
        static final Painel EDITAR_CASO_DE_USO = new EditarCasoDeUsoPainel();
    }






}
