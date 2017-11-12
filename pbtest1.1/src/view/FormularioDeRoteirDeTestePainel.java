/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CasoDeTesteController;
import controller.RoteiroDeTesteController;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TreeSelectionEvent;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;
import model.RoteiroDeTestes;
import view.Componetes.MeuScrollbarUI;
import view.Componetes.MyTreeCellRender;

/**
 *
 * @author matheus
 */
public class FormularioDeRoteirDeTestePainel extends javax.swing.JPanel implements Painel{
    
    MyTreeNode root = new MyTreeNode("src", null);
    private TipoDePainel tipo;
    private String srcProducao, srcTestes;
    private Map<?, ?> dados = null;
    private LinkedList<CasoDeTeste> casosDeTesteDoRoteiro;
    private LinkedList<CasoDeTeste> casosDeTesteDoArtefatoSelecionado;
    private RoteiroDeTestes roteiroDeTestes;
    
    FormularioDeRoteirDeTestePainel(){
        initComponents();
        iniciarListeners();
        casosDeTesteDoRoteiro = new LinkedList<>();
        /*listaDeCasosDeTesteDoArtefato.setCellRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel l = new JLabel(value);
                l.setBorder(new EmptyBorder(5,5,5,5));
                if (isSelected){
                    set
                }
                
                return l;
            }
        });*/
    }
    
