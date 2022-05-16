package com.faceart.faceart.servlets.api.cart;

import com.faceart.faceart.dao.cart.CartJpaDAO;
import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ApiCartServlet", value = "/api/cart")
public class ApiCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        String productId = request.getParameter("productId");
        String action = request.getParameter("action");
        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        CartJpaDAO cartJpaDAO = new CartJpaDAO();
        Product product = productJpaDAO.getProductById(Long.parseLong(productId));

        if (action.equals("add-product")) {
            user.getCart().addProduct(product);
            cartJpaDAO.save(user.getCart());
            request.getSession().setAttribute("user", user);
        }

        if (action.equals("remove-product")) {
            user.getCart().removeProduct(product.getId());
            cartJpaDAO.save(user.getCart());
            request.getSession().setAttribute("user", user);
        }

        List<Integer> countProducts = new ArrayList<>();
        countProducts.add(user.getCart().getProducts().size());

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        out.print(countProducts);
        out.flush();
    }

}