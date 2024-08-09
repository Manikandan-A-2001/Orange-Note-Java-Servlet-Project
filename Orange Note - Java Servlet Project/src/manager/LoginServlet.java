package manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		DatabaseOperations dbops = new DatabaseOperations();
		String username = dbops.checkLoginDetails(email, password);
			PrintWriter out = res.getWriter();
			if(username!=null) {
				System.out.println("Username from LoginServlet: "+username);
				req.setAttribute("username", username);
				RequestDispatcher rd = req.getRequestDispatcher("/profile");
				rd.forward(req, res);
			}
			else{
				res.setContentType("text/html");
				out.println("<h3 style='color:white;'>Invalid User Details..</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.include(req, res);
			}
	}
}
