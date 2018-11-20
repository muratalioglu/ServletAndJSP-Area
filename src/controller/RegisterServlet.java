package controller;

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

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("name") == null || request.getParameter("password") == null) {
            response.sendRedirect("registrationFailed.html");
        } else if (request.getParameter("name").length() == 0 || request.getParameter("password").length() == 0) {
            response.sendRedirect("registrationFailed.html");
        } else {
            String name = request.getParameter("name");
            String password = request.getParameter("password");            
            Connection conn = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
                conn = DriverManager.getConnection(connectionString);
                statement = conn.prepareStatement("SELECT id FROM users WHERE name = ?;");
                statement.setString(1, name);
                resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    statement.close();
                    statement = conn.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?);");
                    statement.setString(1, name);
                    statement.setString(2, password);
                    if (statement.executeUpdate() == 1) {
                        response.sendRedirect("registrationSuccessful.html");
                    } else {
                        response.sendRedirect("registrationFailed.html");
                    }
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
}
