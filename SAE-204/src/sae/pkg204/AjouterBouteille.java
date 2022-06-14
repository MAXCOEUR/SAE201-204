/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chama
 */
public class AjouterBouteille extends JDialog implements ActionListener, FocusListener{

    private Bouteille ajout;
    private JTextField nom;
    private JTextField annee;
    private JTextField nb_bouteille;
    private JButton valider;
    private JPanel pano;
    
    public AjouterBouteille(Frame owner) {
        super(owner,true);
        ajout = new Bouteille();
        nom = new JTextField("nom de la bouteille");
        annee = new JTextField("annee de mise en bouteille");
        nb_bouteille = new JTextField("nombre de bouteille a ajouter");
        valider = new JButton("valider");
        pano = new JPanel();
        
        this.setTitle("ajouter bouteille");
        this.setContentPane(pano);
        pano.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.gridx = 0;
        g.gridy = 0;
        pano.add(nom,g);
        
        g.gridy = 1;
        pano.add(annee,g);
        
        g.gridy = 2;
        pano.add(nb_bouteille, g);
        
        g.gridy = 3;
        pano.add(valider,g);
        
        this.pack();
        nom.addFocusListener(this);
        annee.addFocusListener(this);
        nb_bouteille.addFocusListener(this);
        valider.addActionListener(this);
    }

    public Bouteille ShowDialog(){
        this.setVisible(true);
        return ajout;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){
            ajout.setNom(nom.getText());
            ajout.setAnnee(annee.getText());
            try {
                int tmp = Integer.parseInt(nb_bouteille.getText());
                ajout.setNb_bouteille(tmp);
            } catch (NumberFormatException ex) {
                System.out.println("nombre de bouteille pas au bon format");
            }
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
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    
}
