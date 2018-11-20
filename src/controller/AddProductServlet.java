package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productName = request.getParameter("productName");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imgName = request.getParameter("imgName");
        
        Product product = new Product(productName, stock, imgName);
        
        ProductDAO productDAO = new ProductDAO();
        productDAO.getConnection();
        
        if (productDAO.add(product)) {
            response.sendRedirect("addProductSuccessful.html");
        } else {
            response.sendRedirect("addProductFailed.html");
        }
    }
}
