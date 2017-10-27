package view;

import controller.CasoDeTesteController;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Classe responsavel pelo view da aba casos de testes
 */
public class CasosDeTestePainel extends Painel{

    private JSplitPane painelDividido;
    private JPanel painelArtefatos;
    private PainelDeCasoDeTeste painelCasosDeTeste;
    private ArvoreDeArtefatos arvoreDeArtefatos;

    public CasosDeTestePainel(){
        super(Strings.TITULO_PAINEL_CASOS_DE_TESTE);
    }

    /**
     * Carrega os artefatos de teste do projeto ativo na JTree
     */
    public void iniciarArvore() {
        super.limparConteudo();
        carregarPaineis();
        carregarArtefatosDeTeste();
        arvoreDeArtefatos.setOnSelectedEvent((e) -> {
            if (e.getArtefato() != null)
                this.carregarCasosDeTesteDoArtefato(e.getArtefato());
        });
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
        super.addConteudo(painelDividido);
    }

    /**
     * Configura os paineis desta area de trabalho
     */
    private void carregarPaineis() {
        painelArtefatos = new JPanel(new BorderLayout(5, 5));
        painelCasosDeTeste = new PainelDeCasoDeTeste("Selecione um artefato");
    }

    /**
     * Carrega os artefatos mapeados em <link>CasoDeTesteController</link> na Jtree.
     */
    private void carregarArtefatosDeTeste() {
        HashMap<String, LinkedList<ArtefatoDeTeste>> mapaDeArtefatos = CasoDeTesteController.getMapaDeArtefatos();
        Set<String> chaves = mapaDeArtefatos.keySet();
        ArvoreNode root = new ArvoreNode("src", null);

        for (String chave : chaves){
            if (chave.equals("")){
                for (ArtefatoDeTeste e : mapaDeArtefatos.get(chave)){
                    root.add(new ArvoreNode(e.getNomeArquivo(), e));
                }
            }else {
                DefaultMutableTreeNode galho = new ArvoreNode(chave, null);
                for (ArtefatoDeTeste e : mapaDeArtefatos.get(chave)){
                    galho.add(new ArvoreNode(e.getNomeArquivo(), e));
                }
                root.add(galho);
            }
        }
        arvoreDeArtefatos = new ArvoreDeArtefatos(root);
    }

    /**
     * Carrega os casos de teste associados a o arefato no painel direito.
     * @param artefato artefato que tera os casos de teste carregados.
     */
    private void carregarCasosDeTesteDoArtefato(ArtefatoDeTeste artefato) {
        painelCasosDeTeste.setTitulo("Casos de teste Para: " + artefato.getNomeArquivo());
        painelCasosDeTeste.limparConteudo();
        CasoDeTesteController.carregarCasosDeTesteDoArtefato(artefato);
        artefato.getCasosDeTeste().forEach((e) -> {
            painelCasosDeTeste.addConteudo(new CasoDeTesteItem(e));
        });
        Botao novo = new Botao();
        novo.setText("NOVO");
        novo.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        novo.setCorDeFundoHover(Color.red);
        novo.setPreferredSize(new Dimension(90, 50));
        painelCasosDeTeste.addConteudo(novo);
        painelCasosDeTeste.repaint();
        novo.setOnMouseClick((e) -> {
            this.novoCasoDeTeste(artefato);
        });
    }

    /**
     * Abre uam jenela popup com um formulario para o cadastro de casos de testes
     * para o artefato selecionado.
     * @param artefato artefato selecionado
     */
    private void novoCasoDeTeste(ArtefatoDeTeste artefato){
        new CriarCasoDeTestePainelPopup().iniciarPopup(artefato);
    }

    private void deletarCasoDeTeste(CasoDeTeste casoDeTeste){

    }

    private void editarCasoDeTeste(CasoDeTeste casoDeTeste){
        new CriarCasoDeTestePainelPopup().iniciarPopup(casoDeTeste.getArtefatoDeTeste()).carregarCasoDeTeste(casoDeTeste);
    }

    /**
     * Modela um item da listagem de artefatos
     */
    private class CasoDeTesteItem extends PainelItem {
        private MeuLabel codigo;
        private javax.swing.JPanel jPanel1;
        private MeuLabel legendaCodigo;
        private MeuLabel legendaNome;
        private MeuLabel nome;
        private CasoDeTeste casoDeTeste;

