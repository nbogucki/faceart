package com.faceart.faceart.servlets.users;

import com.faceart.faceart.dao.comment.CommentJpaDAO;
import com.faceart.faceart.dao.opinion.OpinionJpaDAO;
import com.faceart.faceart.dao.user.UserJpaDAO;
import com.faceart.faceart.entities.Comment;
import com.faceart.faceart.entities.Opinion;
import com.faceart.faceart.entities.User;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null ||
                request.getSession().getAttribute("user") == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String userId = request.getParameter("id");

        if (userId == null || userId.equals("")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        UserJpaDAO userJpaDAO = new UserJpaDAO();
        try {
            User user = userJpaDAO.getUserById(Long.parseLong(userId));
            request.setAttribute("userToShowOpinions", user);
            ArrayList<Opinion> opinions = user.getOpinions();

            int values = 0;

            if (opinions.size() == 0) {
                request.setAttribute("opinionsAverage", values);
            } else {
                for (Opinion opinion : opinions) {
                    values += opinion.getValue();
                }

                if (values == 0) {
                    request.setAttribute("opinionsAverage", values);
                }

                double optionsAverage = values/opinions.size();
                request.setAttribute("opinionsAverage", optionsAverage);
            }
        } catch (NoResultException e) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        request.getRequestDispatcher("/sites/users/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opinionButtonValue = request.getParameter("opinionButton");
        String opinionInputValue = request.getParameter("opinionInput");
        String commentInputValue = request.getParameter("commentInput");
        String userToShowOpinionsId =  request.getParameter("userToShowOpinionsId");
        User user = (User) request.getSession().getAttribute("user");
        if (opinionButtonValue.equals("true")) {
            UserJpaDAO userJpaDAO = new UserJpaDAO();
            OpinionJpaDAO opinionJpaDAO = new OpinionJpaDAO();
            CommentJpaDAO commentJpaDAO = new CommentJpaDAO();
            User userToShowOpinions = (User) userJpaDAO.getUserById(Long.parseLong(userToShowOpinionsId));
            long leftLimit = 1L;
            long rightLimit = 10000L;
            long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            boolean newId = false;
            while (!newId) {
                if(opinionJpaDAO.getOpinionById(generatedLong) == null) {
                    newId = true;
                } else {
                    generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
                }
            }

            if (!opinionInputValue.equals("")) {
                Opinion opinion = new Opinion(generatedLong);
                opinion.setValue(Integer.parseInt(opinionInputValue));
                opinion.setAuthor(user.getId());
                opinion.setUser(userToShowOpinions);
                opinion.setCreatedAt(java.time.LocalDateTime.now());
                opinionJpaDAO.save(opinion);
                userToShowOpinions.addOpinion(opinion);
                userJpaDAO.save(userToShowOpinions);
            }

            if (!commentInputValue.equals("")) {
                Comment comment = new Comment(generatedLong);
                comment.setValue(commentInputValue);
                comment.setAuthor(user.getId());
                comment.setUser(userToShowOpinions);
                comment.setCreatedAt(java.time.LocalDateTime.now());
                commentJpaDAO.save(comment);
                userToShowOpinions.addComment(comment);
                userJpaDAO.save(userToShowOpinions);
            }
        }
        response.sendRedirect(request.getContextPath()+"/user?id="+userToShowOpinionsId);
    }
}