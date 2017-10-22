package model;

/**
 * Classe que modela a entidade Projeto dentro do sistema
 */
public class Projeto {

    private String codigo;
    private String nome;
    private String descricao;
    private String src;
    private String prefixoCT;
    private String prefixoRT;
    private String prefixoCU;

    public Projeto (String codigo, String nome, String descricao,String src, String prefixoCT, String prefixoRT, String prefixoCU) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.src = src.replaceAll("/", "\\\\");
        this.prefixoCT = prefixoCT;
        this.prefixoRT = prefixoRT;
        this.prefixoCU = prefixoCU;
    }


    public Projeto(String id, String nome, String descricao, String srcRaiz) {
        this.codigo = id;
        this.nome = nome;
        this.descricao = descricao;
        this.src = srcRaiz.replaceAll("/", "\\\\");
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

    public String getCodigo() {
        return codigo;
    }

    public String getPrefixoCT() {
        return prefixoCT;
    }

    public String getPrefixoRT() {
        return prefixoRT;
    }

    public String getPrefixoCU() {
        return prefixoCU;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", src='" + src + '\'' +
                ", prefixoCT='" + prefixoCT + '\'' +
                ", prefixoRT='" + prefixoRT + '\'' +
                ", prefixoCU='" + prefixoCU + '\'' +
                '}';
    }
}
