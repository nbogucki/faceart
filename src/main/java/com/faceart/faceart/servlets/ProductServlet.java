package com.faceart.faceart.servlets;

import java.io.IOException;
import java.util.List;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Product;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String productId = request.getParameter("id");

        if (productId == null || productId.equals("")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        try {
            Product product = productJpaDAO.getProductById(Long.parseLong(productId));
            request.setAttribute("product", product);
        } catch (NoResultException e) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        request.getRequestDispatcher("/sites/product/product.jsp").forward(request, response);
    }
}