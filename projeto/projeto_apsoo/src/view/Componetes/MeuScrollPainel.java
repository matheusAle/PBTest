package view.Componetes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MeuScrollPainel extends JScrollPane {


    public MeuScrollPainel(Component view) {
        super(view);
        super.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        super.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        super.setBorder(new EmptyBorder(1,1,1,1));
    }

}
