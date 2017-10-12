/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

/**
 *
 * @author ELODIECAROY
 */
public class saveDialog extends JDialog{
    public JRadioButton excelB;
    public JRadioButton csvB;
    public JRadioButton jpgB;
    public saveDialog(Dialog owner) {
        super(owner);
    }

    saveDialog(ProjetCaroyBordier aThis) {
        super(aThis, "save", true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
