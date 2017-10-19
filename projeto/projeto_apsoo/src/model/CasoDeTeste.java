package model;


import controller.adapters.CasosDeTesteAdapter;

import java.io.File;

/**
 * Classe que modela um caso de teste do sitema
 */
public class CasoDeTeste {
    private String codigo;
    private String nome;
    private String descricao;
    private String codigoCasoDeUso;
    private String resultado;
    private File classeDeTeste;
    private ArtefatoDeTeste artefatoDeTeste;
    private String emailUsuario;

    public CasoDeTeste(String codigo, String nome, String descricao, String codigoCasoDeUso, String resultado, String emailUsuario, String src) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.codigoCasoDeUso = codigoCasoDeUso;
        this.resultado = resultado;
        this.emailUsuario = emailUsuario;
        this.classeDeTeste = new File(src);
    }

    /**
     * Vincula a este caso de teste um artefato de teste
     * @param artefatoDeTeste
     */
    void setArtefatoDeTeste(ArtefatoDeTeste artefatoDeTeste) {
        this.artefatoDeTeste = artefatoDeTeste;
    }

    public CasosDeTesteAdapter getAdapter(){
        return new CasosDeTesteAdapter(codigo, nome, descricao);
    }

}
