/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cards;

import controller.ProjetoController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;
import model.Projeto;
import view.Paineis;
import view.mainApp;

/**
 *
 * @author matheus
 */
public class ProjetoCard extends javax.swing.JPanel {
    
    private Projeto projeto;
    /**
     * Creates new form ProjetoCard
     */
    public ProjetoCard(Projeto p) {
        initComponents();
        projeto = p;
        nome.setText(p.getNome());
        codigo.setText(p.getCodigo());
        srcProd.setText(p.getSrcProducao());
        srcTest.setText(p.getSrcTestes());
        email.setText(p.getEmail());
        iniciarListaners();
    }
    
    private void iniciarListaners(){
        btnAtivar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProjetoController.ativarProjeto(projeto);
                mainApp.trocarDePainel(Paineis.PROJETOS, null);
            }
        });
        btnVerEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HashMap<String, Projeto> hashMap = new HashMap<String, Projeto>();
                hashMap.put("projeto", projeto);
                mainApp.trocarDePainel(Paineis.FORMULARIO_DE_CADASTRO_DE_PROJETO, hashMap);
            }
        });
        
        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Você está certo disso?", "tem certeza?", JOptionPane.YES_NO_OPTION);
                if (i == 0){
                    ProjetoController.deletarProjetoDeCodigo(projeto);
                    mainApp.trocarDePainel(Paineis.PROJETOS, null);
                }                
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @Override
   public void repaint(){
       try {
           if (super.getBackground().equals(new Color(251, 85, 85))){
                srcTest.setForeground(Color.BLACK);
                srcProd.setForeground(Color.BLACK);
                email.setForeground(Color.BLACK);
                btnAtivar.setForeground(Color.BLACK);
                btnDeletar.setForeground(Color.BLACK);
                btnVerEditar.setForeground(Color.BLACK);
            }
       }catch (NullPointerException e){}
       super.repaint();
   } 

   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nome = new javax.swing.JLabel();
        codigo = new javax.swing.JLabel();
        srcProd = new javax.swing.JLabel();
        srcTest = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        btnAtivar = new javax.swing.JButton();
        btnVerEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        setMaximumSize(getMinimumSize());
        setMinimumSize(new java.awt.Dimension(90, 135));

        nome.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        nome.setText("nome");
        nome.setToolTipText("nome do projeto");
        nome.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        codigo.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        codigo.setText("codigo");
        codigo.setToolTipText("codigo do projeto");
        codigo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        srcProd.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        srcProd.setForeground(new java.awt.Color(102, 102, 102));
        srcProd.setText("src produção");
        srcProd.setToolTipText("caminho para as classes de produção do projeto");
        srcProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        srcTest.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        srcTest.setForeground(new java.awt.Color(102, 102, 102));
        srcTest.setText("src testes");
        srcTest.setToolTipText("caminho para as calsses de teste");
        srcTest.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        email.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("e-mail");
        email.setToolTipText("e-mail do usuario que criou este projeto");
        email.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnAtivar.setBackground(new java.awt.Color(0, 0, 0));
        btnAtivar.setFont(new java.awt.Font("Ubuntu", 2, 14)); // NOI18N
        btnAtivar.setForeground(new java.awt.Color(153, 153, 153));
        btnAtivar.setText("ativar");
        btnAtivar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAtivar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtivar.setFocusPainted(false);
        btnAtivar.setFocusable(false);
        btnAtivar.setOpaque(false);

        btnVerEditar.setBackground(new java.awt.Color(0, 0, 0));
        btnVerEditar.setFont(new java.awt.Font("Ubuntu", 2, 14)); // NOI18N
        btnVerEditar.setForeground(new java.awt.Color(153, 153, 153));
        btnVerEditar.setText("ver/editar");
        btnVerEditar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnVerEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerEditar.setFocusPainted(false);
        btnVerEditar.setFocusable(false);
        btnVerEditar.setOpaque(false);

        btnDeletar.setBackground(new java.awt.Color(0, 0, 0));
        btnDeletar.setFont(new java.awt.Font("Ubuntu", 2, 14)); // NOI18N
        btnDeletar.setForeground(new java.awt.Color(153, 153, 153));
        btnDeletar.setText("deletar");
        btnDeletar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.setFocusPainted(false);
        btnDeletar.setFocusable(false);
        btnDeletar.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(srcProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(srcTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerEditar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAtivar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(nome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srcProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srcTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnAtivar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletar)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtivar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnVerEditar;
    private javax.swing.JLabel codigo;
    private javax.swing.JLabel email;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel srcProd;
    private javax.swing.JLabel srcTest;
    // End of variables declaration//GEN-END:variables
}
