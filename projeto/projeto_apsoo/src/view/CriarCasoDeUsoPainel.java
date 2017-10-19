package view;

import controller.CasoDeUsoController;
import controller.ProjetoController;
import controller.SistemaController;
import controller.exceptions.CasoDeTesteExeption;
import controller.exceptions.CasoDeUsoExeption;
import controller.exceptions.RoteiroDeTesteExeption;
import model.CasoDeUso;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CriarCasoDeUsoPainel extends Painel {

    private MeuLabel codigo;
    private MeuLabel legendaNome;
    private MeuLabel legendaAtores;
    private MeuLabel legendaCodigo;
    private MeuLabel legendaObjetivo;
    private MeuCampoDeTexto campoNome;
    private MeuCampoDeTexto campoAtores;
    private MeuCampoDeTexto campoObjetivo;
    private javax.swing.JPanel painelLegenda;
    private javax.swing.JTextArea campoDescricao;
    private MeuPainelComScrollBar painelDescricao;

    public CriarCasoDeUsoPainel(){
        super(Strings.TITULO_PAINEL_CRIAR_CASOS_DE_USO);
        super.setAlturaELarguraDosItensDoConteudo(1,1);
        carregarOpscoes();
    }

    /**
     * Carrega as opçoes ára este painel.
     */
    private void carregarOpscoes(){
        Botao salvar = new Botao();
        salvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        salvar.setCorDoTextoNormal(Cores.TEXTOS);
        salvar.setCorDeFundoHover(Color.red);
        salvar.setText(Strings.CRIAR_CU_TEXTO_BTN_SALVAR);
        salvar.setOnMouseClick((e) -> this.cadastarProjeto());

        Botao cancelar = new Botao();
        cancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        cancelar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDoTextoHover(Cores.TEXTOS);
        cancelar.setText(Strings.CRIAR_CU_TEXTO_BTN_CANCELAR);
        cancelar.setOnMouseClick((e) -> this.cancelarCadastro());

        Botao limpar = new Botao();
        limpar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        limpar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        limpar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        limpar.setCorDoTextoHover(Cores.TEXTOS);
        limpar.setText(Strings.CRIAR_CU_TEXTO_BTN_LIMPAR);
        limpar.setOnMouseClick((e) -> this.limpar());


        super.addOpcao(salvar);
        super.addOpcao(limpar);
        super.addOpcao(cancelar);
    }


    /**
     * carrega o formulario ja com um codiogo de caso de uso
     */
    public void iniciarFormulario(){
        super.limparConteudo();
        super.addConteudo(new Formulario(ProjetoController.getPrefixoDoProjetoAtual("caso de uso")));
    }


    /**
     * Salva o novo caso de uso no banco de dados
     */
    private void cadastarProjeto() {
        if(!todosOsCamposValidos())
            return;
        while (!CasoDeUsoController.salvarCasoDeUso(codigo.getText(), campoNome.getText(), campoObjetivo.getText(), campoAtores.getText(), campoDescricao.getText())){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Erro ao salvar o caso de uso!", "o caso de uso não foi salvo", JOptionPane.ERROR_MESSAGE);
        }
        cancelarCadastro();
    }

    /**
     * Limpa todos os campos e retorna a lista de casos de uso do projeto.
     */
    private void cancelarCadastro() {
        limpar();
        try {
            SistemaController.setPainelDeTrabalho("CASOS_DE_USO");
        } catch (CasoDeUsoExeption casoDeUsoExeption) {
            casoDeUsoExeption.printStackTrace();
        } catch (CasoDeTesteExeption casoDeTesteExeption) {
            casoDeTesteExeption.printStackTrace();
        } catch (RoteiroDeTesteExeption roteiroDeTesteExeption) {
            roteiroDeTesteExeption.printStackTrace();
        }
    }

    /**
     * Limpa todos os campos do formulario
     */
    private void limpar() {
        campoNome.setText("");
        campoObjetivo.setText("");
        campoAtores.setText("");
        campoDescricao.setText("");
    }


    /**
     * Verifoca se todos os campos estão validos.
     * Entende-se por valido todos os campos estarem preenchidos.
     * @return
     */
    private boolean todosOsCamposValidos(){
        if (!campoNome.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe o nome do caso de uso!", "O nome não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!campoObjetivo.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe o objetivo nome do caso de uso!", "O campo objetivo não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!campoAtores.campoValido()){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Informe o(s) atorer(es) deste caso de uso!", "O caso de uso deve pelo menos um  atorer", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoDescricao.getText().equals("") || campoDescricao.getText().equals(" ")){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Você deve imformar o a descrição do caso de uso!", "O campo descrição não pode ser nulo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    protected class Formulario extends JPanel {
        Formulario(String codigo){
            iniciarComponentes();
            iniciarLayout();
            iniciarTextos();
            iniciarEstilo();
            iniciarValores(codigo);
        }

        Formulario(){

        }
        public void iniciarValores(String codigo){
            CriarCasoDeUsoPainel.this.codigo.setText(codigo);
        }

        private void iniciarComponentes(){
            painelLegenda = new javax.swing.JPanel();
            legendaCodigo = new MeuLabel();
            legendaObjetivo = new MeuLabel();
            legendaNome = new MeuLabel();
            legendaAtores = new MeuLabel();
            codigo = new MeuLabel();
            campoNome = new MeuCampoDeTexto();
            campoObjetivo = new MeuCampoDeTexto();
            campoAtores = new MeuCampoDeTexto();
            campoDescricao = new javax.swing.JTextArea();
            painelDescricao = new MeuPainelComScrollBar(campoDescricao);
        }

        private void iniciarLayout(){
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(legendaCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaObjetivo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaNome, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaAtores, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addContainerGap())
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(legendaCodigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaNome)
                                    .addGap(18, 18, 18)
                                    .addComponent(legendaObjetivo)
                                    .addGap(18, 18, 18)
                                    .addComponent(legendaAtores)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(campoNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                                                            .addComponent(campoObjetivo, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(campoAtores, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(painelDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(codigo)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(campoObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(campoAtores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(painelDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                                    .addContainerGap())
            );
        }

        private void iniciarTextos(){
            legendaNome.setText(Strings.LG_CASOS_DE_USO_NOME);
            legendaAtores.setText(Strings.LG_CASOS_DE_USO_ATORES);
            legendaCodigo.setText(Strings.LG_CASOS_DE_USO_CODIGO);
            legendaObjetivo.setText(Strings.LG_CASOS_DE_USO_OBJETIVO);


        }

        private void iniciarEstilo (){
            painelLegenda.setBackground(Cores.FUNDO_MENU_ESQUERDO);
            super.setBackground((Cores.TEXTO_MENU_ESQUERDO));

            for (Component c : painelLegenda.getComponents()){
                c.setFont(Fontes.LEGENDA_ITEM_LISTA);
                c.setForeground(Cores.TEXTOS);
                ((JLabel)c).setHorizontalAlignment(SwingConstants.RIGHT);
                ((JLabel)c).setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            }
            codigo.setFont(Fontes.LEGENDA_ITEM_LISTA);
            codigo.setForeground(Cores.TEXTOS);
            painelDescricao.setViewportView(campoDescricao);
            
            campoDescricao.setBorder(new EmptyBorder(10,10,10,10));
            campoDescricao.setWrapStyleWord(true);
            campoDescricao.setLineWrap(true);
            campoDescricao.setFont(Fontes.CAMPO_DE_TEXTO.deriveFont(16f));
        }
    }


}
