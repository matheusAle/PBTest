package view;

import controller.ProjetoController;
import controller.ProjetoMetaData;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.Painel;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsavel pelo view da aba Projetos
 */
public class Painel_Projetos {

    private Painel painel;
    private ItemListaProjeto projetoAtivo;
    private PainelProjetoAtivo painelProjetoAtivo;
    private Dimension tamanhoItemLista = new Dimension(900, 120);

    public Painel_Projetos (){
        painel = new Painel(Strings.TITULO_PAINEL_PROJETOS);
        painel.setAlturaELarguraDosItensDoConteudo(tamanhoItemLista.height, tamanhoItemLista.width);
        carregarProjetos();
    }

    public void carregarProjetos (){
        ProjetoController.listaDeprojetos.forEach(projetoMetaData -> {
            painel.addConteudo(new ItemListaProjeto(projetoMetaData));
        });
    }
    public JScrollPane getPainel(){
        return painel.getPainel();
    }


    /**
     * classe que modela os itens da lista de projetos cadastrados
     */
    private class ItemListaProjeto extends JPanel{

        ItemListaProjeto(ProjetoMetaData projeto){
            super.setLayout(new BorderLayout(0, 0));
            super.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            super.setPreferredSize(tamanhoItemLista);
            super.add(gemPainelLegenda(), BorderLayout.WEST);

            JPanel painelInfo = new JPanel(new GridLayout(4, 1, 0, 0));
            painelInfo.setBackground(Cores._9fb3a5);
            painelInfo.add(new JLabel("  "+ projeto.getNome()));
            painelInfo.add(new JLabel("  "+ projeto.getCodigo()));
            painelInfo.add(new JLabel("  "+ projeto.getSrc()));
            painelInfo.add(new JLabel("  "+ projeto.getDescricao()));
            for (Component c : painelInfo.getComponents()){
                c.setForeground(Cores._ffffff);
                c.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
            }
            super.add(painelInfo, BorderLayout.CENTER);
        }

        private JPanel gemPainelLegenda() {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5,10));
            panel.setBackground(Cores._232b2d);
            panel.setPreferredSize(new Dimension(100, tamanhoItemLista.height));
            panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(new JLabel(Strings.LG_PROJETO_NOME), BorderLayout.AFTER_LAST_LINE);
            panel.add(new JLabel(Strings.LG_PROJETO_CODIGO), BorderLayout.AFTER_LAST_LINE);
            panel.add(new JLabel(Strings.LG_PROJETO_SRC), BorderLayout.AFTER_LAST_LINE);
            panel.add(new JLabel(Strings.LG_PROJETO_DESCRI), BorderLayout.AFTER_LAST_LINE);
            for (Component c : panel.getComponents()){
                c.setForeground(Cores._ffffff);
                c.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
            }
            return panel;
        }

    }

    private class PainelProjetoAtivo {

    }
}
