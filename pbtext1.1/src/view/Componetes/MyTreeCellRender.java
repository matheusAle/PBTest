package view.Componetes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;
import resources.Arquivos;
import resources.Cores;

/**
 *
 * @author matheus
 */
public class MyTreeCellRender extends DefaultTreeCellRenderer{

    public MyTreeCellRender() {
        super.setLeafIcon(Arquivos.ICONE_PONTO_JAVA);
        super.setClosedIcon(Arquivos.ICONE_PASTA);
        super.setOpenIcon(Arquivos.ICONE_PASTA_OPEN);
        super.setFont(new java.awt.Font("Ubuntu", 0, 14));
        super.setBorder(new EmptyBorder(2,2,2,2));
        super.setBackgroundNonSelectionColor(Color.WHITE);
        super.setBackgroundSelectionColor(Cores.FUNDO_BOTAO);
        super.setTextNonSelectionColor(Color.DARK_GRAY);
        super.setTextSelectionColor(Color.BLACK);
    }
    
    
}
