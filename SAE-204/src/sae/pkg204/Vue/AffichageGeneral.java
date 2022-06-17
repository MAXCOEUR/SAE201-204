/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartPanel;
import sae.pkg204.RechercheDansBDD.Camenbert;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.RechercheDansBDD.DernierePriseT;
import sae.pkg204.RechercheDansBDD.LineChart;
import sae.pkg204.TempEtHum;

/**
 * cette classe permet de créer un JPanel pour afficher les 3 graphiques de notre affichage par default.
 * @author Maxen
 */
public class AffichageGeneral extends JPanel{
    private JLabel JLabelTemperature = null; //derneir temperature enrtegistrer
    private JLabel JLabelHumidite = null;
    private ChartPanel temperature;
    private ChartPanel humiditer;
    private ChartPanel camembert;
    private JPanel Donnee= new JPanel();
    
    public AffichageGeneral(){
        try {
            TempEtHum TH=DernierePriseT.DerniereTempérature();
            JLabelTemperature = new JLabel(TH.getTemperature()+"°C");
            JLabelHumidite = new JLabel(TH.getHumidide()+"%");
            JLabelTemperature.setFont(new Font("Serif", Font.BOLD, 75));
            JLabelHumidite.setFont(new Font("Serif", Font.BOLD, 60));
            
            ResultSet resultMin = DatabaseConnection.Requete("SELECT MIN(T) MinT from ((SELECT DateHeure  D, temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC) e;");
            float min =0;
            while (resultMin.next()) {
                min = resultMin.getFloat("MinT");
            }
            temperature = LineChart.LineChartTemperature("(SELECT DateHeure  D,temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC;",min);
            temperature.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, fenetre.tailleFenetre.height/2-20));
            
            ResultSet resultMin2 = DatabaseConnection.Requete("SELECT MIN(H) MinH from ((SELECT DateHeure  D, temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC) e;");
            float min2 =0;
            while (resultMin2.next()) {
                min2 = resultMin2.getFloat("MinH");
            }
            humiditer = LineChart.LineChartHumidite("(SELECT DateHeure  D,temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC;",min2);
            humiditer.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, fenetre.tailleFenetre.height/2-20));
            
            
            camembert = Camenbert.CreatePie("SELECT count(type) as Nombre,type from stock group by type;");
            camembert.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, (fenetre.tailleFenetre.height/3)*2-50));
            
            Donnee.setLayout(new GridBagLayout());
            GridBagConstraints g1 = new GridBagConstraints();
            g1.fill = GridBagConstraints.BOTH;
            g1.gridx = 0;
            g1.gridy = 0;
            g1.fill = GridBagConstraints.HORIZONTAL;
            Donnee.add(JLabelTemperature,g1);
            g1.gridy = 1;
            Donnee.add(JLabelHumidite,g1);
            
            this.setLayout(new GridBagLayout());
        
            GridBagConstraints g = new GridBagConstraints();

            g.fill = GridBagConstraints.BOTH;

            g.gridx = 0;
            g.gridy = 0;
            g.gridheight =1;
            g.fill = GridBagConstraints.HORIZONTAL;
            this.add(temperature, g);
            
            g.gridx = 0;
            g.gridy = 1;
            this.add(humiditer, g);

            g.gridx = 1;
            g.gridy = 1;
            g.gridheight =1;
            this.add(camembert, g);

            

            g.gridx = 1;
            g.gridy = 0;
            g.gridheight =1;
            g.fill = GridBagConstraints.VERTICAL;
            this.add(Donnee, g);
            
            
        } catch (SQLException ex) {
            ;
        } catch (IOException ex) {
            ;
        } catch (Exception ex) {
            ;
        }
        
        
    }
    public void update(){
        
    }
}
