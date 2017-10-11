package view.Componetes;

import resources.Cores;
import resources.Fontes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.StringTokenizer;

/**
 * Classe que modela Label com ToolTip personalizado. E limitação de caractes
 * visiveis.
 */
public class MeuLabel extends JLabel {

    int max = 70;

    public MeuLabel(String text){

    }

    public MeuLabel(){

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
        StringTokenizer st = new StringTokenizer(text, " ");
        String tip = "<html>";
        for (int i = 0; st.hasMoreElements(); i++){
            if (i%15 == 14)
                tip = tip.concat(" <br> ");
            tip = tip.concat(" ").concat(st.nextToken());
        }
        super.setToolTipText(tip + "</html>");
    }

    /**
     * Adiciona o texto ao label. Mas ates, se necessario oculta parte do texto e insere um tootip com
     * o texto original.
     * @param text
     */
    @Override
    public void setText(String text) {
        if (text.length() > max){
            this.setToolTipText(text);
            super.setText(text.substring(0, max).concat(" [...]"));
        }else{
            super.setText(text);
        }
    }

    /**
     * Configura a quantidade maxima de caracteres que o labal pode ter.
     * @param max
     */
    public void setQuantidadeMaximaDeCaracteres(int max) {
        this.max = max;
    }
}
