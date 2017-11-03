package view;

import controller.RoteiroDeTesteController;
import controller.SistemaController;
import model.CasoDeTeste;
import model.RoteiroDeTestes;
import view.Componetes.MeuPainelComScrollBar;
import view.Componetes.Painel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe responsavel pelo view da aba matriz de rastreabilidade
 */
public class MatrizDeRastreabilidadePainel extends Painel {

    private JTable tabela;
    MeuPainelComScrollBar tabelaConteiner;

    public MatrizDeRastreabilidadePainel() {
        super("Matriz");
        super.setQuandoAtivo(e -> carregarMatriz());
    }

    public void carregarMatriz(){
        super.limparConteudo();
        RoteiroDeTestes roteiro = RoteiroDeTesteController.MATRIZ;
        HashMap<String, ListaEncadeada<CasoDeTeste>> mapa = new HashMap<>();
        for (CasoDeTeste ct : roteiro.getCasosDeTeste()){ // mapeia os casos de teste com seis artefatos
            String nomeArtefato = ct.getArtefatoDeTeste().getNomeArquivo();
            if (mapa.containsKey(nomeArtefato)) {
                mapa.put(nomeArtefato, mapa.get(nomeArtefato).add(ct));
            } else {
                mapa.put(nomeArtefato, new ListaEncadeada<CasoDeTeste>().add(ct));
            }
        }

        Object [][] data = new Object[mapa.size()][roteiro.getCasosDeTeste().size()+1];
        for (int i = 0; i < data.length; i++){ // carrega uma matriz com objetos vazios
            for (int x = 0; x < data[i].length; x++){
                data[i][x] = " ";
            }
        }

        String nomes[] = new String[mapa.keySet().size()];
        mapa.keySet().toArray(nomes); // cria um array com o nome dos artefatos

        String nomesColuna[] = new String[roteiro.getCasosDeTeste().size()+1];
        nomesColuna[0] = "Artefatos";
        for (int row = 0, col = 1; row < nomes.length; row++){
            LinkedList<CasoDeTeste> lista = mapa.get(nomes[row]).getLista();
            data[row][0] = nomes[row]; // a primeira coluna da linha é o nome do artefato
            for(int i = 1; i < col; data[row][i++] = " "); // preeenche as lacunas do
            for (CasoDeTeste ct : lista){
                nomesColuna[col] = ct.toString();
                data[row][col++] = ct.getResultado().contains("OK") ? "OK" : "ERR";
            }
        }
        tabela = new JTable(data, nomesColuna);
        tabelaConteiner =  new MeuPainelComScrollBar(tabela);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabelaConteiner.setPreferredSize(new Dimension(980, 525));
        super.addConteudo(tabelaConteiner);
    }

    class ListaEncadeada<T> {
        LinkedList<T> lista;

        ListaEncadeada(){
            lista = new LinkedList<T>();
        }

        public LinkedList<T> getLista() {
            return lista;
        }

        public ListaEncadeada<T> add(T a){
            lista.add(a);
            return this;
        }

        public int getSize(){
            return lista.size();
        }


    }
    public class Matriz extends javax.swing.JPanel {

        /**
         * Creates new form matriz
         */
        public Matriz() {
            initComponents();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabelaConteiner, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabelaConteiner, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
            );
        }
    }
}
