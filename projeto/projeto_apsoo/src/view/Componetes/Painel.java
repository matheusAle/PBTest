package view.Componetes;

import controller.SistemaController;
import resources.Fontes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.function.Consumer;

/**
 * classe que abistrai o componente de painel das abas de navegação do software
 */
public class Painel{

    /**
     * Texto que de titulo deste painel.
     */
    private JLabel labelTitulo;
    /**
     * painel que contem o titulo e o painel de conteudo.
     */
    private Meu_PainelPrincipal painel;
    /**
     * painel que contem o conteudo do painel.
     */
    private Meu_painelConteudo painelConteudo;
    /**
     * Painel com Scroll que contem apenas o painel principal.
     */
    private MeuPainelComScrollBar painelScroll;
    /**
     * Painel que contem as opçoes do painel.
     */
    private PainelDeOpcoes opcoes;

    /**
     * boolean que marca o estado da painel atual.
     */
    private boolean painelAtivo = true;

    /**
     * Execulta um preprocessamento do carregamento do painel
     */
    private Consumer<Component> quandoAtivo = component -> {

    };
    /**
     * Execunta um preprocessamento para a inativodade do painel.
     */
    private Consumer<Component> quandoInativo = component -> {

    };


    private int alturaConteudo = 1;
    private int larguraConteudo = 2;
    private int quantidadeDeItens = 1;

    protected Painel (){

    }

    /**
     * instancia um novo painel com um titulo.
     * @param titulo
     */
    public Painel(String titulo){
        labelTitulo = new JLabel(titulo);
        painel = new Meu_PainelPrincipal();
        painel.setLayout(new BorderLayout(5, 5));

        labelTitulo.setBorder(new EmptyBorder(20, 20, 10, 10));
        labelTitulo.setFont(Fontes.PAINEL_TITULO);
        painel.add(labelTitulo, BorderLayout.NORTH);

        painelConteudo = new Meu_painelConteudo();
        painelConteudo.setLayout(new FlowLayout());
        painel.add(painelConteudo, BorderLayout.CENTER);
        painelScroll = new MeuPainelComScrollBar(painel);
        opcoes = new PainelDeOpcoes();
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
        painelConteudo.repaint();
        painelConteudo.revalidate();
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

    /**
     * @return retorna um JScrollPane contendo os elementos do painel;
     */
    public JScrollPane getPainel (){
        if (painelAtivo){
            quandoAtivo.accept(null);
        } else {
            quandoInativo.accept(null);
        }
        painelAtivo = !painelAtivo;
        return painelScroll;
    }

    /**
     * Adiciona o elementos passado como paramentro no JPanel de opções.
     * @param c
     */
    public void addOpcao(Component c) {
        opcoes.addComponente(c);
    }


    /**
     * Painel prique contem um titulo e um painel de conteudo.
     *
     */
    private class Meu_PainelPrincipal extends JPanel {

        @Override
        public Dimension getPreferredSize() {
            Dimension d =  Painel.this.painelConteudo.getPreferredSize();
            return new Dimension(d.width, d.height + 70);
        }
    }

    /**
     * Modela um JPanel com tamanho que varia de acordo com o conteudo que ele contem.
     */
    private class Meu_painelConteudo extends JPanel {
        @Override
        public Dimension getPreferredSize() {
            int ht = SistemaController.JANELA.getWidth();
            return new Dimension(950, alturaConteudo/((ht-200)/larguraConteudo)*quantidadeDeItens);

        }

    }

    /**
     * @return Retorna um JPanel com As opções do painel.
     */
    public JPanel getOpcoes(){
        return opcoes.getPainel();
    }


    /**
     * Metodo chamado sempre que o painel ficar ativo.
     * @param quandoAtivo
     */
    public void setQuandoAtivo(Consumer<Component> quandoAtivo) {
        this.quandoAtivo = quandoAtivo;
    }

    /**
     * Metodo chamado sempre queo painel for ficar ficar inativo
     * @param quandoInativo
     */
    public void setQuandoInativo(Consumer<Component> quandoInativo) {
        this.quandoInativo = quandoInativo;
    }


    public void setTitulo(String titulo){
        this.labelTitulo.setText(titulo);
    }


}



