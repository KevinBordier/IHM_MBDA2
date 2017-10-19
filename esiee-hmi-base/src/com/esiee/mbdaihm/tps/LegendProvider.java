/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import static com.esiee.mbdaihm.tps.ColorProvider.max;
import static com.esiee.mbdaihm.tps.ColorProvider.min;
import static com.esiee.mbdaihm.tps.ColorProvider.step;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import static java.awt.PageAttributes.ColorType.COLOR;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kévin
 */
public class LegendProvider extends JPanel{
    private JLabel legend = new JLabel("Legend:");
    public static Color col1;
    public static Color col2;
    public static Color col3;
    public static Color col4;
    public static Color col5;
    public static Color col6;
   // private Graphics g;
    
    public LegendProvider(){
        col1 = Color.CYAN;
        col2 = Color.RED;
        col3 = Color.GREEN;
        col4 = Color.YELLOW;
        col5 = Color.PINK;
        col6 = Color.GRAY;
        this.setLayout(new BorderLayout());        
        this.add(legend, BorderLayout.BEFORE_FIRST_LINE);
    }



    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(col1);
        g.fillRect(0, 25, 30, 15);
        g.setColor(Color.BLACK);
        g.drawString("<80%", 35, 36);
        
        g.setColor(col2);
        g.fillRect(0, 50, 30, 15);
        g.setColor(Color.BLACK);
        g.drawString("60~80%", 35, 62);
        
        g.setColor(col3);
        g.fillRect(0, 75, 30, 15);
        g.setColor(Color.BLACK);
        g.drawString("40~60%", 35, 86);
        
        g.setColor(col4);
        g.fillRect(0, 100, 30, 15);
        g.setColor(Color.BLACK);
        g.drawString("20~40%", 35, 110);
        
        g.setColor(col5);
        g.fillRect(0, 125, 30, 15);
        g.setColor(Color.BLACK);
        g.drawString("0~20%", 35, 136);
        
        g.setColor(col6);
        g.fillRect(0, 150, 30, 15);
        g.setColor(Color.BLACK);
        g.drawString("Unkown", 35, 162);
    }    
}
