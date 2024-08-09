package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class DatabaseOperations {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3305/password";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mysql@123";
    
    public int storeIntoDatabase(String username, String email, String password) throws ServletException, SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("insert into userDetails (username, email, password) values(?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
    }

    public String checkLoginDetails(String email, String password) throws ServletException {
        Connection con = null;
        String username = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("select * from userDetails where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getNString("password").equals(password)) {
                    username = rs.getNString("username");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
        return username;
    }

    public void createUserTable(String username) throws ServletException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "create table " + username + " (title VARCHAR(200) UNIQUE, notes VARCHAR(2000), cur_time DATETIME)";
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
    }

    public List<List<String>> getDataFromUserTable(String username) throws ServletException {
        Connection con = null;
        List<List<String>> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM " + username + " ORDER BY cur_time DESC";
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> templist = new ArrayList<>();
                templist.add(rs.getNString("title"));
                templist.add(rs.getNString("notes"));
                list.add(templist);
            }
            if (list.isEmpty()) {
                List<String> templist = new ArrayList<>();
                templist.add("title");
                templist.add("Type notes here...");
                list.add(templist);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
        return list;
    }

    public int insertUserNotes(String username, String title, String notes) throws ServletException, SQLException {
        Connection con = null;
        int ans = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO " + username + " (title, notes, cur_time) VALUES (?, ?, NOW())";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, notes);
            ans = ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
        return ans;
    }

    public List<String> selectUserData(String username, String title) throws ServletException {
        Connection con = null;
        List<String> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + username + " WHERE title = ?");
            ps.setString(1, title);
            ResultSet rs=  ps.executeQuery();
            while(rs.next()) {
            	list.add(rs.getNString("title"));
            	System.out.println(rs.getNString("title")+ "      "+rs.getNString("notes"));
            	list.add(rs.getNString("notes"));
            }
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
    }

	public int deleteUserNote(String username, String title) throws ServletException {
		Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("DELETE FROM " + username + " WHERE title = ?");
            ps.setString(1, title);
            return  ps.executeUpdate();
        
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
	}
}
