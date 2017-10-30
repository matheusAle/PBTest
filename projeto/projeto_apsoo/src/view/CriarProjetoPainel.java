package view;

import controller.ProjetoController;
import controller.SistemaController;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class CriarProjetoPainel extends Painel{


    protected Botao btnEscolherDiretorioDeProducao;
    protected Botao btnEscolherDiretorioDeTestes;
    protected javax.swing.JTextArea campoDescricao;
    protected MeuPainelComScrollBar campoDescricaoContainer;
    protected MeuCampoDeTexto campoNome;
    protected MeuCampoDeTexto campoPrefixoCT;
    protected MeuCampoDeTexto campoPrefixoCU;
    protected MeuCampoDeTexto campoPrefixoRT;
    protected MeuLabel labelDescricao;
    protected MeuLabel labelDiretorios;
    protected MeuLabel labelNome;
    protected MeuLabel labelPrefixos;
    protected MeuLabel labelSrcProducao;
    protected MeuLabel labelSrcTestes;
    protected javax.swing.JPanel painelLegenda;

    protected Botao salvar;
    protected Botao cancelar;
    protected Botao limpar;
    protected String srcProducao;
    protected String srcTestes;


    public CriarProjetoPainel (){
        super(Strings.TITULO_PAINEL_CRIAR_PROJETO);
        super.setAlturaELarguraDosItensDoConteudo(1, 1);
        super.addConteudo(new Formulario());

        carregarOpcoes();
    }

    protected CriarProjetoPainel(String titulo){
        super(titulo);
        super.setAlturaELarguraDosItensDoConteudo(1, 1);
    }
    /**
     * Carrga as opções do do painel
     */
    private void carregarOpcoes() {
        salvar = new Botao();
        salvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        salvar.setCorDoTextoNormal(Cores.TEXTOS);
        salvar.setCorDeFundoHover(Color.red);
        salvar.setText(Strings.CRIAR_PROJ_TEXTO_BTN_SALVAR);
        salvar.setOnMouseClick((e) -> this.cadastarProjeto());

        cancelar = new Botao();
        cancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        cancelar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDoTextoHover(Cores.TEXTOS);
        cancelar.setText(Strings.CRIAR_PROJ_TEXTO_BTN_CANCELAR);
        cancelar.setOnMouseClick((e) -> this.cancelarCadastro());

        limpar = new Botao();
        limpar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        limpar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        limpar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        limpar.setCorDoTextoHover(Cores.TEXTOS);
        limpar.setText(Strings.CRIAR_PROJ_TEXTO_BTN_LIMPAR);
        limpar.setOnMouseClick((e) -> this.limpar());


        super.addOpcao(salvar);
        super.addOpcao(limpar);
        super.addOpcao(cancelar);
    }

    /**
     * clamado quando o usario clicar em "Cancelar cadastro".
     * Todos os campos serão limpos e o uruari sera levado de volta para a lsita de projetos.
     */
    private void cancelarCadastro(){
        limpar();
        SistemaController.setPainelDeTrabalho("PROJETOS");
    }

    private boolean cadastarProjeto(){
        if (!todosOsCamposEstaoValidos())
            return false;
        boolean resultado;
        do {
            resultado = ProjetoController.cadastrarProjeto(
                    campoNome.getText(),
                    srcProducao,
                    srcTestes,
                    campoPrefixoCU.getText(),
                    campoPrefixoCT.getText(),
                    campoPrefixoRT.getText(),
                    campoDescricao.getText()
            );
            if (!resultado){

            }
        }while (!resultado);
        limpar();
        return true;
    }

    /**
     * valida as informações dos campos do formilario
     * @return retorna true apenas quando todos os campos forem validos.
     */
    protected boolean todosOsCamposEstaoValidos() {
        if (!campoNome.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe o nome do projeto!", "O nome não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!campoPrefixoCT.campoValido() || !campoPrefixoCU.campoValido() || !campoPrefixoRT.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Os prefixos devem ter no maximo " +campoPrefixoRT.getQuantidadeMaximaDeCaracteres()+ " caractes cada!", "Prefixo invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (srcTestes == null || srcProducao == null){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Você deve imformar os diretórios do projeto!", "src invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Abre uma jenela de seleção de arquivos para o usuario informar ao sistema
     * a pasta raiz do diretorio de produção do software que será testado.
     */
    private void selecionarDiretoriosDeProducao(){
        JFileChooser chooserArquivo = new JFileChooser();
        chooserArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserArquivo.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Selecione o seu diretório raiz dos arquivos .class";
            }
        });
        chooserArquivo.showOpenDialog(SistemaController.JANELA);
        srcProducao = chooserArquivo.getSelectedFile().getAbsolutePath();
        labelSrcProducao.setText(srcProducao);
    }

    /**
     * Abre uma jenela de seleção de arquivos para o usuario informar ao sistema
     * a pasta raiz do diretorio de testes do software que será testado.
     */
    private void selecionarDiretoriosDeTestes(){
        JFileChooser chooserArquivo = new JFileChooser();
        chooserArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserArquivo.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Selecione o seu diretório raiz dos arquivos .class de teste";
            }
        });
        chooserArquivo.showOpenDialog(SistemaController.JANELA);
        srcTestes = chooserArquivo.getSelectedFile().getAbsolutePath();
        labelSrcTestes.setText(srcTestes);
    }
    /**
     * Limpa todas as informações de todos os campos do formulario.
     */
    public void limpar (){
        campoPrefixoCT.setText(null);
        campoPrefixoCU.setText(null);
        campoPrefixoRT.setText(null);
        labelDescricao.setText(" ");
        labelSrcTestes.setText(" ");
        campoNome.setText("");
        campoDescricao.setText("");
    }


    /**
     * classe que modela o formulario de cadastro de projeto.
     */
    protected class Formulario extends JPanel{

        Formulario (){
            iniciarComponentes();
            iniciarLayout();
            iniciarTextos();
            iniciarEstilo();
            iniciarListaners();
        }

        private void iniciarComponentes (){
            painelLegenda = new javax.swing.JPanel();
            labelNome = new MeuLabel();
            labelPrefixos = new MeuLabel();
            labelDiretorios = new MeuLabel();
            labelDescricao = new MeuLabel();
            campoNome = new MeuCampoDeTexto();
            campoPrefixoCT = new MeuCampoDeTexto();
            campoPrefixoRT = new MeuCampoDeTexto();
            campoPrefixoCU = new MeuCampoDeTexto();
            btnEscolherDiretorioDeTestes = new Botao();
            btnEscolherDiretorioDeProducao = new Botao();
            labelSrcProducao = new MeuLabel();
            labelSrcTestes = new MeuLabel();
            campoDescricao = new javax.swing.JTextArea();
            campoDescricaoContainer = new MeuPainelComScrollBar(campoDescricao);

            campoPrefixoCU.setQuantidadeMaximaDeCaracteres(10);
            campoPrefixoRT.setQuantidadeMaximaDeCaracteres(10);
            campoPrefixoCT.setQuantidadeMaximaDeCaracteres(10);
            labelSrcProducao.setQuantidadeMaximaDeCaracteres(40);
            labelSrcTestes.setQuantidadeMaximaDeCaracteres(40);
        }

        private void iniciarTextos(){
            labelNome.setText(Strings.LG_CRIAR_PROJ_NOME);
            labelPrefixos.setText(Strings.LG_CRIAR_PROJ_PROFIXOS);
            labelDiretorios.setText(Strings.LG_CRIAR_PROJ_DIRETORIO_RAIZ);
            labelDescricao.setText(Strings.LG_CRIAR_PROJ_DESCRICAO);
            labelSrcTestes.setText(" ");
            campoPrefixoCT.setPlaceHolder(Strings.PLACEHOLDER_PREF_CASO_DE_TESTE);
            campoPrefixoCU.setPlaceHolder(Strings.PLACEHOLDER_PREF_CASO_DE_USO);
            campoPrefixoRT.setPlaceHolder(Strings.PLACEHOLDER_PREF_ROTEIRO_DE_TESTE);
            btnEscolherDiretorioDeProducao.setText(Strings.LG_BTN_ESCOLHER_RAIZ);

            labelSrcProducao.setText(" ");
            labelSrcTestes.setText(" ");

            labelNome.setText("NOME:");
            labelPrefixos.setText("PREFIXOS:");
            labelDiretorios.setText("DIRETÓRIOS:");
            labelDescricao.setText("DESCRIÇÃO:");

            btnEscolherDiretorioDeTestes.setText("Diretório de Produção");
            btnEscolherDiretorioDeProducao.setText("Diretório de Testes");
        }

        private void iniciarLayout(){
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelNome, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelDiretorios, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelPrefixos, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelNome)
                                    .addGap(26, 26, 26)
                                    .addComponent(labelPrefixos)
                                    .addGap(46, 46, 46)
                                    .addComponent(labelDiretorios)
                                    .addGap(67, 67, 67)
                                    .addComponent(labelDescricao)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoNome)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(campoPrefixoCT, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(31, 31, 31)
                                                    .addComponent(campoPrefixoCU, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(campoPrefixoRT, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(btnEscolherDiretorioDeProducao, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                                            .addComponent(labelSrcProducao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnEscolherDiretorioDeTestes, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                                                            .addComponent(labelSrcTestes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(campoDescricaoContainer))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(campoPrefixoCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoPrefixoRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoPrefixoCU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(33, 33, 33)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnEscolherDiretorioDeTestes)
                                            .addComponent(btnEscolherDiretorioDeProducao))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelSrcProducao)
                                            .addComponent(labelSrcTestes))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                    .addComponent(campoDescricaoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
        }

        private void iniciarEstilo() {
            this.setBackground(Cores.TEXTO_MENU_ESQUERDO);
            painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            for (Component c : painelLegenda.getComponents()) {
                c.setFont(Fontes.LEGENDA_ITEM_LISTA);
                c.setForeground(Cores.TEXTOS);
            }
            labelSrcProducao.setFont(Fontes.LEGENDA_ITEM_LISTA);
            labelSrcProducao.setForeground(Cores.TEXTOS);
            labelSrcTestes.setFont(Fontes.LEGENDA_ITEM_LISTA);
            labelSrcTestes.setForeground(Cores.TEXTOS);

            campoDescricaoContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
            campoDescricao.setBorder(new EmptyBorder(10, 10, 10, 10));
            campoDescricao.setWrapStyleWord(true);
            campoDescricao.setLineWrap(true);
            campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO);
            campoDescricao.setEnabled(true);
            campoDescricao.setEditable(true);
            campoDescricaoContainer.setEnabled(true);
            campoDescricaoContainer.setFocusable(true);
            campoDescricao.setFocusable(true);

            btnEscolherDiretorioDeProducao.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
            btnEscolherDiretorioDeProducao.setCorDeFundoHover(Color.red);
            btnEscolherDiretorioDeProducao.setCorDoTextoNormal(Cores.TEXTOS);
            btnEscolherDiretorioDeProducao.setCorDoTextoHover(Cores.TEXTOS);


            btnEscolherDiretorioDeTestes.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
            btnEscolherDiretorioDeTestes.setCorDeFundoHover(Color.red);
            btnEscolherDiretorioDeTestes.setCorDoTextoNormal(Cores.TEXTOS);
            btnEscolherDiretorioDeTestes.setCorDoTextoHover(Cores.TEXTOS);
        }

        private void iniciarListaners(){
            btnEscolherDiretorioDeProducao.setOnMouseClick((e) -> selecionarDiretoriosDeProducao());
            btnEscolherDiretorioDeTestes.setOnMouseClick((e) -> selecionarDiretoriosDeTestes());
        }


    }
}