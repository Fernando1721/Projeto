package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	 private String driver = "com.mysql.cj.jdbc.Driver";
	    private String url = "jdbc:mysql://localhost:3306/tcc ";
	    private String user = "root";
	    private String password = "123@saojudas";

	    public Connection conectar() {	
	            Connection con = null;
	            try {
	                    Class.forName(driver);
	                    con = DriverManager.getConnection(url, user, password);
	                    return con;
	            } catch (Exception e) {
	                    System.out.println(e);
	                    return null;
	            }
	    }
	

}
