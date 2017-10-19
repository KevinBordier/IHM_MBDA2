/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author ELODIECAROY
 */
public class saveDialog extends JDialog{
    public JRadioButton excelB;
    public JRadioButton csvB;
    public JRadioButton jpgB;
    public JTextField jText;
    public JButton browse;
    public JButton save;
    public JButton cancel;
    public JPanel jP;
    
    public saveDialog(Dialog owner) {
        super(owner);
    }

    saveDialog(ProjetCaroyBordier aThis) {
        super(aThis, "save", true);
        this.setSize(400, 200);
        
        jP = new JPanel();
        this.add(jP);
        
        excelB = new JRadioButton("excel");
        csvB = new JRadioButton("csv");
        jpgB = new JRadioButton("jpg");  
        
        jText = new JTextField("", 10);        
        browse = new JButton("browse");       
        save = new JButton("save");       
        cancel = new JButton("cancel");
        
        jP.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,20,0,0);
        c.weightx = 1;
        jP.add(excelB, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,0);
        c.weightx = 1.5;
        jP.add(csvB, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,0);
        c.weightx = 2;
        jP.add(jpgB, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,20,0,0);
        c.weightx = 1;
        jP.add(jText, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,20,0,0);
        c.weightx = 1.5;
        jP.add(browse, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(20,20,0,0);
        c.weightx = 1;
        jP.add(save, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(20,20,0,20);
        c.weightx = 1.5;
        jP.add(cancel, c);
        
        
        /*jP.add(excelB);
        jP.add(csvB);
        jP.add(jpgB);
        jP.add(jText);
        jP.add(browse);*/
        
        /*excelB.setVisible(true);
        csvB.setVisible(true);
        jpgB.setVisible(true);*/
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
