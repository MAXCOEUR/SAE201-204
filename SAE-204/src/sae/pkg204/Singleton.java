/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.pkg204;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author p2101068
 */
public class Singleton {

    Connection connect=null;
    Statement st=null;
    public Singleton(String t) throws SQLException, ClassNotFoundException {
        
        Class.forName("org.mariadb.jdbc.Driver");
       connect = DriverManager.getConnection( 
         "jdbc:mariadb://localhost:3306/"+t ,     
         "root",     
         "user01");
       st = connect.createStatement( );
    }
    
    
    public Statement getStatement(){
        return st;
    }
    
}
