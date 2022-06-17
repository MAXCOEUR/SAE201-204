/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.RechercheDansBDD;

/**
 *
 * @author Maxen
 */


import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class Graph{


//	public Graph(String query) {
//        super("temprature");
//        XYDataset dataset = createDataset(query);
//        JFreeChart chart = createChart(dataset);
//        ChartPanel chartPanel = new ChartPanel(chart, false);
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//        chartPanel.setMouseZoomable(true, false);
//        setContentPane(chartPanel);
//    }

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset,String titre) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            titre,  // title
            "heure",             // x-axis label
            titre,   // y-axis label
            dataset,            // data
            true,               // create legend?
            false,               // generate tooltips?
            false               // generate URLs?
        );

//        chart.setBackgroundPaint(null);
//
//        XYPlot plot = (XYPlot) chart.getPlot();
//        plot.setBackgroundPaint(null);
//        plot.setDomainGridlinePaint(Color.white);
//        plot.setRangeGridlinePaint(Color.white);
//        plot.setAxisOffset(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
//        plot.setDomainCrosshairVisible(true);
//        plot.setRangeCrosshairVisible(true);
//        XYItemRenderer r = plot.getRenderer();
//        if (r instanceof XYLineAndShapeRenderer) {
//            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
//            renderer.setBaseShapesVisible(true);
//            renderer.setBaseShapesFilled(true);
//        }
        
//        DateAxis axis = (DateAxis) plot.getDomainAxis();
//        axis.setDateFormatOverride(new SimpleDateFormat("HH-mm-ss"));
        
        return chart;

    }

    /**
     * Creates a dataset, containing a typical labour-daily timeseries.
     * Meaning: Daily intervals, but no data points on sat./sun.
     *          Furthermore it is possible, that datapoints don't contain a value (null) because
     *          for some reason it was not supplied by the data supplier.
     *          
     * Behaviour: If value == null, then line is interrupted. If complete data points are missing, 
     *            the line is continued from the last datapoint to the next datapoint.
     *            See dataset definition with comments below.
     *
     * @return The dataset.
     */
//    private static XYDataset createDataset(String query) throws SQLException {
//        
//        
//        return dataset;
//    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static ChartPanel GraphTemp(String query) {
        try {
            ResultSet resultSet = DatabaseConnection.Requete(query);
            
            TimeSeries s1 = new TimeSeries("", Second.class);
            while (resultSet.next()) {
                final Day today=new Day();
                s1.add(new Second(resultSet.getInt("s"), new Minute(resultSet.getInt("m"), new Hour(resultSet.getInt("h"),today))), resultSet.getDouble("T"));
            }
            
            
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(s1);
            dataset.setDomainIsPointsInTime(true);
            
            
            
            
            JFreeChart chart = createChart(dataset,"Temperature");
            ChartPanel chartPanel = new ChartPanel(chart, false);
            return chartPanel;
        } catch (SQLException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static ChartPanel GraphHum(String query) {
        try {
            ResultSet resultSet = DatabaseConnection.Requete(query);
            
            TimeSeries s1 = new TimeSeries("", Second.class);
            while (resultSet.next()) {
                final Day today=new Day();
                s1.add(new Second(resultSet.getInt("s"), new Minute(resultSet.getInt("m"), new Hour(resultSet.getInt("h"),today))), resultSet.getDouble("HU"));
            }
            
            
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(s1);
            dataset.setDomainIsPointsInTime(true);
            
            
            
            
            JFreeChart chart = createChart(dataset,"Humiditer");
            ChartPanel chartPanel = new ChartPanel(chart, false);
            return chartPanel;
        } catch (SQLException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
//    public static void main(String[] args) {
//        Graph demo = new Graph(
//            "Time Series Chart Demo 1");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//    }

	
}
