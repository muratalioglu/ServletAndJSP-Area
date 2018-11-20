package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.ProductDAO;

public class GetProductsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("currentSessionUser") != null) {
                ProductDAO productDAO = new ProductDAO();
                productDAO.getConnection();
                List<Product> products = productDAO.get();
                session.setAttribute("products", products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("login.html");
        }
    }
}   
