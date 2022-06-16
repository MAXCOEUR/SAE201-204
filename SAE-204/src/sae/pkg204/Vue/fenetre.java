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
import com.sun.source.tree.BreakTree;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import org.jfree.chart.ChartPanel;
import sae.pkg204.RechercheDansBDD.Camenbert;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.RechercheDansBDD.LineChart;
import sae.pkg204.RechercheDansBDD.DernierePriseT;
import sae.pkg204.AnalogI2CInput;
import sae.pkg204.DHT22;
import sae.pkg204.SAE204;
import sae.pkg204.TempEtHum;

/**
 *
 * @author chama
 */
public class fenetre extends JFrame implements ActionListener {
    
    public static Dimension tailleFenetre;
    private boolean droit;
    public static int page=1;
    

    private JPanel pano = new JPanel();
    private JLabel JLabelTemperature; //derneir temperature enrtegistrer
    private JLabel JLabelHumidite;
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
    

    public fenetre() throws SQLException, ClassNotFoundException, I2CFactory.UnsupportedBusNumberException, IOException, IOException, Exception {
//        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
//        GpioController gpio = GpioFactory.getInstance();
        
//        I2CBus i2c=I2CFactory.getInstance(I2CBus.BUS_1);
//        I2CDevice device = i2c.getDevice(0x04);
        
//        final DHT22 dht = new DHT22(0x04);
        
        
        
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        tailleFenetre=getPreferredSize();
        
        
        setTitle("Domaine Montazac");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Changer_utilisateur JDialogDebut = new Changer_utilisateur(this);
        String ut = JDialogDebut.ShowDialog();
        droit=JDialogDebut.getRole();
              
        affichage(page);
        
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(this);
        } else {
            System.err.println("Le mode plein ecran n'est pas disponible");
        }
        
        ajouter_Bouteille.addActionListener(this);
        supprimer_Bouteille.addActionListener(this);
        general.addActionListener(this);
        changer_utilisateur.addActionListener(this);
        ajouter_utilisateur.addActionListener(this);
        supprimer_utilisateur.addActionListener(this);
        quitter.addActionListener(this);
    }
    
    public void affichageGeneral(boolean tous_les_droits){
        
        pano.removeAll();
        JPanelCamenbert.removeAll();
        JPanelGraphique.removeAll();
        utilisateur.removeAll();

        
        try {
            TempEtHum TH=DernierePriseT.DerniereTempérature();
            JLabelTemperature = new JLabel(TH.getTemperature()+"°C");
            JLabelHumidite = new JLabel(TH.getHumidide()+"%");
            
            ChartPanel tmp = LineChart.LineChart("(SELECT DateHeure  D,temperature T,humidite H FROM temperature ORDER BY D DESC LIMIT 100) ORDER BY D ASC;");
            tmp.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, fenetre.tailleFenetre.height-50));
            JPanelGraphique.add(tmp);
            JPanelGraphique.setPreferredSize(new Dimension(getPreferredSize().width/2-5, getPreferredSize().height-40));
            
            ChartPanel tmp2 = Camenbert.CreatePie("SELECT count(type) as Nombre,type from stock group by type;");
            tmp2.setPreferredSize(new Dimension( fenetre.tailleFenetre.width/2-10, (fenetre.tailleFenetre.height/3)*2-50));
            JPanelCamenbert.add(tmp2);
            JPanelCamenbert.setPreferredSize(new Dimension(getPreferredSize().width/2-5, (getPreferredSize().height/3)*2-40));
            
        } catch (SQLException ex) {
            ;
        } catch (IOException ex) {
            ;
        } catch (Exception ex) {
            ;
        }
        
        
        
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
        g.gridheight =3;
        g.fill = GridBagConstraints.HORIZONTAL;
        JPanelGraphique.setBorder(new LineBorder(Color.BLACK));
        
        pano.add(JPanelGraphique, g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridheight =1;
        JPanelCamenbert.setBorder(new LineBorder(Color.BLACK));
        pano.add(JPanelCamenbert, g);
        
        JLabelTemperature.setFont(new Font("Serif", Font.BOLD, 75));
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridheight =1;
        g.fill = GridBagConstraints.VERTICAL;
        pano.add(JLabelTemperature, g);
        
        JLabelHumidite.setFont(new Font("Serif", Font.BOLD, 60));
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridheight =1;
        g.fill = GridBagConstraints.VERTICAL;
        pano.add(JLabelHumidite, g);
        
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
        utilisateur.updateUI();
        
        
    }
    
    

    @Override
    public Dimension getPreferredSize() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        return tailleEcran;
    }
    
    public void affichage(int affiche) throws Exception{
        switch(affiche)
        {
        case 1:
            affichageGeneral(droit);
        break;
        case 2:
            ;
        break;
        case 3:
            

        default:
        //default statement or expression;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ajouter_Bouteille){
            Bouteille ajout;
            AjouterBouteille dialogue = new AjouterBouteille(this);
            ajout = dialogue.ShowDialog();
            if(ajout != null){
               try {
                   String Query="INSERT INTO `stock` (`id`, `nom`, `date`, `type`) VALUES ";
                   for (int i = 0; i < ajout.getNb_bouteille(); i++) {
                       Query+="(NULL, '"+ajout.getNom()+"', '"+ajout.getAnnee()+"', '"+ajout.getType()+"'),";
                   }
                   Query = Query.substring(0, Query.length()-1);
                   Query+=";";
                   DatabaseConnection.Requete(Query);
                   affichage(page);
                } catch (SQLException ex) {
                    Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
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
                    
                    DatabaseConnection.Requete("DELETE from stock WHERE nom like '"+supp.getNom()+"' and date like '"+supp.getAnnee()+"' and type like '"+supp.getType()+"' LIMIT "+supp.getNb_bouteille()+";");
                }
                affichage(page);
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource() == general){
            try {
                page=1;
                affichage(page);
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(e.getSource() == changer_utilisateur){
            
            Changer_utilisateur dialogue = null;
            try {
                dialogue = new Changer_utilisateur(this);
                String tmp = dialogue.ShowDialog();
                if(!(tmp.equals(""))){
                    droit=dialogue.getRole();
                    affichage(page);
                }
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        if(e.getSource() == ajouter_utilisateur){
            ajouter_utilisateur dialogue = new ajouter_utilisateur(this);
            Utilisateur ut = new Utilisateur();
            ut = dialogue.ShowDialog();
            if(ut != null){
                try {
                    DatabaseConnection.Requete("INSERT INTO `Utilisateur` (`id`, `nom`, `password`, `droit`) VALUES (NULL, '"+ut.getNom()+"', '"+ut.getMot_de_passe()+"', '"+ut.role+"')");
                } catch (SQLException ex) {
                    Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if(e.getSource() == supprimer_utilisateur){
            Supprimer_utilisateur dialogue = null;
            try {
                dialogue = new Supprimer_utilisateur(this);
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            String tmp = dialogue.ShowDialog();
            if(!(tmp.equals(""))){
                try {
                    DatabaseConnection.Requete("DELETE FROM Utilisateur WHERE nom = '"+tmp+"' ;");
                } catch (SQLException ex) {
                    Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        if(e.getSource() == quitter){
            SAE204.t.stop();
            this.dispose();
        }
    }
    
}
