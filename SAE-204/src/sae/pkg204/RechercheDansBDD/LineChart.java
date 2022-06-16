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

      JFreeChart lineChartObject = ChartFactory.createLineChart(
         "",
         "",
         "",
         line_chart_dataset,PlotOrientation.VERTICAL,
         true,false,false);
      
      
      
//      int width = 640;    /* Width of the image */
//      int height = 480;   /* Height of the image */ 
//      File lineChart = new File( "image/LineChart.jpeg" ); 
//      ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        
        
      return  new ChartPanel(lineChartObject);
    }
}