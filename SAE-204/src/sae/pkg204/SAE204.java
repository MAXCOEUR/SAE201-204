/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;
import sae.pkg204.Vue.fenetre;
import java.io.File;
import java.sql.*;  
import java.sql.SQLException;
import static javax.management.remote.JMXConnectorFactory.connect;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

/**
 *
 * @author p2101068
 */
public class SAE204 {

    public static fenetre fen;
    public static ThreadPriseDonnee t;
public static void main( String[ ] args )throws Exception {

    
    fen = new fenetre();
    t = new ThreadPriseDonnee();
    t.start();
    fen.setVisible(true);
    
   }
}
    








