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
import sae.pkg204.RechercheDansBDD.Graph;
import sae.pkg204.RechercheDansBDD.LineChart;

/**
 * cette classe permet de créer un JPanel avec le graphique de temperature.
 * @author Maxen
 */
public class AffichageTemperature extends JPanel{
    private ChartPanel Graphique;
    public AffichageTemperature(){
        try {
            
            Graphique = Graph.GraphTemp("(SELECT hour(DateHeure) h, minute(DateHeure) m, second(DateHeure) s, DateHeure  D,temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 500) ORDER BY D ASC;");
            Graphique.setPreferredSize(new Dimension( fenetre.tailleFenetre.width-10, fenetre.tailleFenetre.height-50));
            
            this.add(Graphique);
        } catch (Exception ex) {
            Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(){
        
    }
}
