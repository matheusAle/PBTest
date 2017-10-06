package view.Componetes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Meu_Botao extends JButton {
    private Color corDeFundoNormal;
    private Color corDoTextoNormal;
    private Color corDoTextoHover;
    private Color corDeFundoHover;
    private Color corOnClick;
    public Meu_Botao() {
        init();
    }

    public Meu_Botao(Icon icon) {
        super(icon);
        init();
    }

    public Meu_Botao(String text) {
        super(text);
        init();
    }

    public Meu_Botao(Action a) {
        super(a);
        init();
    }

    public Meu_Botao(String text, Icon icon) {
        super(text, icon);
        init();
    }

    private void init() {
        iniciarListaners();
        super.setMargin(new Insets(5, 10, 5, 10));
        super.setFocusPainted(false);
        super.setBorderPainted(false);
    }

    private void iniciarListaners(){
        super.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("afçmadsfkmadsfadsf");
                Meu_Botao.super.setBackground(corOnClick);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Meu_Botao.super.setBackground(corOnClick);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Meu_Botao.super.setBackground(corDeFundoHover);
                Meu_Botao.super.setForeground(corDoTextoHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Meu_Botao.super.setBackground(corDeFundoNormal);
                Meu_Botao.super.setForeground(corDoTextoNormal);
            }
        });
    }

    public void setCorDeFundoNormal(Color corDeFundoNormal) {
        this.corDeFundoNormal = corDeFundoNormal;
        Meu_Botao.super.setBackground(corDeFundoNormal);
    }

    public void setCorDoTextoNormal(Color corDoTextoNormal) {
        this.corDoTextoNormal = corDoTextoNormal;
        Meu_Botao.super.setForeground(corDoTextoNormal);
    }

    public void setCorDoTextoHover(Color getCorDoTextoHover) {
        this.corDoTextoHover = getCorDoTextoHover;
    }

    public void setCorDeFundoHover(Color getCorDeFundoHover) {
        this.corDeFundoHover = getCorDeFundoHover;
    }

    public Color getCorOnClick() {
        return corOnClick;
    }

    public void setCorOnClick(Color corOnClick) {
        this.corOnClick = corOnClick;
    }
}
