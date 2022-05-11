package com.faceart.faceart.servlets;

import java.io.IOException;
import java.util.List;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexServlet", value = "/heartart")
public class IndexServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        List indexProducts = productJpaDAO.getProductsForIndex();

        request.setAttribute("indexProducts", indexProducts);
        request.getRequestDispatcher("/sites/heartart.jsp").forward(request, response);
    }
}