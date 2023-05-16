/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Miranda
 */
public class DAO {
    
    private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/tcc";
	private String user="root";
	private String password = "123@saojudas";
	
	/**
	 * Método responsável pela conexão
	 * @return con / null
	 */
	
	public Connection conectar() {
		// Objeto usado para conexão
		Connection con = null;
		// Tratamento de exceções
		try {
			// uso do driver
			Class.forName(driver);
			// estabelecer a conexão
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
			
		}		
	}		
}
