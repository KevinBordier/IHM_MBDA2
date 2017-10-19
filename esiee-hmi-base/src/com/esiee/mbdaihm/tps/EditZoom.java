/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<ZoomListener>listeners = new ArrayList<ZoomListener>();
    private int factor;
    private JSpinner zoomFactor;
    private JLabel lZoom;
    
    public EditZoom(){
        factor = 0;
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
                if((Integer)zoomFactor.getValue()==0){
                    factor = (Integer)zoomFactor.getValue();
                    for(ZoomListener zl : listeners){
                        zl.refreshZoom(factor);
                    }
                }
                else{
                    if(factor > (Integer)zoomFactor.getValue())
                {
                    factor = (Integer)zoomFactor.getValue();
                    for(ZoomListener zl : listeners){
                        zl.zoomInModified(factor);
                    }                
                }
                else{
                    factor = (Integer)zoomFactor.getValue();
                    for(ZoomListener zl : listeners){
                        zl.zoomOutModified(factor);
                    }
                }
                }
                
            }
        });
        //zoomFactor.add
    }
    
    public int getFactor(){
        return factor;
    }
    
    public void addListener(ZoomListener toAdd){
        listeners.add(toAdd);
    }
    
}
