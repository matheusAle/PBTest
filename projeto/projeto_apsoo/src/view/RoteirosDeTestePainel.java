package view;

import controller.ProjetoController;
import controller.RoteiroDeTesteController;
import controller.SistemaController;
import model.RoteiroDeTestes;
import resources.Cores;
import view.Componetes.*;

import javax.swing.*;
import java.awt.*;


public class RoteirosDeTestePainel extends PainelDeListagem{


    public RoteirosDeTestePainel() {
        super("Roteiros de teste");
        super.setRecarregarLista((e) -> carregarItems());
        super.setSincronizarLista((e) -> sincronizarItems());
    }

    /**
     * Resincroniza os roteiros de testes com a base de dados
     */
    private void sincronizarItems() {
        RoteiroDeTesteController.carregarRoteirosDoProjetoAtivo();
    }

    /**
     * Carrega os itens na listagem de roteiros de testes
     */
    private void carregarItems() {
        super.limparConteudo();
        RoteiroDeTesteController.getListaDeRoteiros().forEach(
                (e) -> {
                    super.addConteudo(new ItemDaLista(e));
                }
        );
    }

    @Override
    protected void carregarOpcoes() {
        Botao novo = new Botao("Criar novo roteiro de teste");
        novo.setCorDoTextoNormal(Cores.TEXTOS);
        novo.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        novo.setCorDeFundoHover(Color.red);
        novo.setOnMouseClick(e -> novoRoteiro());
        super.addOpcao(novo);
    }

    /**
     * Abre um formulario para a edição de roteiro de teste
     */
    private void novoRoteiro(){
        new CriarRoteiroDeTestePainelPopup().setVisible(true);
    }


    /**
     * Ececuta o roteiro de testes
     * @param roteiroDeTestes
     */
    private void execultarRoteiro(RoteiroDeTestes roteiroDeTestes) {

    }

    /**
     * Deleta o roteiro de teste
     * @param roteiroDeTestes roteiro a ser deletado.
     */
    private void deletarRoteiro(RoteiroDeTestes roteiroDeTestes) {
        int i = JOptionPane.showConfirmDialog(SistemaController.JANELA, "Você está certo disso?", "tem certeza?", JOptionPane.YES_NO_OPTION);
        if (i == 0){
            RoteiroDeTesteController.deletarRoteiriDeTestes(roteiroDeTestes);
            carregarItems();
        }
    }

    /**
     * Abre o formulario de edição de roteiros de teste
     * @param roteiro
     */
    private void editarRoteiro(RoteiroDeTestes roteiro) {
        new CriarRoteiroDeTestePainelPopup().setRoteiro(roteiro).setVisible(true);
    }


    /**
     * Nodela um item da listagem de roteiros de testes
     */
    class ItemDaLista extends PainelDeListagem.PainelItem {

        private MeuLabel codigo;
        private MeuLabel descricao;
        private MeuLabel legendaCodigo;
        private MeuLabel legendaDescricao;
        private MeuLabel legendaNome;
        private MeuLabel nome;
        private javax.swing.JPanel painelStatus;
        private RoteiroDeTestes roteiroDeTestes;

        public ItemDaLista(RoteiroDeTestes roteiroDeTeste) {
            this.roteiroDeTestes = roteiroDeTeste;
            nome.setText(roteiroDeTeste.getNome());
            codigo.setText(roteiroDeTeste.getCodigo());
            descricao.setText(roteiroDeTeste.getDescricao());
            menuPopup = new MeuMenuPopup();
            MeuItemMenuPopup item1 = new MeuItemMenuPopup("Editar")
                    .setOnClick((e) -> {editarRoteiro(this.roteiroDeTestes); menuPopup.setVisible(false);});
            MeuItemMenuPopup item2 = new MeuItemMenuPopup("Deletar")
                    .setOnClick((e) -> {deletarRoteiro(roteiroDeTestes); menuPopup.setVisible(false);});
            MeuItemMenuPopup item3 = new MeuItemMenuPopup("Execultar")
                    .setOnClick((e) -> {execultarRoteiro(roteiroDeTeste); menuPopup.setVisible(false);});
            menuPopup.add(item1);
            menuPopup.add(item2);
            menuPopup.add(item3);

            switch (roteiroDeTeste.getSituacao()){
                case 0:
                    painelStatus.setBackground(Cores.TEXTO_MENU_ESQUERDO);
                    painelStatus.setToolTipText("Aguardando execução!");
                    break;
                case 1:
                    painelStatus.setBackground(Cores.FUNDO_USUARIO_LOGADO);
                    painelStatus.setToolTipText("Exito!");
                    break;
                case 2:
                    painelStatus.setBackground(Cores.FUNDO_BOTAO);
                    painelStatus.setToolTipText("Falha!");
                    break;
            }
        }

        @Override
        protected void iniciarComponetes() {
            painelStatus = new javax.swing.JPanel();
            painelLegenda = new javax.swing.JPanel();
            legendaCodigo = new MeuLabel();
            legendaDescricao = new MeuLabel();
            legendaNome = new MeuLabel();
            nome = new MeuLabel();
            codigo = new MeuLabel();
            descricao = new MeuLabel();

            nome.setText("NOME:");
            codigo.setText("CODIGO:");
            descricao.setText("DESCRIÇÃO:");
            descricao.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        }

        @Override
        protected void iniciarLayout() {
            javax.swing.GroupLayout painelStatusLayout = new javax.swing.GroupLayout(painelStatus);
            painelStatus.setLayout(painelStatusLayout);
            painelStatusLayout.setHorizontalGroup(
                    painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 15, Short.MAX_VALUE)
            );
            painelStatusLayout.setVerticalGroup(
                    painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );

            legendaCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            legendaCodigo.setText("CODIGO:");

            legendaDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            legendaDescricao.setText("DESCRIÇÃO:");

            legendaNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            legendaNome.setText("NOME:");


            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaNome)
                                            .addComponent(legendaCodigo)
                                            .addComponent(legendaDescricao))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(legendaNome)
                                    .addGap(11, 11, 11)
                                    .addComponent(legendaCodigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaDescricao)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(painelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(painelLegenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(painelStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(nome)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(codigo)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE))
            );
        }
    }


}
