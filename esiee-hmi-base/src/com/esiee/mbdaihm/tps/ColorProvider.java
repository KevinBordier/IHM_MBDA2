/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm.tps;

import com.esiee.mbdaihm.Launch;
import com.esiee.mbdaihm.dataaccess.wdi.RawWDIData;
import com.esiee.mbdaihm.dataaccess.wdi.WDIDataDecoder;
import com.esiee.mbdaihm.datamodel.DataManager;
import com.esiee.mbdaihm.datamodel.countries.Country;
import com.esiee.mbdaihm.datamodel.indicators.Indicator;
import static com.esiee.mbdaihm.tps.LegendProvider.*;
import java.awt.Color;
import java.awt.Paint;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 *
 * @author ELODIECAROY
 */
class ColorProvider {

    private static Indicator lastComputedIndicator = null;
    private static double max;
    private static double min;
    private static double step;
    private double myMin;
    
    
    static Paint getColorForCountry(Country country, String codeIndic, int currentYear) {
        Indicator toTest = DataManager.INSTANCE.getIndicators().
                    filter(i->i.getName().equals(codeIndic)).findFirst().get();
        
        if(toTest != lastComputedIndicator){
            List<RawWDIData> myIndicatorToMap
                        = WDIDataDecoder.decode(Launch.WDI_FOLDER,toTest.getCode() );
            
            DoubleSummaryStatistics stats = myIndicatorToMap.stream().
                    mapToDouble(rd -> rd.getValueForYear(""+currentYear)).
                    filter(d -> !(Double.isNaN(d))).
                    summaryStatistics();
            /*System.out.println("max: " + stats.getMax());
            System.out.println("min: " + stats.getMin());
            System.out.println("year: " + currentYear);*/
            //System.out.println("summaryStatistics = " + stats);
            max = stats.getMax();
            if(max == Double.NEGATIVE_INFINITY){
                max = Double.NaN;
            }
            min = stats.getMin();
            System.out.println(min);
            if(min == Double.POSITIVE_INFINITY){
                min =Double.NaN;
            }
            step = (max-min)/5;
            lastComputedIndicator = toTest;
            DataManager.INSTANCE.setCurrentIndicator(lastComputedIndicator);
        }

        double val = country.getValueForYear(currentYear);
        if(val>min+4*step){
            //System.out.println("legende: " + country.getName()+ "-> "+ min+4*step + " val: " + val);
            return col1;
        }
        if(val<=min+4*step){
            return col2;
        }if(val<=min+3*step){
            return col3;
        }if(val<=min+2*step){
            return col4;
        }if(val<=min+step){
            return col5;
        }
        return col6;
    }

    static double getMin() {
        System.out.println(min);
       return min; 
    }

    static double getStep() {
        return step;
    }
       
}
