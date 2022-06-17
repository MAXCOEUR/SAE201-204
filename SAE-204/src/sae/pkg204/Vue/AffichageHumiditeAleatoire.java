/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import sae.pkg204.RechercheDansBDD.Graph;

/**
 *
 * @author Maxen
 */
public class AffichageHumiditeAleatoire extends JPanel{
    private ChartPanel Graphique;
    public AffichageHumiditeAleatoire(){
        try {
            
            Graphique = Graph.GraphHum("(SELECT hour(DateHeure) h, minute(DateHeure) m, second(DateHeure) s, DateHeure  D,temperature T,humidite HU FROM AleatoireTemperature ORDER BY D DESC LIMIT 500) ORDER BY D ASC;");
            Graphique.setPreferredSize(new Dimension( fenetre.tailleFenetre.width-10, fenetre.tailleFenetre.height-50));
            
            this.add(Graphique);
        } catch (Exception ex) {
            Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
