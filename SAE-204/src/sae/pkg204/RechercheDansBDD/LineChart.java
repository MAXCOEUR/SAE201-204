package sae.pkg204.RechercheDansBDD;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;

public class LineChart {

   public static void LineChart(String query) throws Exception {
       Statement s = DatabaseConnection.getConnection();
       DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
       
       ResultSet resultSet = s.executeQuery(query);
        
        
        while (resultSet.next()) {
            line_chart_dataset.addValue( Double.parseDouble(resultSet.getString("T")) , "" , resultSet.getString("D"));
        }

      JFreeChart lineChartObject = ChartFactory.createLineChart(
         "Temperature Vs Date",
         "Temperature",
         "Date",
         line_chart_dataset,PlotOrientation.VERTICAL,
         true,true,false);

      int width = 640;    /* Width of the image */
      int height = 480;   /* Height of the image */ 
      File lineChart = new File( "image/LineChart.jpeg" ); 
      ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
    }
}