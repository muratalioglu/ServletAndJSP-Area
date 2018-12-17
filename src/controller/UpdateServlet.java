package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
