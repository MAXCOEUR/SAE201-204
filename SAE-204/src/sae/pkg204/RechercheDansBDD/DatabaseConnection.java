/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204.RechercheDansBDD;

/**
 *
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
	public static Statement getConnection()
	{
            if (st==null) {
                DatabaseConnection();
            }
            return st;
	}
        public static ResultSet Requete(String r) throws SQLException{
            getConnection();
            return st.executeQuery(r);
        }
}

