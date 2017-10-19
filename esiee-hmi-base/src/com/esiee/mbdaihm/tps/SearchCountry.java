/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.layout.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Kévin
 */
public class SearchCountry extends JPanel{
    private JTextField tfSearch;
    private JLabel lSearch;
    private String country;

    public SearchCountry() {
        tfSearch = new JTextField(15);
        lSearch = new JLabel("Search: ");
        this.setLayout(new BorderLayout());
        this.add(lSearch, BorderLayout.WEST);
        this.add(tfSearch,BorderLayout.SOUTH);
        
        tfSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                country = tfSearch.getText();
            }
        });
        
    }
    
    
    
}
