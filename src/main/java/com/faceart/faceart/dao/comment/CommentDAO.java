package com.faceart.faceart.dao.comment;

import com.faceart.faceart.entities.Comment;

import java.util.List;

public interface CommentDAO {

    Comment getCommentById(long id);

    List getAll();

    void save(Comment comment);

    void remove(Comment comment);
}
