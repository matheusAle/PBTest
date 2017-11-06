/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UsuarioController;
import java.util.Map;

/**
 *
 * @author matheus
 */
public class mainApp {
    private static Janela JANELA; 
    
    
    public static void trocarDePainel(Paineis p, Map<?, ?> dados){
        JANELA.trocarDePainel(p, dados);
    }
    
    
    public static class PaineisDeTRabalho {
        static final Painel USUARIOS = new UsuariosPainel();
        static final Painel PROJETOS = new ProjetosPainel();
        static final Painel FORMULARIO_DE_CADASTRO_DE_PROJETO = new FormularioDeProjetoPainel();
        static final Painel CASOS_DE_USO = new CasosDeUsoPainel();
        static final Painel FORMULARIO_DE_CADASTRO_DE_CASO_DE_USO = new FormularioDeCasoDeUsoPainel();
        static final Painel CASOS_DE_TESTE = new CasosDeTestePainel();
        static final Painel FORMULARIO_DE_CADASTRO_DE_CASOS_DE_TESTE = new FormularioDeCasoDeTestePainel();
        static final Painel ROTEIROS_DE_TESTE = new RoteirosDeTestePainel();
        static final Painel FORMULARIO_DE_CADASTRO_DE_ROTEIROS_DE_TESTE = new FormularioDeRoteirDeTestePainel();
        static final Painel EXECUCAO_DE_ROTEIRO = new ExecusaoDeTestesPainel();
        static final Painel MATRIZ = new MatrizPainel();
        /*
        static final Painel MATRIZ_DE_RASTREABIBLIDADE = new MatrizDeRastreabilidadePainel();
        static final Painel EDITAR_CASO_DE_USO = new EditarCasoDeUsoPainel();
        */
    }
    
    
    public static void main(String args[]) {
        UsuarioController.fazerLogin("root@root", "root");
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JANELA = new Janela();
                JANELA.setVisible(true);
            }
        });
    }
    
}
