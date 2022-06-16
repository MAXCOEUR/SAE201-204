/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sae.pkg204.AnalogI2CInput;
import sae.pkg204.DHT22;
import sae.pkg204.RechercheDansBDD.DatabaseConnection;
import sae.pkg204.Vue.fenetre;

/**
 *
 * @author Maxen
 */
public class ThreadPriseDonnee extends Thread {
    fenetre fen = SAE204.fen;
  public ThreadPriseDonnee(){
    }
    public void run(){
        while (true) {
            try {
                
                AnalogI2CInput an = new AnalogI2CInput(0);
                DHT22 dht22 = new DHT22(5);
                TempEtHum transf = dht22.getTemperatureAndHumidity();
                DatabaseConnection.Requete("INSERT INTO temperature (DateHeure, temperature, humidite) VALUES (now(), '"+transf.getTemperature()+"', '"+transf.getHumidide()+"');");
                
                sleep(5000);
                try {
                    fen.affichage(fen.page);
                } catch (Exception e) {
                    System.out.println(e);;
                }
                
                System.out.println(transf.getTemperature()+" "+transf.getHumidide());
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (I2CFactory.UnsupportedBusNumberException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
