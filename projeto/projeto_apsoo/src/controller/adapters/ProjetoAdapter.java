package controller;

/**
 * Classe de transporte de dados referente aos projetos
 */
public class ProjetoAdapter {

    private String codigo;
    private String nome;
    private String descricao;
    private String src;
    private String prefixoCU;
    private String prefixoCT;
    private String prefixoRT;

    public ProjetoAdapter(String codigo, String nome, String descricao, String src) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.src = src.replaceAll("/", "\\\\");
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

    public void setSrc(String src) {
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





    public String getPrefixoCU() {
        return prefixoCU;
    }

    public void setPrefixoCU(String prefixoCU) {
        this.prefixoCU = prefixoCU;
    }

    public String getPrefixoCT() {
        return prefixoCT;
    }

    public void setPrefixoCT(String prefixoCT) {
        this.prefixoCT = prefixoCT;
    }

    public String getPrefixoRT() {
        return prefixoRT;
    }

    public void setPrefixoRT(String prefixoRT) {
        this.prefixoRT = prefixoRT;
    }
}
