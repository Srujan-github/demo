package app;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uusername=request.getParameter("uname");
		String upassword=request.getParameter("password");
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3305/app1?characterEncoding=utf8","root","root");
			PreparedStatement pt=con.prepareStatement("insert into apptable values(?,?)");
			pt.setString(1, uusername);
			pt.setString(2, upassword);
			int i=pt.executeUpdate();
			if(i>0){
				response.sendRedirect("loginpage.jsp");
				
			}
			else{
				out.print("<h3>registration failed</h3>");
			}
		}
		catch (Exception ex){
			System.out.println(ex);
		}
		 
	}

}
