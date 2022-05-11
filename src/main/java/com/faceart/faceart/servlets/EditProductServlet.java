package com.faceart.faceart.servlets;

import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "EditProductServlet", value = "/edit-product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class EditProductServlet extends HttpServlet {
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

        if (product.getUser().getId() != user.getId() && !user.hasRole("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/sites/product/edit-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }
        String productId = request.getParameter("id");

        User user = (User) request.getSession().getAttribute("user");
        ProductJpaDAO productJpaDAO = new ProductJpaDAO();
        Product product = productJpaDAO.getProductById(Long.parseLong(productId));

        if (product.getUser().getId() != user.getId() && !user.hasRole("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String uploadPath = getServletContext().getRealPath("/")+"images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        String addProductButton = request.getParameter("editProductButton");
        String nameInputValue = request.getParameter("nameInput");
        String priceInputValue = request.getParameter("priceInput");
        String titleInputValue = request.getParameter("titleInput");
        String descriptionInputValue = request.getParameter("descriptionInput");

        request.setAttribute("productInfo", "");

        if (addProductButton.equals("true")) {
            if (!nameInputValue.equals(product.getName())) {
                product.setName(nameInputValue);
            }

            if (Float.parseFloat(priceInputValue) != product.getPrice()) {
                product.setPrice(Float.parseFloat(priceInputValue));
            }

            if (!titleInputValue.equals(product.getTitle())) {
                product.setTitle(titleInputValue);
            }

            if (!descriptionInputValue.equals(product.getDescription())) {
                product.setDescription(descriptionInputValue);
            }

            List<Part> fileParts = request.getParts().stream().filter(part -> "imagesInput".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());

            for (Part filePart : fileParts) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                String newFileName = System.currentTimeMillis() + "_" + fileName;
                File save = new File(uploadPath, newFileName);
                final String absolutePath = save.getAbsolutePath();
                filePart.write(absolutePath);
                product.addImage("images/"+newFileName);
            }

            productJpaDAO.save(product);

            request.setAttribute("product", product);

            request.getRequestDispatcher("/sites/product/edit-product.jsp").forward(request, response);
        }
    }
}