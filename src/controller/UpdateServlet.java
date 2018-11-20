package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;

public class UpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = null;
        if (hasImage(request)) {
            product = new Product(Integer.parseInt(request.getParameter("id")),
                                          request.getParameter("productName"),
                                          Integer.parseInt(request.getParameter("stock")),
                                          request.getParameter("imgName"));
        } else {
            product = new Product(Integer.parseInt(request.getParameter("id")),
                                          request.getParameter("productName"),
                                          Integer.parseInt(request.getParameter("stock")));
        }
        
        ProductDAO productDAO = new ProductDAO();
        productDAO.getConnection();
        
        if (productDAO.update(product)) {
            response.sendRedirect("updateSuccessful.html");
        } else {
            response.sendRedirect("updateFailed.html");
        }
    }
    
    private boolean hasImage(HttpServletRequest request) {
        String imgName = request.getParameter("imgName");
        if (imgName == null)
            return false;
        return imgName.toUpperCase().endsWith(".JPG") || imgName.toUpperCase().endsWith(".JPEG");
    }
}
