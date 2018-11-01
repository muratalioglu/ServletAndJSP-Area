package main;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productName = request.getParameter("productName");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imgName = request.getParameter("imgName");
        
        if (!(imgName.toUpperCase().endsWith(".JPG") || imgName.toUpperCase().endsWith(".JPEG"))) {
            response.sendRedirect("addProductFailed.html");
        }
        
        Product product = new Product(productName, stock, imgName);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
            Connection conn = DriverManager.getConnection(connectionString);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO products (name, stock, img_name) VALUES (?, ?, ?);");
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.setString(3, product.getImgName());
            if (statement.executeUpdate() == 1) {
                response.sendRedirect("addProductSuccessful.html");
            } else {
                response.sendRedirect("addProductFailed.html");
            }
                
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
