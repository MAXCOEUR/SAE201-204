/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import sae.pkg204.DataBase;
import sae.pkg204.Singleton;
import sae.pkg204.Utilisateur;

/**
 *
 * @author chama
 */
public class fenetre extends JFrame implements ActionListener {
    Singleton DataBaseApp;
    DataBase Application;
    private JPanel pano;
    private JPanel JPanelTemperature; //derneir temperature enrtegistrer
    private JPanel JPanelGraphique; // le graph des température sur le temp
    private JLabel JPanelCamenbert; // de type de vin
    private JMenuBar menu = new JMenuBar();
    
    private JMenu affiche = new JMenu("affiche");
    private JMenu Modifier = new JMenu("modifier");
    private JMenu utilisateur = new JMenu("Utilisateur");
    private JMenu database = new JMenu("Database");
    
    private JMenuItem general = new JMenuItem("general");
    private JMenuItem ajouter_Bouteille = new JMenuItem("Ajoute bouteille");
    private JMenuItem supprimer_Bouteille = new JMenuItem("Supprimer bouteille");
    private JMenuItem ajouter_utilisateur = new JMenuItem("Ajouter un utilisateur");
    private JMenuItem supprimer_utilisateur = new JMenuItem("Supprimer un utilisateur");
    private JMenuItem changer_utilisateur = new JMenuItem("changer utilisateur");
    private JMenuItem allumer_database = new JMenuItem("Allumer la database");
    private JMenuItem eteindre_database = new JMenuItem("Eteindre la database");
    

    public fenetre() throws SQLException, ClassNotFoundException {
        this.Application = new DataBase(DataBaseApp);
        this.setTitle("Domaine Montazac");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        affichage_début(true);
        
        ajouter_Bouteille.addActionListener(this);
        supprimer_Bouteille.addActionListener(this);
        general.addActionListener(this);
        changer_utilisateur.addActionListener(this);
        ajouter_utilisateur.addActionListener(this);
        supprimer_utilisateur.addActionListener(this);
        allumer_database.addActionListener(this);
        eteindre_database.addActionListener(this);
    }
    
    private void affichage_début(boolean tous_les_droits){
        
        pano = new JPanel();
        JPanelTemperature = new JPanel();
        JPanelGraphique = new JPanel();
        
        
        
        
        menu.add(affiche);
            affiche.add(general);
        
        menu.add(Modifier);
            Modifier.add(ajouter_Bouteille);
            Modifier.add(supprimer_Bouteille);
        
        menu.add(utilisateur);
            utilisateur.add(changer_utilisateur);
        
        menu.add(database);
            database.add(allumer_database);
            database.add(eteindre_database);
        
        
        
        
        setContentPane(pano);
        pano.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.fill = GridBagConstraints.BOTH;
        
        g.gridx = 1;
        g.gridy = 1;
        pano.add(JPanelGraphique, g);
        
        g.gridx = 2;
        g.gridy = 1;
        pano.add(JPanelTemperature, g);
        
        if(tous_les_droits == true){
            utilisateur.add(ajouter_utilisateur);
            utilisateur.add(supprimer_utilisateur);
        }
        
        setJMenuBar(menu);
        this.pack();
    }
    
    public void affichage_temperature(){
        JLabel image;
        image = new JLabel(new ImageIcon("image/Pie_Chart.jpeg"));
        this.JPanelTemperature.add(image);
    }
    
    public void affichage_graphique(){
        JLabel image;
        image = new JLabel(new ImageIcon("image/Pie_Chart.jpeg"));
        this.JPanelGraphique.add(image);
        
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int)tailleEcran.getHeight();
        int largeur = (int)tailleEcran.getWidth();
        return tailleEcran;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ajouter_Bouteille){
            System.out.println(1);
        }
        if(e.getSource() == supprimer_Bouteille){
            System.out.println(2);
        }
        if(e.getSource() == general){
            affichage_graphique();
            affichage_temperature();
            pano.updateUI();
        }
        if(e.getSource() == changer_utilisateur){
            Changer_utilisateur dialogue = new Changer_utilisateur(this);
            String tmp = dialogue.ShowDialog();
            System.out.println(tmp);
        }
        if(e.getSource() == ajouter_utilisateur){
            ajouter_utilisateur dialogue = new ajouter_utilisateur(this);
            Utilisateur ut = new Utilisateur();
            ut = dialogue.ShowDialog();
            System.out.println(ut);
            try {
                Application.CreateUser("CREATE USER "+ut.getNom()+" IDENTIFIED BY "+ut.getMot_de_passe()+"; \n GRANT"+ ut.getRole()+" TO "+ut.getNom()+"; ");
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == supprimer_utilisateur){
            Supprimer_utilisateur dialogue = new Supprimer_utilisateur(this);
            String tmp = dialogue.ShowDialog();
            System.out.println(tmp);
            try {
                Application.CreateUser("DROP USER "+tmp+"; ");
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == allumer_database){
            try {
              DataBaseApp = new Singleton("application");
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == eteindre_database){
            try {
                DataBaseApp.stop();
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
