package com.faceart.faceart.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import com.faceart.faceart.dao.product.ProductJpaDAO;
import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "AddProductServlet", value = "/add-product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class AddProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getRequestDispatcher("/sites/product/add-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("/")+"images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        String addProductButton = request.getParameter("addProductButton");
        String nameInputValue = request.getParameter("nameInput");
        String priceInputValue = request.getParameter("priceInput");
        String titleInputValue = request.getParameter("titleInput");
        String descriptionInputValue = request.getParameter("descriptionInput");

        request.setAttribute("productInfo", "");

        if (addProductButton.equals("true")) {
            ProductJpaDAO productJpaDAO = new ProductJpaDAO();
            try {
                Product product = new Product(
                        nameInputValue,
                        Float.parseFloat(priceInputValue),
                        titleInputValue,
                        (User) request.getSession().getAttribute("user"),
                        java.time.LocalDateTime.now()
                );

                if (!descriptionInputValue.equals("")) {
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
            } catch (NoResultException e) {
                request.setAttribute("productInfo", "<h2 style='color:red'>Can't add new product</h2>");
            }
            request.getRequestDispatcher("/sites/product/add-product.jsp").forward(request, response);
        }
    }
}