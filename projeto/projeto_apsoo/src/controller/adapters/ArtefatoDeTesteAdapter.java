package controller.adapters;

public class ArtefatoDeTesteAdapter {
    private String pakage;
    private String nomeArquivo;
    private String caminhoAbsoluto;


    public ArtefatoDeTesteAdapter(String pakage, String nomeArquivo, String caminhoabsoluto) {
        this.pakage = pakage;
        this.nomeArquivo = nomeArquivo;
        this.caminhoAbsoluto = caminhoabsoluto;
    }

    public String getPakage() {
        return pakage;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getCaminhoAbsoluto() {
        return caminhoAbsoluto;
    }

    @Override
    public String toString() {
        return "ArtefatoDeTesteAdapter{" +
                "pakage='" + pakage + '\'' +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", caminhoAbsoluto='" + caminhoAbsoluto + '\'' +
                '}';
    }
}
