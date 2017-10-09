package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class BotaoDeNavegacao extends JLabel implements MouseListener{

    private String texto;
    private Color corDoTextoNormal = Cores.TEXTO_MENU_ESQUERDO;
    private Color corDoTextoHover = Cores.TEXTOS;
    private Consumer<Component> onClick;

    public BotaoDeNavegacao(String text) {
        super(text);
        texto = text;

        setPreferredSize(new Dimension(170, 20));
        super.setFont(Fontes.NAV_LABEL);
        super.addMouseListener(this);
        super.setForeground(corDoTextoNormal);
        super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setOnClick(Consumer<Component> onClick) {
        this.onClick = onClick;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        onClick.accept(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.setForeground(corDoTextoHover);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.setForeground(corDoTextoNormal);
    }



}
