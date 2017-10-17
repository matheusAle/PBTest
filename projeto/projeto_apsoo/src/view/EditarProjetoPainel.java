package view;

import controller.adapters.ProjetoAdapter;
import controller.ProjetoController;
import controller.SistemaController;
import resources.Cores;
import resources.Strings;
import view.Componetes.Botao;

import javax.swing.*;
import java.awt.*;

public final class EditarProjetoPainel extends CriarProjetoPainel {

    ProjetoAdapter projetoSendoEditado;

    public EditarProjetoPainel(){
        super(Strings.TITULO_PAINEL_EDITAR_PROJETO);
        this.carregarOpcoes();
    }

    /**
     * Carrega as opçoes deste painel
     */
    private void carregarOpcoes(){
        super.salvar = new Botao();
        super.salvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        super.salvar.setCorDoTextoNormal(Cores.TEXTOS);
        super.salvar.setCorDeFundoHover(Color.red);
        super.salvar.setText(Strings.EDITAR_PROJ_TEXTO_BTN_SALVAR);
        super.salvar.setOnMouseClick((e) -> this.salvarProjeto());

        super.cancelar = new Botao();
        super.cancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        super.cancelar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        super.cancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        super.cancelar.setCorDoTextoHover(Cores.TEXTOS);
        super.cancelar.setText(Strings.EDITAR_PROJ_TEXTO_BTN_CANCELAR);
        super.cancelar.setOnMouseClick((e) -> this.cancelarEdicao());

        super.addOpcao(salvar);
        super.addOpcao(cancelar);
    }

    /**
     * persiste as auterações no banco de dados
     */
    private void salvarProjeto() {
        if(!super.todosOsCamposEstaoValidos())
            return;
        projetoSendoEditado.setNome(campoNome.getText());
        projetoSendoEditado.setDescricao(campoDescricao.getText());
        projetoSendoEditado.setSrc(campoSrc.getText());

        if(!ProjetoController.atualizarProjeto(projetoSendoEditado)){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "Erro ao atualizar os dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cancelarEdicao();
    }

    /**
     * Limpa todos os campos e retorna a lista de projetos
     */
    private void cancelarEdicao() {
        super.limpar();
        SistemaController.setPainelDeTrabalho("PROJETOS");
        projetoSendoEditado = null;
    }

    /**
     * Carrega o formulario com as informações do projeto passado por paramentro.
     * @param projetoAdapter
     */
    public void setProjeto(ProjetoAdapter projetoAdapter){
        projetoSendoEditado = projetoAdapter;
        super.limparConteudo();
        super.addConteudo(new FormularioEditar(projetoAdapter));
    }

    class FormularioEditar extends Formulario {

        FormularioEditar(ProjetoAdapter projeto){
            super();
            campoNome.setText(projeto.getNome());
            campoDescricao.setText(projeto.getDescricao());
            campoSrc.setText(projeto.getSrc());
            campoPrefixoCT.setText(projeto.getPrefixoCT());
            campoPrefixoCU.setText(projeto.getPrefixoCU());
            campoPrefixoRT.setText(projeto.getPrefixoRT());

            campoPrefixoCT.setEditable(false);
            campoPrefixoCU.setEditable(false);
            campoPrefixoRT.setEditable(false);

        }


    }


}
