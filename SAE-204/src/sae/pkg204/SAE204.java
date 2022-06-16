/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;
import sae.pkg204.Vue.fenetre;

/**
 *
 * @author p2101068
 */
public class SAE204 {

    public static fenetre fen;
    public static ThreadPriseDonnee t;
public static void main( String[ ] args )throws Exception {

    
    fen = new fenetre();
    t = new ThreadPriseDonnee();
    t.start();
    fen.setVisible(true);
    
   }
}
    








