/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.RechercheDansBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maxen
 */
public class DernierePriseT {
    public static double DerniereTempérature() throws SQLException{
        Statement s = DatabaseConnection.getConnection();
        
        ResultSet resultSet = s.executeQuery("SELECT ROUND(temperature,1) temperature from temperature where DateHeure in (SELECT max(DateHeure) from temperature);");
        
        double tmp=0.0;
        while (resultSet.next()) {
            tmp= Double.parseDouble(resultSet.getString("temperature"));
        }
        return tmp;
        
        
        
    }
    
}
