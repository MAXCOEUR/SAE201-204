/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author p2101068
 */
public class Courbe  {
    ResultSet resultSet;
    Statement statement = null;
    String r=null;
      
    public Courbe(String r1, Singleton s) throws SQLException {
        statement = s.getStatement();
        r=r1;
    }
    
   public void CreateCourbe(String g, String p, String gr,int palier) throws SQLException, IOException {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
    resultSet = statement.executeQuery(r);
      int niveau=0;
      while( resultSet.next() ) {
        Double temp=Double.parseDouble( resultSet.getString( p ));
         dataset.addValue(
         temp,
         Integer.toString(niveau+palier),        
         resultSet.getString( g ));
         
         niveau=niveau+palier;
      }
      
      JFreeChart lineChart = ChartFactory.createLineChart(
         "Courbe",  
         gr,"Palier",
         dataset,
         PlotOrientation.VERTICAL,      
         false,                 
         false,           
         false );

      int width = 1920;    /* Width of the image */
      int height = 1080;   /* Height of the image */ 
      File pieChart = new File( "Courbe.jpeg" );
      ChartUtilities.saveChartAsJPEG( pieChart , lineChart , width , height );
   }
}

