package model;

/**
 * Classe que modela a entidade Projeto dentro do sistema
 */
public class Projeto {

    private String codigo;
    private String nome;
    private String descricao;
    private String src;
    private Prefixos PREFIXOS;

    public Projeto(
            String codigo,
            String nome,
            String descricao,
            String src,
            String CASO_DE_USO,
            String CASO_DE_TESTE,
            String ROTEIRO_DE_TESTE,
            int contadorCU,
            int contadorCT,
            int contadorRT
    ) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.src = src;
        this.PREFIXOS.CASO_DE_USO = new Prefixo(CASO_DE_USO, contadorCU);
        this.PREFIXOS.CASO_DE_TESTE = new Prefixo(CASO_DE_TESTE, contadorCT);
        this.PREFIXOS.ROTEIRO_DE_TESTE = new Prefixo(ROTEIRO_DE_TESTE, contadorRT);
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
