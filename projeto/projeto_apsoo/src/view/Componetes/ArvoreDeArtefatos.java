package view.Componetes;

import resources.Cores;
import resources.Fontes;
import resources.Icones;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.util.function.Consumer;

/**
 * Modela um Jtree personalizada
 */
public class ArvoreDeArtefatos extends JTree implements TreeSelectionListener{

    private Consumer<ArvoreNode> onSelectedEvent = (e) -> {};



    public ArvoreDeArtefatos(TreeNode root) {
        super(root);
        super.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        super.setBackground(Cores.FUNDO_MENU_ESQUERDO);
        super.setForeground(Cores.TEXTOS);
        super.setShowsRootHandles(true);
        DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();

        render.setLeafIcon(Icones.ICONE_PONTO_JAVA);
        render.setClosedIcon(Icones.ICONE_PASTA);
        render.setOpenIcon(Icones.ICONE_PASTA_OPEN);
        render.setFont(Fontes.NAV_LABEL);

        render.setBorder(new EmptyBorder(2,2,2,2));
        render.setBackgroundNonSelectionColor(Cores.FUNDO_MENU_ESQUERDO);
        render.setBackgroundSelectionColor(Cores.FUNDO_BOTAO);
        render.setTextNonSelectionColor(Cores.TEXTOS);
        render.setTextSelectionColor(Cores.TEXTOS);
        super.putClientProperty ("JTree.lineStyle", "None");
        render.setIcon(null);
        super.setCellRenderer(render);

        super.addTreeSelectionListener(this);
    }

    /**
     * Chama o metodo definido para tratar a seleção de um node da arvore,
     * passando como parametro o objeto ArvoreNode selecionado.
     * @param e
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        try {
            onSelectedEvent.accept(((ArvoreNode)super.getLastSelectedPathComponent()));
        }catch (NullPointerException x){}

    }


    /**
     * Seta o metodo chamado quando um node da arvore for selecionado.
     * @param onSelectedEvent
     */
    public void setOnSelectedEvent(Consumer<ArvoreNode> onSelectedEvent) {
        this.onSelectedEvent = onSelectedEvent;
    }
}

