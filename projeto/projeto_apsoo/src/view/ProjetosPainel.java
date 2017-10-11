package view;

import controller.ProjetoAdapter;
import controller.ProjetoController;
import controller.SistemaController;
import resources.Cores;
import resources.Fontes;
import resources.Strings;
import view.Componetes.Botao;
import view.Componetes.MeuLabel;
import view.Componetes.Painel;
import view.Componetes.PainelItem;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsavel pelo view da aba Projetos
 */
public class ProjetosPainel extends Painel{

    private ItemListaProjeto projetoAtivo;
    private Dimension tamanhoItemLista = new Dimension(900, 125);
    private Botao novoProjeto;

    public ProjetosPainel(){
        super(Strings.TITULO_PAINEL_PROJETOS);
        super.setAlturaELarguraDosItensDoConteudo(tamanhoItemLista.height, tamanhoItemLista.width);
        carregarProjetos();
        carregarOpcoes();

        iniciarListaners();
    }

    private void iniciarListaners() {
        super.setQuandoAtivo((o) ->{
            super.limparConteudo();
            ProjetoController.atualizarListaDeProjetos();
            carregarProjetos();
        });
    }

    private void carregarOpcoes() {
        novoProjeto = new Botao();
        novoProjeto.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        novoProjeto.setCorDoTextoNormal(Cores.TEXTOS);
        novoProjeto.setCorDeFundoHover(Color.red);
        novoProjeto.setText(Strings.TEXTO_BTN_NOVO_PROJETO);
        novoProjeto.setOnMouseClick((e) -> SistemaController.setPainelDeTrabalho("CRIAR_PROJETO"));

        super.addOpcao(novoProjeto);
    }

    /**
     * Carrega a lista de projetos no painel.
     */
    private void carregarProjetos (){
        ProjetoController.listaDeprojetos.forEach(projetoMetaData -> {
            super.addConteudo(new ItemListaProjeto(projetoMetaData));
        });
    }

    /**
     * classe que modela os itens da lista de projetos cadastrados
     */
    private class ItemListaProjeto extends PainelItem{

        private MeuLabel src;
        private MeuLabel nome;
        private MeuLabel codigo;
        private MeuLabel descricao;
        private MeuLabel legendaSrc;
        private MeuLabel legendaNome;
        private MeuLabel legendaCodigo;
        private javax.swing.JPanel legendaPainel;
        private MeuLabel legendaDescricao;

        ItemListaProjeto(ProjetoAdapter projeto){
            iniciarComponetes();
            iniciarLayout();
            iniciarTextos();
            iniciarEstilo();

            nome.setText(projeto.getNome());
            codigo.setText(projeto.getCodigo());
            src.setText(projeto.getSrc());
            descricao.setText(projeto.getDescricao());
        }

        private void iniciarComponetes(){
            legendaPainel = new javax.swing.JPanel();
            legendaNome = new MeuLabel();
            legendaCodigo = new MeuLabel();
            legendaSrc = new MeuLabel();
            legendaDescricao = new MeuLabel();
            nome = new MeuLabel();
            codigo = new MeuLabel();
            src = new MeuLabel();
            descricao = new MeuLabel();
        }

        private void iniciarTextos (){
            legendaNome.setText(Strings.LG_PROJETO_NOME);
            legendaCodigo.setText(Strings.LG_PROJETO_CODIGO);
            legendaSrc.setText(Strings.LG_PROJETO_SRC);
            legendaDescricao.setText(Strings.LG_PROJETO_DESCRICAO);
        }


        private void iniciarLayout(){
            javax.swing.GroupLayout legendaPainelLayout = new javax.swing.GroupLayout(legendaPainel);
            legendaPainel.setLayout(legendaPainelLayout);
            legendaPainelLayout.setHorizontalGroup(
                    legendaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(legendaPainelLayout.createSequentialGroup()
                                    .addContainerGap(56, Short.MAX_VALUE)
                                    .addGroup(legendaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(legendaNome, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaSrc, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(legendaDescricao, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addContainerGap())
            );
            legendaPainelLayout.setVerticalGroup(
                    legendaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(legendaPainelLayout.createSequentialGroup()
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

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(legendaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(descricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(codigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                                                            .addComponent(nome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(src, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(legendaPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        }

        private void iniciarEstilo() {
            super.setCorDeFundoNormal(Cores.TEXTO_MENU_ESQUERDO);
            super.setCorDeFundoHover(Cores.FUNDO_MENU_ESQUERDO);

            legendaPainel.setBackground(Cores.FUNDO_MENU_ESQUERDO);

            nome.setHorizontalTextPosition(SwingConstants.RIGHT);

            for (Component c : this.getComponents()){
                c.setForeground(Cores.TEXTOS);
                c.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
            }

            for (Component c : legendaPainel.getComponents()){
                c.setForeground(Cores.TEXTOS);
                c.setFont(Fontes.ITEM_LISTA_PROJETO_LEGENDA);
            }

        }

    }

}
