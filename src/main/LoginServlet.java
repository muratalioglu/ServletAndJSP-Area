package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        if (name.length() == 0 || password.length() == 0) {
            response.sendRedirect("loginFailed.html");
        }
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
            conn = DriverManager.getConnection(connectionString);
            statement = conn.prepareStatement("SELECT * FROM users WHERE name = ?;");
            statement.setString(1, name);
            
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString(3).equals(password)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("currentSessionUser", name);
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("loginFailed.html");
                }
            } else {
                response.sendRedirect("loginFailed.html");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
