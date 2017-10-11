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
    private int quantidadeMaximaDeCaracteres = -1;

    public MeuCampoDeTexto (){
        super.setBorder(new EmptyBorder(5,5,5,5));
        super.setFont(Fontes.CAMPO_DE_TEXTO);
        super.addFocusListener(this);

    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
        focusLost(null);
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
        if (super.getText().isEmpty() || super.getText().equals(null)) {
            super.setForeground(Color.GRAY);
            super.setText(placeHolder);
        }
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        focusLost(null);
    }

    /**
     * Valida o Campo de texto
     * @return retorna true apenas se:
     * <ul>
     *     <li>O texto for diferente de "";</li>
     *     <li>O texto for Diferente do PlaceHolder</li>
     *     <li>O texto for menor do que a quantidade maxima de caracteres especificada</li>
     * </ul>
     */
    public boolean campoValido (){
        if (super.getText().equals("") || super.getText().equals(placeHolder))
            return false;
        if (super.getText().length() > quantidadeMaximaDeCaracteres && quantidadeMaximaDeCaracteres != -1)
            return false;
        return true;
    }

    public void setQuantidadeMaximaDeCaracteres(int quantidadeMaximaDeCaracteres) {
        this.quantidadeMaximaDeCaracteres = quantidadeMaximaDeCaracteres;
    }

    public int getQuantidadeMaximaDeCaracteres() {
        return quantidadeMaximaDeCaracteres;
    }
}
