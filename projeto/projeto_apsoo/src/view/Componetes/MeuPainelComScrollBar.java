package view.Componetes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MeuPainelComScrollBar extends JScrollPane {


    public MeuPainelComScrollBar(Component view) {
        super(view);

        super.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        super.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        super.setBorder(new EmptyBorder(0,0,0,0));

        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                MeuPainelComScrollBar.super.setPreferredSize(MeuPainelComScrollBar.super.getSize());
            }
        });
    }

    public MeuPainelComScrollBar() {

        super.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        super.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        super.setBorder(new EmptyBorder(0,0,0,0));

        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                MeuPainelComScrollBar.super.setPreferredSize(MeuPainelComScrollBar.super.getSize());
            }
        });
    }
}
