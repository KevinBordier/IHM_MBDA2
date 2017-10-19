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
import static com.esiee.mbdaihm.tps.EditYear.currentYear;
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
    public static double max;
    public static double min;
    public static double step;
    
    
    
    static Paint getColorForCountry(Country country, String codeIndic) {
        Indicator toTest = DataManager.INSTANCE.getIndicators().
                    filter(i->i.getCode().equals(codeIndic)).findFirst().get();

        if(toTest != lastComputedIndicator){
            List<RawWDIData> myIndicatorToMap
                        = WDIDataDecoder.decode(Launch.WDI_FOLDER,toTest.getCode() );
            DoubleSummaryStatistics stats = myIndicatorToMap.stream().
                    mapToDouble(rd -> rd.getValueForYear(""+currentYear)).
                    filter(d -> !(Double.isNaN(d))).
                    summaryStatistics();

            max = stats.getMax();
            min = stats.getMin();
            step = (max-min)/5;
            lastComputedIndicator = toTest;
        }

        double val = country.getValueForYear(currentYear);
        if(val>min+4*step){
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
        //LegendProvider.setLegend();
        return col6;
    }
    
}
