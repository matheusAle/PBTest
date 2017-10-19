package view.Componetes;

import resources.Cores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class PainelDeOpcoes {

    JPanel painel;

    PainelDeOpcoes(){
        painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painel.setBorder(new EmptyBorder(0, 0, 0,0));
        painel.setBackground(Cores.FUNDO_BARRA_SUPERIOR);
    }

    void addComponente(Component c){
        painel.add(c);
    }

    JPanel getPainel(){
        return painel;
    }
}
