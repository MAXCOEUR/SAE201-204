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
        
        Class.forName( "com.mysql.jdbc.Driver" );
       connect = DriverManager.getConnection( 
         "jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/"+t ,     
         "p2101068",     
         "12101068");
       st = connect.createStatement( );
    }
    
    
    public Statement getStatement(){
        return st;
    }
    
}
