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
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uusername=request.getParameter("name");
		String upassword=request.getParameter("password");
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3305/app1?characterEncoding=utf8","root","root");
			String sql="SELECT * FROM apptable where username='"+uusername +"' and password='"+upassword+"' ";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
 
			if(rs.next()){
				response.sendRedirect("success.jsp");
				
			}
			else{
				out.print("<h3>registration failed</h3>");
				out.print(rs);
				out.print(uusername);out.print(upassword);
			}
		}
		catch (Exception ex){
			System.out.println(ex);
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
    

}
