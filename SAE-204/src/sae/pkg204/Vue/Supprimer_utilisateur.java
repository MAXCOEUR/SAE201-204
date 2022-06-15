/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import sae.pkg204.Vue.fenetre;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author chama
 */
public class Supprimer_utilisateur extends JDialog implements ActionListener{

    private JComboBox choix_utilisateur;
    private JButton valider;
    private JButton annuler;
    private String utilisateur;
    private JPanel pano;
    
    public Supprimer_utilisateur(fenetre fen) {
        
        super(fen, true);
        utilisateur = new String();
        
        this.setTitle("changer d'utilisateur");
        pano = new JPanel();
        this.setContentPane(pano);
        this.pano.setLayout(new GridBagLayout());
        
        this.choix_utilisateur = new JComboBox();
        choix_utilisateur.addItem("Admin");
        choix_utilisateur.addItem("user01");
        choix_utilisateur.addItem("Bob");
        
        this.valider = new JButton("valider");
        this.valider.addActionListener(this);
        
        annuler = new JButton("annuler");
        annuler.addActionListener(this);
        
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.VERTICAL;
        
        g.gridwidth = 2;
        g.weightx = 0.5;
        
        g.gridx = 0;
        g.gridy = 0;
        pano.add(choix_utilisateur, g);
        
        g.fill = GridBagConstraints.BOTH;
        
        g.gridwidth = 1;
        g.weightx = 0.0;
        
        g.gridx = 1;
        g.gridy = 1;
        pano.add(valider,g);
        
        g.gridx = 0;
        pano.add(annuler,g);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){
            utilisateur = (String) choix_utilisateur.getItemAt(choix_utilisateur.getSelectedIndex());
            setVisible(false);
        }
        if(e.getSource() == annuler){
            utilisateur = "";
            this.setVisible(false);
        }
    }
    
    public String ShowDialog(){
        this.setVisible(true);
        return utilisateur;
    }
}

