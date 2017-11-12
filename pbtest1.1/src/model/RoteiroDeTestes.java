package model;

import model.CasoDeTeste;

import java.util.LinkedList;

public class RoteiroDeTestes {
    private String codigo;
    private String nome;
    private String descricao;
    private int situacao;
    private String projetoID;
    private String emailUsuario;
    private LinkedList<CasoDeTeste> casosDeTeste;
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

    public LinkedList<CasoDeTeste> getCasosDeTeste() {
        return casosDeTeste;
    }

    public void setCasosDeTeste(LinkedList<CasoDeTeste> casosDeTeste) {
        this.casosDeTeste = casosDeTeste;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
