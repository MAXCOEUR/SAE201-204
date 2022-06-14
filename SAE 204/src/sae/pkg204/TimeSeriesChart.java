/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.ChartUtilities;

/**
 *
 * @author p2101068
 */
public class TimeSeriesChart {
    ResultSet resultSet;
    Statement statement = null;
    String r=null;
      
    public TimeSeriesChart(String r1, Singleton s) throws SQLException {
        statement = s.getStatement();
        r=r1;
    }
    
   public void CreateTimeSeries() throws SQLException, IOException {
    final TimeSeries series = new TimeSeries( "Temperature" );
      Second current = new Second();
      double value = 100.0;
      
      for ( int i = 0 ; i < 4000 ; i++ ) {
         
         try {
            value = value + Math.random( ) - 0.5;
            series.add( current , new Double( value ) );
            current = ( Second ) current.next( );
         } catch ( SeriesException e ) {
            System.err.println( "Error adding to series" );
         }
      }
      
      final XYDataset dataset=( XYDataset )new TimeSeriesCollection(series);
      JFreeChart timechart = ChartFactory.createTimeSeriesChart(
         "Temperature", 
         "Temps", 
         "Valeur", 
         dataset,
         false, 
         false, 
         false);
         
      int width = 560;   /* Width of the image */
      int height = 370;  /* Height of the image */ 
      File timeChart = new File( "TimeChart.jpeg" ); 
      ChartUtilities.saveChartAsJPEG( timeChart, timechart, width, height );
   }
}
