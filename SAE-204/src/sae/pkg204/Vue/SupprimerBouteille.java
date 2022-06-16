/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

import java.sql.ResultSet;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;

/**
 *
 * @author chama
 */
public class SupprimerBouteille extends JDialog implements ActionListener, FocusListener{

    private JPanel pano= new JPanel();
    private JComboBox liste_bouteille= new JComboBox();
    private JComboBox liste_date_bouteille = new JComboBox();
    private JComboBox liste_type = new JComboBox();
    private JTextField nb_Bouteille = new JTextField("1");
    private JButton valider = new JButton("valider");
    private Bouteille supp= new Bouteille();
    private JLabel question  = new JLabel("entrez le nombre de bouteilles");
    private JButton annuler= new JButton("annuler");
    

    public SupprimerBouteille(Frame owner) throws SQLException {
        super(owner, true);
        
        
        Statement s = DatabaseConnection.getConnection();
        
        ResultSet resultSet = s.executeQuery("select distinct(nom) N from stock;");
        
        while (resultSet.next()) {
            liste_bouteille.addItem(resultSet.getString("N"));
        }
        actionPerformed(new ActionEvent(liste_bouteille, 10, ""));
        actionPerformed(new ActionEvent(liste_date_bouteille, 10, ""));
        actualiser();
        
    }
    private void actualiser(){
        pano.removeAll();
        GridBagConstraints g = new GridBagConstraints();
        pano.setLayout(new GridBagLayout());
        this.setContentPane(pano);
        g.fill = GridBagConstraints.BOTH;
        
        g.gridx = 1;
        g.gridy = 0;
        pano.add(liste_bouteille,g);
        
        g.gridy = 1;
        pano.add(liste_date_bouteille,g);
        
        g.gridy = 2;
        pano.add(liste_type,g);
        
        g.gridy = 3;
        pano.add(nb_Bouteille,g);
        
        g.gridy = 4;
        pano.add(valider,g);
        
        g.gridx = 0;
        g.gridy = 3;
        pano.add(question,g);
        
        g.gridy = 4;
        pano.add(annuler,g);
        
        this.pack();
        
        nb_Bouteille.addFocusListener(this);
        valider.addActionListener(this);
        liste_bouteille.addActionListener(this);
        annuler.addActionListener(this);
        liste_date_bouteille.addActionListener(this);
    }
    
    public Bouteille ShowDialog(){
        this.setVisible(true);
        return supp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == valider){            
            try {
                int nb = Integer.parseInt(nb_Bouteille.getText());
                String tmp =(String)liste_date_bouteille.getSelectedItem();
                String y="";
                for (int i = 0; i < 4; i++) {
                    y+=tmp.charAt(i);
                }
                supp = new Bouteille(nb, (String)liste_bouteille.getSelectedItem(), y, (String)liste_type.getSelectedItem());
            } catch (NumberFormatException ex) {
                System.out.println("nombre de bouteille pas au bon format");
            }
            this.setVisible(false);
        }
        if(e.getSource() == liste_bouteille){
            liste_date_bouteille = new JComboBox();
            Statement s = DatabaseConnection.getConnection();
            try {
                ResultSet resultSet = s.executeQuery("SELECT distinct (date) D from stock where nom like \""+(String)liste_bouteille.getSelectedItem()+"\";" );
                while (resultSet.next()) {
                    liste_date_bouteille.addItem(resultSet.getString("D"));
                }
                actionPerformed(new ActionEvent(liste_date_bouteille, 10, ""));
                actualiser();
            } catch (SQLException ex) {
                Logger.getLogger(SupprimerBouteille.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == liste_date_bouteille ){
            liste_type= new JComboBox();
            Statement s = DatabaseConnection.getConnection();
            try {
                String tmp =(String)liste_date_bouteille.getSelectedItem();
                String y="";
                for (int i = 0; i < 4; i++) {
                    y+=tmp.charAt(i);
                }
                ResultSet resultSet = s.executeQuery("SELECT distinct(type) T from stock where nom like '"+(String)liste_bouteille.getSelectedItem()+"' and date like '"+y+"';" );
                while (resultSet.next()) {
                    liste_type.addItem(resultSet.getString("T"));
                }
                actualiser();
            } catch (SQLException ex) {
                Logger.getLogger(SupprimerBouteille.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == annuler){
            supp = null;
            this.setVisible(false);
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
         if(e.getSource() == nb_Bouteille){
             if(nb_Bouteille.getText().equals("")){
                 nb_Bouteille.setText("1");
             }
         }
    }
    
}
