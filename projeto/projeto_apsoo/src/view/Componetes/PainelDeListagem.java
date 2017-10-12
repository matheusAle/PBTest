package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

/**
 * Modela um painel utilizado para listar itens.
 * Este painel é responsavel por atialixar os itens da lista de foir ma automatica.
 */
public abstract class PainelDeListagem extends Painel {

    private Consumer<Object> recarregarLista = component -> {};
    private Consumer<Object> sincronizarLista = component -> {};

    protected PainelDeListagem(String titulo){
        super(titulo);
        super.setQuandoAtivo((o) -> this.atualizarLista(true));
        carregarOpcoes();
    }

    /**
     * Metodo chamado no contrutor. Controi os componetes de opçoes da listagem.
     */
    protected abstract void carregarOpcoes();

    /**
     * Define o metodo a ser chamando quando a for necessario sincromizar os valores da lista.
     * @param sincronizarLista Consumer<Object>
     */
    public void setSincronizarLista(Consumer<Object> sincronizarLista) {
        this.sincronizarLista = sincronizarLista;
    }

    /**
     * Define o metodo a ser chamado para recarregar a lista de itens do painel
     * @param recarregarLista Consumer<Object>
     */
    protected void setRecarregarLista(Consumer<Object> recarregarLista) {
        this.recarregarLista = recarregarLista;
    }

    /**
     * Chama o metodo setado em <link>setRecarregarLista</link>
     * @param sincronizar se este valor for true a listam sera sincronizada com os dados no banco de dados. Se for false, os itens serão apenas repintados na tela.
     */
    protected void atualizarLista(boolean sincronizar){
        super.limparConteudo();
        if (sincronizar)
            sincronizarLista.accept(this);
        recarregarLista.accept(this);
    }

    /**
     * Classe que modela um item de listagem
     */
    public abstract class PainelItem extends JPanel implements MouseListener {

        protected JPanel painelLegenda;
        protected MeuMenuPopup menuPopup;

        private Color corDeFundoNormal;
        private Color corDeFundoHover;

        private Consumer<MouseEvent> onMouseClick = component -> {};
        private Consumer<MouseEvent> onMouseEnter = component -> {};
        private Consumer<MouseEvent> onMouseExit = component -> {};

        public PainelItem(){
            super.addMouseListener(this);
            iniciarComponetes();
            iniciarLayout();
            iniciarEstilo();
        }

        /**
         * Define cor de fundo do painel que contem o item.
         * @param cor
         */
        public void setCorDeFundoNormal(Color cor) {
            super.setBackground(cor);
            this.corDeFundoNormal = cor;
            this.corDeFundoHover = cor;
        }

        /**
         * Define a cor de fundo do painel quando o mouse estiver sobre ele.
         * @param cor
         */
        public void setCorDeFundoHover(Color cor) {
            this.corDeFundoHover = cor;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            onMouseClick.accept(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)){
                menuPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.setBackground(corDeFundoHover);
            this.onMouseEnter.accept(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.setBackground(corDeFundoNormal);
            this.onMouseExit.accept(e);
        }

        /**
         * Evento a ser disparado quando o usuario clicar no item.
         * @param onMouseClick
         */
        public void setOnMouseClick(Consumer<MouseEvent> onMouseClick) {
            this.onMouseClick = onMouseClick;
        }

        /**
         * Evento disparado quando o usuario colocar o mouse sobre o item
         * @param onMouseEnter
         */
        public void setOnMouseEnter(Consumer<MouseEvent> onMouseEnter) {
            this.onMouseEnter = onMouseEnter;
        }

        /**
         * Evento disparado quando o usuario remover o mouse de cima do item.
         * @param onMouseExit
         */
        public void setOnMouseExit(Consumer<MouseEvent> onMouseExit) {
            this.onMouseExit = onMouseExit;
        }


        /**
         * Instancia os objtos que compoes o item da lista.
         */
        protected abstract void iniciarComponetes();

        /**
         * Carrega as definições de posicionamento dos compementes de um item da lista.
         */
        protected abstract void iniciarLayout();

        /**
         * Formata a aparencia so item da lista.
         */
        private void iniciarEstilo(){
            this.painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            this.setCorDeFundoNormal(Cores.TEXTO_MENU_ESQUERDO);
            this.setCorDeFundoHover(Cores.FUNDO_MENU_ESQUERDO);

            for (Component c : painelLegenda.getComponents()){
                c.setFont(Fontes.LEGENDA_ITEM_LISTA);
                c.setForeground(Cores.TEXTOS);
                try {
                    ((JLabel)c).setHorizontalAlignment(SwingConstants.RIGHT);
                    ((JLabel)c).setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                }catch (Exception e){}
            }
            for (Component c : this.getComponents()){
                c.setFont(Fontes.LEGENDA_ITEM_LISTA);
                c.setForeground(Cores.TEXTOS);
                try{
                    ((JLabel)c).setHorizontalAlignment(SwingConstants.LEFT);
                    ((JLabel)c).setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                }catch (Exception e){}
            }
        }

    }
}
