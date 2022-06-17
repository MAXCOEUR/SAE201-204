/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import sae.pkg204.RechercheDansBDD.BarChart;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.RechercheDansBDD.LineChart;

/**
 * cette classe permet de créer un JPanel pour afficher le graphiqeu des stock de la cave.
 * @author Maxen
 */
public class AffichageStock extends JPanel{
    private ChartPanel Graph;
    public AffichageStock(){
        try {
            
            Graph = BarChart.BarChart("", "");
            Graph.setPreferredSize(new Dimension( fenetre.tailleFenetre.width-10, fenetre.tailleFenetre.height-50));
            
            this.add(Graph);
        } catch (Exception ex) {
            Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void update(){
        
    }
}
