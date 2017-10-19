package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe que modela Label com ToolTip personalizado. E limitação de caractes
 * visiveis.
 */
public class MeuLabel extends JLabel implements MouseListener{

    int max = 70;

    public MeuLabel(String text){

    }

    public MeuLabel(){
        super.setFocusable(false);
        super.addMouseListener(this);
    }


    /**
     * Personaliza a aparencia do tooltip com um fundo cinza e o texto branco.
     * @return
     */
    @Override
    public JToolTip createToolTip() {
        JToolTip tip =  super.createToolTip();
        tip.setBackground(Color.gray);
        tip.setForeground(Cores.TEXTOS);
        tip.setBorder(new EmptyBorder(5, 5, 5, 5));
        tip.setFont(Fontes.TEXTO_BTN);
        return tip;
    }

    /**
     * Gera uma tooltip multlinha para o label.
     * @param text
     */
    @Override
    public void setToolTipText(String text) {
        String tip = "<html><p width=\"400px\">";
        super.setToolTipText(tip + text + "</p></html>");
    }

    /**
     * Adiciona o texto ao label. Mas ates, se necessario oculta parte do texto e insere um tootip com
     * o texto original.
     * @param text
     */
    @Override
    public void setText(String text) {
        if (text.length() > max && max != -1){
            this.setToolTipText(text);
            super.setText(text.substring(0, max).concat(" [...]"));
        }else{
            super.setText(text);
        }
    }

    /**
     * Configura a quantidade maxima de caracteres que o labal pode ter.
     * se o valor -1 foir passado não existira mais restrição de tamanho.
     * @param max
     */
    public void setQuantidadeMaximaDeCaracteres(int max) {
        this.max = max;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            ((PainelDeListagem.PainelItem)super.getParent()).mouseClicked(e);
        }catch (ClassCastException x){}
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            ((PainelDeListagem.PainelItem)super.getParent()).mousePressed(e);
        }catch (ClassCastException x){}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            ((PainelDeListagem.PainelItem)super.getParent()).mouseReleased(e);
        }catch (ClassCastException x){}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            ((PainelDeListagem.PainelItem)super.getParent()).mouseEntered(e);
        }catch (ClassCastException x){}
    }

    @Override
    public void mouseExited(MouseEvent e) {
        try {
            ((PainelDeListagem.PainelItem)super.getParent()).mouseExited(e);
        }catch (ClassCastException x){}
    }
}
