package view;

import controller.CasoDeUsoController;
import controller.ProjetoController;
import controller.SistemaController;
import controller.adapters.CasoDeUsoAdapter;
import controller.exceptions.ProjetoException;
import resources.Cores;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import java.awt.*;

public class CasosDeUsoPainel extends PainelDeListagem {

    private MeuLabel atores;
    private MeuLabel legendaAtores;
    private MeuLabel legendaNome;
    private MeuLabel legendaObjetivo;
    private MeuLabel nome;
    private MeuLabel codigo;
    private MeuLabel objetivo;
    private MeuLabel legendaCodigo;
    private Dimension tamanhoItemDaLista = new Dimension(850, 140);

    public CasosDeUsoPainel(){
        super(Strings.TITULO_PAINEL_CASOS_DE_USO);
        super.setAlturaELarguraDosItensDoConteudo(tamanhoItemDaLista.height, tamanhoItemDaLista.width);
        super.setRecarregarLista((o) -> this.carregarListaDeCasosDeUso());
        super.setSincronizarLista((o) -> CasoDeUsoController.recarregarLista());
    }


    /**
     * Carriga a lista de casos de uso cadastrados no sistema
     */
    private void carregarListaDeCasosDeUso(){
        super.setTitulo(Strings.TITULO_PAINEL_CASOS_DE_USO + ProjetoController.getInformacoesDoProjetoAtivo().getNome());
        try {
            CasoDeUsoController.getListaDeCasosDeUso().forEach( (c) -> {
            super.addConteudo(new ItemDaLista(c));
        });
        }catch (Exception e){
            JOptionPane.showConfirmDialog(SistemaController.JANELA, "ERRO!\nVocê deve selecionar um projeto antes!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carrega as opçoes do painel
     */
    @Override
    protected void carregarOpcoes() {
        Botao novoProjeto = new Botao();
        novoProjeto.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        novoProjeto.setCorDoTextoNormal(Cores.TEXTOS);
        novoProjeto.setCorDeFundoHover(Color.red);
        novoProjeto.setText(Strings.TEXTO_BTN_NOVO_CASO_DE_USO);
        novoProjeto.setOnMouseClick((e) -> {
            SistemaController.setPainelDeTrabalho("CRIAR_CASO_DE_USO");

        });
        super.addOpcao(novoProjeto);
    }

    /**
     * Classe que modela um item da lista de casos de uso.
     */
    private final class ItemDaLista extends PainelItem {

        /**
         * Instacia um novo item da lista com os valores do caso de uso pasdo por paramentro.
         * @param casoDeUso
         */
        ItemDaLista (CasoDeUsoAdapter casoDeUso){
            iniciarTextos(casoDeUso);
        }


        /**
         * Carrega o texto da legenda do item e os valores do caso de uso.
         * @param casoDeUso
         */
        private void iniciarTextos(CasoDeUsoAdapter casoDeUso){

            legendaNome.setText(Strings.LG_CASOS_DE_USO_NOME);
            legendaCodigo.setText(Strings.LG_CASOS_DE_USO_CODIGO);
            legendaAtores.setText(Strings.LG_CASOS_DE_USO_ATORES);
            legendaObjetivo.setText(Strings.LG_CASOS_DE_USO_OBJETIVO);

            nome.setText(casoDeUso.getNome());
            codigo.setText(casoDeUso.getCodigo());
            atores.setText(casoDeUso.getAtores());
            objetivo.setText(casoDeUso.getObjetivo());
        }

        @Override
        protected void iniciarComponetes() {
            painelLegenda = new javax.swing.JPanel();
            legendaNome = new MeuLabel();
            legendaObjetivo = new MeuLabel();
            legendaAtores = new MeuLabel();
            objetivo = new MeuLabel();
            nome = new MeuLabel();
            atores = new MeuLabel();
            codigo = new MeuLabel();
            legendaCodigo = new MeuLabel();
        }

        @Override
        protected void iniciarLayout(){
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(legendaNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(legendaObjetivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(legendaAtores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(legendaCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(legendaNome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaObjetivo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaCodigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaAtores)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                                            .addComponent(objetivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(atores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(24, 24, 24))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(nome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(objetivo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(codigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(atores)
                                    .addContainerGap())
                            .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            super.setSize(tamanhoItemDaLista);
        }
    }
}
