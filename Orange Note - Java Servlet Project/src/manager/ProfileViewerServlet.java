package manager;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/profile")
public class ProfileViewerServlet extends HttpServlet{

//	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		String username = (String) req.getAttribute("username");
//		System.out.println("Username from profile Servlet: "+username);
//		DatabaseOperations dbops = new DatabaseOperations();
//		List<List<String>> list = dbops.getDataFromUserTable(username);
//		req.setAttribute("username", username);
//		req.setAttribute("list", list);
//		RequestDispatcher rd = req.getRequestDispatcher("userprofile.jsp");
//		rd.forward(req, res);
//	}
	
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = (String) req.getAttribute("username");
		DatabaseOperations dbops = new DatabaseOperations();
		List<List<String>> list = dbops.getDataFromUserTable(username);
		req.setAttribute("username", username);
		req.setAttribute("list", list);
		if(list!=null) {
			req.setAttribute("title", list.get(0).get(0));
			req.setAttribute("notes", list.get(0).get(1));
		}
		RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
		rd.forward(req, res);
	}
}
