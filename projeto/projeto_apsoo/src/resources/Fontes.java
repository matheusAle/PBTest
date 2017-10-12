package resources;

import sun.font.CreatedFontTracker;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Fontes {

    public static final Font NAV_LABEL = Arq_Fontes.BOLD.deriveFont(14f);
    public static final Font NOME_USUARIO = Arq_Fontes.BOLD_ITALIC.deriveFont(16f);
    public static final Font CARGO_USUARIO = Arq_Fontes.LIGHT_ITALIC.deriveFont(12f);
    public static final Font CARD_NOME_USUARIO = Arq_Fontes.BLACK.deriveFont(18f);
    public static final Font CARD_CARGO_USUARIO = Arq_Fontes.BLACK_ITALIC.deriveFont(14f);
    public static final Font PAINEL_TITULO = Arq_Fontes.LIGHT.deriveFont(24f);
    public static final Font LEGENDA_ITEM_LISTA = Arq_Fontes.BOLD.deriveFont(16f);
    public static final Font CAMPO_DE_TEXTO = Arq_Fontes.MEDIUM.deriveFont(14f);
    public static final Font TEXTO_BTN = Arq_Fontes.REGULAR.deriveFont(16f);

    private static class Arq_Fontes {

        public static final Font BLACK = loadFont("Roboto-Black.ttf").deriveFont(Font.BOLD);
        public static final Font BLACK_ITALIC = loadFont("Roboto-BlackItalic.ttf").deriveFont(Font.BOLD | Font.ITALIC);
        public static final Font BOLD = loadFont("Roboto-Bold.ttf").deriveFont(Font.BOLD);
        public static final Font BOLD_ITALIC = loadFont("Roboto-BoldItalic.ttf").deriveFont(Font.BOLD | Font.ITALIC);
        public static final Font ITALIC = loadFont("Roboto-Italic.ttf").deriveFont(Font.ITALIC);
        public static final Font LIGHT = loadFont("Roboto-Light.ttf").deriveFont(Font.PLAIN);
        public static final Font LIGHT_ITALIC = loadFont("Roboto-LightItalic.ttf").deriveFont(Font.ITALIC);
        public static final Font MEDIUM = loadFont("Roboto-Medium.ttf").deriveFont(Font.PLAIN);
        public static final Font MEDIUM_ITALIC = loadFont("Roboto-MediumItalic.ttf").deriveFont(Font.ITALIC);
        public static final Font REGULAR = loadFont("Roboto-Regular.ttf").deriveFont(Font.PLAIN);
        public static final Font THIN = loadFont("Roboto-Thin.ttf").deriveFont(Font.PLAIN);
        public static final Font THIN_ITALIC = loadFont("Roboto-ThinItalic.ttf").deriveFont(Font.ITALIC);

        private static Font loadFont(String resourceName) {
            try (InputStream inputStream = Fontes.class.getResourceAsStream("fontes/" + resourceName)) {
                return Font.createFont(Font.TRUETYPE_FONT, inputStream);
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException("Could not load " + resourceName, e);
            }
        }
    }
}
