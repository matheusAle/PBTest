package view.Componetes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

/**
 * Classe que modela um item de listagem
 */
//TODO implementar a mudança de cursor do mouse (apenas quando por possivem editar o item)
public class PainelItem extends JPanel implements MouseListener {

    private Color corDeFundoNormal;
    private Color corDeFundoHover;

    private Consumer<Component> onMouseClick = component -> {

    };
    private Consumer<Component> onMouseEnter = component -> {

    };
    private Consumer<Component> onMouseExit = component -> {

    };

    public PainelItem(){
        super.addMouseListener(this);
    }

    public void setCorDeFundoNormal(Color cor) {
        super.setBackground(cor);
        this.corDeFundoNormal = cor;
        this.corDeFundoHover = cor;
    }

    public void setCorDeFundoHover(Color cor) {
        this.corDeFundoHover = cor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        onMouseClick.accept(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.setBackground(corDeFundoHover);
        this.onMouseEnter.accept(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.setBackground(corDeFundoNormal);
        this.onMouseExit.accept(this);
    }
}
