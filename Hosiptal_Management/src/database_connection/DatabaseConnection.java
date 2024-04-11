package database_connection;

import java.sql.*;

public class DatabaseConnection {
	public static  Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosiptal","root","poovarasan@13");
return con;
	}
}


