package view;

import controller.ProjetoController;
import controller.SistemaController;
import model.Projeto;
import resources.Cores;
import resources.Strings;
import view.Componetes.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsavel pelo view da aba Projetos
 */
public final class ProjetosPainel extends PainelDeListagem{

    private ProjetoAtivo projetoAtivo;
    private Dimension tamanhoItemLista = new Dimension(900, 140);

    public ProjetosPainel(){
        super(Strings.TITULO_PAINEL_PROJETOS);
        super.setAlturaELarguraDosItensDoConteudo(tamanhoItemLista.height, tamanhoItemLista.width);
        super.setRecarregarLista((o) -> this.carregarProjetos());
        super.setSincronizarLista((o) -> ProjetoController.atualizarListaDeProjetos());
    }


    /**
     * Ativa o projeto com o codigo passado como paremetro
     * @param codigo
     */
    private void ativarProjeto(String codigo){
        if(!ProjetoController.ativarProjeto(codigo)){
            JOptionPane.showConfirmDialog(SistemaController.JANELA,"Ocorreu um erro ao ativar o projeto.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        projetoAtivo = new ProjetoAtivo(ProjetoController.getProjetoAtivo());
        super.atualizarLista(false);
    }

    /**
     * carrega as poções deste painel
     */
    @Override
    protected void carregarOpcoes() {
        Botao novoProjeto = new Botao();
        novoProjeto.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        novoProjeto.setCorDoTextoNormal(Cores.TEXTOS);
        novoProjeto.setCorDeFundoHover(Color.red);
        novoProjeto.setText(Strings.TEXTO_BTN_NOVO_PROJETO);
        novoProjeto.setOnMouseClick((e) -> SistemaController.setPainelDeTrabalho("CRIAR_PROJETO"));

        super.addOpcao(novoProjeto);
    }

    /**
     * Carrega a lista de projetos no painel. Sempre deixando o projeto ativo no topo da lista
     */
    private void carregarProjetos (){
        super.limparConteudo();
        if (projetoAtivo != null){
            super.addConteudo(projetoAtivo);
        }
        ProjetoController.getListaDeProjetos().forEach(projeto -> {
            super.addConteudo(new ItemListaProjeto(projeto));
        });
    }

    /**
     * classe que modela os itens da lista de projetos cadastrados
     */
    private class ItemListaProjeto extends PainelItem{

        private String codigoDoProjeto;
        private MeuLabel src;
        private MeuLabel nome;
        private MeuLabel codigo;
        private MeuLabel descricao;
        private MeuLabel legendaSrc;
        private MeuLabel legendaNome;
        private MeuLabel legendaCodigo;
        private MeuLabel legendaDescricao;

        ItemListaProjeto(Projeto projeto){
            iniciarlistaners();

            codigoDoProjeto = projeto.getCodigo();

            legendaNome.setText(Strings.LG_PROJETO_NOME);
            legendaCodigo.setText(Strings.LG_PROJETO_CODIGO);
            legendaSrc.setText(Strings.LG_PROJETO_SRC);
            legendaDescricao.setText(Strings.LG_PROJETO_DESCRICAO);

            nome.setText(projeto.getNome());
            codigo.setText(projeto.getCodigo());
            src.setText(projeto.getSrc());
            descricao.setText(projeto.getDescricao());
        }

        /**
         * Quando o usuario clicar mais de 2 vez sobre um item da listagem de projeto,
         * este projeto será ativo.
         */
        private void iniciarlistaners() {
            super.menuPopup = new MeuMenuPopup();
            MeuItemMenuPopup item1 = new MeuItemMenuPopup("Ativar este projeto");
            item1.setOnClick((e) -> {
                ativarProjeto(codigoDoProjeto);
                menuPopup.setVisible(false);
            });
            MeuItemMenuPopup item2 = new MeuItemMenuPopup("Editar este projeto");
            item2.setOnClick((e) -> {
                ProjetoController.setProjetoAtualizavel(codigoDoProjeto);
                SistemaController.setPainelDeTrabalho("EDITAR_PROJETO");
                menuPopup.setVisible(false);
            });

            MeuItemMenuPopup item3 = new MeuItemMenuPopup("Deletar este projeto");
            item3.setOnClick( (e) -> {
                int i = JOptionPane.showConfirmDialog(SistemaController.JANELA, "Você está certo disso?", "tem certeza?", JOptionPane.YES_NO_OPTION);
                if (i == 0){
                    ProjetoController.deletarProjetoDeCodigo(codigoDoProjeto);
                    ProjetosPainel.this.carregarProjetos();
                }
                menuPopup.setVisible(false);
            });


            super.menuPopup.add(item1);
            super.menuPopup.add(item2);
            super.menuPopup.add(item3);
        }

        @Override
        protected void iniciarComponetes(){
            super.painelLegenda = new javax.swing.JPanel();
            legendaNome = new MeuLabel();
            legendaCodigo = new MeuLabel();
            legendaSrc = new MeuLabel();
            legendaDescricao = new MeuLabel();
            nome = new MeuLabel();
            codigo = new MeuLabel();
            src = new MeuLabel();
            descricao = new MeuLabel();
        }

        @Override
        protected void iniciarLayout(){
            javax.swing.GroupLayout painelLegendaLayout = new javax.swing.GroupLayout(painelLegenda);
            painelLegenda.setLayout(painelLegendaLayout);
            painelLegendaLayout.setHorizontalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap(42, Short.MAX_VALUE)
                                    .addGroup(painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(legendaNome, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaSrc, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaDescricao, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addContainerGap())
            );
            painelLegendaLayout.setVerticalGroup(
                    painelLegendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLegendaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(legendaNome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaCodigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaSrc)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(legendaDescricao)
                                    .addContainerGap(20, Short.MAX_VALUE))
            );

            nome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            nome.setText("NOME:");
            nome.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            codigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            codigo.setText("NOME:");
            codigo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            src.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            src.setText("NOME:");
            src.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            descricao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            descricao.setText("NOME:");
            descricao.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(descricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(src, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(nome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(codigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(src)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(descricao)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            this.setSize(tamanhoItemLista);
        }

    }

    private class ProjetoAtivo extends ItemListaProjeto {

        ProjetoAtivo(Projeto projeto) {
            super(projeto);
            super.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
            super.setCorDeFundoHover(Color.red);
        }
    }
}
