package view;

import controller.CasoDeTesteController;
import controller.adapters.ArtefatoDeTesteAdapter;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Classe responsavel pelo view da aba casos de testes
 */
public class CasosDeTestePainel extends Painel{

    private JSplitPane painelDividido;
    private JPanel painelArtefatos;
    private JPanel painelCasosDeTeste;
    private ArvoreDeArtefatos arvoreDeArtefatos;

    public CasosDeTestePainel(){
        super(Strings.TITULO_PAINEL_CASOS_DE_TESTE);
    }

    /**
     * Carrega os artefatos de teste do projeto ativo na JTree
     */
    public void iniciarArvore() {
        super.limparConteudo();
        carregarPaineis();
        carregarArtefatosDeTeste();
        arvoreDeArtefatos.setOnSelectedEvent((e) -> {
            if (e.getArtefato() != null)
                this.carregarCasosDeTesteDoArtefato(e.getArtefato());
        });

        painelDividido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new MeuPainelComScrollBar(arvoreDeArtefatos),
                new MeuPainelComScrollBar(painelCasosDeTeste)
        ) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension((getParent().getPreferredSize().width), 550);
            }
        };

        painelDividido.setBorder(new EmptyBorder(1,1,1,1));
        BasicSplitPaneDivider dividerContainer = (BasicSplitPaneDivider) painelDividido.getComponent(2);
        dividerContainer.setBorder(new EmptyBorder(1,1,1,1));

        super.addConteudo(painelDividido);
    }

    /**
     * Configura os paineis desta area de trabalho
     */
    private void carregarPaineis() {
        painelArtefatos = new JPanel(new BorderLayout(5, 5));
        painelCasosDeTeste = new JPanel(new BorderLayout(5, 5));
        painelCasosDeTeste.setBorder(new EmptyBorder(1, 1,1 ,1));
        painelArtefatos.setBorder(new EmptyBorder(1, 1,1 ,1));

    }

    /**
     * Carrega os artefatos mapeados em <link>CasoDeTesteController</link> na Jtree.
     */
    private void carregarArtefatosDeTeste() {

        HashMap<String, LinkedList<ArtefatoDeTesteAdapter>> mapaDeArtefatos = CasoDeTesteController.getMapaDeArtefatos();
        Set<String> chaves = mapaDeArtefatos.keySet();
        ArvoreNode root = new ArvoreNode("src", null);

        for (String chave : chaves){
            if (chave.equals("")){
                for (ArtefatoDeTesteAdapter e : mapaDeArtefatos.get(chave)){
                    root.add(new ArvoreNode(e.getNomeArquivo(), e));
                }
            }else {
                DefaultMutableTreeNode galho = new ArvoreNode(chave, null);
                for (ArtefatoDeTesteAdapter e : mapaDeArtefatos.get(chave)){
                    galho.add(new ArvoreNode(e.getNomeArquivo(), e));
                }
                root.add(galho);
            }
        }
        arvoreDeArtefatos = new ArvoreDeArtefatos(root);
    }


    /**
     * Carrega os casos de teste asociados a o arefato no painel direito.
     * @param artefato artefato que reta os casos de teste carregados.
     */
    private void carregarCasosDeTesteDoArtefato(ArtefatoDeTesteAdapter artefato){

    }


    private class CasoDeTestePainelItem extends PainelItem {

        CasoDeTestePainelItem(){

        }
    }

}
