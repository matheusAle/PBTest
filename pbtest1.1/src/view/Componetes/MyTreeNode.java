/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import model.ArtefatoDeTeste;

/**
 *
 * @author matheus
 */
public class MyTreeNode extends DefaultMutableTreeNode{
    
    private ArtefatoDeTeste artefato;
    
    public MyTreeNode(Object root, ArtefatoDeTeste artefato) {
        super(root);
        this.artefato = artefato;
        
    }

    public MyTreeNode(TreeNode root) {
        super(root);
    }

    MyTreeNode() {
    }

  
    
    public ArtefatoDeTeste getArtefato() {
        return artefato;
    }
    
    
    
    
    
}
