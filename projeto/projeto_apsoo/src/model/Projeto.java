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
        this.src = src.replaceAll("/", "\\\\");
        this.PREFIXOS = new Prefixos();
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

    public String getCodigo() {
        return codigo;
    }
    public String getPrefixoCU() {
        return PREFIXOS.CASO_DE_USO.getPrefixo();
    }

    public String getPrefixoCT() {
        return PREFIXOS.CASO_DE_TESTE.getPrefixo();
    }

    public String getPrefixoRT() {
        return PREFIXOS.ROTEIRO_DE_TESTE.getPrefixo();
    }

    /**
     * Gera um prefixo do tipo solicitado.
     * @param tipo tipo de prefixo. Valores possiveis: caso de uso, caso de teste, roteiro de teste;
     * @return retorna umas string com o prefixo.
     */
    public String getPrefixo(String tipo){
        switch (tipo){
            case "caso de uso":
                return PREFIXOS.CASO_DE_USO.getProximo();
            case "caso de teste":
                return PREFIXOS.CASO_DE_TESTE.getProximo();
            case "roteiro de teste":
                return PREFIXOS.ROTEIRO_DE_TESTE.getPrefixo();
        }
        return null;
    }

    /**
     * Retorna apenas o valor de contagem do prefixo.
     * @param tipo tipo de prefixo que reta o valor retornado. Valores possiveis: caso de uso, caso de teste, roteiro de teste;
     * @return inteiro representado o contador do tipo solicitado.
     */
    public int getContador(String tipo){
        switch (tipo){
            case "caso de uso":
                return PREFIXOS.CASO_DE_USO.getContador();
            case "caso de teste":
                return PREFIXOS.CASO_DE_TESTE.getContador();
            case "roteiro de teste":
                return PREFIXOS.ROTEIRO_DE_TESTE.getContador();
        }
        return -1;
    }

   /**
     * Classe que mantem referencias para os profixos do projeto.
     */
    private class Prefixos {
        private Prefixo CASO_DE_USO;
        private Prefixo CASO_DE_TESTE;
        private Prefixo ROTEIRO_DE_TESTE;
    }


    /**
     * Classe que modela um prefixo de projeto.
     */
    private class Prefixo {
        private int contador;
        private String prefixo;

        Prefixo(String prefixo, int contador) {
            this.prefixo = prefixo;
            this.contador = contador;
        }

        /**
         * @return retorna uma string que representa o proximo codigo do prefixo.
         */
        public String getProximo(){
            return prefixo + (contador++);
        }

        /**
         * @return retorna apenas o prefixo cadastrado pelo usuario.
         */
        public String getPrefixo() {
            return prefixo;
        }

        /**
         * @return Retorna o valor atual do contador do prefixo.
         */
        public int getContador() {
            return contador;
        }
    }

}
