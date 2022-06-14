/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;

import java.io.File;
import java.io.IOException;
import java.sql.*;  
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.io.File;
import java.sql.*;  
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;

/**
 *
 * @author p2101068
 */
public class Camenbert  {
    
    ResultSet resultSet;
    Statement statement = null;
    String r=null;
      
    public Camenbert(String r1) throws SQLException {
        statement = DatabaseConnection.getConnection();
        r=r1;
        
    }
    
   
    void CreatePie(String g, String p, String gr) throws SQLException, IOException{
     DefaultPieDataset dataset = new DefaultPieDataset( );
     resultSet = statement.executeQuery(r);
        try {
            while( resultSet.next() ) {
                dataset.setValue(
                        resultSet.getString( g ) ,
                        Double.parseDouble( resultSet.getString(p )));
            } } catch (SQLException ex) {
            Logger.getLogger(Camenbert.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      JFreeChart chart = ChartFactory.createPieChart(
         gr,   // chart title           
         dataset,          // data           
         true,             // include legend          
         true,           
         false );

      int width = 560;    /* Width of the image */
      int height = 370;   /* Height of the image */ 
      File pieChart = new File( "Pie_Chart.jpeg" );
      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
    }

}
