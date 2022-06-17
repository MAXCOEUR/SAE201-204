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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * cette classe permet de créer la JDialog pour ajouter un utilisateur.
 * @author chama
 */
public class ajouter_utilisateur extends JDialog implements ActionListener, FocusListener{
    
    private JPanel pano;
    private JRadioButton tous_les_droits;
    private JRadioButton aucun_droits;
    private ButtonGroup gp_bouton;
    private JButton valider;
    private JButton annuler;
    private JTextField nom;
    private JTextField pass_word;
    private JLabel question_password;
    private JLabel question_nom;
    
    Utilisateur ut; 

    public ajouter_utilisateur(Frame owner) {
        super(owner, true);
        ut = new Utilisateur();
        
        pano = new JPanel();
        valider = new JButton("valider");
         nom = new JTextField("entrer un nom d'utilisateur");
        pass_word = new JTextField("entrer un mot de passe");
        
        annuler = new JButton("annuler");
        tous_les_droits = new JRadioButton("peut créer un utilisateur");
        aucun_droits = new JRadioButton("ne peut pas créer un utilisateur");
        gp_bouton = new ButtonGroup();
        gp_bouton.add(tous_les_droits);
        gp_bouton.add(aucun_droits);
        question_password = new JLabel("mot de passe de l'utilisateur");
        question_nom = new JLabel("nom de l'utilisateur");
        
        GridBagConstraints g = new GridBagConstraints();
        
        
        this.setContentPane(pano);
        pano.setLayout(new GridBagLayout());
        
        g.fill = GridBagConstraints.BOTH;
        
        g.gridx = 1;
        g.gridy = 0;
        pano.add(nom,g);
        
        g.gridy = 1;
        pano.add(pass_word,g);
        
        g.gridy = 2;
        pano.add(tous_les_droits,g);
        
        g.gridy = 3;
        pano.add(aucun_droits,g);
        
        g.gridy = 4;
        pano.add(pass_word,g);
        
        g.gridy = 5;
        pano.add(valider,g);
        
        g.gridx = 0;
        g.gridy = 0;
        pano.add(question_nom,g);
        
        g.gridy = 4;
        pano.add(question_password,g);
        
        g.gridx = 0;
        g.gridy = 5;
        pano.add(annuler,g);
        
        valider.addActionListener(this);
        annuler.addActionListener(this);
        nom.addFocusListener(this);
        pass_word.addFocusListener(this);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){
            ut.setNom(nom.getText());
            if(gp_bouton.getSelection() == tous_les_droits){
                ut.setRole(1);
            }
            else{
                ut.setRole(0);
            }
            ut.setMot_de_passe(pass_word.getText());
            this.setVisible(false);
        }
        if(e.getSource() == annuler){
            ut = null;
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
        if(e.getSource() == nom){
            if(nom.getText().equals("")){
                nom.setText("nom de l'utilisateur");
            }
        }
        if(e.getSource() == pass_word){
            if(pass_word.getText().equals("")){
                pass_word.setText("mot de passe de l'utilisateur");
            }
        }
    }
    
    /**
     * cette méthode permet d'afficher cette JDialog et renvoie une instance de Utilisateur avec les valeur récupé dans la JDialog.
     * @return l'utilisateur a créer.
     */
    public Utilisateur ShowDialog(){
        this.setVisible(true);
        return ut;       
    }
    
    
}
