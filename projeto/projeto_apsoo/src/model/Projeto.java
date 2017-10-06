package model;

/**
 * Classe que modela a entidade Projeto dentro do sistema
 */
public class Projeto {

    private String nome;
    private String descricao;
    private String src;
    private Prefixos PREFIXOS;

    public Projeto(String nome, String descricao, String src, Prefixo CASO_DE_USO, Prefixo CASO_DE_TESTE, Prefixo ROTEIRO_DE_TESTE) {
        this.nome = nome;
        this.descricao = descricao;
        this.src = src;
        this.PREFIXOS.CASO_DE_USO = CASO_DE_USO;
        this.PREFIXOS.CASO_DE_TESTE = CASO_DE_TESTE;
        this.PREFIXOS.ROTEIRO_DE_TESTE = ROTEIRO_DE_TESTE;
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

    private class Prefixos {
        private Prefixo CASO_DE_USO;
        private Prefixo CASO_DE_TESTE;
        private Prefixo ROTEIRO_DE_TESTE;
    }

    private class Prefixo {
        int contador;
        String prefixo;
        Prefixo(String prefixo, int contador) {
            this.prefixo = prefixo;
        }

        public String getProximo(){
            return prefixo + contador++;
        }
    }

}
