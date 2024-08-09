package manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/select")
public class SelectServlet extends HttpServlet{

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String title = req.getParameter("title");
		DatabaseOperations dbops = new DatabaseOperations();
		List<String> list = dbops.selectUserData(username, title);
		req.setAttribute("title", list.get(0));
		req.setAttribute("notes", list.get(1));
		int result = dbops.deleteUserNote(username, title);
		if(result>0) {
			List<List<String>> newlist = dbops.getDataFromUserTable(username);
			req.setAttribute("list", newlist);
			req.setAttribute("username", username);
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, resp);
		}
	}
}
