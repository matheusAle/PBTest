/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Map;
/**
 *
 * @author matheus
 */
public interface Painel {
    void preProcessamentoAntesDeAbrir();
    void preProcessamentoAntesDeFechar();
    Component getCompomenteRaiz();
    TipoDePainel pegarTipoDePainel();
    void btnNovoOnClick(MouseEvent e);
    void btnSalvarOnClick(MouseEvent e);
    void btnCancelarOnClick(MouseEvent e);
    void btnLimparOnClick(MouseEvent e);
    void setData(Map<?, ?> dados);
}
