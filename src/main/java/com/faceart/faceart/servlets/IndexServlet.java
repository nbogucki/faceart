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

@WebServlet(name = "IndexServlet", value = "/faceart")
public class IndexServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("favourite") == null ||
                request.getSession().getAttribute("favourite") == "") {
            FavouriteJpaDAO favouriteJpaDAO = new FavouriteJpaDAO();
            Favourite favourite = favouriteJpaDAO.persist(new Favourite());
            favouriteJpaDAO.save(favourite);
            request.getSession().setAttribute("favourite", favourite);
        }

        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        List indexProducts = productJpaDAO.getProductsForIndex();

        request.setAttribute("indexProducts", indexProducts);
        request.getRequestDispatcher("/sites/faceart.jsp").forward(request, response);
    }
}