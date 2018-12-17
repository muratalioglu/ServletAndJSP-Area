package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

public class FindProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            final int id = Integer.parseInt(request.getParameter("id"));
            Product product = getProduct(id);
            if (product != null) {
                request.setAttribute("product", product);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("updateProduct.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Product getProduct(final int id) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.getConnection();
        Product product = productDAO.get(id);
        return product;
    }
}
