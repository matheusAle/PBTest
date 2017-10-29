package resources;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class Arquivos {
    public static final Icon IMG_PERFIL_PADRAO = new ImageIcon(Arquivos.class.getResource("icones/imgPerfilGenerico.jpg"));
    public static final Icon ICONE_PASTA = new ImageIcon(Arquivos.class.getResource("icones/icone-pasta.png"));
    public static final Icon ICONE_PONTO_JAVA = new ImageIcon(Arquivos.class.getResource("icones/icone-dot-java.png"));
    public static final Icon ICONE_PASTA_OPEN = new ImageIcon(Arquivos.class.getResource("icones/icone-pasta-open.png"));
    public static Properties PROPERTIES_PADRAO;

    static {
        try {
            PROPERTIES_PADRAO = new Properties();
            PROPERTIES_PADRAO.load(new FileReader(new File(Arquivos.class.getResource("prop_pbtest.properties").getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
