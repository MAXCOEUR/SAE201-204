/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
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
import java.sql.Statement;
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
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.RechercheDansBDD.LineChart;
import sae.pkg204.RechercheDansBDD.DernierePriseT;
import sae.pkg204.AnalogI2CInput;
import sae.pkg204.DHT22;

/**
 *
 * @author chama
 */
public class fenetre extends JFrame implements ActionListener {
    
    public static Dimension tailleFenetre;
    
    

    private JPanel pano = new JPanel();
    private JLabel JLabelTemperature; //derneir temperature enrtegistrer
    private JPanel JPanelGraphique = new JPanel(); // le graph des température sur le temp
    private JPanel JPanelCamenbert = new JPanel(); // de type de vin
    private JMenuBar menu = new JMenuBar();
    
    private JMenu affiche = new JMenu("affiche");
    private JMenu Modifier = new JMenu("modifier");
    private JMenu utilisateur = new JMenu("Utilisateur");
    
    private JMenuItem general = new JMenuItem("general");
    private JMenuItem ajouter_Bouteille = new JMenuItem("Ajoute bouteille");
    private JMenuItem supprimer_Bouteille = new JMenuItem("Supprimer bouteille");
    private JMenuItem ajouter_utilisateur = new JMenuItem("Ajouter un utilisateur");
    private JMenuItem supprimer_utilisateur = new JMenuItem("Supprimer un utilisateur");
    private JMenuItem changer_utilisateur = new JMenuItem("changer utilisateur");
    private JMenuItem quitter = new JMenuItem("Quitter");
    

    public fenetre() throws SQLException, ClassNotFoundException, I2CFactory.UnsupportedBusNumberException, IOException, IOException {
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
        GpioController gpio = GpioFactory.getInstance();
        
        I2CBus i2c=I2CFactory.getInstance(0);
        
        
        tailleFenetre=getPreferredSize();
        
        
        setTitle("Domaine Montazac");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Changer_utilisateur JDialogDebut = new Changer_utilisateur(this);
        String ut = JDialogDebut.ShowDialog();
        
        
        affichageGeneral(true);
        
        ajouter_Bouteille.addActionListener(this);
        supprimer_Bouteille.addActionListener(this);
        general.addActionListener(this);
        changer_utilisateur.addActionListener(this);
        ajouter_utilisateur.addActionListener(this);
        supprimer_utilisateur.addActionListener(this);
        quitter.addActionListener(this);
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
        
        menu.add(quitter);
        
        
        
        
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
            Bouteille ajout;
            AjouterBouteille dialogue = new AjouterBouteille(this);
            ajout = dialogue. ShowDialog();
            if(ajout != null){
               try {
                    DatabaseConnection.Requete("INSERT INTO stock (nom,annee,type) VALUES ("+ajout.getNom()+","+ajout.getAnnee()+",rouge)");
                } catch (SQLException ex) {
                    Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(e.getSource() == supprimer_Bouteille){
            Bouteille supp;

            SupprimerBouteille dialogue;
            try {
                dialogue = new SupprimerBouteille(this);
                supp = dialogue.ShowDialog();
                if(supp != null){
                    ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource() == general){
            affichageGeneral(true);
        }
        if(e.getSource() == changer_utilisateur){
            Changer_utilisateur dialogue = null;
            try {
                dialogue = new Changer_utilisateur(this);
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            String tmp = dialogue.ShowDialog();
            System.out.println(tmp);
        }
        
        if(e.getSource() == ajouter_utilisateur){
            ajouter_utilisateur dialogue = new ajouter_utilisateur(this);
            Utilisateur ut = new Utilisateur();
            ut = dialogue.ShowDialog();
            System.out.println(ut);
            try {
                DatabaseConnection.Requete("INSERT INTO `Utilisateur` (`id`, `nom`, `password`, `droit`) VALUES (NULL, "+ut.getNom()+","+ut.getMot_de_passe()+","+ut.getRole()+");");
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == supprimer_utilisateur){
            Supprimer_utilisateur dialogue = new Supprimer_utilisateur(this);
            String tmp = dialogue.ShowDialog();
            System.out.println(tmp);
            try {
                DatabaseConnection.Requete("DELETE FROM Utilisateur WHERE `Utilisateur`.`nom` = "+tmp+" ");
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == quitter){
            this.dispose();
        }
    }
    
}
