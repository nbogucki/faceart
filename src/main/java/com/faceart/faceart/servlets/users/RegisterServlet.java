package com.faceart.faceart.servlets.users;

import java.io.IOException;
import com.faceart.faceart.dao.cart.CartJpaDAO;
import com.faceart.faceart.entities.Cart;
import com.faceart.faceart.dao.user.UserJpaDAO;
import com.faceart.faceart.entities.User;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") != null &&
                request.getSession().getAttribute("user") != "") {
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getRequestDispatcher("/sites/users/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String registerButton = req.getParameter("registerButton");
        String emailInputValue = req.getParameter("emailInput");
        String passwordInputValue = req.getParameter("passwordInput");
        String addressInput = req.getParameter("addressInput");
        String firstNameInput = req.getParameter("firstNameInput");
        String secondNameInput = req.getParameter("secondNameInput");

        req.setAttribute("userExistInfo", "");

        if (registerButton.equals("true")) {
            UserJpaDAO userJpaDAO = new UserJpaDAO();
            try {
                User user = userJpaDAO.getUserByEmail(emailInputValue);
                req.setAttribute("userExistInfo", "<h2 style='color:red'>User with this email actually exist</h2>");
            } catch (NoResultException e) {
                long leftLimit = 1L;
                long rightLimit = 10000L;
                long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
                boolean newId = false;
                while (!newId) {
                    if(userJpaDAO.getUserById(generatedLong) == null) {
                        newId = true;
                    } else {
                        generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
                    }
                }

                User user = new User(
                        generatedLong,
                        firstNameInput,
                        secondNameInput,
                        emailInputValue,
                        passwordInputValue,
                        addressInput,
                        true
                );
                user.addRole("ROLE_USER");

                Cart cart = new Cart(generatedLong);
                CartJpaDAO cartJpaDAO = new CartJpaDAO();
                cartJpaDAO.save(cart);
                user.setCart(cart);

                userJpaDAO.save(user);

                cart.setUser(user);
                cartJpaDAO.save(cart);

                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath());
                return;
            }
            req.getRequestDispatcher("/sites/users/register.jsp").forward(req, resp);
        }
    }
}