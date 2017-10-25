/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import com.esiee.mbdaihm.datamodel.DataManager;
import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
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
        c.gridwidth = 2;
        jP.add(jText, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0,20,0,20);
        c.weightx = 1.5;
        jP.add(browse, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(20,20,0,0);
        c.weightx = 1;
        c.gridwidth = 1;
        jP.add(save, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(20,20,0,20);
        c.weightx = 1.5;
        c.gridwidth = 1;
        jP.add(cancel, c);
        
        browse.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parcours();
            }
        });
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sauvegarde();
                } catch (AWTException ex) {
                    Logger.getLogger(saveDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(saveDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ferme();
            }
        });
        excelB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkE();
            }
        });
        csvB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkC();
            }
        });
        jpgB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkJ();
            }
        });
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void parcours(){
        String str ="";
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showOpenDialog(this);
        str += jfc.getCurrentDirectory();
        jText.setText(str);
    }
    
    public void ferme(){
        this.dispose();
    }
    public void checkE(){
        csvB.setSelected(false);
        jpgB.setSelected(false);
    }
    public void checkC(){
        excelB.setSelected(false);
        jpgB.setSelected(false);
    }
    public void checkJ(){
        csvB.setSelected(false);
        excelB.setSelected(false);
    }
    
    public void sauvegarde() throws AWTException, IOException{
        
        String namefile = "";
        String indic = DataManager.INSTANCE.getCurrentIndicator().getCode();
        indic = indic.replace(".", "_");
        if(jText.getText().length() > 0){
            namefile = jText.getText();
        }
        else System.out.println("error tf");
        namefile += File.separator + indic;
        
        if(excelB.isSelected() == true){
            namefile += ".xlsx";
        }
        else if(csvB.isSelected() == true){
            namefile += ".csv";
        }
        else if(jpgB.isSelected() == true){
            namefile += ".jpg";
            this.dispose();
            
            Container content = this.getParent();
            //BufferedImage image = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "jpg", new File(namefile));
        }
        else System.out.println("error rb");

        System.out.println(namefile);
    }
    
}
