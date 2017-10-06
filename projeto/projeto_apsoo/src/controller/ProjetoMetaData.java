package controller;

/**
 * Classe de transporte de dados referente aos projetos
 */
public class ProjetoMetaData {

    private String codigo;
    private String nome;
    private String descricao;
    private String src;

    //TODO terminar a implementação!


    public ProjetoMetaData(String codigo, String nome, String descricao, String src) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.src = src;
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

    public String getSrc() {
        return src;
    }
}
