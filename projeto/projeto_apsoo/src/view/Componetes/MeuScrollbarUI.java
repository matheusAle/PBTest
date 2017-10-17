package view.Componetes;

import resources.Cores;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;

    /** @see //stackoverflow.com/a/12270067/230513 */
public class MeuScrollbarUI extends MetalScrollBarUI {


    private Image imageThumb, imageTrack;
    private Botao b = new Botao() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };

    public MeuScrollbarUI() {

        imageThumb = FauxImage.create(5, 5, Cores.FUNDO_BOTAO);
        imageTrack = FauxImage.create(10, 10, Color.white);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        g.setColor(Color.white);
        ((Graphics2D) g).drawImage(imageThumb,
                r.x+4, r.y, r.width-8, r.height, null);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        ((Graphics2D) g).drawImage(imageTrack,
                r.x, r.y, r.width, r.height, null);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return b;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return b;
    }

    private static class FauxImage {

        static public Image create(int w, int h, Color c) {
            BufferedImage bi = new BufferedImage(
                    w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.setPaint(c);
            g2d.fillRect(0, 0, w, h);
            g2d.dispose();
            return bi;
        }
    }
}


