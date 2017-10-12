package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class Botao extends JButton implements MouseListener{
    private Color corDeFundoNormal;
    private Color corDeFundoHover;
    private Color corDoTextoNormal;
    private Color corDoTextoHover;

    private Consumer<Component> onMouseClick = component -> {

    };
    private Consumer<Component> onMouseEnter = component -> {

    };
    private Consumer<Component> onMouseExit = component -> {

    };

    public Botao() {
        super.removeAll();
        super.setMargin(new Insets(5, 10, 5, 10));
        super.setFocusPainted(false);
        super.setBorderPainted(false);
        super.addMouseListener(this);
        super.setFont(Fontes.TEXTO_BTN);

    }


    /**
     * Define como cor de fundo padrão da instancia do botão. Bem como a cor de fundo qaundo o
     * mouse passar sobre o botão.
     * @param corDeFundoNormal cor de fundo.
     */
    public void setCorDeFundoNormal(Color corDeFundoNormal) {
        this.corDeFundoNormal = corDeFundoNormal;
        this.corDeFundoHover = corDeFundoNormal;
        super.setBackground(corDeFundoNormal);
        super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        for (Component c: super.getComponents())
            c.addMouseListener(this);
    }

    /**
     * Define a cor do texto padrão do botão. bem como a cor do texto quando o mouse pasar sobre
     * o botão.
     * @param corDoTextoNormal cor do texto.
     */
    public void setCorDoTextoNormal(Color corDoTextoNormal) {
        this.corDoTextoNormal = corDoTextoNormal;
        this.corDoTextoHover = corDoTextoNormal;
        super.setForeground(corDoTextoNormal);
    }

    /**
     * define a cor do texto quando o mouse estiver sobre ele.
     * @param getCorDoTextoHover
     */
    public void setCorDoTextoHover(Color getCorDoTextoHover) {
        this.corDoTextoHover = getCorDoTextoHover;
    }

    /**
     * Define a cor de fundo do botão para quando p mouse estiver sobre ele.
     * @param getCorDeFundoHover
     */
    public void setCorDeFundoHover(Color getCorDeFundoHover) {
        this.corDeFundoHover = getCorDeFundoHover;
    }

    public void setOnMouseClick(Consumer<Component> onMouseClick) {
        this.onMouseClick = onMouseClick;
    }

    public void setOnMouseEnter(Consumer<Component> onMouseEnter) {
        this.onMouseEnter = onMouseEnter;
    }

    public void setOnMouseExit(Consumer<Component> onMouseExit) {
        this.onMouseExit = onMouseExit;
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
        super.setForeground(corDoTextoHover);
        this.onMouseEnter.accept(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.setBackground(corDeFundoNormal);
        super.setForeground(corDoTextoNormal);
        this.onMouseExit.accept(this);
    }
}
