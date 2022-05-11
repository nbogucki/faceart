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

@WebServlet(name = "RemoveProductImageServlet", value = "/remove-product-image")
public class RemoveProductImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String productId = request.getParameter("id");
        String image = request.getParameter("image");

        User user = (User) request.getSession().getAttribute("user");
        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        Product product = productJpaDAO.getProductById(Long.parseLong(productId));

        if (product.getUser().getId() == user.getId() || user.hasRole("ROLE_ADMIN")) {
            product.getImages().remove(image);
            productJpaDAO.save(product);
        }

        response.sendRedirect("edit-product?id="+productId);
    }
}