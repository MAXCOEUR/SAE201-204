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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sae.pkg204.Bouteille;

/**
 *
 * @author chama
 */
public class SupprimerBouteille extends JDialog implements ActionListener, FocusListener{

    private JPanel pano;
    private JComboBox liste_bouteille;
    private JTextField nb_Bouteille;
    private JButton valider;
    private Bouteille supp;

    public SupprimerBouteille(Frame owner) {
        super(owner, true);
        
        supp = new Bouteille();
        
        pano = new JPanel();
        pano.setLayout(new GridBagLayout());
        this.setContentPane(pano);
        
        liste_bouteille = new JComboBox();
        nb_Bouteille = new JTextField("nombre de bouteilles à retirer");
        valider = new JButton("valider");
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.gridx = 0;
        g.gridy = 0;
        pano.add(liste_bouteille,g);
        
        g.gridy = 1;
        pano.add(nb_Bouteille,g);
        
        g.gridy = 2;
        pano.add(valider,g);
        
        this.pack();
        
        nb_Bouteille.addFocusListener(this);
        valider.addActionListener(this);
        
    }
    
    public Bouteille ShowDialog(){
        this.setVisible(true);
        return supp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){
            String tmp = (String)liste_bouteille.getSelectedItem();
            String nom = tmp.substring(0, tmp.length()-5);
            supp.setNom(nom);
            supp.setAnnee(tmp.substring(tmp.length()-4));
            try {
                int nb = Integer.parseInt(nb_Bouteille.getText());
                supp.setNb_bouteille(nb);
                this.setVisible(false);
            } catch (NumberFormatException ex) {
                System.out.println("nombre de bouteille pas au bon format");
            }
            
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == nb_Bouteille){
            nb_Bouteille.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    
}
