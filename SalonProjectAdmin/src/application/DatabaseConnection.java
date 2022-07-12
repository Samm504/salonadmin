package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public Connection databaseLink;
	
	public Connection getConnection() {
		String databaseUser = "root";
		String databasePassword = "c5hB4PJ9zIKP3WSN3D";
		String url = "jdbc:mysql://localhost/projectsalon";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return databaseLink;
	}
}
