package view;

import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.MeuCampoDeTexto;
import view.Componetes.MeuScrollPainel;
import view.Componetes.Meu_Botao;
import view.Componetes.Painel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CriarProjetoPainel {

    private Painel painel;

    private Meu_Botao btmEscolherDiretorio;
    private javax.swing.JTextArea campoDescricao;
    private MeuCampoDeTexto campoNome;
    private MeuCampoDeTexto campoPrefixoCT;
    private MeuCampoDeTexto campoPrefixoCU;
    private MeuCampoDeTexto campoPrefixoRT;
    private MeuScrollPainel jScrollPane1;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPrefixo;
    private javax.swing.JLabel lavelSrc;
    private javax.swing.JPanel painelLegenda;
    private javax.swing.JLabel srcDiretorio;

    public CriarProjetoPainel (){
        iniciarPainel();
    }

    private void iniciarPainel(){
        painel = new Painel("Cadastrar Projeto");
        painel.setAlturaELarguraDosItensDoConteudo(1, 1);
        painel.addConteudo(new Formulario());
    }

    public JScrollPane getPainel(){
        return painel.getPainel();
    }

    private class Formulario extends JPanel{

        Formulario (){
            iniciarComponentes();
            iniciarLayout();
            iniciarEstilo();
        }

        private void iniciarComponentes (){
            painelLegenda = new javax.swing.JPanel();

            labelNome = new javax.swing.JLabel();
            labelPrefixo = new javax.swing.JLabel();
            lavelSrc = new javax.swing.JLabel();
            labelDescricao = new javax.swing.JLabel();
            srcDiretorio = new javax.swing.JLabel();

            campoNome = new MeuCampoDeTexto();
            campoPrefixoCU = new MeuCampoDeTexto();
            campoPrefixoRT = new MeuCampoDeTexto();
            btmEscolherDiretorio = new Meu_Botao();
            campoDescricao = new javax.swing.JTextArea();
            jScrollPane1 = new MeuScrollPainel(campoDescricao);
            campoPrefixoCT = new MeuCampoDeTexto();

            setPreferredSize(new java.awt.Dimension(950, 500));

            labelNome.setText(Strings.LG_CRIAR_PROJ_NOME);
            labelPrefixo.setText(Strings.LG_CRIAR_PROJ_PROFIXOS);
            lavelSrc.setText(Strings.LG_CRIAR_PROJ_DIRETORIO_RAIZ);
            labelDescricao.setText(Strings.LG_CRIAR_PROJ_DESCRICAO);
            btmEscolherDiretorio.setText("Escolher Diretorio");
            srcDiretorio.setText("Escolha um diretorio!");

        }

        private void iniciarLayout(){
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(37, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelDescricao)
                                            .addComponent(lavelSrc)
                                            .addComponent(labelPrefixo)
                                            .addComponent(labelNome))
                                    .addGap(30, 30, 30))
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(labelNome)
                                    .addGap(28, 28, 28)
                                    .addComponent(labelPrefixo)
                                    .addGap(30, 30, 30)
                                    .addComponent(lavelSrc)
                                    .addGap(41, 41, 41)
                                    .addComponent(labelDescricao)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoNome)
                                            .addComponent(jScrollPane1)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(srcDiretorio)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                            .addComponent(btmEscolherDiretorio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                                            .addComponent(campoPrefixoCU, javax.swing.GroupLayout.Alignment.LEADING))
                                                                    .addGap(30, 30, 30)
                                                                    .addComponent(campoPrefixoRT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(30, 30, 30)
                                                                    .addComponent(campoPrefixoCT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(27, 27, 27)
                                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(23, 23, 23)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(campoPrefixoCU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(campoPrefixoRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(campoPrefixoCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btmEscolherDiretorio)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(srcDiretorio)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(98, 98, 98)))
                                    .addContainerGap())
            );


        }

        private void iniciarEstilo (){
            this.setBackground(Cores.TEXTO_MENU_ESQUERDO);
            painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            for (Component c : painelLegenda.getComponents()){
                c.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
                c.setForeground(Cores.TEXTOS);
            }
            lavelSrc.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
            lavelSrc.setForeground(Cores.TEXTOS);

            campoDescricao.setBorder(new EmptyBorder(10,10,10,10));
            campoDescricao.setWrapStyleWord(true);
            campoDescricao.setLineWrap(true);
            campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO);


            btmEscolherDiretorio.setCorDeFundoNormal(Cores.FUNDO_BOTOAO);
            btmEscolherDiretorio.setCorDeFundoHover(Color.red);
            btmEscolherDiretorio.setCorDoTextoNormal(Cores.TEXTOS);
            btmEscolherDiretorio.setCorDoTextoHover(Cores.TEXTOS);


        }
    }
}