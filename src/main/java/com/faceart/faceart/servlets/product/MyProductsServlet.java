package com.faceart.faceart.servlets.product;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyProductsServlet", value = "/my-products")
public class MyProductsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        List myProducts = productJpaDAO.getProductsByUser(user);

        request.setAttribute("myProducts", myProducts);
        request.getRequestDispatcher("/sites/product/my-products.jsp").forward(request, response);
    }
}