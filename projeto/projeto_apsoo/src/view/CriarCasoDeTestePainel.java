package view;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CriarCasoDeTestePainel extends Painel {

    private MeuCampoDeTexto CampoNome;
    private javax.swing.JTextArea campoDescricao;
    private MeuPainelComScrollBar jScrollPane1;
    private RTextScrollPane editorDeCodigo;
    private MeuLabel labelNome;
    private MeuLabel labeldescricao;
    private javax.swing.JPanel painelLegenda;


    public CriarCasoDeTestePainel() {
        super(Strings.TITULO_PAINEL_CRIAR_CASOS_DE_TESTE);
        super.setAlturaELarguraDosItensDoConteudo(380, 600);
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
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(34, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelNome, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labeldescricao, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addContainerGap())
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelNome)
                                    .addGap(18, 18, 18)
                                    .addComponent(labeldescricao)
                                    .addContainerGap(113, Short.MAX_VALUE))
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
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE))
                                    .addGap(0, 18, Short.MAX_VALUE))
                            .addComponent(editorDeCodigo)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jScrollPane1)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(editorDeCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
        }

        private void iniciarComponetes() {
            painelLegenda = new javax.swing.JPanel();
            labelNome = new MeuLabel();
            labeldescricao = new MeuLabel();
            CampoNome = new MeuCampoDeTexto();
            campoDescricao = new javax.swing.JTextArea();
            jScrollPane1 = new MeuPainelComScrollBar(campoDescricao);

            RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            textArea.setCodeFoldingEnabled(true);
            editorDeCodigo = new RTextScrollPane(textArea);

            labelNome.setText("NOME:");
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

            campoDescricao.setBorder(new EmptyBorder(10,10,10,10));
            campoDescricao.setWrapStyleWord(true);
            campoDescricao.setLineWrap(true);
            campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO.deriveFont(16f));
        }
    }


}