/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Kévin
 */
public class EditYear extends JPanel{
    
    private List<YearListener>listeners = new ArrayList<YearListener>();
    private int currentYear;
    JLabel lYear= new JLabel("Year:");
    JTextField tfYear = new JTextField(""+1960, 6);
    JSlider sYear = new JSlider(JSlider.HORIZONTAL,1960,2015, 1960);
    
    public EditYear(){
        //instance.
        currentYear = 1960;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        lYear.setOpaque(true);
        tfYear.setOpaque(true);
        sYear.setPaintLabels(true);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, -30, 0, 0);
        c.weightx = 1;
        this.add(lYear, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 30, 0, 0);
        c.weightx = 1.5;
        this.add(tfYear, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(sYear, c);
        
        
        
        sYear.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if(!source.getValueIsAdjusting()){
                    int changeYear = (int)source.getValue();
                    tfYear.setText(""+changeYear);
                    currentYear = changeYear;
                    for(YearListener yl : listeners){
                        yl.yearModified(currentYear);
                    }
                }
            }
        });
        tfYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tfYear.getText();
                currentYear = Integer.parseInt(text);
                sYear.setValue(currentYear);
                for(YearListener yl : listeners){
                    yl.yearModified(currentYear);
                }
            }
        });
    }
    
    
    public int getCurrentYear(){
        return currentYear;
    }
    
    public void addListener(YearListener toAdd){
        listeners.add(toAdd);
    }
}
