/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.RechercheDansBDD.LineChart;

/**
 * cette classe permet de créer un JPanel avec le graphique de temperature.
 * @author Maxen
 */
public class AffichageTemperature extends JPanel{
    private ChartPanel Graph;
    public AffichageTemperature(){
        try {
            ResultSet resultMin = DatabaseConnection.Requete("SELECT MIN(T) MinT from ((SELECT DateHeure  D, temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 500) ORDER BY D ASC) e;");
            float min =0;
            while (resultMin.next()) {
                min = resultMin.getFloat("MinT");
            }
            Graph = LineChart.LineChartTemperature("(SELECT DateHeure  D,temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 500) ORDER BY D ASC;",min);
            Graph.setPreferredSize(new Dimension( fenetre.tailleFenetre.width-10, fenetre.tailleFenetre.height-50));
            
            this.add(Graph);
        } catch (Exception ex) {
            Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(){
        
    }
}
