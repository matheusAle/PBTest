package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Classe que modela um campo de texto com placeHolder
 */
public class MeuCampoDeTexto extends JTextField implements FocusListener{

    private String placeHolder = "";

    public MeuCampoDeTexto (){
        super.setBorder(new EmptyBorder(5,5,5,5));
        super.setFont(Fontes.CAMPO_DE_TEXTO);
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }


    @Override
    public void focusGained(FocusEvent e) {
        if (super.getText().equals(placeHolder)) {
            super.setText("");
            super.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (super.getText().isEmpty()) {
            super.setForeground(Color.GRAY);
            super.setText(placeHolder);
        }
    }
}
