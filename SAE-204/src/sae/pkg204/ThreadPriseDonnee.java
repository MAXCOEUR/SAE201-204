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
        
        
        LocalDateTime timeNow = null;
        int i=0; 
        TempEtHum liste[] = new TempEtHum[nbrIterationDansTime];
        String listeDate[]= new String [nbrIterationDansTime];
        while (true) {
            
            try {
                if (i==0) {
                    timeNow = LocalDateTime.now();
                }
                
                while (liste[i]==null) {
                    AnalogI2CInput an = new AnalogI2CInput(0);
                    DHT22 dht22 = new DHT22(5);
                    if (dht22==null) {
                        ;
                    }
                    else{
                        liste[i] = dht22.getTemperatureAndHumidity();
                    }
                }
                
                
                
                
                if (timeNow.getSecond()+i>=60) {
                    listeDate[i] = timeNow.getYear()+"-"+timeNow.getMonthValue()+"-"+timeNow.getDayOfMonth()+" "+timeNow.getHour()+":"+(timeNow.getMinute()+1)+":"+((timeNow.getSecond()+i)%60);
                }
                else{
                    listeDate[i] = timeNow.getYear()+"-"+timeNow.getMonthValue()+"-"+timeNow.getDayOfMonth()+" "+timeNow.getHour()+":"+timeNow.getMinute()+":"+((timeNow.getSecond()+i)%60);
                
                
                
                
                
                
                
                
                
                sleep(time/nbrIterationDansTime);
                
                
            }
            } catch (IOException ex) {
                System.out.println("erreur1");
            } catch (I2CFactory.UnsupportedBusNumberException ex) {
                System.out.println("erreur2");
            } catch (InterruptedException ex) {
                System.out.println("erreur3");
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
                            System.out.println("erreur10");
                        }
                    }
                    query = query.substring(0, query.length()-1);
                    query+=";";
                    DatabaseConnection.Requete(query);
                    String query2 ="DELETE from temperature where 2000<(Select count(DateHeure) from temperature) LIMIT 500;";
                    DatabaseConnection.Requete(query2);
                    
                    liste = new TempEtHum[nbrIterationDansTime];
                    listeDate= new String [nbrIterationDansTime];
                    
                    sleep(time/nbrIterationDansTime);
                    
                    fen.affichage();
                } catch (SQLException ex) {
                    System.out.println(ex);
                } catch (Exception ex) {
                    System.out.println("erreur5");
                }
                
            }
        }
        
    }
}
