package view;

import controller.CasoDeTesteController;
import controller.CasoDeUsoController;
import controller.ProjetoController;
import controller.SistemaController;
import model.ArtefatoDeTeste;
import model.CasoDeTeste;
import resources.Cores;
import resources.Fontes;
import view.Componetes.Botao;
import view.Componetes.MeuCampoDeTexto;
import view.Componetes.MeuLabel;
import view.Componetes.MeuPainelComScrollBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class CriarCasoDeTestePainelPopup extends JFrame{

    private MeuCampoDeTexto campoNome;
    private Botao btnCancelar;
    private Botao btnLimpar;
    private Botao btnNovo;
    private Botao btnSalvar;
    private Botao btnSelecionarArquivo;
    private javax.swing.JTextArea campoDescricao;
    private MeuPainelComScrollBar campoDescricaoContainer;
    private javax.swing.JComboBox<String> casosDeUso;
    private MeuLabel labelArquivoDeTeste;
    private MeuLabel labelCodigo;
    private MeuLabel labelTituloPainel;
    private MeuLabel labeldescricao;
    private MeuLabel labeldescricao1;
    private MeuLabel labeldescricao2;
    private javax.swing.JPanel painelFormulario;
    private javax.swing.JPanel painelLegenda;
    private javax.swing.JPanel painelOpcoes;
    private ArtefatoDeTeste artefato;
    private CasoDeTeste casoDeTeste;
    private String srcCasoDeTeste;

    public CriarCasoDeTestePainelPopup(){
        iniciarComponentes();
        iniciarLayout();
        carregarTextos();
        carregarEstilo();
        carregarlistaners();

        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        super.setPreferredSize(new Dimension(1000, 480));
        super.pack();
        super.setResizable(false);
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }

    /**
     * inicia o foirmulario de catrastro de caso de teste.
     * @param artefatoDeTeste
     * @return
     */
    public CriarCasoDeTestePainelPopup iniciarPopup(ArtefatoDeTeste artefatoDeTeste){
        labelTituloPainel.setText(labelTituloPainel.getText().concat(" ").concat(artefatoDeTeste.getNomeArquivo()));
        this.artefato = artefatoDeTeste;
        carregarCasosDeUso();
        return this;
    }

    /**
     * Carrega as informações do aso de teste no formulario.
     * @param casoDeTeste
     */
    public void carregarCasoDeTeste(CasoDeTeste casoDeTeste){
        campoNome.setText(casoDeTeste.getNome());
        campoDescricao.setText(casoDeTeste.getDescricao());
        labelArquivoDeTeste.setText(casoDeTeste.getSrcCasoDeTeste());
        srcCasoDeTeste = casoDeTeste.getSrcCasoDeTeste();
        int index;
        System.out.println(casosDeUso.getItemCount());
        for (index = 0; index < casosDeUso.getItemCount(); index++){
            String codDropbox = casosDeUso.getItemAt(index);
            String codCt = casoDeTeste.getCodigoCasoDeUso();
            if(codDropbox.startsWith(codCt)) break;
        }
        casosDeUso.setSelectedIndex(index);
        this.casoDeTeste = casoDeTeste;
    }

    /**
     * @return true ou false se um dos campod forem falso.
     */
    private boolean todosOsCamposValidos(){
        if (casosDeUso.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Selecione um caso de uso", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoDescricao.getText().isEmpty()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "o campo 'DESCRIÇÃO' não pode ser nulo.", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!campoNome.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Dê um nome a este caso de teste!", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (srcCasoDeTeste == null){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Selecione um arquivo de teste", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Carrega a combobox como os casos de uso do projeto
     */
    private void carregarCasosDeUso(){
        casosDeUso.addItem("Selecione um caso de uso...");
        CasoDeUsoController.getListaDeCasosDeUso().forEach((caso) -> {
            casosDeUso.addItem(caso.getCodigo().concat("          ".concat(caso.getNome())));
        });
    }

    /**
     * @return retorna o codigo do caso de uso selecionado
     */
    private String getCodigoCasoDeUsoSelecionado(){
        return ((String)casosDeUso.getSelectedItem()).substring(0, 11).trim();
    }

    /**
     * Abre uma janela de seleção de arquivo que permite apenas que arquivos .java ou .class sejan selecionados
     * O valor de retorno é atribuido ao labelDeAquivoDeTeste
     */
    private void selecionarArquivo() {
        JFileChooser chooserArquivo = new JFileChooser(ProjetoController.getSrcProjetoAtivo());
        chooserArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooserArquivo.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                return f.getName().endsWith(".java") || f.getName().endsWith(".class");
            }

            @Override
            public String getDescription() {
                return "Apenas arquivos .java ou .class";
            }
        });
        chooserArquivo.showOpenDialog(getParent());
        srcCasoDeTeste = chooserArquivo.getSelectedFile().getAbsolutePath();
        labelArquivoDeTeste.setText(srcCasoDeTeste);

    }

    /**
     * Fecha janela popup
     */
    private void cancelar() {
        super.dispose();
    }

    /**
     * Se o não existir nem um caso de teste setado, um novo caso de teste criado com os valores atuais dos campos.
     * Se existir um caso de uso setado. ele reta seus dados alterados co os valores atuais dos campos.
     */
    private void salvarCasoDeTeste() {
        if (todosOsCamposValidos()) {
            if (casoDeTeste == null) {
                String cod = CasoDeTesteController.salvarCasoDeTeste(
                        artefato,
                        campoNome.getText(),
                        campoDescricao.getText(),
                        srcCasoDeTeste,
                        getCodigoCasoDeUsoSelecionado()
                );
                limparCampos();
                JOptionPane.showMessageDialog(this, "Caso de teste criado com sucesso!\nO novo caso de teste tem o codigo: "+cod, "Sucesso!",  JOptionPane.PLAIN_MESSAGE);
            } else {
                CasoDeTesteController.salvarMudancasNoCasoDeTeste(casoDeTeste,
                        campoNome.getText(),
                        campoDescricao.getText(),
                        srcCasoDeTeste,
                        getCodigoCasoDeUsoSelecionado()
                );
                JOptionPane.showMessageDialog(this, "As alterações foram slavas!", "Sucesso!",  JOptionPane.PLAIN_MESSAGE);
                cancelar();
            }
        }
    }

    /**
     * Limpa todos os textos dos campos do formulario
     */
    private void limparCampos() {
        campoNome.setText("");
        campoDescricao.setText("");
        casosDeUso.setSelectedIndex(0);
        labelArquivoDeTeste.setText("Selecione um arquivo");
        srcCasoDeTeste = null;
    }

    /**
     * Carrega os componentes em suas posiçoes no layout
     */
    private void iniciarLayout(){

        javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
        painelLegenda.setLayout(painelLegendaLayout);
        painelLegendaLayout.setHorizontalGroup(
                painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLegendaLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labeldescricao, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labeldescricao1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
                        .addGroup(painelLegendaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labeldescricao2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelLegendaLayout.setVerticalGroup(
                painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLegendaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelCodigo)
                                .addGap(18, 18, 18)
                                .addComponent(labeldescricao2)
                                .addGap(18, 18, 18)
                                .addComponent(labeldescricao)
                                .addGap(30, 30, 30)
                                .addComponent(labeldescricao1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelFormularioLayout = new javax.swing.GroupLayout(painelFormulario);
        painelFormulario.setLayout(painelFormularioLayout);
        painelFormularioLayout.setHorizontalGroup(
                painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelFormularioLayout.createSequentialGroup()
                                .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(painelFormularioLayout.createSequentialGroup()
                                                .addGroup(painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(casosDeUso, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnSelecionarArquivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelArquivoDeTeste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(painelFormularioLayout.createSequentialGroup()
                                                .addGroup(painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(campoDescricaoContainer)
                                                        .addComponent(campoNome))
                                                .addContainerGap())))
        );
        painelFormularioLayout.setVerticalGroup(
                painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelFormularioLayout.createSequentialGroup()
                                .addGroup(painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(painelFormularioLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnSelecionarArquivo)
                                                        .addComponent(labelArquivoDeTeste))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(casosDeUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                                .addComponent(campoDescricaoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout painelOpcoesLayout = new javax.swing.GroupLayout(painelOpcoes);
        painelOpcoes.setLayout(painelOpcoesLayout);
        painelOpcoesLayout.setHorizontalGroup(
                painelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelOpcoesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelOpcoesLayout.setVerticalGroup(
                painelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelOpcoesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(painelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSalvar)
                                        .addComponent(btnLimpar)
                                        .addComponent(btnCancelar))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelTituloPainel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(painelFormulario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(painelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTituloPainel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(painelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    /**
     * instancia os componentes
     */
    private void iniciarComponentes(){
        painelFormulario = new javax.swing.JPanel();
        painelLegenda = new javax.swing.JPanel();
        labelCodigo = new MeuLabel();
        labeldescricao = new MeuLabel();
        labeldescricao1 = new MeuLabel();
        labeldescricao2 = new MeuLabel();
        campoNome = new MeuCampoDeTexto();
        campoDescricao = new javax.swing.JTextArea();
        btnSelecionarArquivo = new Botao();
        casosDeUso = new javax.swing.JComboBox<String>();
        labelArquivoDeTeste = new MeuLabel();
        painelOpcoes = new javax.swing.JPanel();
        btnSalvar = new Botao();
        btnNovo = new Botao();
        btnLimpar = new Botao();
        btnCancelar = new Botao();
        labelTituloPainel = new MeuLabel();
        campoDescricaoContainer = new MeuPainelComScrollBar(campoDescricao);
    }

    /**
     * Carrega os textos do painel
     */
    private void carregarTextos(){
        labelCodigo.setText("NOME:");
        labeldescricao.setText("CASO DE USO:");
        labeldescricao2.setText("CASO DE TESTE:");
        labeldescricao1.setText("DESCRIÇÃO:");
        btnSalvar.setText("Salvar");
        btnNovo.setText("Novo");
        btnLimpar.setText("Limpar");
        labelTituloPainel.setText("Caso de teste para o artefato: ");
        labelArquivoDeTeste.setText("Selecione um arquivo");
        btnSelecionarArquivo.setText("Selecionar Arquivo");
        btnCancelar.setText("Cancelar");
    }

    /**
     * Carrega a aparencia dos componentes
     */
    private void carregarEstilo(){
        campoDescricao.setBorder(new EmptyBorder(5, 5,5,5));
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO);


        labelTituloPainel.setBorder(new EmptyBorder(20, 20, 10, 10));
        labelTituloPainel.setFont(Fontes.PAINEL_TITULO);

        btnNovo.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        btnNovo.setCorDeFundoHover(Color.red);
        btnSalvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        btnSalvar.setCorDeFundoHover(Color.red);
        btnLimpar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        btnLimpar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        btnCancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        btnCancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);

        painelOpcoes.setBackground(Cores.FUNDO_BARRA_SUPERIOR);
        for (Component c : painelOpcoes.getComponents()){
            c.setFont(Fontes.TEXTO_BTN);
            ((Botao)c).setCorDoTextoNormal(Cores.TEXTOS);
        }

        painelLegenda.setBackground(Cores.FUNDO_BARRA_SUPERIOR);
        for (Component c : painelLegenda.getComponents()){
            c.setFont(Fontes.LEGENDA_ITEM_LISTA);
            c.setForeground(Cores.TEXTOS);
        }

        painelFormulario.setBackground(Cores.TEXTO_MENU_ESQUERDO);

        btnSelecionarArquivo.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        btnSelecionarArquivo.setCorDeFundoHover(Color.red);

    }

    /**
     * inicia os ouvites dos componetes
     */
    private void carregarlistaners(){
        btnLimpar.setOnMouseClick((e) -> this.limparCampos());
        btnSalvar.setOnMouseClick((e) -> this.salvarCasoDeTeste());
        btnCancelar.setOnMouseClick((e)-> this.cancelar());
        btnSelecionarArquivo.setOnMouseClick((e) -> selecionarArquivo());
    }

}
