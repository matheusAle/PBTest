package controller.adapters;

public class CasosDeTesteAdapter {

    private String codigo;
    private String nome;
    private String descricao;

    public CasosDeTesteAdapter(String codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
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
}
