package view;

import controller.ProjetoController;
import controller.SistemaController;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CriarProjetoPainel extends Painel{


    private Botao btmEscolherDiretorio;
    private javax.swing.JTextArea campoDescricao;
    private MeuCampoDeTexto campoNome;
    private MeuCampoDeTexto campoPrefixoCT;
    private MeuCampoDeTexto campoPrefixoCU;
    private MeuCampoDeTexto campoPrefixoRT;
    private MeuScrollPainel jScrollPane1;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPrefixo;
    private javax.swing.JLabel labelSrc;
    private javax.swing.JPanel painelLegenda;
    private javax.swing.JLabel srcDiretorio;

    private Botao salvar;
    private Botao cancelar;
    private Botao limpar;

    public CriarProjetoPainel (){
        super(Strings.TITULO_PAINEL_PROJETOS);
        super.setAlturaELarguraDosItensDoConteudo(1, 1);
        super.addConteudo(new Formulario());

        carregarOpcoes();
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
        SistemaController.setPainelDeTrabalho(SistemaController.PaineisDeTabalho.PROJETOS);
    }

    private boolean cadastarProjeto(){
        if (!todosOsCamposEstaoValidos())
            return false;
        ProjetoController.cadastrarProjeto(
                campoNome.getText(),
                srcDiretorio.getText(),
                campoPrefixoCU.getText(),
                campoPrefixoCT.getText(),
                campoPrefixoRT.getText(),
                campoDescricao.getText()
        );
        limpar();
        return true;
    }

    /**
     * valida as informações dos campos do formilario
     * @return retorna true apenas quando todos os campos forem validos.
     */
    private boolean todosOsCamposEstaoValidos() {
        if (!campoNome.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe o nome do projeto!", "O nome não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!campoPrefixoCT.campoValido() || !campoPrefixoCU.campoValido() || !campoPrefixoRT.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Os prefixos devem ter no maximo 5 caractes cada!", "Prefixo invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (srcDiretorio.getText().equals(" ")){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Você deve imformar o diretório raiz do projeto!", "src invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Limpa todas as informações de todos os campos do formulario.
     */
    public void limpar (){
        campoPrefixoCT.setText(null);
        campoPrefixoCU.setText(null);
        campoPrefixoRT.setText(null);
        srcDiretorio.setText(" ");
        campoNome.setText("");
        campoDescricao.setText("");
    }


    /**
     * classe que modela o formulario de cadastro de projeto.
     */
    private class Formulario extends JPanel{

        Formulario (){
            iniciarComponentes();
            iniciarLayout();
            iniciarTextos();
            iniciarEstilo();
            iniciarListaners();
        }

        private void iniciarComponentes (){
            painelLegenda = new javax.swing.JPanel();

            labelNome = new javax.swing.JLabel();
            labelPrefixo = new javax.swing.JLabel();
            labelSrc = new javax.swing.JLabel();
            labelDescricao = new javax.swing.JLabel();
            srcDiretorio = new javax.swing.JLabel();

            campoNome = new MeuCampoDeTexto();
            campoPrefixoCU = new MeuCampoDeTexto();
            campoPrefixoRT = new MeuCampoDeTexto();
            campoPrefixoCT = new MeuCampoDeTexto();
            btmEscolherDiretorio = new Botao();
            campoDescricao = new javax.swing.JTextArea();
            jScrollPane1 = new MeuScrollPainel(campoDescricao);

            campoPrefixoCU.setQuantidadeMaximaDeCaracteres(5);
            campoPrefixoRT.setQuantidadeMaximaDeCaracteres(5);
            campoPrefixoCT.setQuantidadeMaximaDeCaracteres(5);
        }

        private void iniciarTextos(){
            labelNome.setText(Strings.LG_CRIAR_PROJ_NOME);
            labelPrefixo.setText(Strings.LG_CRIAR_PROJ_PROFIXOS);
            labelSrc.setText(Strings.LG_CRIAR_PROJ_DIRETORIO_RAIZ);
            labelDescricao.setText(Strings.LG_CRIAR_PROJ_DESCRICAO);
            btmEscolherDiretorio.setText(Strings.LG_BTN_ESCOLHER_RAIZ);
            srcDiretorio.setText(" ");
            campoPrefixoCT.setPlaceHolder(Strings.PLACEHOLDER_PREF_CASO_DE_TESTE);
            campoPrefixoCU.setPlaceHolder(Strings.PLACEHOLDER_PREF_CASO_DE_USO);
            campoPrefixoRT.setPlaceHolder(Strings.PLACEHOLDER_PREF_ROTEIRO_DE_TESTE);
        }

        private void iniciarLayout(){
            setPreferredSize(new java.awt.Dimension(950, 500));
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(37, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelDescricao)
                                            .addComponent(labelSrc)
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
                                    .addComponent(labelSrc)
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

        private void iniciarEstilo(){
            this.setBackground(Cores.TEXTO_MENU_ESQUERDO);
            painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            for (Component c : painelLegenda.getComponents()){
                c.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
                c.setForeground(Cores.TEXTOS);
            }
            labelSrc.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
            labelSrc.setForeground(Cores.TEXTOS);

            campoDescricao.setBorder(new EmptyBorder(10,10,10,10));
            campoDescricao.setWrapStyleWord(true);
            campoDescricao.setLineWrap(true);
            campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO);


            btmEscolherDiretorio.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
            btmEscolherDiretorio.setCorDeFundoHover(Color.red);
            btmEscolherDiretorio.setCorDoTextoNormal(Cores.TEXTOS);
            btmEscolherDiretorio.setCorDoTextoHover(Cores.TEXTOS);


        }

        private void iniciarListaners() {
            btmEscolherDiretorio.setOnMouseClick((e) ->{
                JFileChooser chooserDiretorio = new JFileChooser();
                chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooserDiretorio.showOpenDialog(getParent());
                srcDiretorio.setText(chooserDiretorio.getSelectedFile().getAbsolutePath());
            });
        }


    }
}