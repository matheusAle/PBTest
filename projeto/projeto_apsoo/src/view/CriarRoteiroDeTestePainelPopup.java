package view;

import controller.CasoDeTesteController;
import controller.RoteiroDeTesteController;
import controller.SistemaController;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;
import model.RoteiroDeTestes;
import model.TestesPool;
import resources.Cores;
import resources.Fontes;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Classe responsavel pelo view da aba roteiros de teste
 */
public class CriarRoteiroDeTestePainelPopup extends JFrame{

    private Botao btnCancelar;
    private Botao btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel container;
    private ArvoreDeArtefatos arvoreDeArtefatos;
    private MeuPainelComScrollBar arvoreDeArtefatosContainer;
    private Botao btnAdicionarAListaDoRoteiro;
    private javax.swing.JTextArea campoDescricao;
    private MeuCampoDeTexto campoNome;
    private MeuPainelComScrollBar descricaocontainer;
    private Botao btnRemoverDaListaDoRoteiro;
    private MeuLabel labelListaArtefatos;
    private MeuLabel labelSequnciaDeExecucao;
    private MeuLabel labellistaDeCasosDeTeste;
    private MeuLabel legendaDescricao;
    private MeuLabel legendaNome;
    private javax.swing.JList<CasoDeTeste> listaCasosDeTeste;
    private MeuPainelComScrollBar listaCasosDeTesteContainer;
    private javax.swing.JList<CasoDeTeste> listaDeCasosDeTesteDoRoteiro;
    private MeuPainelComScrollBar listaDeCasosDeTesteDoRoteiroContainer;
    private javax.swing.JPanel painelLegenda;
    private LinkedList<CasoDeTeste> casosDeTesteDoRoteiro = new LinkedList<>();
    private RoteiroDeTestes roteiroDeTestes;
    /**
     * Creates new form RoteirosDeTeste
     */
    public CriarRoteiroDeTestePainelPopup() {
        carregarArvore();
        iniciarComponetes();
        initlayout();
        carregarEstilo();
        super.setLocationRelativeTo(null);
        iniciarListaners();
    }

