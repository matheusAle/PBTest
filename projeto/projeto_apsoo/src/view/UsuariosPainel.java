package view;

import controller.UsuarioController;
import resources.Cores;
import resources.Fontes;
import resources.Icones;
import resources.Strings;
import view.Componetes.Painel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe responsavel pelo view da aba usuarios.
 */
public class UsuariosPainel {

    private Painel painel;
    private Dimension tamanhoItens = new Dimension(200, 200);

    public UsuariosPainel(){
        painel = new Painel(Strings.TITULO_PAINEL_USUARIO);
        painel.setAlturaELarguraDosItensDoConteudo(tamanhoItens.height, tamanhoItens.width);
        carregarListaDeUsuario();
    }

    /**
     * Carrega/atualiza a lista de usuarios no painel.
     */
    private void carregarListaDeUsuario () {
        painel.setVisible(false);
        painel.limparConteudo();

        UsuarioController.getListaDeUsuarios().forEach(u -> {
            painel.addConteudo(new PainelInfos(u.getImgPerfil(), u.getNome(), u.getCargo()));
        });

        painel.setVisible(true);
    }

    /**
     * Retorna um objeto swing do tipo <link>JScrollPane</link>.
     * @return
     */
    public JScrollPane getPainel() {
        return painel.getPainel();
    }

    /**
     * classe que modela o painel que contem as informações de um usuario visivel a todos os usuarios.
     */
    private class PainelInfos extends JPanel{

        JLabel imgPerfil;
        JLabel nomeUsuario;
        JLabel cargoUsuario;

        public PainelInfos(ImageIcon imgPerfil, String nomeUsuario, String cargoUsuario) {
            super.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
            super.setPreferredSize(tamanhoItens);
            super.setMaximumSize(tamanhoItens);
            super.setBackground(Cores.FUNDO_ITEM_LISTA);
            if (imgPerfil != null)
                this.imgPerfil = new JLabel(imgPerfil);
            else
                this.imgPerfil = new JLabel(Icones.imgPerfil);

            this.nomeUsuario = new JLabel(nomeUsuario);
            this.nomeUsuario.setFont(Fontes.CARD_NOME_USUARIO);
            this.nomeUsuario.setForeground(Cores.TEXTOS);

            this.cargoUsuario = new JLabel(cargoUsuario);
            this.cargoUsuario.setFont(Fontes.CARD_CARGO_USUARIO);
            this.cargoUsuario.setForeground(Cores.TEXTOS);

            super.add(this.imgPerfil);
            super.add(this.nomeUsuario);
            super.add(this.cargoUsuario);

            super.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //TODO Impmementar a janela de detalhes do usuario
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    PainelInfos.super.setBackground(Cores.FUNDO_ITEM_LISTA_HOVER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    PainelInfos.super.setBackground(Cores.FUNDO_ITEM_LISTA);
                }
            });
        }
    }
}
