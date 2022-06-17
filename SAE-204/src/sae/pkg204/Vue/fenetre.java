/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import com.pi4j.io.i2c.I2CFactory;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.SAE204;

/**
 * cette classe permet de créer la fenêtres de notre application.
 * @author chama
 */
public class fenetre extends JFrame implements ActionListener {
    
    public static Dimension tailleFenetre;
    private boolean droit;
    public static int page=1;
    

    private JPanel pano = new JPanel();
    
    private JMenuBar menu = new JMenuBar();
    
    private JMenu affichage = new JMenu("Affichage");
    private JMenu Modifier = new JMenu("Modifier");
    private JMenu utilisateur = new JMenu("Utilisateur");
    
    private JMenuItem general = new JMenuItem("Général");
    private JMenuItem temperature = new JMenuItem("Temperature");
    private JMenuItem humidite = new JMenuItem("Humidité");
    private JMenuItem stock = new JMenuItem("Stock");
    
    
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
        
        
        
        
        
        tailleFenetre=getPreferredSize();
        
        
        setTitle("Domaine Montazac");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Changer_utilisateur JDialogDebut = new Changer_utilisateur(this);
        String ut = JDialogDebut.ShowDialog();
        droit=JDialogDebut.getRole();
        affichageMenu();
        affichage();
        
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(this);
        } else {
            System.err.println("Le mode plein ecran n'est pas disponible");
        }
        
        setContentPane(pano);
        
        ajouter_Bouteille.addActionListener(this);
        supprimer_Bouteille.addActionListener(this);
        general.addActionListener(this);
        temperature.addActionListener(this);
        humidite.addActionListener(this);
        stock.addActionListener(this);
        changer_utilisateur.addActionListener(this);
        ajouter_utilisateur.addActionListener(this);
        supprimer_utilisateur.addActionListener(this);
        quitter.addActionListener(this);
    }
    
    /**
     * cette fonction nous permet d'afficher la JMenuBar que chaque utilisateur posséde.
     */
    public void affichageMenu(){
        utilisateur.removeAll();
        menu.add(affichage);
            affichage.add(general);
            affichage.add(temperature);
            affichage.add(humidite);
            affichage.add(stock);
        
        menu.add(Modifier);
            Modifier.add(ajouter_Bouteille);
            Modifier.add(supprimer_Bouteille);
        
        menu.add(utilisateur);
            utilisateur.add(changer_utilisateur);
        
        menu.add(quitter);
        if(droit == true){
            utilisateur.add(ajouter_utilisateur);
            utilisateur.add(supprimer_utilisateur);
        }
        
        setJMenuBar(menu);
    }
    
    /**
     * cette methode permet de faire l'affichage du graphique de temperature seul.
     */    
    public void affichageTemperature(){
        pano.removeAll();
        
        
        AffichageTemperature panoTemperature = new AffichageTemperature();
        pano.add(panoTemperature);
                
        
        pano.updateUI();
        
    }
    
    /**
     * cette methdoe permet de  faire l'affichage du graphique de l'humidité seul
     */
    public void affichageHumidite(){
        pano.removeAll();
        
        
        AffichageHumidite panoHumidite = new AffichageHumidite();
        pano.add(panoHumidite);
                
        
        pano.updateUI();
        
    }
    
    /**
     * cette methode permet de faire l'affichage originel de notre application.
     */
    public void affichageGeneral(){
        
        pano.removeAll();
        
        
        AffichageGeneral panoGeneral = new AffichageGeneral();
        pano.add(panoGeneral);
                
        
        pano.updateUI();
        
        
        
    }
    
    /**
     * cette methode nous permet de faire l'affichage des vin par date 
     */
    public void affichageStock(){
        
        pano.removeAll();
        
        
        AffichageStock panoStock = new AffichageStock();
        pano.add(panoStock);
                
        
        pano.updateUI();
        
        
        
    }
    
    public void affichageGeneralAlearoire(){
        pano.removeAll();
        
        
        AffichageGeneralAleatoire panoG = new AffichageGeneralAleatoire();
        pano.add(panoG);
                
        
        pano.updateUI();
    }
    public void affichageTemperatureAleatoire(){
        pano.removeAll();
        
        
        AffichageTemperatureAleatoire panoStock = new AffichageTemperatureAleatoire();
        pano.add(panoStock);
                
        
        pano.updateUI();
    }
    public void affichageHumiditeAleatoire(){
        pano.removeAll();
        
        
        AffichageHumiditeAleatoire panoStock = new AffichageHumiditeAleatoire();
        pano.add(panoStock);
                
        
        pano.updateUI();
    }
    public void affichageStockAleatoire(){
        pano.removeAll();
        
        
        AffichageStockAleatoire panoStock = new AffichageStockAleatoire();
        pano.add(panoStock);
                
        
        pano.updateUI();
    }
    
    

    @Override
    public Dimension getPreferredSize() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        return tailleEcran;
    }
    
    public void affichage() throws Exception{
        switch(page)
        {
        case 1:
            affichageGeneral();
            break;
        case 2:
            affichageTemperature();
            break;
        case 3:
            affichageHumidite();
            break;
        case 4:
            affichageStock();
            break;
            
            
        case 10:
            affichageGeneralAlearoire();
            break;
        case 11:
            affichageTemperatureAleatoire();
            break;
        case 12:
            affichageHumiditeAleatoire();
            break;
        case 13:
            affichageStockAleatoire();
            break;
            
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
                   affichage();
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
                affichage();
            } catch (SQLException ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource() == general){
            try {
                page=1;
                affichage();
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == temperature){
            try {
                page=2;
                affichage();
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == humidite){
            try {
                page=3;
                affichage();
            } catch (Exception ex) {
                Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == stock){
            try {
                page=4;
                affichage();
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
                    affichageMenu();
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