        CasoDeTesteItem(CasoDeTeste casoDeTeste){
            this.casoDeTeste = casoDeTeste;
            super.setBorder(new EmptyBorder(0,0,0,10));
            carregarComponetes();
            carregarLayout();
            codigo.setText(casoDeTeste.getCodigo());
            nome.setText(casoDeTeste.getNome());

            jPanel1.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            legendaCodigo.setFont(Fontes.LEGENDA_ITEM_LISTA);
            legendaNome.setFont(Fontes.LEGENDA_ITEM_LISTA);
            legendaCodigo.setForeground(Cores.TEXTOS);
            legendaNome.setForeground(Cores.TEXTOS);

            codigo.setFont(Fontes.LEGENDA_ITEM_LISTA);
            nome.setFont(Fontes.LEGENDA_ITEM_LISTA);
            codigo.setForeground(Cores.TEXTOS);
            nome.setForeground(Cores.TEXTOS);
            super.setCorDeFundoNormal(Cores.TEXTO_MENU_ESQUERDO);
            super.setCorDeFundoHover(Cores.FUNDO_MENU_ESQUERDO);

            codigo.addMouseListener(this);
            nome.addMouseListener(this);
            jPanel1.addMouseListener(this);
            legendaCodigo.addMouseListener(this);
            legendaNome.addMouseListener(this);
            carregarListaners();

        }

        private void carregarListaners(){
            MeuMenuPopup meuMenuPopup = new MeuMenuPopup();
            MeuItemMenuPopup editar = new MeuItemMenuPopup("Editar");
            editar.setOnClick((e) -> {
                editarCasoDeTeste(casoDeTeste);
                meuMenuPopup.setVisible(false);
            });
            MeuItemMenuPopup deletar = new MeuItemMenuPopup("Deletar");
            deletar.setOnClick((e) -> {
                deletarCasoDeTeste(casoDeTeste);
                meuMenuPopup.setVisible(false);
            });
            meuMenuPopup.add(editar);
            meuMenuPopup.add(deletar);
            super.setOnMouseClick((e)-> {
                if (SwingUtilities.isRightMouseButton(e)){
                    meuMenuPopup.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        }

        /**
         * inicia os componentes.
         */
        private void carregarComponetes(){
            jPanel1 = new javax.swing.JPanel();
            legendaCodigo = new MeuLabel();
            legendaNome = new MeuLabel();

            codigo = new MeuLabel();
            nome = new MeuLabel();
            nome.setQuantidadeMaximaDeCaracteres(20);

            legendaCodigo.setText("CODIGO:");
            legendaNome.setText("NOME:");
        }

        private void carregarLayout(){
            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(legendaCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaNome, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(legendaCodigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaNome)
                                    .addContainerGap(19, Short.MAX_VALUE))
            );
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nome)
                                            .addComponent(codigo)))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(codigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(nome))
            );
        }

    }

    /**
     * modela o painel que contem a listagem dos casos de teste vinculado ao artefato selecionado
     * na arvore deartefatas.
     */
    class PainelDeCasoDeTeste extends JPanel {
        JLabel titulo;
        JPanel conteudo;
        PainelDeCasoDeTeste (String titulo){
            super(new BorderLayout(5, 5));
            super.setBorder(new EmptyBorder(0, 0,0 ,0));
            this.titulo = new JLabel(titulo);
            this.titulo.setFont(Fontes.PAINEL_TITULO.deriveFont(14f));
            super.add(this.titulo, BorderLayout.NORTH);
            super.setPreferredSize(new Dimension(10000, 10000));
            super.setBackground(Color.cyan);
            conteudo = new JPanel(new FlowLayout(FlowLayout.LEADING, 5,5));
            conteudo.setBorder(new EmptyBorder(0, 0,0 ,0));
            super.add(conteudo);

        }



        /**
         * @param c item que sera colocado no painel
         */
        public void addConteudo(Component c){
            conteudo.add(c);
        }

        /**
         * inpa todos os item
         */
        public void limparConteudo(){
            conteudo.removeAll();
            conteudo.repaint();
        }
        public void setTitulo(String titulo) {
            this.titulo.setText(titulo);
        }
    }
}
