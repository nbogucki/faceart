package com.faceart.faceart.servlets;

import java.io.IOException;
import java.util.List;

import com.faceart.faceart.dao.favourite.FavouriteJpaDAO;
import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Favourite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("favourite") == null ||
                request.getSession().getAttribute("favourite") == "") {
            FavouriteJpaDAO favouriteJpaDAO = new FavouriteJpaDAO();
            Favourite favourite = favouriteJpaDAO.persist(new Favourite());
            favouriteJpaDAO.save(favourite);
            request.getSession().setAttribute("favourite", favourite);
        }

        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        List products = productJpaDAO.getAll();

        request.setAttribute("products", products);
        request.getRequestDispatcher("/sites/shop.jsp").forward(request, response);
    }
}