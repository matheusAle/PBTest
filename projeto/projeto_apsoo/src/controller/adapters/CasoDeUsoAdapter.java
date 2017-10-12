package controller.adapters;

/**
 * Classe que modela um adaptador o objeto <link>CasoDeUso</link>.
 */
public class CasoDeUsoAdapter {

    private String codigo;
    private String nome;
    private String objetivo;
    private String atores;
    private String descricao;

    public CasoDeUsoAdapter(String codigo, String nome, String objetovo, String atores) {
        this.codigo = codigo;
        this.nome = nome;
        this.objetivo = objetovo;
        this.atores = atores;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getAtores() {
        return atores;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
