package view.Componetes;

import resources.Cores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MeuMenuPopup extends JPopupMenu {

    public MeuMenuPopup(){
        super.setBackground(Cores.TEXTO_MENU_ESQUERDO);
        super.setForeground(Cores.TEXTOS);
        super.setBorder(new EmptyBorder(1,1,1,1));
    }


}



