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

    /**
     * Este metodo será chamado imediatamente antes do painel ficar visivel para o usuario
     */
    void preProcessamentoAntesDeAbrir();

    /**
     *Este metodo é chamado logo antes do painel ser removido da tela do usuario
     */
    void preProcessamentoAntesDeFechar();

    /**
     * 
     * @return Este metodo deve retornar o componente que contenha todo o conteudo 
     * do painel de trabalho.
     */
    Component getCompomenteRaiz();

    /**
     * 
     * @return Retorna um dos item no enum <link>TipoDePainel</link>;
     */
    TipoDePainel pegarTipoDePainel();

    /**
     * Metodo cnhamado quando o usuario clicar no botão Novo. quando o painel Estiver ativo.
     * @param e evento de mouse.
     */
    void btnNovoOnClick(MouseEvent e);
    /**
     * Metodo cnhamado quando o usuario clicar no botão Salvar. quando o painel Estiver ativo.
     * @param e evento de mouse.
     */
    void btnSalvarOnClick(MouseEvent e);
    /**
     * Metodo cnhamado quando o usuario clicar no botão Cancelar. quando o painel Estiver ativo.
     * @param e evento de mouse.
     */
    void btnCancelarOnClick(MouseEvent e);
    /**
     * Metodo cnhamado quando o usuario clicar no botão Limpar. quando o painel Estiver ativo.
     * @param e evento de mouse.
     */
    void btnLimparOnClick(MouseEvent e);
    
    /**
     * Metodo que passa as informações enviadas do painel anterior para o proximo painel.
     * @param dados Map<?, ?> que contem os dados enviados para o proximo painel.
     */
    void setData(Map<?, ?> dados);
}
