package view.Componetes;

import resources.Cores;
import resources.Fontes;
import view.Janela;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Meu_Botao_Nav extends JLabel {

    private Color corDoTextoNormal;
    private Color corDoTextoHover;

    private void init() {
        super.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Meu_Botao_Nav.super.setForeground(corDoTextoHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Meu_Botao_Nav.super.setForeground(corDoTextoNormal);
            }
        });
    }

    public void setCorDoTextoNormal(Color corDoTextoNormal) {
        this.corDoTextoNormal = corDoTextoNormal;
        Meu_Botao_Nav.super.setForeground(corDoTextoNormal);
    }

    public void setCorDoTextoHover(Color getCorDoTextoHover) {
        this.corDoTextoHover = getCorDoTextoHover;
    }


    public Meu_Botao_Nav(String text) {
        super(text);
        init();
        setCorDoTextoNormal(Cores._6f7b7e);
        setCorDoTextoHover(Cores._ffffff);
        setPreferredSize(new Dimension(170, 20));
        super.setFont(Fontes.NAV_LABEL);

    }

    private  class s extends MetalButtonUI {
        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {
            super.paintButtonPressed(g, b);
        }

        @Override
        protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
            super.paintText(g, c, textRect, text);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
        }

        @Override
        protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
            super.paintIcon(g, c, iconRect);
        }

        @Override
        protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
            super.paintText(g, b, textRect, text);
        }

        @Override
        public Dimension getMaximumSize(JComponent c) {
            return super.getMaximumSize(c);
        }
    }
}
