/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.RechercheDansBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sae.pkg204.TempEtHum;

/**
 * cette classe permet de récuper les dernières valeurs de temperature et humidité dans la base de donnnées.
 * @author Maxen
 */
public class DernierePriseT {
    public static TempEtHum DerniereTempérature() throws SQLException{
        Statement s = DatabaseConnection.getConnection();
        
        ResultSet resultSet = s.executeQuery("SELECT ROUND(temperature,1) temperature,ROUND(humidite,1) humidite  from temperature where DateHeure in (SELECT max(DateHeure) from temperature);");
        
        double T=0.0;
        double H=0.0;
        while (resultSet.next()) {
            T= Double.parseDouble(resultSet.getString("temperature"));
            H= Double.parseDouble(resultSet.getString("humidite"));
        }
        return new TempEtHum(T, H);
    }
    
}
