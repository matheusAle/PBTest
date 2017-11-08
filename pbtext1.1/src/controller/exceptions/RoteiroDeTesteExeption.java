package controller.exceptions;

/**
 * Classe que modela uma exeção dos roteiros de teste
 */
public class RoteiroDeTesteExeption extends RuntimeException {
    public RoteiroDeTesteExeption(String s) {
        super(s);
    }
}
