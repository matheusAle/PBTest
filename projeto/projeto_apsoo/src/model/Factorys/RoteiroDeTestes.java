package model.Factorys;

public class RoteiroDeTestes {
    private String codigo;
    private String nome;
    private String descricao;
    private int situacao;
    private String projetoID;
    private String emailUsuario;

    public RoteiroDeTestes(String codigo, String nome, String descricao, int situacao, String projetoID, String emailUsuario) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.situacao = situacao;
        this.projetoID = projetoID;
        this.emailUsuario = emailUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getSituacao() {
        return situacao;
    }

    public String getProjetoID() {
        return projetoID;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }
}
