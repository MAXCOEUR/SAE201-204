/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author p2101068
 */
public class DataBase {
    Singleton Base = null;
    ResultSet resultSet;
    Statement statement = null;
    String r=null;
    
    
    public DataBase(Singleton t) throws SQLException, ClassNotFoundException {
        Base = t;
    }
    
    public void CreateUser(String requete) throws SQLException{
        statement.executeQuery(requete);
    }

}
