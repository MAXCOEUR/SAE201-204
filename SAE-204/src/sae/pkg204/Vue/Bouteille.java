/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.Vue;

/**
 * cette classe permet de créer une instance de Bouteille affin de concerver le nom, l'annee, le type et le nombre de bouteille.
 * @author chama
 */
public class Bouteille {
    private int nb_bouteille;
    private String nom;
    private String annee;
    private String type;
    
    public Bouteille() {
        type = new String();
        nom= new String();
        annee= new String();
    }

    public Bouteille(int nb_bouteille, String nom, String annee, String type) {
        this.nb_bouteille = nb_bouteille;
        this.nom = nom;
        this.annee = annee;
        this.type = type;
    }
    
    public int getNb_bouteille() {
        return nb_bouteille;
    }

    public void setNb_bouteille(int nb_bouteille) {
        this.nb_bouteille = nb_bouteille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bouteille{" + "nb_bouteille=" + nb_bouteille + ", nom=" + nom + ", annee=" + annee + ", type=" + type + '}';
    }
    
    
}
