package view;

import controller.CasoDeTesteController;
import controller.CasoDeUsoController;
import controller.SistemaController;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import resources.Cores;
import resources.Fontes;
import view.Componetes.*;

import javax.swing.*;
import java.awt.*;

public class CriarCasoDeTestePainel extends Painel {

    private MeuCampoDeTexto campoNome;
    private JComboBox<String> codigosCasoDeUso;
    private javax.swing.JTextArea campoDescricao;
    private MeuPainelComScrollBar jScrollPane1;
    private RTextScrollPane editorDeCodigo;
    private MeuLabel legendaCasosDeUso;
    private MeuLabel legendaNome;
    private MeuLabel legendaDescricao;
    private javax.swing.JPanel painelLegenda;
    private Formulario formulario;

    public CriarCasoDeTestePainel() {
        super("aaa");
        super.setAlturaELarguraDosItensDoConteudo(390, 600);
        super.addConteudo(formulario = new Formulario());
        carregarOpcoes();
    }

    /**
     * Carrega a lista de casos de uso
     */
    public void carregarCasosDeUso(){
        codigosCasoDeUso.addItem("Selecione um caso de uso...");
        CasoDeUsoController.getListaDeCasosDeUso().forEach((caso) -> {
            codigosCasoDeUso.addItem(caso.getCodigo().concat("          ".concat(caso.getNome())));
        });

    }
    /**
     * Carrga as opções do do painel
     */
    private void carregarOpcoes() {
        Botao salvar = new Botao();
        salvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        salvar.setCorDoTextoNormal(Cores.TEXTOS);
        salvar.setCorDeFundoHover(Color.red);
        salvar.setText("Salvar");
        salvar.setOnMouseClick((e) -> this.cadastarProjeto());

        Botao cancelar = new Botao();
        cancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        cancelar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDoTextoHover(Cores.TEXTOS);
        cancelar.setText("Cancelar");
        cancelar.setOnMouseClick((e) -> this.cancelarCadastro());

        Botao limpar = new Botao();
        limpar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        limpar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        limpar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        limpar.setCorDoTextoHover(Cores.TEXTOS);
        limpar.setText("Limpar");
        limpar.setOnMouseClick((e) -> this.limpar());


        super.addOpcao(salvar);
        super.addOpcao(limpar);
        super.addOpcao(cancelar);
    }

    private void limpar() {

    }

    public void iniciar (){
        codigosCasoDeUso.removeAllItems();
        carregarCasosDeUso();
        formulario.carregarCodigoPadrao();
    }

    private void cancelarCadastro() {
    }

    private void cadastarProjeto() {
        if (todosOsCamposValidos()){
            String codigoCasoDeUso = ((String)codigosCasoDeUso.getSelectedItem()).substring(0, 11).trim();
            CasoDeTesteController.salvarCasoDeTeste(campoNome.getText(), campoDescricao.getText(), editorDeCodigo.getTextArea().getText(), codigoCasoDeUso);
        }
    }

    private boolean todosOsCamposValidos(){
        if (codigosCasoDeUso.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Selecione um caso de uso", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoDescricao.getText().isEmpty()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "o campo 'DESCRIÇÃO' não pode ser nulo.", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!campoNome.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Dê um nome a este caso de teste!", "", JOptionPane.INFORMATION_MESSAGE);

        }
        return true;
    }
    protected class Formulario extends JPanel {

        Formulario() {
            iniciarComponetes();
            carregarLayout();
            carregarEstilo();
            carregarCodigoPadrao();
        }

        private void carregarLayout() {
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(34, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaNome)
                                            .addComponent(legendaDescricao)
                                            .addComponent(legendaCasosDeUso))
                                    .addContainerGap())
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(legendaNome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaCasosDeUso)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaDescricao)
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
                                            .addComponent(campoNome)
                                            .addComponent(codigosCasoDeUso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                    .addComponent(codigosCasoDeUso)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(editorDeCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
        }

        private void iniciarComponetes() {
            painelLegenda = new javax.swing.JPanel();
            legendaCasosDeUso = new MeuLabel();
            legendaNome = new MeuLabel();
            legendaDescricao = new MeuLabel();
            campoNome = new MeuCampoDeTexto();
            codigosCasoDeUso = new JComboBox<String>();
            campoDescricao = new javax.swing.JTextArea();
            jScrollPane1 = new MeuPainelComScrollBar(campoDescricao);

            RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            textArea.setCodeFoldingEnabled(true);
            editorDeCodigo = new RTextScrollPane(textArea);

            legendaCasosDeUso.setText("NOME:");

            legendaNome.setText("CODIGO:");

            legendaDescricao.setText("DESCRIÇÂO:");

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

        private void carregarCodigoPadrao(){
            try {
                editorDeCodigo.getTextArea().setText(
                        "package testes.pbtest;\n" +
                                "import " + CasoDeTesteController.getArtefatoDeTesteSendoEditado().getPakage() + "." + CasoDeTesteController.getArtefatoDeTesteSendoEditado().getNomeArquivo().replaceAll("\\.java", ";\n") +
                                "/**\n\n\n" +
                                "* O nome da classe não pode ser substituido.\n" +
                                "* O software ira gerar um nome no momento que o arquivo for salvo.\n" +
                                "*/\n" +
                                "public class {$className} {\n" +
                                "\n" +
                                "\t// Insira o seu codigo aqui!\n" +
                                "\n" +
                                "}\n"
                );
            }catch (Exception e){}
        }
    }


}