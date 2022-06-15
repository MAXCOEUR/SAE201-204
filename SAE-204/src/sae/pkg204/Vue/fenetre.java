/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import sae.pkg204.Camenbert;
import sae.pkg204.DataBase;
import sae.pkg204.RechercheDansBDD.LineChart;
import sae.pkg204.RechercheDansBDD.DernierePriseT;
import sae.pkg204.Singleton;
import sae.pkg204.TimeSeriesChart;
import sae.pkg204.Utilisateur;

/**
 *
 * @author chama
 */
public class fenetre extends JFrame implements ActionListener {
    
    public static Dimension tailleFenetre;
    
    Singleton DataBaseApp;
    DataBase Application;
    private JPanel pano = new JPanel();
    private JLabel JLabelTemperature; //derneir temperature enrtegistrer
    private JPanel JPanelGraphique = new JPanel(); // le graph des température sur le temp
    private JPanel JPanelCamenbert = new JPanel(); // de type de vin
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
        
        tailleFenetre=getPreferredSize();
        
        this.Application = new DataBase(DataBaseApp);
        this.setTitle("Domaine Montazac");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        affichageGeneral(true);
        
        ajouter_Bouteille.addActionListener(this);
        supprimer_Bouteille.addActionListener(this);
        general.addActionListener(this);
        changer_utilisateur.addActionListener(this);
        ajouter_utilisateur.addActionListener(this);
        supprimer_utilisateur.addActionListener(this);
        allumer_database.addActionListener(this);
        eteindre_database.addActionListener(this);
    }
    
    private void affichageGeneral(boolean tous_les_droits){
        
        pano.removeAll();
        JPanelCamenbert.removeAll();
        JPanelGraphique.removeAll();
        
        try {
            JLabelTemperature = new JLabel(DernierePriseT.DerniereTempérature()+"°C");
            JPanelGraphique.add(LineChart.LineChart("(SELECT left(right(DateHeure,8),5) D,temperature T FROM temperature ORDER BY D DESC LIMIT 6) ORDER BY D ASC;"));
            JPanelGraphique.setPreferredSize(new Dimension(getPreferredSize().width/2, getPreferredSize().height-25));
            JPanelCamenbert.add(Camenbert.CreatePie("SELECT count(type) as Nombre,type from stock group by type;"));
            JPanelCamenbert.setPreferredSize(new Dimension(getPreferredSize().width/2-30, (getPreferredSize().height/3)*2-30));
            
        } catch (SQLException ex) {
            ;
        } catch (IOException ex) {
            ;
        } catch (Exception ex) {
            ;
        }
        
        JLabelTemperature.setFont(new Font("Serif", Font.BOLD, 100));

         
//        try {
//            //BufferedImage bufImg = ImageIO.read(new File("image/LineChart.jpeg"));
//            //JPanelGraphique.add(new JLabel(new ImageIcon(new ImageIcon(bufImg).getImage().getScaledInstance(getPreferredSize().width/2-10, getPreferredSize().height-20, Image.SCALE_DEFAULT))));
//            
//            //BufferedImage bufImg2 = ImageIO.read(new File("image/Pie_Chart.jpeg"));
//            
//        } catch (IOException ex) {
//            ;
//        }
        
        
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
        
        
        
        
        
        pano.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.fill = GridBagConstraints.BOTH;
        
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight =2;
        g.fill = GridBagConstraints.HORIZONTAL;
        JPanelGraphique.setBorder(new LineBorder(Color.BLACK));
        
        pano.add(JPanelGraphique, g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridheight =1;
        JPanelCamenbert.setBorder(new LineBorder(Color.BLACK));
        pano.add(JPanelCamenbert, g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridheight =1;
        g.fill = GridBagConstraints.VERTICAL;
        JLabelTemperature.setBorder(new LineBorder(Color.BLACK));
        pano.add(JLabelTemperature, g);
        
        if(tous_les_droits == true){
            utilisateur.add(ajouter_utilisateur);
            utilisateur.add(supprimer_utilisateur);
        }
        
        setJMenuBar(menu);
        this.pack();
        
        setContentPane(pano);
        pano.updateUI();
        JPanelGraphique.updateUI();
        JPanelCamenbert.updateUI();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        tailleEcran.height-=50;
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
            affichageGeneral(true);
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
              DataBaseApp = new Singleton();
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
