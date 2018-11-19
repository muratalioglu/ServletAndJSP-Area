package main;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        if (!fieldsAreEmpty(request)) {
            String productName = request.getParameter("productName");
            int stock = Integer.parseInt(request.getParameter("stock"));
            String imgName = request.getParameter("imgName");
            
            Product product = new Product(productName, stock, imgName);
            ProductDAOImpl productDAOImpl = new ProductDAOImpl();
            productDAOImpl.getConnection();
            if (productDAOImpl.add(product)) {
                response.sendRedirect("addProductSuccessful.html");
            } else {
                response.sendRedirect("addProductFailed.html");
            }
        } else {
            response.sendRedirect("addProductFailed.html");
        }     
    }
    
    private boolean fieldsAreEmpty(HttpServletRequest r) {
        return r.getParameter("productName").length() == 0 || r.getParameter("stock").length() == 0 || r.getParameter("imgName").length() == 0;
    }
}
