/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Componetes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author matheus
 */
public class CampoDeTestoFocusListaner implements FocusListener{
    private Color cor;
    private Component c;
    private Color CorAnterior;
    public CampoDeTestoFocusListaner(Color cor, Component c) {
        this.cor = cor;
        this.c = c;
        CorAnterior = c.getBackground();
    }
    
    
    @Override
    public void focusGained(FocusEvent e) {
        c.setBackground(cor);
    }

    @Override
    public void focusLost(FocusEvent e) {
        c.setBackground(CorAnterior);
    }
    
}
