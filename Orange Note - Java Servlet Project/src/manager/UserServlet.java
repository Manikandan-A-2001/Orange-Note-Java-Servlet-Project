package manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newUser")
public class UserServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		DatabaseOperations dbops = new DatabaseOperations();
		int result;
		try {
			result = dbops.storeIntoDatabase(username, email, password);
			if(result>0) {
				res.sendRedirect("login.jsp");
				dbops.createUserTable(username);
			}else {
				PrintWriter out = res.getWriter();
				out.println("Its not working...");
			}
		} catch (SQLException e) {
			if(e.getErrorCode()==1062) {
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				out.println("<h4 style='color:red;'>Username and Email id Already exist...</h4>");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.include(req, res);
			}
		}
		

	}
}
