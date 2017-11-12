package resources;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Arquivos {
    public static  Icon IMG_PERFIL_PADRAO;
    public static  Icon ICONE_PASTA = new ImageIcon(Arquivos.class.getResource("icones/icone-pasta.png").getFile().replace("%20", " "));
    public static  Icon ICONE_PONTO_JAVA = new ImageIcon(Arquivos.class.getResource("icones/icone-dot-java.png").getFile().replace("%20", " "));
    public static  Icon ICONE_PASTA_OPEN = new ImageIcon(Arquivos.class.getResource("icones/icone-pasta-open.png").getFile().replace("%20", " "));
    public static Properties PROPERTIES_PADRAO;
    public static Image ICONE_APP = Toolkit.getDefaultToolkit().getImage(Arquivos.class.getResource("icones/app.png").getFile().replace("%20", " "));;

    static {
        try {
            PROPERTIES_PADRAO = new Properties();
            PROPERTIES_PADRAO.load(new FileReader(new File(Arquivos.class.getResource("prop_pbtest.properties").getFile().replace("%20", " "))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
