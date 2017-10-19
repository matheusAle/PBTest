package view.Componetes;

import model.ArtefatoDeTeste;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Modela um Node da Jtree que permide anexar um objeto <link>ArtefatoDeTeste</link> a ele
 * para ser faciumente recuperado.
 */
public class ArvoreNode extends DefaultMutableTreeNode{

    private ArtefatoDeTeste artefato;

    public ArvoreNode(Object userObject, ArtefatoDeTeste artefato) {
        super(userObject);
        this.artefato = artefato;
    }

    /**
     * @return Retorna o artefato asociado a o objeto deste node.
     */
    public ArtefatoDeTeste getArtefato(){
        return artefato;
    }
}
