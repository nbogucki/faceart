package com.faceart.faceart.servlets;

import java.io.IOException;
import com.faceart.faceart.dao.user.UserJpaDAO;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AccountServlet", value = "/account")
public class AccountServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getRequestDispatcher("/sites/users/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String changeButton = req.getParameter("changeButton");
        String emailInputValue = req.getParameter("emailInput");
        String passwordInputValue = req.getParameter("passwordInput");
        String addressInput = req.getParameter("addressInput");
        String firstNameInput = req.getParameter("firstNameInput");
        String secondNameInput = req.getParameter("secondNameInput");

        if (changeButton.equals("true")) {
            User user = (User) req.getSession().getAttribute("user");
            UserJpaDAO userJpaDAO = new UserJpaDAO();
            boolean change = false;
            req.setAttribute("showChangeInfo", "");

            if (!emailInputValue.equals("") && !emailInputValue.equals(user.getEmail())) {
                user.setEmail(emailInputValue);
                change = true;
            }

            if (!passwordInputValue.equals("") && !passwordInputValue.equals(user.getPassword())) {
                user.setPassword(passwordInputValue);
                change = true;
            }

            if (!addressInput.equals("") && !addressInput.equals(user.getAddress())) {
                user.setAddress(addressInput);
                change = true;
            }

            if (!firstNameInput.equals("") && !firstNameInput.equals(user.getFirstName())) {
                user.setFirstName(firstNameInput);
                change = true;
            }

            if (!secondNameInput.equals("") && !secondNameInput.equals(user.getSecondName())) {
                user.setSecondName(secondNameInput);
                change = true;
            }

            if (change) {
                userJpaDAO.save(user);
                req.setAttribute("showChangeInfo", "<h2 style='color:red'>Your details were changed</h2>");
                req.getSession().setAttribute("user", user);
            }
            req.getRequestDispatcher("/sites/users/account.jsp").forward(req, resp);
        }
    }
}