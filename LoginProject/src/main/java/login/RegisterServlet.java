package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 public static final String LOAD_DRIVER="com.mysql.cj.jdbc.Driver";
	    public static final String URL="jdbc:mysql://localhost:3306/userdb";
	    public static final String PASSWORD="root";
	    public static final String USERNAME="root";
	    Connection connection;
	    
    public RegisterServlet() {
        super();
    }

	
	public void init(ServletConfig config) throws ServletException {
		 try {
				connection = DriverManager.getConnection(URL,PASSWORD,USERNAME);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			PreparedStatement ps = connection.prepareStatement("insert into userInfo values (?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=gainsboro><center>");
			pw.println("<h1>User Registered Successfully...</h1>");
			pw.println("<a href=login.html>Login Here</a>");
			pw.println("</center></body></html>");
			
			} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

}
