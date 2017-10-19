package controller.exceptions;

/**
 * Classe que modela uma exeção dos casos de teste
 */
public class CasoDeTesteExeption extends RuntimeException {
    public CasoDeTesteExeption(String s) {
        super(s);
    }
}
