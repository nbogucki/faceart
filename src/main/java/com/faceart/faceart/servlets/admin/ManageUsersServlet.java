package com.faceart.faceart.servlets.admin;

import com.faceart.faceart.dao.user.UserJpaDAO;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageUsersServlet", value = "/manage-users")
public class ManageUsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        User user = (User) request.getSession().getAttribute("user");

        if (!user.hasRole("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        UserJpaDAO userJpaDAO = new UserJpaDAO();
        List users = userJpaDAO.getAll();

        request.setAttribute("users", users);
        request.getRequestDispatcher("/sites/admin/manage-users.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        User user = (User) request.getSession().getAttribute("user");

        if (!user.hasRole("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String showProductsButton = request.getParameter("showProducts");
        String activateButton = request.getParameter("activate");
        String removeButton = request.getParameter("remove");
        UserJpaDAO userJpaDAO = new UserJpaDAO();

        if (showProductsButton != null) {
            User userToShowProducts = userJpaDAO.getUserById(Long.parseLong(showProductsButton));
            response.sendRedirect("show-user-products?userToShowProductsId="+userToShowProducts.getId());
        }

        if (activateButton != null) {
            User userToUpdate = userJpaDAO.getUserById(Long.parseLong(activateButton));
            userToUpdate.setActive(!userToUpdate.isActive());
            userJpaDAO.save(userToUpdate);
            response.sendRedirect("manage-users");
        }

        if (removeButton != null) {
            User userToUpdate = userJpaDAO.getUserById(Long.parseLong(removeButton));
            userJpaDAO.remove(userToUpdate);
            response.sendRedirect("manage-users");
        }
    }
}