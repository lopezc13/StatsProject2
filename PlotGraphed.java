
//package org.jfree.chart.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class PlotGraphed extends ApplicationFrame {

	   
 
     
    public PlotGraphed(final String title) {

        super(title);
        
        
        
        Plot temp = new Plot(1,10,1);
		temp.PlotPoints();
		temp.getRollingAverage(5);
		//temp.saltPoints(40);
		//temp.smoothPlot(5);
        final XYSeries series = new XYSeries("XY Data");
        for(int i = 0; i <10;i++) {
        	
        	series.add(temp.getX(i),temp.getY(i));
        }
        
        
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart("Smoothed Data",    "X",  "Y",  data,  PlotOrientation.VERTICAL, true, true,false  );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 470));
        setContentPane(chartPanel);

    }

    
    public static void main(final String[] args) {

        final PlotGraphed demo = new PlotGraphed("Apache");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
     

    }

}

           