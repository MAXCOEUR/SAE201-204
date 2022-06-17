/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import sae.pkg204.RechercheDansBDD.Camenbert;
import sae.pkg204.RechercheDansBDD.DernierePriseT;
import sae.pkg204.RechercheDansBDD.Graph;
import sae.pkg204.TempEtHum;

/**
 *
 * @author Maxen
 */
public class AffichageGeneralAleatoire extends JPanel{
    private JLabel JLabelTemperature = null; //derneir temperature enrtegistrer
    private JLabel JLabelHumidite = null;
    private ChartPanel temperature;
    private ChartPanel humiditer;
    private ChartPanel camembert;
    private JPanel Donnee= new JPanel();
    
    public AffichageGeneralAleatoire(){
        try {
            TempEtHum TH=DernierePriseT.DerniereTempérature();
            JLabelTemperature = new JLabel(TH.getTemperature()+"°C");
            JLabelHumidite = new JLabel(TH.getHumidide()+"%");
            JLabelTemperature.setFont(new Font("Serif", Font.BOLD, (fenetre.tailleFenetre.height+fenetre.tailleFenetre.width)/17));
            JLabelHumidite.setFont(new Font("Serif", Font.BOLD, (fenetre.tailleFenetre.height+fenetre.tailleFenetre.width)/21));
            
            
            temperature =  Graph.GraphTemp("(SELECT hour(DateHeure) h, minute(DateHeure) m, second(DateHeure) s, DateHeure  D,temperature T,humidite H FROM AleatoireTemperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC;");
            temperature.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, fenetre.tailleFenetre.height/2-10));
            
            
            humiditer = Graph.GraphHum("(SELECT hour(DateHeure) h, minute(DateHeure) m, second(DateHeure) s, DateHeure  D,temperature T,humidite HU FROM AleatoireTemperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC;");
            humiditer.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, fenetre.tailleFenetre.height/2-10));
            
            
            camembert = Camenbert.CreatePie("SELECT count(type) as Nombre,type from AleatoireStock group by type;"); // a changer
            camembert.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, (fenetre.tailleFenetre.height/3)*2-50));
            
            Donnee.setLayout(new GridBagLayout());
            GridBagConstraints g1 = new GridBagConstraints();
            g1.gridx = 0;
            g1.gridy = 0;
            
            Donnee.add(JLabelTemperature,g1);
            g1.gridy = 1;
            Donnee.add(JLabelHumidite,g1);
            
            this.setLayout(new GridBagLayout());
        
            GridBagConstraints g = new GridBagConstraints();

            
            g.fill = GridBagConstraints.VERTICAL;
            g.gridx = 0;
            g.gridy = 0;
            g.gridheight =2;
            this.add(temperature, g);
            
            g.gridx = 0;
            g.gridy = 2;
            g.gridheight =2;
            this.add(humiditer, g);

            g.gridx = 1;
            g.gridy = 1;
            g.gridheight =3;
            this.add(camembert, g);

            

            g.gridx = 1;
            g.gridy = 0;
            g.gridheight =1;
            this.add(Donnee, g);
            
            
        } catch (SQLException ex) {
            ;
        } catch (IOException ex) {
            ;
        } catch (Exception ex) {
            ;
        }
        
        
    }
}
