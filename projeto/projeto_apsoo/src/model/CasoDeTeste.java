package model;


import java.io.File;

/**
 * Classe que modela um caso de teste do sitema
 */
public class CasoDeTeste {
    private String codigo;
    private String nome;
    private String nomeClasseDeTeste;
    private String nomeClasseSendoTestada;
    private String descricao;
    private String projetoId;
    private String codigoCasoDeUso;
    private String resultado;
    private ArtefatoDeTeste artefatoDeTeste;
    private String emailUsuario;

    public CasoDeTeste(String codigo, String nome, String nomeClasseDeTeste, String nomeClasseSendoTestada, String descricao, String projetoId, String codigoCasoDeUso, String resultado, String emailUsuario) {
        this.codigo = codigo;
        this.nome = nome;
        this.nomeClasseDeTeste = nomeClasseDeTeste;
        this.nomeClasseSendoTestada = nomeClasseSendoTestada;
        this.descricao = descricao;
        this.projetoId = projetoId;
        this.codigoCasoDeUso = codigoCasoDeUso;
        this.resultado = resultado;
        this.emailUsuario = emailUsuario;
    }

    /**
     * Vincula a este caso de teste um artefato de teste
     *
     * @param artefatoDeTeste
     */
    void setArtefatoDeTeste(ArtefatoDeTeste artefatoDeTeste) {
        this.artefatoDeTeste = artefatoDeTeste;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeClasseDeTeste() {
        return nomeClasseDeTeste;
    }

    public String getNomeClasseSendoTestada() {
        return nomeClasseSendoTestada;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getProjetoId() {
        return projetoId;
    }

    public String getCodigoCasoDeUso() {
        return codigoCasoDeUso;
    }

    public String getResultado() {
        return resultado;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }
}
