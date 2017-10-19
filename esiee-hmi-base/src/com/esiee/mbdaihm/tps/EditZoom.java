/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Kévin
 */
public class EditZoom extends JPanel{
    public static int factor = 0;
    private JSpinner zoomFactor;
    private JLabel lZoom;
    
    public EditZoom(){
        zoomFactor = new JSpinner();
        Component mySpinnerEditor = zoomFactor.getEditor();
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
        jftf.setColumns(2);
        lZoom = new JLabel("Zoom :");
        this.setLayout(new BorderLayout());
        this.add(lZoom, BorderLayout.WEST);
        this.add(zoomFactor, BorderLayout.EAST);
        
        zoomFactor.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                factor = (Integer)zoomFactor.getValue();
            }
        });
    }
    
}
