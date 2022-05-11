package com.faceart.faceart.servlets;

import java.io.IOException;
import com.faceart.faceart.dao.user.UserJpaDAO;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") != null &&
                request.getSession().getAttribute("user") != "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        request.getRequestDispatcher("/sites/users/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginButtonValue = req.getParameter("loginButton");
        String emailInputValue = req.getParameter("emailInput");
        String passwordInputValue = req.getParameter("passwordInput");

        if (loginButtonValue.equals("true")) {
            UserJpaDAO userJpaDAO = new UserJpaDAO();
            try {
                User user = userJpaDAO.getUserByEmailAndPassword(emailInputValue, passwordInputValue);
                if (!user.isActive()) {
                    resp.sendRedirect(req.getContextPath());
                    return;
                }
                req.getSession().setAttribute("user", user);
            } catch (NullPointerException e) {
                resp.sendRedirect(req.getContextPath()+"/login");
            }

            resp.sendRedirect(req.getContextPath());
        }
    }
}