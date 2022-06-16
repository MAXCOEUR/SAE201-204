package sae.pkg204.RechercheDansBDD;

import java.awt.Dimension;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.Vue.fenetre;

// getTimeInMillis

public class LineChart {

   public static ChartPanel LineChart(String query) throws Exception {
       
       Statement s = DatabaseConnection.getConnection();
       
       DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
       
       ResultSet resultSet = s.executeQuery(query);
        
        
        while (resultSet.next()) {
            line_chart_dataset.addValue( Double.parseDouble(resultSet.getString("T")) , "Temperature" , resultSet.getString("D"));
            line_chart_dataset.addValue( Double.parseDouble(resultSet.getString("H")) , "Humidite" , resultSet.getString("D"));
        }
//        line_chart_dataset.removeRow(10);
      JFreeChart lineChartObject = ChartFactory.createLineChart(
         "Temperature&Humidite",
         "",
         "",
         line_chart_dataset,PlotOrientation.VERTICAL,
         true,false,true);
      return  new ChartPanel(lineChartObject);
    }
   
   public static ChartPanel LineChartTemperature(String query,float min) throws Exception {
       
        Statement s = DatabaseConnection.getConnection();

        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        ResultSet resultSet = s.executeQuery(query);

        while (resultSet.next()) {
            
            line_chart_dataset.addValue( (resultSet.getFloat("T")-min) , "Temperature" , resultSet.getString("D"));
        }
        
//        line_chart_dataset.removeRow(10);
      JFreeChart lineChartObject = ChartFactory.createLineChart(
         "Temperature",
         "",
         "",
         line_chart_dataset,PlotOrientation.VERTICAL,
         false,false,true);
      return  new ChartPanel(lineChartObject);
    }
   
   public static ChartPanel LineChartHumidite(String query,float min) throws Exception {
       
        Statement s = DatabaseConnection.getConnection();

        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        ResultSet resultSet = s.executeQuery(query);

        while (resultSet.next()) {
            
            line_chart_dataset.addValue( (resultSet.getFloat("H")-min) , "Humidite" , resultSet.getString("D"));
        }
        
//        line_chart_dataset.removeRow(10);
      JFreeChart lineChartObject = ChartFactory.createLineChart(
         "Humidite",
         "",
         "",
         line_chart_dataset,PlotOrientation.VERTICAL,
         false,false,true);
      return  new ChartPanel(lineChartObject);
    }
}