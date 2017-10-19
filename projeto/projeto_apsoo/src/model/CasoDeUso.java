package model;

public class CasoDeUso {

    private String codigo;
    private String nome;
    private String objetovo;
    private String atores;
    private String descricao;
    private String emailUsuario;
    private String projetoID;
    public CasoDeUso(String codigo, String nome, String objetovo, String atores, String descricao, String emailUsuario, String pjID) {
        this.codigo = codigo;
        this.nome = nome;
        this.objetovo = objetovo;
        this.atores = atores;
        this.descricao = descricao;
        this.emailUsuario = emailUsuario;
        projetoID = pjID;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getObjetivo() {
        return objetovo;
    }

    public String getAtores() {
        return atores;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getProjetoID() {
        return projetoID;
    }
}
