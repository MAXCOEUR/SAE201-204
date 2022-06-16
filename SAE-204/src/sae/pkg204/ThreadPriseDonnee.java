/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
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
    private int time = 10000;
    private int nbrIterationDansTime=10;
  public ThreadPriseDonnee(){
    }
    public void run(){
        TempEtHum liste[] = new TempEtHum[nbrIterationDansTime];
        String listeDate[]= new String [nbrIterationDansTime];
        
        LocalDateTime timeNow = null;
        int i=0; 
        
        while (true) {
            try {
                if (i==0) {
                    timeNow = LocalDateTime.now();
                }
                
                
                AnalogI2CInput an = new AnalogI2CInput(0);
                DHT22 dht22 = new DHT22(5);
                liste[i] = dht22.getTemperatureAndHumidity();
                
                if (timeNow.getSecond()+i>=60) {
                    listeDate[i] = timeNow.getYear()+"-"+timeNow.getMonthValue()+"-"+timeNow.getDayOfMonth()+" "+timeNow.getHour()+":"+(timeNow.getMinute()+1)+":"+((timeNow.getSecond()+i)%60);
                }
                else{
                    listeDate[i] = timeNow.getYear()+"-"+timeNow.getMonthValue()+"-"+timeNow.getDayOfMonth()+" "+timeNow.getHour()+":"+timeNow.getMinute()+":"+((timeNow.getSecond()+i)%60);
                
                
                
                
                
                
                
                
                
                
                sleep(time/nbrIterationDansTime);
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (I2CFactory.UnsupportedBusNumberException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPriseDonnee.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            i++;
            if (i%nbrIterationDansTime==0) {
                try {
                    i=0;
                    String query = "INSERT INTO temperature (DateHeure, temperature, humidite) VALUES ";
                    for (int j = 0; j < nbrIterationDansTime; j++) {
                        try {
                            query+= "('"+listeDate[j]+"', '"+liste[j].getTemperature()+"', '"+liste[j].getHumidide()+"'),";
                            //DatabaseConnection.Requete("INSERT INTO temperature (DateHeure, temperature, humidite) VALUES (now(), '"+liste[j].getTemperature()+"', '"+liste[j].getHumidide()+"');");
                            //System.out.println(liste[j].getTemperature());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    query = query.substring(0, query.length()-1);
                    query+=";";
                    DatabaseConnection.Requete(query);
                    
                    fen.affichage(fen.page);
                } catch (SQLException ex) {
                    Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
}
