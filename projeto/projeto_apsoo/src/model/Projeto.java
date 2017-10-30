package model;

import controller.Utils;

/**
 * Classe que modela a entidade Projeto dentro do sistema
 */
public class Projeto {

    private String codigo;
    private String nome;
    private String descricao;
    private String srcProducao;
    private String srcTestes;
    private String prefixoCT;
    private String prefixoRT;
    private String prefixoCU;

    public Projeto (String codigo, String nome, String descricao, String srcProducao,String srcTestes, String prefixoCT, String prefixoRT, String prefixoCU) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.srcProducao = Utils.srcToObject(srcProducao);
        this.srcTestes= Utils.srcToObject(srcTestes);
        this.prefixoCT = prefixoCT;
        this.prefixoRT = prefixoRT;
        this.prefixoCU = prefixoCU;
    }


    public Projeto(String id, String nome, String descricao, String srcProducao) {
        this.codigo = id;
        this.nome = nome;
        this.descricao = descricao;
        this.srcProducao = Utils.srcToObject(srcProducao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSrcProducao() {
        return srcProducao;
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
                ", srcProducao='" + srcProducao + '\'' +
                ", prefixoCT='" + prefixoCT + '\'' +
                ", prefixoRT='" + prefixoRT + '\'' +
                ", prefixoCU='" + prefixoCU + '\'' +
                '}';
    }

    public String getSrcTestes() {
        return srcTestes;
    }

    public void setSrcTestes(String srcTestes) {
        this.srcTestes = srcTestes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSrcProducao(String srcProducao) {
        this.srcProducao = srcProducao;
    }

    public void setPrefixoCT(String prefixoCT) {
        this.prefixoCT = prefixoCT;
    }

    public void setPrefixoRT(String prefixoRT) {
        this.prefixoRT = prefixoRT;
    }

    public void setPrefixoCU(String prefixoCU) {
        this.prefixoCU = prefixoCU;
    }
}
