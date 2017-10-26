package model;

import java.util.LinkedList;

/**
 * Classe que modela um artefato de teste
 */
public class ArtefatoDeTeste implements Comparable<ArtefatoDeTeste>{
    private String pakage;
    private String nomeArquivo;
    private LinkedList<CasoDeTeste> casosDeTeste;
    private String projetoId;

    public ArtefatoDeTeste(String pakage, String nomeArquivo, String prjId) {
        this.pakage = pakage;
        this.nomeArquivo = nomeArquivo;
        this.projetoId = prjId;
    }

    /**
     * Retorna o caminho do pacote no qual o artefato de teste encotra-se no diretorio do projeto
     * @return
     */
    public String getPakage() {
        return pakage;
    }

    /**
     * retorna o nome do arquivo do artefato de teste.
     * @return
     */
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    /**
     * Retorna o caminho do arquivo dentro do diretorio do projeto.
     * @return
     */
    public String getCaminhoRelativoAoProjeto() {
        return pakage.concat("\\").concat(nomeArquivo);
    }

    /**
     * Retorna uma lista de casos de teste
     * @return
     */
    public LinkedList<CasoDeTeste> getCasosDeTeste() {
        return casosDeTeste;
    }

    /**
     * Vincula a este artefato de teste o caso de teste passado como paramentro
     * @param casoDeTeste
     */
    public void addCasoDeTeste(CasoDeTeste casoDeTeste){
        casosDeTeste.add(casoDeTeste);
        casoDeTeste.setArtefatoDeTeste(this);

    }

    public void setCasosDeTeste(LinkedList<CasoDeTeste> casosDeTeste) {
        this.casosDeTeste = casosDeTeste;
    }

    @Override
    public int compareTo(ArtefatoDeTeste o) {
        return getPakage().compareTo(o.getPakage());
    }

    public String getProjetoId() {
        return projetoId;
    }


    @Override
    public String toString() {
        return "ArtefatoDeTeste{" +
                "pakage='" + pakage + '\'' +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", casosDeTeste=" + casosDeTeste +
                ", projetoId='" + projetoId + '\'' +
                '}';
    }
}
