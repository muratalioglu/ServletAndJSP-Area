package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (inputFieldsAreOK(request)) {
            Product product = new Product(request.getParameter("productName"),
                                            Integer.parseInt(request.getParameter("stock")),
                                            request.getParameter("imgName"));
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
    
    private boolean inputFieldsAreOK(HttpServletRequest r) {
        return r.getParameter("productName").length() > 0 && r.getParameter("stock").length() > 0 && r.getParameter("imgName").length() > 0;
    }
}
