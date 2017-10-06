package view.Componetes;

import javax.swing.*;
import java.awt.*;

public class Meu_Scroll_Painel extends JScrollPane {


    public Meu_Scroll_Painel(Component view) {
        super(view);
        super.getVerticalScrollBar().setUI(new Meu_ScrollbarUI());
        super.getHorizontalScrollBar().setUI(new Meu_ScrollbarUI());
    }

}