    private void carregarArtefatosDeTeste() {
        HashMap<String, LinkedList<ArtefatoDeTeste>> mapaDeArtefatos = CasoDeTesteController.getMapaDeArtefatos();
        Set<String> chaves = mapaDeArtefatos.keySet();

        for (String chave : chaves){
            if (chave.equals("")){
                for (ArtefatoDeTeste e : mapaDeArtefatos.get(chave)){
                    root.add(new MyTreeNode(e.getNomeArquivo(), e));
                }
            }else {
                MyTreeNode galho = new MyTreeNode(chave, null);
                for (ArtefatoDeTeste e : mapaDeArtefatos.get(chave)){
                    galho.add(new MyTreeNode(e.getNomeArquivo(), e));
                }
                root.add(galho);
            }
        }
        arvoreDeArtefatos.removeAll();
        
        arvoreDeArtefatos.revalidate();
    }
    
    
    private void iniciarListeners(){
        arvoreDeArtefatos.addTreeSelectionListener((TreeSelectionEvent e) -> {
            MyTreeNode node = (MyTreeNode) e.getNewLeadSelectionPath().getLastPathComponent();
            if (node.getArtefato() != null){
                carregarCasosDeTesteDoArtefato(node.getArtefato());
            }
        }); 
        
        btnAdicionarNoRoteiro.addMouseListener(new MouseAdapter() {
            Color anterior;
            @Override
            public void mouseEntered(MouseEvent e) {
                anterior = btnAdicionarNoRoteiro.getBackground();
                btnAdicionarNoRoteiro.setBackground(new Color(221,221,221));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdicionarNoRoteiro.setBackground(anterior);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listaDeCasosDeTesteDoArtefato.getSelectedIndex();
                CasoDeTeste c = casosDeTesteDoArtefatoSelecionado.get(index);
                if (casosDeTesteDoRoteiro.contains(c)){
                    JOptionPane.showMessageDialog(null, "Este caso de teste já está no roteiro!", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                casosDeTesteDoRoteiro.add(c);
                listaDeCasosDeTesteDoRoteiro.setModel(new ListModel<String>() {
                LinkedList<String> casosDeTeste;
                {
                    LinkedList<String> linkedList = new LinkedList<>();
                    casosDeTesteDoRoteiro.forEach((c) -> {
                       linkedList.add(toListItem(c));
                    });
                    this.casosDeTeste = linkedList;
                }
                @Override
                public int getSize() {
                    return casosDeTeste.size();
                }

                @Override
                public String getElementAt(int index) {
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
        });
        
        btnRemoverDoRoteiro.addMouseListener(new MouseAdapter() {
            Color anterior;
            @Override
            public void mouseEntered(MouseEvent e) {
                anterior = btnRemoverDoRoteiro.getBackground();
                if(listaDeCasosDeTesteDoRoteiro.getSelectedIndex() == -1){
                    btnRemoverDoRoteiro.setBackground(Color.red);
                    btnRemoverDoRoteiro.setEnabled(false);
                }else {
                    btnRemoverDoRoteiro.setBackground(new Color(221,221,221));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRemoverDoRoteiro.setBackground(anterior);
                btnRemoverDoRoteiro.setEnabled(true);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(listaDeCasosDeTesteDoRoteiro.getSelectedIndex() != -1){
                    int index = listaDeCasosDeTesteDoRoteiro.getSelectedIndex();
                    casosDeTesteDoRoteiro.remove(index);
                    listaDeCasosDeTesteDoRoteiro.setModel(new ListModel<String>() {
                        LinkedList<String> casosDeTeste;
                        {
                            LinkedList<String> linkedList = new LinkedList<>();
                            casosDeTesteDoRoteiro.forEach((c) -> {
                               linkedList.add(toListItem(c));
                            });
                            this.casosDeTeste = linkedList;
                        }
                        @Override
                        public int getSize() {
                            return casosDeTeste.size();
                        }

                        @Override
                        public String getElementAt(int index) {
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
            }
        });
    }
    
    
    private void carregarCasosDeTesteDoArtefato(ArtefatoDeTeste artefato) {
        casosDeTesteDoArtefatoSelecionado = artefato.getCasosDeTeste();
        LinkedList<String> linkedList = new LinkedList<>();
        artefato.getCasosDeTeste().forEach((c) -> {
           linkedList.add(toListItem(c));
        });
        
        listaDeCasosDeTesteDoArtefato.removeAll();

        listaDeCasosDeTesteDoArtefato.setModel(new ListModel<String>() {
            LinkedList<String> casosDeTeste = linkedList;
            @Override
            public int getSize() {
                return casosDeTeste.size();
            }

            @Override
            public String getElementAt(int index) {
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
    
    
    private String toListItem(CasoDeTeste c){
        String i = String.format("<html><Strong>Nome</strong>: %s<br>", c.getNome());
        i = i.concat(String.format("<Strong>Codigo</strong>: %s<br>", c.getCodigo()));
        return i.concat(String.format("<Strong>Classe</strong>: %s<br></html>", c.getSrcCasoDeTeste()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formunario = new javax.swing.JPanel();
        campoDeTextoContainer = new javax.swing.JPanel();
        bordaInferiorNome = new javax.swing.JPanel();
        campoNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        arvoreDeArtefatos = new javax.swing.JTree(root);
        jScrollPane3 = new javax.swing.JScrollPane();
        listaDeCasosDeTesteDoArtefato = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaDeCasosDeTesteDoRoteiro = new javax.swing.JList<>();
        btnAdicionarNoRoteiro = new javax.swing.JButton();
        btnRemoverDoRoteiro = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formunario.setBackground(new java.awt.Color(255, 255, 255));
        formunario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));

        campoDeTextoContainer.setBackground(new java.awt.Color(255, 255, 255));
        campoDeTextoContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        bordaInferiorNome.setBackground(new java.awt.Color(153, 153, 153));
        bordaInferiorNome.setPreferredSize(new java.awt.Dimension(340, 2));

        javax.swing.GroupLayout bordaInferiorNomeLayout = new javax.swing.GroupLayout(bordaInferiorNome);
        bordaInferiorNome.setLayout(bordaInferiorNomeLayout);
        bordaInferiorNomeLayout.setHorizontalGroup(
            bordaInferiorNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bordaInferiorNomeLayout.setVerticalGroup(
            bordaInferiorNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        campoNome.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        campoNome.setForeground(new java.awt.Color(51, 51, 51));
        campoNome.setBorder(null);

        javax.swing.GroupLayout campoDeTextoContainerLayout = new javax.swing.GroupLayout(campoDeTextoContainer);
        campoDeTextoContainer.setLayout(campoDeTextoContainerLayout);
        campoDeTextoContainerLayout.setHorizontalGroup(
            campoDeTextoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campoNome)
            .addComponent(bordaInferiorNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );
        campoDeTextoContainerLayout.setVerticalGroup(
            campoDeTextoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campoDeTextoContainerLayout.createSequentialGroup()
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(bordaInferiorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        jLabel1.setText("Nome:");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)), "Descrição", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N

        campoDescricao.setColumns(20);
        campoDescricao.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        campoDescricao.setLineWrap(true);
        campoDescricao.setRows(5);
        campoDescricao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(campoDescricao);

        arvoreDeArtefatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artefatos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        arvoreDeArtefatos.setCellRenderer(new MyTreeCellRender());
        jScrollPane2.setViewportView(arvoreDeArtefatos);

        listaDeCasosDeTesteDoArtefato.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Casos de teste", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        listaDeCasosDeTesteDoArtefato.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        listaDeCasosDeTesteDoArtefato.setForeground(new java.awt.Color(102, 102, 102));
        listaDeCasosDeTesteDoArtefato.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaDeCasosDeTesteDoArtefato.setDragEnabled(true);
        listaDeCasosDeTesteDoArtefato.setDropMode(javax.swing.DropMode.INSERT);
        listaDeCasosDeTesteDoArtefato.setFocusable(false);
        listaDeCasosDeTesteDoArtefato.setMinimumSize(new java.awt.Dimension(240, 0));
        listaDeCasosDeTesteDoArtefato.setSelectionBackground(new java.awt.Color(204, 204, 204));
        listaDeCasosDeTesteDoArtefato.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane3.setViewportView(listaDeCasosDeTesteDoArtefato);

        listaDeCasosDeTesteDoRoteiro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Casos de teste do roteiro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        listaDeCasosDeTesteDoRoteiro.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        listaDeCasosDeTesteDoRoteiro.setForeground(new java.awt.Color(102, 102, 102));
        listaDeCasosDeTesteDoRoteiro.setDragEnabled(true);
        listaDeCasosDeTesteDoRoteiro.setFocusable(false);
        listaDeCasosDeTesteDoRoteiro.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane4.setViewportView(listaDeCasosDeTesteDoRoteiro);

        btnAdicionarNoRoteiro.setBackground(new java.awt.Color(255, 255, 255));
        btnAdicionarNoRoteiro.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnAdicionarNoRoteiro.setForeground(new java.awt.Color(102, 102, 102));
        btnAdicionarNoRoteiro.setText(">>");
        btnAdicionarNoRoteiro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        btnAdicionarNoRoteiro.setFocusPainted(false);

        btnRemoverDoRoteiro.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoverDoRoteiro.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnRemoverDoRoteiro.setForeground(new java.awt.Color(102, 102, 102));
        btnRemoverDoRoteiro.setText("X");
        btnRemoverDoRoteiro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        btnRemoverDoRoteiro.setFocusPainted(false);

        javax.swing.GroupLayout formunarioLayout = new javax.swing.GroupLayout(formunario);
        formunario.setLayout(formunarioLayout);
        formunarioLayout.setHorizontalGroup(
            formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formunarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formunarioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formunarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdicionarNoRoteiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoverDoRoteiro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        formunarioLayout.setVerticalGroup(
            formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formunarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoDeTextoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formunarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addGap(10, 10, 10))
                    .addGroup(formunarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarNoRoteiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoverDoRoteiro)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane1.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane1.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane1.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane1.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane2.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane2.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane3.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane3.getVerticalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane4.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane4.getVerticalScrollBar().setUI(new MeuScrollbarUI());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formunario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formunario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arvoreDeArtefatos;
    private javax.swing.JPanel bordaInferiorNome;
    private javax.swing.JButton btnAdicionarNoRoteiro;
    private javax.swing.JButton btnRemoverDoRoteiro;
    private javax.swing.JPanel campoDeTextoContainer;
    private javax.swing.JTextArea campoDescricao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JPanel formunario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listaDeCasosDeTesteDoArtefato;
    private javax.swing.JList<String> listaDeCasosDeTesteDoRoteiro;
    // End of variables declaration//GEN-END:variables

    
    @Override
    public TipoDePainel pegarTipoDePainel() {
        return TipoDePainel.FORMULARIO_DE_CRIACAO;
    }

    @Override
    public void preProcessamentoAntesDeAbrir() {
        carregarArtefatosDeTeste();
        tipo = TipoDePainel.FORMULARIO_DE_CRIACAO;
        if (dados != null)
            roteiroDeTestes = (RoteiroDeTestes) dados.get("roteiro");
        if (roteiroDeTestes != null){
            campoNome.setText(roteiroDeTestes.getNome());
            campoNome.setText(roteiroDeTestes.getDescricao());
            tipo = TipoDePainel.FORMULARIO_DE_EDICAO;
            RoteiroDeTesteController.carregarCasosDeTesteDoRoteiro(roteiroDeTestes);
            casosDeTesteDoRoteiro = roteiroDeTestes.getCasosDeTeste();
            listaDeCasosDeTesteDoRoteiro.setModel(new ListModel<String>() {
                LinkedList<String> casosDeTeste;
                {
                    LinkedList<String> linkedList = new LinkedList<>();
                    casosDeTesteDoRoteiro.forEach((c) -> {
                       linkedList.add(toListItem(c));
                    });
                    this.casosDeTeste = linkedList;
                }
                @Override
                public int getSize() {
                    return casosDeTeste.size();
                }

                @Override
                public String getElementAt(int index) {
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
    }

    @Override
    public void preProcessamentoAntesDeFechar() {
    }

    @Override
    public Component getCompomenteRaiz() {
        return this;
    }

    @Override
    public void btnNovoOnClick(MouseEvent e) {
    }

    @Override
    public void btnSalvarOnClick(MouseEvent e) {
        if (todosOsCamposValidos()) {
            if (roteiroDeTestes == null){
                String codigo = RoteiroDeTesteController.salvarNovoRoteiro(campoNome.getText(), campoDescricao.getText(), casosDeTesteDoRoteiro);
                JOptionPane.showMessageDialog(null, "Roteiro salvo!\n\tO Codigo é: "+codigo, "", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            }else {
                RoteiroDeTesteController.salvarAlteracao(roteiroDeTestes, campoNome.getText(), campoDescricao.getText(), casosDeTesteDoRoteiro);
                JOptionPane.showMessageDialog(null, "Roteiro salvo!","", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    private boolean todosOsCamposValidos(){
        if (campoNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Informe o nome!", "O nome não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoDescricao.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Informe a decrição!", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (casosDeTesteDoRoteiro.isEmpty()){
            JOptionPane.showMessageDialog(null, "Insira os casos de teste!", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
   
    @Override
    public void btnCancelarOnClick(MouseEvent e) {
        mainApp.trocarDePainel(Paineis.ROTEIROS_DE_TESTE, null);
    }

    @Override
    public void btnLimparOnClick(MouseEvent e) {
        limparCampos();
    }

    @Override
    public void setData(Map<?, ?> dados) {
        this.dados = dados;
    }

    private void limparCampos() {
        campoNome.setText("");
        campoDescricao.setText("");
        casosDeTesteDoRoteiro = new LinkedList<>();
        casosDeTesteDoArtefatoSelecionado = null;
        listaDeCasosDeTesteDoRoteiro.setModel(new DefaultListModel<>());
        listaDeCasosDeTesteDoArtefato.setModel(new DefaultListModel<>());
    }
}
