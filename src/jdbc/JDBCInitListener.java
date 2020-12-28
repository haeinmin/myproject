package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class JDBCInitListener
 *
 */
@WebListener
public class JDBCInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public JDBCInitListener() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
    ServletContext application = sce.getServletContext();
    
    String url = application.getInitParameter("jdbcUrl");
    String user = application.getInitParameter("jdbcUser");
    String pw = application.getInitParameter("jdbcPw");
    
//    System.out.println(url);
//    System.out.println(user);
//    System.out.println(pw);
    
    // 1. load class
    try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    // 2. get connection from drivermanager + close();
    try (
    		Connection con = DriverManager.getConnection(url, user, pw);
    ) {
    	System.out.println("connected successfully");
    } catch (Exception e) {
    	e.printStackTrace();
    }
    
    ConnectionProvider.setUrl(url);
    ConnectionProvider.setUser(user);
    ConnectionProvider.setPw(pw);
    
    // context root path
    String contextPath = application.getContextPath();
    application.setAttribute("root", contextPath);
    
    
    }
	
}
