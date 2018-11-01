package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DoUpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Connection conn = null;
        PreparedStatement statement = null;
        if (session != null) {
            if (session.getAttribute("currentSessionUser") != null) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                    final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
                    conn = DriverManager.getConnection(connectionString);
                    statement = conn.prepareStatement("UPDATE products SET name = ?, stock = ?, img_name = ? WHERE id = ?;");
                    statement.setString(1, request.getParameter("productName"));
                    statement.setInt(2, Integer.parseInt(request.getParameter("stock")));
                    statement.setString(3, request.getParameter("imgName"));
                    statement.setInt(4, Integer.parseInt(request.getParameter("id")));
                    if (statement.executeUpdate() == 1) {
                        response.sendRedirect("updateSuccessful.html");
                    } else {
                        response.sendRedirect("updateFailed.html");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
