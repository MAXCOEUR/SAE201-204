/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;

/**
 *
 * @author chama
 */
public class Affichage_Bouteille extends JPanel{
    private ArrayList <Bouteille> liste_bouteille = new ArrayList<>();

    public Affichage_Bouteille() {
        this.setLayout(new GridBagLayout());
        
        try {
            ResultSet rs = DatabaseConnection.Requete("select nom, left(date,4) annee, type from stock");
            while(rs.next()){
                Bouteille b = new Bouteille();
                b.setNom(rs.getString("nom"));
                b.setAnnee(rs.getString("annee"));
                b.setType(rs.getString("type"));
                b.setNb_bouteille(1);
                liste_bouteille.add(b);
            }
            
            int x = 0;
            
            for (Bouteille b : liste_bouteille) {
                JPanel p = new JPanel();
                GridBagConstraints g = new GridBagConstraints();
                p.setLayout(new GridBagLayout());
                
                g.fill = GridBagConstraints.BOTH;
                
                JLabel nom = new JLabel(b.getNom() + "     " + b.getAnnee() + "     " + b.getType());
                g.gridx = 0;
                g.gridy = 0;
                p.add(nom,g);
                
                g.gridy = x;
                this.add(p,g);
                x++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_Bouteille.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
