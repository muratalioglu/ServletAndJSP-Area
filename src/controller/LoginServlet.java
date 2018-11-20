package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        User user = new User(name, password);
        
        UserDAO userDAO = new UserDAO();
        userDAO.getConnection();
        if (userDAO.login(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("currentSessionUser", name);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("loginFailed.html");
        }        
    }
}
