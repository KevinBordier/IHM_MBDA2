// DRAWING PANEL !!!!!!!!!!


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import com.esiee.mbdaihm.datamodel.DataManager;
import com.esiee.mbdaihm.datamodel.countries.Country;
import com.esiee.mbdaihm.datamodel.countries.Polygon;
import com.esiee.mbdaihm.datamodel.indicators.Indicator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ELODIECAROY
 */
public class Map extends JPanel implements YearListener, ZoomListener{

    public static Map instance = new Map();
    private float zoom;
    private int x;
    private int y;
    private int diffX;
    private int diffY;
//<<<<<<< HEAD
    private double max;
    private double min;
    private double step;
    private int year;
    private String nomindic;
    private CalculIndicator myColor;

//=======
    private double minMap;
    private double stepMap;
    //private int year;
    private int currentYear;
    private List<LegendListener>listeners = new ArrayList<LegendListener>();
    
//>>>>>>> Kev
    public Map() {
            zoom = 1.0f;
            x = 0;
            y = 0;
            diffX=0;
            diffY=0;
            //year = new EditYear();
            minMap = 0;
            stepMap = 0;
            currentYear = 1960;
            
            
            addMouseWheelListener(new MouseWheelListener(){
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    if( notches <0){
                        //zoom in
                        zoom = zoom*1.1f;
                        if(zoom ==0){
                            zoom+=1;
                        }
                    }else{
                        //zoom out
                        zoom = zoom/1.1f;
                        if(zoom ==0){
                            zoom+=1;
                        }
                    }
                    Map.this.repaint();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            });
            addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    x = e.getX();
                    y = e.getY();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            addMouseMotionListener(new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent e) {
                    int eX = e.getX();
                    int eY = e.getY();
                    diffX += eX-x;
                    diffY += eY-y;
                    x= e.getX();
                    y=e.getY();
                    Map.this.repaint();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            });
        
    }
//<<<<<<< HEAD
    
    public void setNomIndic(String str){
        nomindic = str;
    }
//=======
    public static Map getInstance(){
        return instance;
    }
       
//>>>>>>> Kev
    
    @Override
    protected void paintComponent(Graphics g){
        
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        AffineTransform tr = g2d.getTransform();
        tr.translate(getWidth()/2,getHeight()/2);
        tr.translate(diffX, diffY);
        tr.scale((zoom*getWidth())/(2*180f), (-1*zoom*getHeight())/(2*90f));
        g2d.setTransform(tr);
        g2d.setStroke(new BasicStroke(1.0f/zoom));
        
        for (Country country : DataManager.INSTANCE.getCountries()) {
           // System.out.println(country.getName());
            List<Polygon> poly = country.getGeometry().getPolygons();
            for (Polygon polygon : poly) {
                int i =0;
                GeneralPath path = new GeneralPath();
                path.moveTo(polygon.points[i].lon, polygon.points[i].lat);
                for(i=1;i<polygon.points.length;i++){
                    path.lineTo(polygon.points[i].lon, polygon.points[i].lat);
                }
                path.closePath();
                g2d.setPaint(Color.BLACK);
                g2d.draw(path);
//<<<<<<< HEAD
                //g2d.setPaint(ColorProvider.getColorForCountry(country,"SP.DYN.LE00.FE.IN",1995));
                //g2d.setPaint(Color.GRAY);
                if(nomindic != null){
                   g2d.setPaint(ColorProvider.getColorForCountry(country,nomindic,year));
                }/*
=======
                g2d.setPaint(ColorProvider.getColorForCountry(country,"SP.DYN.LE00.FE.IN",currentYear));
>>>>>>> Kev*/
                g2d.fill(path);
            }      
        }
        if(minMap !=ColorProvider.getMin() && stepMap !=ColorProvider.getStep() ){
            for(LegendListener ll : listeners){
                ll.setMin(ColorProvider.getMin());
                ll.setStep(ColorProvider.getStep());
            }
        }
    } 
    
    @Override
    public void yearModified(int year) {
        currentYear = year;
        Map.this.repaint();
    }

    @Override
    public void zoomOutModified(int pZoom) {
        zoom = pZoom*1.1f;
        Map.this.repaint();
    }

    @Override
    public void zoomInModified(int pZoom) {
        zoom = pZoom/1.1f;
        Map.this.repaint();
    }
    
    @Override
    public void refreshZoom(int pZoom) {
        zoom = 1.0f;
        Map.this.repaint();
    }

    public void addListeners(LegendListener toAdd){
        listeners.add(toAdd);
    }
    
    
    
}
