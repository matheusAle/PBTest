package view;

import controller.CasoDeTesteController;
import controller.adapters.ArtefatoDeTesteAdapter;
import controller.exceptions.CasoDeTesteException;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.ArvoreDeArtefatos;
import view.Componetes.MeuLabel;
import view.Componetes.MeuPainelComScrollBar;
import view.Componetes.Painel;

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

    JSplitPane painelDividido;
    JPanel painelArtefatos;
    JPanel painelCasosDeTeste;
    ArvoreDeArtefatos arvoreDeArtefatos;

    public CasosDeTestePainel(){
        super(Strings.TITULO_PAINEL_CASOS_DE_TESTE);
        iniciarArvore();
        carregarPaineis();

        super.addConteudo(painelDividido);
        carregarArtefatosDeTeste();

    }

    private void iniciarArvore() {
        HashMap<Strings, DefaultMutableTreeNode> mapa = new HashMap<>();
        try {
            CasoDeTesteController.carregarArtefatos();
        } catch (CasoDeTesteException e) {
            e.printStackTrace();
        }
        HashMap<String, LinkedList<ArtefatoDeTesteAdapter>> mapaDeArtefatos = CasoDeTesteController.getMapaDeArtefatos();
        Set<String> chaves = mapaDeArtefatos.keySet();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("src");
        for (String chave : chaves){
            DefaultMutableTreeNode galho = new DefaultMutableTreeNode(chave);
            for (ArtefatoDeTesteAdapter e : mapaDeArtefatos.get(chave)){
                galho.add(new DefaultMutableTreeNode(e.getNomeArquivo()));
            }
            root.add(galho);
        }

        arvoreDeArtefatos = new ArvoreDeArtefatos(root);
    }

    /**
     * Configura os paineis desta area de trabalho
     */
    private void carregarPaineis() {
        painelArtefatos = new JPanel(new BorderLayout(5, 5));
        painelCasosDeTeste = new JPanel(new BorderLayout(5, 5));
        painelCasosDeTeste.setBorder(new EmptyBorder(1, 1,1 ,1));
        painelArtefatos.setBorder(new EmptyBorder(1, 1,1 ,1));

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
    }

    private void carregarArtefatosDeTeste() {

    }


}
