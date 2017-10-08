package view.Componetes;

import controller.SistemaController;
import resources.Fontes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * classe que abistrai o componente de painel das abas de navegação do software
 */
public class Painel{
    private String labelTitulo;
    private Meu_PainelPrincipal painel;
    private Meu_painelConteudo painelConteudo;
    private MeuScrollPainel painelScroll;

    private int alturaConteudo;
    private int larguraConteudo;
    private int quantidadeDeItens = 1;

    /**
     * cria um novo painel com um tulo de conteudo.
     * @param titulo titulo
     */
    public Painel(String titulo){
        this.labelTitulo = titulo;
        painel = new Meu_PainelPrincipal();
        painel.setLayout(new BorderLayout(5, 5));

        JLabel tituloL = new JLabel(labelTitulo);
        tituloL.setBorder(new EmptyBorder(20, 20, 10, 10));
        tituloL.setFont(Fontes.PAINEL_TITULO);
        painel.add(tituloL, BorderLayout.NORTH);

        painelConteudo = new Meu_painelConteudo();
        painelConteudo.setLayout(new FlowLayout());
        painel.add(painelConteudo, BorderLayout.CENTER);
        painelScroll = new MeuScrollPainel(painel);
    }


    /**
     * Deve ser cjamado logo apos a instanciação do Painel
     * @param alturaConteudo
     * @param larguraConteudo
     */
    public void setAlturaELarguraDosItensDoConteudo(int alturaConteudo, int larguraConteudo){
        this.alturaConteudo = alturaConteudo + 10;
        this.larguraConteudo = larguraConteudo + 10;
    }

    /**
     * Adiciona um omponente ao painel de conteudo.
     * @param c
     */
    public void addConteudo(Component c) {
        painelConteudo.add(c);
        quantidadeDeItens++;
    }

    /**
     * remove totod os itens do painel de conteudo
     */
    public void limparConteudo (){
        painelConteudo.removeAll();
        quantidadeDeItens = 1;
    }

    public void setVisible(boolean v){
        painelConteudo.setVisible(v);
    }

    public JScrollPane getPainel (){
        return painelScroll;
    }


    private class Meu_PainelPrincipal extends JPanel {

        @Override
        public Dimension getPreferredSize() {
            Dimension d =  Painel.this.painelConteudo.getPreferredSize();
            return new Dimension(d.width, d.height + 150);
        }
    }

    private class Meu_painelConteudo extends JPanel {
        @Override
        public Dimension getPreferredSize() {
            int ht = SistemaController.JANELA.getWidth();
            return new Dimension(950, alturaConteudo/((ht-200)/larguraConteudo)*quantidadeDeItens);
        }

    }


}
