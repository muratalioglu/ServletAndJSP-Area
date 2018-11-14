package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProductServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (session != null) {
            if (session.getAttribute("currentSessionUser") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                    final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
                    conn = DriverManager.getConnection(connectionString);
                    statement = conn.prepareStatement("SELECT * FROM products WHERE id = ?;");
                    statement.setInt(1, id);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        Product product = new Product( resultSet.getInt("id"),
                                                        resultSet.getString("name"),
                                                        resultSet.getInt("stock"),
                                                        resultSet.getString("img_name"));
                        request.setAttribute("product", product);
                        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("index.jsp");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
