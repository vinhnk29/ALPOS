package alpos.dao.imp;


import org.springframework.stereotype.Repository;

import alpos.dao.CommentDAO;
import alpos.entity.Comment;


@Repository
public class CommentDAOImp extends GenericDAOImp<Comment, Integer> implements CommentDAO {
    public CommentDAOImp() {
        super(Comment.class);
    }
}