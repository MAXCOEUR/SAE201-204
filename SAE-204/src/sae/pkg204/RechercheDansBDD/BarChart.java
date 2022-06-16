/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.RechercheDansBDD;



/**
 *
 * @author Maxen
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 

public class BarChart {
   public static ChartPanel BarChart(String query, String chartTitle) {
            
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "",            
         "",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         false, true, false);
         
         
      return new ChartPanel( barChart );
   }
   
   private static CategoryDataset createDataset( ) {
       
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      try {
            ResultSet s = DatabaseConnection.Requete("SELECT date D, COUNT(nom) N FROM `stock` GROUP BY date;");
            while (s.next()) {
                dataset.addValue( s.getInt("N") , "" , ""+s.getString("D").charAt(0)+s.getString("D").charAt(1)+s.getString("D").charAt(2)+s.getString("D").charAt(3) );
            }   
        } catch (SQLException ex) {
            Logger.getLogger(BarChart.class.getName()).log(Level.SEVERE, null, ex);
        }                  

      return dataset; 
   }
   
   
}
