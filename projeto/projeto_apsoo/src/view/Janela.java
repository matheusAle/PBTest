package view;

import controller.Sistema_Controller;
import resources.Cores;
import resources.Fontes;
import resources.Icones;
import resources.Strings;
import view.Componetes.Meu_Botao_Nav;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Essa classe é a responsavel por gerenciar os elementos não mutaveis da janela.
 */
public class Janela extends JFrame{
    public Nav painel_nav;
    static Controlador_de_Contexto controlador_de_contexto;

    public Janela(){
        super.setLayout(new BorderLayout());
        super.setSize(1200, 720);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlador_de_contexto = new Controlador_de_Contexto();
        super.add(controlador_de_contexto, BorderLayout.NORTH);

    }

    public Janela iniciarNav(String nomeUser, String cargoUser){
        painel_nav = new Nav(nomeUser, cargoUser);
        super.add(painel_nav, BorderLayout.WEST);
        return this;
    }

    /**
     * classe que gerencia os elementos do menu de navegação no sistema
     */
    public class Nav extends JPanel{

        public String nome_usuario;
        public String cargo_usuario;
        public Icon imgPerfil = Icones.imgPerfil;

        public Nav(String nome_usuario, String cargo_usuario){
            this.cargo_usuario = cargo_usuario;
            this.nome_usuario = nome_usuario;
            super.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            super.setPreferredSize(new Dimension(200, Janela.super.getHeight()));
            super.add(new Usuario_logado());
            super.add(new Navegacao());
        }

        class Usuario_logado extends JPanel {

            {
                super.setBackground(Cores._2eb398);
                super.setPreferredSize(new Dimension(200, 140));
                super.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));

                JLabel lImgPerfil = new JLabel(imgPerfil);

                JLabel nome = new JLabel(nome_usuario);
                nome.setFont(Fontes.NOME_USUARIO);
                nome.setForeground(Cores._ffffff);

                JLabel cargo = new JLabel(cargo_usuario);
                cargo.setFont(Fontes.CARGO_USUARIO);
                cargo.setForeground(Cores._ffffff);

                super.add(lImgPerfil);
                super.add(nome);
                super.add(cargo);
            }
        }

        public class Navegacao extends JPanel {
            private Meu_Botao_Nav btn_usuarios;
            private Meu_Botao_Nav btn_projetos;
            private Meu_Botao_Nav btn_casos_teste;
            private Meu_Botao_Nav btn_roteiros_testes;
            private Meu_Botao_Nav btn_matriz_rastreabilidade;

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
                super.setBackground(Cores._232b2d);
                super.setPreferredSize(new Dimension(200, Janela.super.getHeight()));
                super.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            }

            private void iniciarCompomentes(){
                btn_usuarios = new Meu_Botao_Nav(Strings.nav_usuarios);
                btn_projetos = new Meu_Botao_Nav(Strings.nav_projetos);
                btn_casos_teste = new Meu_Botao_Nav(Strings.nav_casos_de_teste);
                btn_roteiros_testes = new Meu_Botao_Nav(Strings.nav_roteiro_de_teste);
                btn_matriz_rastreabilidade = new Meu_Botao_Nav(Strings.nav_matriz_de_rastreabilidade);
            }

            private void initListeners() {
                btn_usuarios.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Sistema_Controller.setPainelDeTrabalho(Sistema_Controller.Estacoes_de_Tabalho.USUARIOS);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                btn_projetos.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Sistema_Controller.setPainelDeTrabalho(Sistema_Controller.Estacoes_de_Tabalho.PROJETOS);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
    }


    private class Controlador_de_Contexto extends JPanel{
        JPanel painel_logo;
        {
            super.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
            painel_logo = new JPanel();

            super.setPreferredSize(new Dimension(Janela.super.getWidth(), 50));
            painel_logo.setPreferredSize(new Dimension(200, 75));

            super.setBackground(Cores._1b2224);
            painel_logo.setBackground(Cores._1b2224);

            super.add(painel_logo);

        }

        @Override
        public Component add(Component comp) {
            return super.add(comp);
        }

        @Override
        public Component add(String name, Component comp) {
            return super.add(name, comp);
        }

        @Override
        public Component add(Component comp, int index) {
            return super.add(comp, index);
        }

        @Override
        public void add(Component comp, Object constraints) {
            super.add(comp, constraints);
        }

        @Override
        public void add(Component comp, Object constraints, int index) {
            super.add(comp, constraints, index);
        }

        @Override
        protected void addImpl(Component comp, Object constraints, int index) {
            super.addImpl(comp, constraints, index);
        }

        public void limpar (){
            super.removeAll();
            super.repaint();
            super.add(painel_logo);
        }

    }
}