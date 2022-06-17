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
import sae.pkg204.RechercheDansBDD.BarChart;

/**
 *
 * @author Maxen
 */
public class AffichageStockAleatoire extends JPanel{
    private ChartPanel Graph;
    public AffichageStockAleatoire(){
        try {
            
            Graph = BarChart.BarChart("SELECT date D, COUNT(nom) N FROM AleatoireStock GROUP BY date;", "");
            Graph.setPreferredSize(new Dimension( fenetre.tailleFenetre.width-10, fenetre.tailleFenetre.height-50));
            
            this.add(Graph);
        } catch (Exception ex) {
            Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
