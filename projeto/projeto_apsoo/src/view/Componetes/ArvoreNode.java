package view.Componetes;

import controller.adapters.ArtefatoDeTesteAdapter;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Modela um Node da Jtree que permide anexar um objeto <link>ArtefatoDeTesteAdapter</link> a ele
 * para ser faciumente recuperado.
 */
public class ArvoreNode extends DefaultMutableTreeNode{

    private ArtefatoDeTesteAdapter artefato;

    public ArvoreNode(Object userObject, ArtefatoDeTesteAdapter artefato) {
        super(userObject);
        this.artefato = artefato;
    }

    /**
     * @return Retorna o artefato asociado a o objeto deste node.
     */
    public ArtefatoDeTesteAdapter getArtefato(){
        return artefato;
    }
}
