package view;

import controller.CasoDeUsoController;
import controller.SistemaController;
import controller.exceptions.CasoDeUsoExeption;
import model.CasoDeUso;
import resources.Cores;
import resources.Strings;
import view.Componetes.Botao;

import javax.swing.*;
import java.awt.*;

public class EditarCasoDeUsoPainel extends CriarCasoDeUsoPainel {

    public EditarCasoDeUsoPainel (){
        super("editar Caso de Uso");
        carregarOpcoes();
        super.setQuandoAtivo((e) -> setCasoDeUso(CasoDeUsoController.getCasoDeUsoEditavel()));
    }
    /**
     * Carrega as opçoes deste painel
     */
    private void carregarOpcoes(){
        Botao salvar = new Botao();
        salvar.setCorDeFundoNormal(Cores.FUNDO_BOTAO);
        salvar.setCorDoTextoNormal(Cores.TEXTOS);
        salvar.setCorDeFundoHover(Color.red);
        salvar.setText(Strings.EDITAR_PROJ_TEXTO_BTN_SALVAR);
        salvar.setOnMouseClick((e) -> this.salvarEdicao());

        Botao cancelar = new Botao();
        cancelar.setCorDeFundoNormal(Cores.FUNDO_MENU_ESQUERDO);
        cancelar.setCorDoTextoNormal(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDeFundoHover(Cores.TEXTO_MENU_ESQUERDO);
        cancelar.setCorDoTextoHover(Cores.TEXTOS);
        cancelar.setText(Strings.EDITAR_PROJ_TEXTO_BTN_CANCELAR);
        cancelar.setOnMouseClick((e) -> this.cancelarEdicao());

        super.addOpcao(salvar);
        super.addOpcao(cancelar);
    }

    private void cancelarEdicao() {
        SistemaController.setPainelDeTrabalho("CASOS_DE_USO");
    }


    public void setCasoDeUso(CasoDeUso c){
        super.limparConteudo();
        super.addConteudo(new Formulario(c));
    }

    private void salvarEdicao() {
        try {
            CasoDeUsoController.salvarMudancas(campoNome.getText(), campoObjetivo.getText(), campoAtores.getText(), campoDescricao.getText());
            cancelarEdicao();
        }catch (CasoDeUsoExeption e ){
            JOptionPane.showMessageDialog(SistemaController.JANELA, "não foi possivel salvar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private class Formulario extends CriarCasoDeUsoPainel.Formulario {


         Formulario(CasoDeUso casoDeUsoEditavel) {
            super();

            campoNome.setText(casoDeUsoEditavel.getNome());
            campoDescricao.setText(casoDeUsoEditavel.getDescricao());
            campoObjetivo.setText(casoDeUsoEditavel.getObjetivo());
            campoAtores.setText(casoDeUsoEditavel.getAtores());
        }
    }
}
