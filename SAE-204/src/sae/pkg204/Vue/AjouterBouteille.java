/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;

/**
 *
 * @author chama
 */
public class AjouterBouteille extends JDialog implements ActionListener, FocusListener{

    private Bouteille ajout;
    private JTextField nom;
    private JTextField annee;
    private JTextField nb_bouteille;
    private JTextField type;
    private JButton valider;
    private JPanel pano;
    private JLabel question_nom;
    private JLabel question_annee;
    private JLabel question_nbBouteille;
    private JLabel question_type;
    private JButton annuler;
    
    public AjouterBouteille(Frame owner) {
        super(owner,true);
        nom = new JTextField("nom de la bouteille");
        annee = new JTextField("annee de mise en bouteille");
        nb_bouteille = new JTextField("nombre de bouteille a ajouter");
        type = new JTextField("type de vin");
        valider = new JButton("valider");
        annuler = new JButton("annuler");
        pano = new JPanel();
        question_nom = new JLabel("nom de la bouteille");
        question_annee = new JLabel("annee de mise en bouteille");
        question_nbBouteille = new JLabel("nombre de bouteille");
        question_type = new JLabel("type de vin");
        
        
        this.setTitle("ajouter bouteille");
        this.setContentPane(pano);
        pano.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        
        g.gridx = 1;
        g.gridy = 0;
        pano.add(nom,g);
        
        g.gridy = 1;
        pano.add(annee,g);
        
        g.gridy = 2;
        pano.add(type,g);
        
        g.gridy = 3;
        pano.add(nb_bouteille, g);
        
        g.gridy = 4;
        pano.add(valider,g);
        
        g.gridx = 0;
        g.gridy = 0;
        pano.add(question_nom,g);
        
        g.gridy = 1;
        pano.add(question_annee,g);
        
        g.gridy = 2;
        pano.add(question_type,g);
        
        g.gridy = 3;
        pano.add(question_nbBouteille,g);
        
        g.gridy = 4;
        pano.add(annuler,g);
        
        this.pack();
        nom.addFocusListener(this);
        annee.addFocusListener(this);
        nb_bouteille.addFocusListener(this);
        type.addFocusListener(this);
        valider.addActionListener(this);
        annuler.addActionListener(this);
    }

    public Bouteille ShowDialog(){
        this.setVisible(true);
        return ajout;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){            
            try {
                int tmp = Integer.parseInt(nb_bouteille.getText());
                ajout = new Bouteille(tmp, nom.getText(), annee.getText(), type.getText());
            } catch (NumberFormatException ex) {
                System.out.println("nombre de bouteille pas au bon format");
            }
            this.setVisible(false);
        }
        if(e.getSource() == annuler){
            ajout = null;
            this.setVisible(false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == nom){
            nom.setText("");
        }
        if(e.getSource() == annee){
            annee.setText("");
        }
        if(e.getSource() == nb_bouteille){
            nb_bouteille.setText("");
        }
        if(e.getSource()== type){
            type.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    

    
}
