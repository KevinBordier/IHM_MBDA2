/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kévin
 */
public class LegendProvider extends JPanel implements LegendListener{

    
    private static LegendProvider instance = new LegendProvider();
    private JLabel legend = new JLabel("Legend:");
    private JLabel leg1;
    private JLabel leg2;
    private JLabel leg3;
    private JLabel leg4;
    private JLabel leg5;
    private JLabel leg6;
    public static Color col1;
    public static Color col2;
    public static Color col3;
    public static Color col4;
    public static Color col5;
    public static Color col6;
    private double myMin;
    private double myStep;
   // private Graphics g;
    
    public LegendProvider(){
        leg1 = new JLabel("");
        leg2 = new JLabel("");
        leg3 = new JLabel("");
        leg4 = new JLabel("");
        leg5 = new JLabel("");
        leg6 = new JLabel("");
        col1 = Color.CYAN;
        col2 = Color.RED;
        col3 = Color.GREEN;
        col4 = Color.YELLOW;
        col5 = Color.PINK;
        col6 = Color.GRAY;
        /*this.setLayout(new BorderLayout());        
        this.add(legend, BorderLayout.BEFORE_FIRST_LINE);
        this.add(leg1, BorderLayout.CENTER);*/
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 275;
        c.insets = new Insets(10, 0, 0, 0);
        this.add(leg1, c);
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 500;
        this.add(leg2, c);
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 700;
        this.add(leg3, c);
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 900;
        this.add(leg4, c);
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 1100;
        this.add(leg5, c);
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 1300;
        this.add(leg6, c);
    }



    @Override
    protected void paintComponent(Graphics g) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        myMin = ColorProvider.getMin();
        myStep = ColorProvider.getStep();
        g.setColor(col1);
        g.fillRect(0, 15, 30, 15);
        g.setColor(Color.BLACK);
        //g.drawString(">"+df.format(myMin+4*myStep), 35, 36);
        
        g.setColor(col2);
        g.fillRect(0, 40, 30, 15);
        g.setColor(Color.BLACK);
        //g.drawString("<=" +df.format(myMin+4*myStep), 35, 62);
        
        g.setColor(col3);
        g.fillRect(0, 67, 30, 15);
        g.setColor(Color.BLACK);
        //g.drawString("<=" +df.format(myMin+3*myStep), 35, 86);
        
        g.setColor(col4);
        g.fillRect(0, 93, 30, 15);
        g.setColor(Color.BLACK);
        //g.drawString("<="+df.format(myMin+2*myStep), 35, 110);
        
        g.setColor(col5);
        g.fillRect(0, 118, 30, 15);
        g.setColor(Color.BLACK);
        //g.drawString("<="+df.format(myMin+myStep), 35, 136);
        
        g.setColor(col6);
        g.fillRect(0, 145, 30, 15);
        g.setColor(Color.BLACK);
    }    

    @Override
    public void setMin(double min) {
        //java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        myMin = min;
    }

    @Override
    public void setStep(double step) {
        //Graphics g = null;
        myStep = step;
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        //g.drawString(">"+df.format(myMin+4*myStep), 35, 36);
        leg1.setText("> "+df.format(myMin+4*myStep));
        leg2.setText("<= " +df.format(myMin+4*myStep));
        leg3.setText("<= " +df.format(myMin+3*myStep));
        leg4.setText("<= " +df.format(myMin+2*myStep));
        leg5.setText("<= " +df.format(myMin+myStep));
        leg6.setText("Unknown");
    }
    
    
}
