/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author chama
 */
public class fenetre extends JFrame implements ActionListener{

    private JPanel pano;
    private JPanel JPanelTemperature;
    private JPanel JPanelGraphique;
    private JMenuBar menu;
    private JMenu affiche;
    private JMenu Modifier;
    private JMenuItem general;
    private JMenuItem ajouter_Bouteille;
    private JMenuItem supprimer_Bouteille;

    public fenetre() {
        
        this.setTitle("tmp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.JPanelTemperature = new JPanel();
        this.JPanelGraphique = new JPanel();
        JPanelGraphique.setLayout(new GridBagLayout());
        JPanelTemperature.setLayout(new GridBagLayout());
        
        
        this.menu = new JMenuBar();
        affiche = new JMenu("affiche");
        this.Modifier = new JMenu("modifier");
        menu.add(affiche);
        menu.add(Modifier);
        
        this.ajouter_Bouteille = new JMenuItem("Ajouter une bouteille");
        this.supprimer_Bouteille = new JMenuItem("Supprimer une bouteille");
        this.general = new JMenuItem("general");
        Modifier.add(ajouter_Bouteille);
        Modifier.add(supprimer_Bouteille);
        
        affiche.add(general);
        this.setJMenuBar(menu);
        
        this.pano = new JPanel();
        
        this.setContentPane(pano);
        this.pano.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.fill = GridBagConstraints.BOTH;
        
        g.gridx = 1;
        g.gridy = 1;
        this.pano.add(JPanelGraphique, g);
        
        g.gridx = 2;
        g.gridy = 1;
        this.pano.add(JPanelTemperature, g);
        
        
        this.pack();
        
        ajouter_Bouteille.addActionListener(this);
        supprimer_Bouteille.addActionListener(this);
        general.addActionListener(this);
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
        }
    }
    
}
