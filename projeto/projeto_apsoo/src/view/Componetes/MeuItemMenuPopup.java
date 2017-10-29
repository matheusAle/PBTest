package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class MeuItemMenuPopup extends JMenuItem implements MouseListener{

    Consumer<MouseEvent> onClick = (e) ->{};

    public MeuItemMenuPopup(String texto){
        super(texto);
        super.getUI().uninstallUI(this);
        super.setBackground(Cores.FUNDO_USUARIO_LOGADO);
        super.setForeground(Cores.TEXTOS);
        super.setFont(Fontes.TEXTO_BTN);
        super.setBorder(new EmptyBorder(5,5,5,5));
        super.addMouseListener(this);
        for (Component c: super.getComponents())
            c.addMouseListener(this);
    }

    public MeuItemMenuPopup setOnClick(Consumer<MouseEvent> onClick) {
        this.onClick = onClick;
        return this;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            super.setBackground(Cores.FUNDO_USUARIO_LOGADO);
        }).start();
        onClick.accept(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.setBackground(Cores.FUNDO_BOTAO);

        super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.setBackground(Cores.FUNDO_USUARIO_LOGADO);
        super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

}