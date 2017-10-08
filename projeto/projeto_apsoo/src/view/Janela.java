package view;

import controller.SistemaController;
import resources.Cores;
import resources.Fontes;
import resources.Icones;
import resources.Strings;
import view.Componetes.BotaoDeNavegacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Essa classe é a responsavel por gerenciar os elementos não mutaveis da janela.
 */
public class Janela extends JFrame{
    private Nav painel_nav;
    private OpcoesDeEstacao opcoes;

    public Janela(){
        super.setLayout(new BorderLayout());
        super.setSize(1200, 720);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        opcoes = new OpcoesDeEstacao();
        super.add(opcoes, BorderLayout.NORTH);

    }

    /**
     * adiciona o painel a barra de opções de contexto da janela.
     * @param painel
     */
    public void setOpcoes (JPanel painel){
        opcoes.set(painel);
    }

    public Janela iniciarNavegacao(String nomeUser, String cargoUser){
        painel_nav = new Nav(nomeUser, cargoUser);
        super.add(painel_nav, BorderLayout.WEST);
        return this;
    }

    /**
     * classe que gerencia os elementos da lateral esqueda da janela.
     */
    public class Nav extends JPanel{

        private String nome_usuario;
        private String cargo_usuario;
        private Icon imgPerfil = Icones.imgPerfil;

        Nav(String nome_usuario, String cargo_usuario){
            this.cargo_usuario = cargo_usuario;
            this.nome_usuario = nome_usuario;
            super.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            super.setPreferredSize(new Dimension(200, Janela.super.getHeight()));
            super.add(new Usuario_logado());
            super.add(new Navegacao());
        }

        /**
         * Classe respinsavel pelo card do usuario logado no sistema.
         */
        class Usuario_logado extends JPanel {

            {
                super.setBackground(Cores.FUNDO_USUARIO_LOGADO);
                super.setPreferredSize(new Dimension(200, 140));
                super.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));

                JLabel lImgPerfil = new JLabel(imgPerfil);

                JLabel nome = new JLabel(nome_usuario);
                nome.setFont(Fontes.NOME_USUARIO);
                nome.setForeground(Cores.TEXTOS);

                JLabel cargo = new JLabel(cargo_usuario);
                cargo.setFont(Fontes.CARGO_USUARIO);
                cargo.setForeground(Cores.TEXTOS);

                super.add(lImgPerfil);
                super.add(nome);
                super.add(cargo);
            }
        }

        /**
         * classe que cuida das opções de navegação do sistema.
         */
        class Navegacao extends JPanel {
            private BotaoDeNavegacao btn_usuarios;
            private BotaoDeNavegacao btn_projetos;
            private BotaoDeNavegacao btn_casos_teste;
            private BotaoDeNavegacao btn_roteiros_testes;
            private BotaoDeNavegacao btn_matriz_rastreabilidade;

            {
                iniciarLayout();
                iniciarCompomentes();
                initListeners();
                super.add(btn_usuarios, 0);
                super.add(btn_projetos);
                super.add(btn_casos_teste);
                super.add(btn_roteiros_testes);
                super.add(btn_matriz_rastreabilidade);
            }

            private void iniciarLayout() {
                super.setBackground(Cores.FUNDO_MENU_ESQUERDO);
                super.setPreferredSize(new Dimension(200, Janela.super.getHeight()));
                super.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            }

            private void iniciarCompomentes(){
                btn_usuarios = new BotaoDeNavegacao(Strings.nav_usuarios);
                btn_projetos = new BotaoDeNavegacao(Strings.nav_projetos);
                btn_casos_teste = new BotaoDeNavegacao(Strings.nav_casos_de_teste);
                btn_roteiros_testes = new BotaoDeNavegacao(Strings.nav_roteiro_de_teste);
                btn_matriz_rastreabilidade = new BotaoDeNavegacao(Strings.nav_matriz_de_rastreabilidade);
            }

            private void initListeners() {
                btn_usuarios.setOnClick((e) -> {SistemaController.setPainelDeTrabalho(SistemaController.PaineisDeTabalho.USUARIOS);});
                btn_projetos.setOnClick((e) -> {SistemaController.setPainelDeTrabalho(SistemaController.PaineisDeTabalho.PROJETOS);});
            }
        }
    }


    /**
     * Classe que modela a barra superior do sistema que contem as opçoes da estação de trabalho ativa
     */
    class OpcoesDeEstacao extends JPanel{

        JPanel painel_logo;

        {
            super.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
            painel_logo = new JPanel();

            super.setPreferredSize(new Dimension(Janela.super.getWidth(), 50));
            painel_logo.setPreferredSize(new Dimension(200, 75));

            super.setBackground(Cores.FUNDO_BARRA_SUPERIOR);
            painel_logo.setBackground(Cores.FUNDO_BARRA_SUPERIOR);

            super.add(painel_logo);

        }

        public void set(JPanel painel){
            this.limpar();
            super.add(painel);
        }

        void limpar(){
            super.removeAll();
            super.repaint();
            super.add(painel_logo);
        }

    }
}