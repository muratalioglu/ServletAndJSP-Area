package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        
        UserDAO userDAO = new UserDAO();
        userDAO.getConnection();
        
        if (userDAO.register(user)) {
            response.sendRedirect("registrationSuccessful.html");
        } else {
            response.sendRedirect("registrationFailed.html");
        }
    }
}
