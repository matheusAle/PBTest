package model;


/**
 * Classe que modela um caso de teste do sitema
 */
public class CasoDeTeste {
    private String codigo;
    private String nome;
    private String srcCasoDeTeste;
    private String srcArtefatoDeTeste;
    private String descricao;
    private String projetoId;
    private String codigoCasoDeUso;
    private String resultado;
    private ArtefatoDeTeste artefatoDeTeste;
    private String emailUsuario;

    public CasoDeTeste(String codigo, String nome, String srcCasoDeTeste, String srcArtefatoDeTeste, String descricao, String projetoId, String codigoCasoDeUso, String resultado, String emailUsuario) {
        this.codigo = codigo;
        this.nome = nome;
        this.srcCasoDeTeste = srcCasoDeTeste.replaceAll("/", "\\\\");
        this.srcArtefatoDeTeste = srcArtefatoDeTeste.replaceAll("/", "\\\\");;
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

    public String getSrcCasoDeTeste() {
        return srcCasoDeTeste;
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


    public ArtefatoDeTeste getArtefatoDeTeste() {
        return artefatoDeTeste;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    void setSrcCasoDeTeste(String srcCasoDeTeste) {
        this.srcCasoDeTeste = srcCasoDeTeste;
    }

    void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    void setCodigoCasoDeUso(String codigoCasoDeUso) {
        this.codigoCasoDeUso = codigoCasoDeUso;
    }

    void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
