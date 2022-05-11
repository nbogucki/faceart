package com.faceart.faceart.servlets;

import java.io.IOException;
import java.util.List;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        List products = productJpaDAO.getAll();

        request.setAttribute("products", products);
        request.getRequestDispatcher("/sites/shop.jsp").forward(request, response);
    }
}