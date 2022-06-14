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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chama
 */
public class ajouter_utilisateur extends JDialog implements ActionListener, FocusListener{
    
    private JPanel pano;
    private JComboBox liste_role;
    private JButton valider;
    private JTextField nom;
    private JTextField pass_word;
    
    Utilisateur ut; 

    public ajouter_utilisateur(Frame owner) {
        super(owner, true);
        ut = new Utilisateur();
        
        pano = new JPanel();
        liste_role = new JComboBox();
        valider = new JButton("valider");
        nom = new JTextField("entrer un nom d'utilisateur");
        pass_word = new JTextField("entrer un mot de passe");
        GridBagConstraints g = new GridBagConstraints();
        
        this.setContentPane(pano);
        pano.setLayout(new GridBagLayout());
        
        g.gridx = 0;
        g.gridy = 0;
        pano.add(nom,g);
        
        g.gridy = 1;
        liste_role.addItem("toto");
        pano.add(liste_role,g);
        
        g.gridy = 2;
        pano.add(pass_word,g);
        
        g.gridy = 3;
        pano.add(valider,g);
        
        valider.addActionListener(this);
        nom.addFocusListener(this);
        pass_word.addFocusListener(this);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){
            ut.setNom(nom.getText());
            ut.setRole((String)liste_role.getItemAt(liste_role.getSelectedIndex()));
            ut.setMot_de_passe(pass_word.getText());
            this.setVisible(false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == nom){
            nom.setText("");
        }
        if(e.getSource() == pass_word){
            pass_word.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    public Utilisateur ShowDialog(){
        this.setVisible(true);
        return ut;       
    }
    
    
}
