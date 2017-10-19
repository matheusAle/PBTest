package controller.exceptions;

/**
 * Classe que modela uma exeção de casos de Uso.
 */
public class CasoDeUsoExeption extends RuntimeException {
    public CasoDeUsoExeption(String s) {
        super(s);
    }
}
