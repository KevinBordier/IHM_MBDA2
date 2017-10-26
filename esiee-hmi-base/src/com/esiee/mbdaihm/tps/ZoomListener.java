/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

/**
 *
 * @author Kévin
 */
public interface ZoomListener {
    public void zoomInModified(int zoom);
    public void zoomOutModified(int zoom);
    public void refreshZoom(int zoom);
}