    private void carregarArvore() {
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

    private void iniciarComponetes(){
        container = new javax.swing.JPanel();
        painelLegenda = new javax.swing.JPanel();
        legendaNome = new MeuLabel();
        legendaDescricao = new MeuLabel();
        campoNome = new MeuCampoDeTexto();
        descricaocontainer = new MeuPainelComScrollBar();
        campoDescricao = new javax.swing.JTextArea();
        labelListaArtefatos = new MeuLabel();
        listaCasosDeTesteContainer = new MeuPainelComScrollBar();
        listaCasosDeTeste = new javax.swing.JList<>();
        labellistaDeCasosDeTeste = new MeuLabel();
        arvoreDeArtefatosContainer = new MeuPainelComScrollBar();
        labelSequnciaDeExecucao = new MeuLabel();
        listaDeCasosDeTesteDoRoteiroContainer = new MeuPainelComScrollBar();
        listaDeCasosDeTesteDoRoteiro = new javax.swing.JList<>();
        btnAdicionarAListaDoRoteiro = new Botao();
        btnRemoverDaListaDoRoteiro = new Botao();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new Botao();
        btnSalvar = new Botao();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        legendaNome.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        legendaNome.setText("NOME:");

        legendaDescricao.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        legendaDescricao.setText("DESCRIÇÃO:");

        campoNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        descricaocontainer.setViewportView(campoDescricao);

        labelListaArtefatos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelListaArtefatos.setText("artefatos");

        listaCasosDeTeste.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaCasosDeTeste.setDragEnabled(true);
        listaCasosDeTesteContainer.setViewportView(listaCasosDeTeste);

        labellistaDeCasosDeTeste.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labellistaDeCasosDeTeste.setText("casos de teste");

        arvoreDeArtefatosContainer.setViewportView(arvoreDeArtefatos);

        labelSequnciaDeExecucao.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelSequnciaDeExecucao.setText("sequência de execução");

        listaDeCasosDeTesteDoRoteiro.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaDeCasosDeTesteDoRoteiro.setDragEnabled(true);
        listaDeCasosDeTesteDoRoteiroContainer.setViewportView(listaDeCasosDeTesteDoRoteiro);

        btnAdicionarAListaDoRoteiro.setText(">>");
        btnAdicionarAListaDoRoteiro.setPreferredSize(new java.awt.Dimension(50, 30));

        btnRemoverDaListaDoRoteiro.setText("X");
        btnRemoverDaListaDoRoteiro.setPreferredSize(new java.awt.Dimension(50, 30));

        jPanel1.setPreferredSize(new java.awt.Dimension(198, 60));

        btnCancelar.setText("Cancelar");

        btnSalvar.setText("Salvar");

    }

    private void initlayout() {

        javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
        painelLegenda.setLayout(painelLegendaLayout);
        painelLegendaLayout.setHorizontalGroup(
                painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLegendaLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(legendaNome)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLegendaLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(legendaDescricao)
                                .addContainerGap())
        );
        painelLegendaLayout.setVerticalGroup(
                painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLegendaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(legendaNome)
                                .addGap(18, 18, 18)
                                .addComponent(legendaDescricao)
                                .addContainerGap(67, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
                containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(containerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(arvoreDeArtefatosContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelListaArtefatos))
                                .addGap(18, 18, 18)
                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(containerLayout.createSequentialGroup()
                                                .addComponent(listaCasosDeTesteContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnAdicionarAListaDoRoteiro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnRemoverDaListaDoRoteiro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(labellistaDeCasosDeTeste))
                                .addContainerGap(257, Short.MAX_VALUE))
                        .addGroup(containerLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(campoNome)
                                        .addComponent(descricaocontainer))
                                .addContainerGap())
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(containerLayout.createSequentialGroup()
                                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(containerLayout.createSequentialGroup()
                                                        .addContainerGap(580, Short.MAX_VALUE)
                                                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelSequnciaDeExecucao)
                                                                .addComponent(listaDeCasosDeTesteDoRoteiroContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(containerLayout.createSequentialGroup()
                                                        .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap()))
        );
        containerLayout.setVerticalGroup(
                containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(containerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descricaocontainer, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelListaArtefatos, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labellistaDeCasosDeTeste, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(listaCasosDeTesteContainer)
                                        .addComponent(arvoreDeArtefatosContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                        .addGroup(containerLayout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(btnAdicionarAListaDoRoteiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnRemoverDaListaDoRoteiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(containerLayout.createSequentialGroup()
                                        .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(labelSequnciaDeExecucao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(listaDeCasosDeTesteDoRoteiroContainer)
                                        .addContainerGap()))
        );


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSalvar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnSalvar))
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );

        pack();
    }

    private void carregarEstilo (){
        jPanel1.setBackground(Cores.FUNDO_BARRA_SUPERIOR);
        painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
        container.setBackground(Cores.TEXTO_MENU_ESQUERDO);
        labelSequnciaDeExecucao.setFont(Fontes.LEGENDA_ITEM_LISTA);
        labellistaDeCasosDeTeste.setFont(Fontes.LEGENDA_ITEM_LISTA);
        labelListaArtefatos.setFont(Fontes.LEGENDA_ITEM_LISTA);
        legendaNome.setFont(Fontes.LEGENDA_ITEM_LISTA);
        legendaDescricao.setFont(Fontes.LEGENDA_ITEM_LISTA);
        labelSequnciaDeExecucao.setForeground(Cores.TEXTOS);
        labellistaDeCasosDeTeste.setForeground(Cores.TEXTOS);
        labelListaArtefatos.setForeground(Cores.TEXTOS);
        legendaNome.setForeground(Cores.TEXTOS);
        legendaDescricao.setForeground(Cores.TEXTOS);

        btnAdicionarAListaDoRoteiro.setCorDoTextoNormal(Cores.TEXTOS);
        btnAdicionarAListaDoRoteiro.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        btnAdicionarAListaDoRoteiro.setCorDeFundoHover(Color.red);

        btnRemoverDaListaDoRoteiro.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        btnRemoverDaListaDoRoteiro.setCorDeFundoHover(Cores.FUNDO_BARRA_SUPERIOR);
        btnRemoverDaListaDoRoteiro.setCorDoTextoNormal(Cores.TEXTOS);
        btnRemoverDaListaDoRoteiro.setFont(Fontes.TEXTO_BTN);

        btnSalvar.setCorDoTextoNormal(Cores.TEXTOS);
        btnSalvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        btnSalvar.setCorDeFundoHover(Color.red);

        btnCancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        btnCancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        btnCancelar.setCorDoTextoNormal(Cores.TEXTOS);
        btnCancelar.setFont(Fontes.TEXTO_BTN);

        listaCasosDeTeste.setCellRenderer(new myCellListRender());
        listaCasosDeTeste.setBackground(Cores.FUNDO_MENU_ESQUERDO);
        listaCasosDeTeste.setBorder(new EmptyBorder(0,0,0,0));
        listaCasosDeTeste.setSelectionBackground(Cores.FUNDO_BOTAO);

        listaDeCasosDeTesteDoRoteiro.setCellRenderer(new myCellListRender());
        listaDeCasosDeTesteDoRoteiro.setBackground(Cores.FUNDO_MENU_ESQUERDO);
        listaDeCasosDeTesteDoRoteiro.setBorder(new EmptyBorder(0,0,0,0));
        listaDeCasosDeTesteDoRoteiro.setSelectionBackground(Cores.FUNDO_BOTAO);

        arvoreDeArtefatos.setBackground(Cores.FUNDO_MENU_ESQUERDO);

        campoDescricao.setBorder(new EmptyBorder(5, 5,5,5));
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO);
    }

    /**
     * Metodo chamando no momento enq que um node da arvore é selecionado pelo usuario
     * @param artefatoDeTeste
     */
    private void quandoNodeDaArvoreSelecionado(ArtefatoDeTeste artefatoDeTeste){
        listaCasosDeTeste.removeAll();

        listaCasosDeTeste.setModel(new ListModel<CasoDeTeste>() {
            LinkedList<CasoDeTeste> casosDeTeste = artefatoDeTeste.getCasosDeTeste();
            @Override
            public int getSize() {
                return casosDeTeste.size();
            }

            @Override
            public CasoDeTeste getElementAt(int index) {
                return casosDeTeste.get(index);
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }
        });

    }

    /**
     * Metodo chamado no momento em que uma caso de teste é selecionado pelo usuario na nsita de casos de teste do artefato
     * @param casoDeTeste
     */
    private void quandoCasoDeTesteSelecionado(CasoDeTeste casoDeTeste) {

    }

    /**
     * Metodo chamado no momento em que uma caso de teste é selecionado pelo usuario na lsita se sequencia
     * @param casoDeTeste
     */
    private void quandoCasoDeTesteDaListaDeRoteidoSelecionado(CasoDeTeste casoDeTeste){

    }

    /**
     * Chamado no momento em que usuario clicar no botão de adicionar ao roteiro.
     */
    private void quandoBtnAdicionarNoRoteiroClicado(){
        CasoDeTeste casoSelecionado = listaCasosDeTeste.getSelectedValue();
        casosDeTesteDoRoteiro.add(casoSelecionado);

        listaDeCasosDeTesteDoRoteiro.setModel(new ListModel<CasoDeTeste>() {
            @Override
            public int getSize() {
                return casosDeTesteDoRoteiro.size();
            }

            @Override
            public CasoDeTeste getElementAt(int index) {
                return casosDeTesteDoRoteiro.get(index);
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }
        });

    }

    /**
     * Chamado no momento em que usuario clicar no botão de remover do roteiro.
     */
    private void quandoBtnDeRemoverDoRoteiroClicado() {
        CasoDeTeste casoSelecionado = listaDeCasosDeTesteDoRoteiro.getSelectedValue();
        casosDeTesteDoRoteiro.remove(casoSelecionado);
        listaDeCasosDeTesteDoRoteiro.setModel(new ListModel<CasoDeTeste>() {
            @Override
            public int getSize() {
                return casosDeTesteDoRoteiro.size();
            }

            @Override
            public CasoDeTeste getElementAt(int index) {
                return casosDeTesteDoRoteiro.get(index);
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }
        });
    }

    /**
     * Quando o usuario clciar em salvar
     */
    private void quandoBtnSalvarClicado(){
        if (todosOsCamposValidos()) {
            if (roteiroDeTestes == null){
                String codigo = RoteiroDeTesteController.salvarNovoRoteiro(campoNome.getText(), campoDescricao.getText(), casosDeTesteDoRoteiro);
                JOptionPane.showMessageDialog(SistemaController.JANELA, "Roteiro salvo!\n\tO Codigo é: "+codigo, "", JOptionPane.INFORMATION_MESSAGE);
            }else {
                RoteiroDeTesteController.salvarAlteracao(roteiroDeTestes, campoNome.getText(), campoDescricao.getText(), casosDeTesteDoRoteiro);
                JOptionPane.showMessageDialog(SistemaController.JANELA, "Roteiro salvo!","", JOptionPane.INFORMATION_MESSAGE);

            }
            quandoBtnCancelarClicado();
        }
    }

    private boolean todosOsCamposValidos(){
        if (campoNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe o nome!", "O nome não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe a decrição!", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (casosDeTesteDoRoteiro.isEmpty()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Insira os casos de teste!", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Quando o usuario clicar Em cancelar Criação
     */
    private void quandoBtnCancelarClicado(){
        super.dispose();
    }

    private void iniciarListaners(){
        btnAdicionarAListaDoRoteiro.setOnMouseClick((e) -> quandoBtnAdicionarNoRoteiroClicado());
        btnRemoverDaListaDoRoteiro.setOnMouseClick((e) -> quandoBtnDeRemoverDoRoteiroClicado());
        btnSalvar.setOnMouseClick((e) -> quandoBtnSalvarClicado());
        btnCancelar.setOnMouseClick((e) -> quandoBtnCancelarClicado());
        arvoreDeArtefatos.setOnSelectedEvent((e) -> quandoNodeDaArvoreSelecionado(e.getArtefato()));
    }

    /**
     * Inicio o formulario com um roteiro de teste para ser editado.
     * @param roteiro roteiro
     * @return this
     */
    public JFrame setRoteiro(RoteiroDeTestes roteiro) {
        roteiroDeTestes = roteiro;
        RoteiroDeTesteController.carregarCasosDeTesteDoRoteiro(roteiro);
        casosDeTesteDoRoteiro = roteiro.getCasosDeTeste();
        campoNome.setText(roteiro.getNome());
        campoDescricao.setText(roteiro.getDescricao());

        listaDeCasosDeTesteDoRoteiro.setModel(new ListModel<CasoDeTeste>() {
            @Override
            public int getSize() {
                return casosDeTesteDoRoteiro.size();
            }

            @Override
            public CasoDeTeste getElementAt(int index) {
                return casosDeTesteDoRoteiro.get(index);
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }
        });
        return this;
    }

    private class myCellListRender extends DefaultListCellRenderer {

        public myCellListRender() {
            super.setFont(Fontes.LEGENDA_ITEM_LISTA);
            super.setBorder(new EmptyBorder(0,0,0,0));
        }

        @Override
        public Color getForeground() {
            return Cores.TEXTOS;
        }
    }

}
