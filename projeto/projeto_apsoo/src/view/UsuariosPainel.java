package view;

import controller.adapters.UsuarioAdapter;
import controller.UsuarioController;
import resources.Cores;
import resources.Fontes;
import resources.Icones;
import resources.Strings;
import view.Componetes.Botao;
import view.Componetes.Painel;
import view.Componetes.PainelItem;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsavel pelo view da aba usuarios.
 */
public class UsuariosPainel extends Painel{

    private Dimension tamanhoItens = new Dimension(200, 200);

    public UsuariosPainel(){
        super(Strings.TITULO_PAINEL_USUARIO);
        super.setAlturaELarguraDosItensDoConteudo(tamanhoItens.height, tamanhoItens.width);
        carregarListaDeUsuario();
    }

    /**
     * Carrega/atualiza a lista de usuarios no painel.
     */
    private void carregarListaDeUsuario () {
        super.setVisible(false);
        super.limparConteudo();

        UsuarioController.getListaDeUsuarios().forEach(u -> {
            super.addConteudo(new PainelInfos(u.getImgPerfil(), u));
        });
        super.addConteudo(new Botao());
        super.setVisible(true);
    }

    /**
     * classe que modela o painel que contem as informações de um usuario visivel a todos os usuarios.
     */
    private class PainelInfos extends PainelItem{

        JLabel imgPerfil;
        JLabel nomeUsuario;
        JLabel cargoUsuario;

        public PainelInfos(ImageIcon imgPerfil, UsuarioAdapter usuario) {
            super.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
            super.setPreferredSize(tamanhoItens);
            super.setMaximumSize(tamanhoItens);
            super.setCorDeFundoNormal(Cores.TEXTO_MENU_ESQUERDO);
            super.setCorDeFundoHover(Cores.FUNDO_MENU_ESQUERDO);

            if (imgPerfil != null)
                this.imgPerfil = new JLabel(imgPerfil);
            else
                this.imgPerfil = new JLabel(Icones.imgPerfil);

            this.nomeUsuario = new JLabel(usuario.getNome());
            this.cargoUsuario = new JLabel(usuario.getCargo());

            super.add(this.imgPerfil);
            super.add(this.nomeUsuario);
            super.add(this.cargoUsuario);
            iniciarEstilo();
            iniciarListaners();
        }

        private void iniciarEstilo(){
            this.nomeUsuario.setFont(Fontes.CARD_NOME_USUARIO);
            this.nomeUsuario.setForeground(Cores.TEXTOS);
            this.cargoUsuario.setFont(Fontes.CARD_CARGO_USUARIO);
            this.cargoUsuario.setForeground(Cores.TEXTOS);
        }

        private void iniciarListaners(){

        }
    }
}
