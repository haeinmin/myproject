package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url;
	private static String user;
	private static String pw;
	
	static void setUrl(String url) {
		ConnectionProvider.url = url;
	}
	
	static void setUser(String user) {
		ConnectionProvider.user = user;
	}
	
	static void setPw(String pw) {
		ConnectionProvider.pw = pw;
	}
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
