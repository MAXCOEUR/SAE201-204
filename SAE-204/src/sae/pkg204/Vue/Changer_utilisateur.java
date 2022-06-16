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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;

/**
 *
 * @author chama
 */
public class Changer_utilisateur extends JDialog implements ActionListener, FocusListener{

    private JComboBox choix_utilisateur;
    private JButton valider;
    private String utilisateur;
    private JPanel pano;
    private JButton annuler;
    private JTextField password;
    private JLabel question_password;
    
    
    public Changer_utilisateur(fenetre fen) throws SQLException {
        
        super(fen, true);
        utilisateur = new String();
        
        this.setTitle("changer d'utilisateur");
        pano = new JPanel();
        this.setContentPane(pano);
        this.pano.setLayout(new GridBagLayout());
        
        this.choix_utilisateur = new JComboBox();
        DatabaseConnection.getConnection();
        ResultSet result = DatabaseConnection.Requete("Select * from Utilisateur");
        while (result.next()) {
            choix_utilisateur.addItem(result.getString("nom"));        
        }

        question_password = new JLabel("mot de passe");
        password = new JTextField("password");
        password.addFocusListener(this);
        this.valider = new JButton("valider");
        annuler = new JButton("annuler");
        annuler.addActionListener(this);
        this.valider.addActionListener(this);
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
        
        g.gridy = 1;
        pano.add(question_password,g);
        
        g.gridx = 1;
        pano.add(password,g);
        
        g.gridy = 2;
        pano.add(valider,g);
        
        g.gridx = 2;
        pano.add(annuler,g);
        
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){
            utilisateur = (String) choix_utilisateur.getItemAt(choix_utilisateur.getSelectedIndex());
            DatabaseConnection.getConnection();
            try {
                ResultSet result = DatabaseConnection.Requete("Select password p from Utilisateur where nom like '" + utilisateur + "';");
                while(result.next()){
                    if(!(password.getText().equals(result.getString("p")))){
                        password.setText("password");
                        JOptionPane.showMessageDialog(this, "mot de passe incorect");
                    }
                    else{
                        setVisible(false);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Changer_utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        if (e.getSource() == annuler) {
            setVisible(false);
            utilisateur = "";
        }
    }
    
    public String ShowDialog(){
        this.setVisible(true);
        return utilisateur;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == password){
            password.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == question_password){
            if(question_password.getText().equals("")){
                question_password.setText("password");
            }
        }
    }
}
