package com.faceart.faceart.servlets.api.favourite;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.faceart.faceart.dao.favourite.FavouriteJpaDAO;
import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Favourite;
import com.faceart.faceart.entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FavouriteServlet", value = "/api/favourite")
public class ApiFavouriteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("favourite") == null ||
                request.getSession().getAttribute("favourite") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        Favourite favourite = (Favourite) request.getSession().getAttribute("favourite");
        String productId = request.getParameter("productId");
        String action = request.getParameter("action");
        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        FavouriteJpaDAO favouriteJpaDAO = new FavouriteJpaDAO();
        Product product = productJpaDAO.getProductById(Long.parseLong(productId));

        if (action.equals("add-product")) {
            favourite.addProduct(product);
            favouriteJpaDAO.save(favourite);
            request.getSession().setAttribute("favourite", favourite);
        }

        if (action.equals("remove-product")) {
            favourite.removeProduct(product.getId());
            favouriteJpaDAO.save(favourite);
            request.getSession().setAttribute("favourite", favourite);
        }

        List<Integer> countProducts = new ArrayList<>();
        countProducts.add(favourite.getProducts().size());

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        out.print(countProducts);
        out.flush();
    }

}