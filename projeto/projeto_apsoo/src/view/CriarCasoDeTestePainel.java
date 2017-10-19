package view;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import resources.Cores;
import resources.Fontes;
import view.Componetes.*;

import javax.swing.*;
import java.awt.*;

public class CriarCasoDeTestePainel extends Painel {

    private MeuCampoDeTexto CampoNome;
    private MeuLabel campoCodigo;
    private javax.swing.JTextArea campoDescicao;
    private MeuPainelComScrollBar jScrollPane1;
    private RTextScrollPane editorDeCodigo;
    private MeuLabel labelCodigo;
    private MeuLabel labelNome;
    private MeuLabel labeldescricao;
    private javax.swing.JPanel painelLegenda;


    public CriarCasoDeTestePainel() {
        super("aaa");
        super.setAlturaELarguraDosItensDoConteudo(425, 600);
        super.addConteudo(new Formulario());
    }


    protected class Formulario extends JPanel {


        Formulario() {
            iniciarComponetes();
            carregarLayout();
            carregarEstilo();
        }

        private void carregarLayout() {
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(34, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelNome)
                                            .addComponent(labeldescricao)
                                            .addComponent(labelCodigo))
                                    .addContainerGap())
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelNome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelCodigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labeldescricao)
                                    .addContainerGap(89, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(CampoNome)
                                            .addComponent(campoCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE))
                                    .addGap(0, 18, Short.MAX_VALUE))
                            .addComponent(editorDeCodigo)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(campoCodigo)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(editorDeCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
        }

        private void iniciarComponetes() {
            painelLegenda = new javax.swing.JPanel();
            labelCodigo = new MeuLabel();
            labelNome = new MeuLabel();
            labeldescricao = new MeuLabel();
            CampoNome = new MeuCampoDeTexto();
            campoCodigo = new MeuLabel();
            campoDescicao = new javax.swing.JTextArea();
            jScrollPane1 = new MeuPainelComScrollBar(campoDescicao);

            RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            textArea.setCodeFoldingEnabled(true);
            editorDeCodigo = new RTextScrollPane(textArea);

            labelCodigo.setText("NOME:");

            labelNome.setText("CODIGO:");

            labeldescricao.setText("DESCRIÇÂO:");

        }

        private void carregarEstilo() {
            super.setBackground(Cores.TEXTO_MENU_ESQUERDO);
            painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            editorDeCodigo.getVerticalScrollBar().setUI(new MeuScrollbarUI());
            editorDeCodigo.getHorizontalScrollBar().setUI(new MeuScrollbarUI());

            for (Component c : painelLegenda.getComponents()){
                c.setFont(Fontes.LEGENDA_ITEM_LISTA);
                c.setForeground(Cores.TEXTOS);
            }
            editorDeCodigo.setEnabled(true);
        }
    }


}