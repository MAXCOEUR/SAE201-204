/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.RechercheDansBDD;

/**
 * cette classe permet de créer une connection vers la base de donnée du raspberry
 * @author Maxen
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private static Connection con = null;
        private static Statement  st= null;

	private static void DatabaseConnection()
	{
		String url = "jdbc:mariadb://localhost:3306/application";
		String user = "root";
		String pass = "user01";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
                        st=con.createStatement();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
        /**
         * cette methode permet de verifier si une instance de connection est deja crée. si c'est le cas, elle ne fais rien et renvoie la connection deja créée.
         * Sino, elle créer la nouvelle connection.
         * @return la connection a la base de donnée.
         */
        
	public static Statement getConnection()
	{
            if (st==null) {
                DatabaseConnection();
            }
            return st;
	}
        
        /**
         * cette methode permet d'executer une requête SQL dans la base.
         * @param r correspond à la requete a executer en SQL
         * @return un ResultSet corespondant au resultat de la requete
         * @throws SQLException si la requete n'a pas pu s'executer.
         */
        public static ResultSet Requete(String r) throws SQLException{
            getConnection();
            return st.executeQuery(r);
        }
}

