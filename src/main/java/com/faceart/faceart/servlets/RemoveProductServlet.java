package com.faceart.faceart.servlets;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveProductServlet", value = "/remove-product")
public class RemoveProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String productId = request.getParameter("id");

        User user = (User) request.getSession().getAttribute("user");
        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        Product product = productJpaDAO.getProductById(Long.parseLong(productId));

        if (product.getUser().getId() == user.getId() || user.hasRole("ROLE_ADMIN")) {
            productJpaDAO.remove(product);
        }

        response.sendRedirect("my-products");
    }
}