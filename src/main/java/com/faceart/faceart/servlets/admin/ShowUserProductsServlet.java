package com.faceart.faceart.servlets.admin;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.dao.user.UserJpaDAO;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowUserProductsServlet", value = "/show-user-products")
public class ShowUserProductsServlet extends HttpServlet {
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

        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        UserJpaDAO userJpaDAO = new UserJpaDAO();
        User userToShowProducts = userJpaDAO.getUserById(Long.parseLong(request.getParameter("userToShowProductsId")));
        List userProducts = productJpaDAO.getProductsByUser(userToShowProducts);

        request.setAttribute("userProducts", userProducts);
        request.getRequestDispatcher("/sites/admin/show-user-products.jsp").forward(request, response);
    }
}