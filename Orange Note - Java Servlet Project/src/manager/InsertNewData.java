package manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/save")
public class InsertNewData extends HttpServlet{

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String title = (String)req.getParameter("title");
			String notes = (String)req.getParameter("notes");
			String username = (String)req.getParameter("username");
			DatabaseOperations dbops = new DatabaseOperations();
			try {
				int result = dbops.insertUserNotes(username,title,notes);
				if(result>0) {
					List<List<String>> list = dbops.getDataFromUserTable(username);
					req.setAttribute("username", username);
					req.setAttribute("list", list);
					if(list!=null) {
						req.setAttribute("title", title);
						req.setAttribute("notes", notes);
					}
					RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
					rd.forward(req, res);
				}
			}catch(SQLException e) {
				if(e.getErrorCode() == 1062) {
					req.setAttribute("error", "The title name already exists...");
					List<List<String>> list = dbops.getDataFromUserTable(username);
					req.setAttribute("username", username);
					req.setAttribute("list", list);
					if(list != null) {
						req.setAttribute("title", title);
						req.setAttribute("notes", notes);
					}
					RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
					rd.forward(req, res);
				}
			}
	}
}

