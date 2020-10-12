package alpos.dao.imp;

import alpos.dao.ReviewCommentDAO;
import alpos.entity.ReviewComment;
import org.springframework.stereotype.Repository;


@Repository
public class ReviewCommentDAOImp extends GenericDAOImp<ReviewComment, Integer> implements ReviewCommentDAO {
    public ReviewCommentDAOImp() {
        super(ReviewComment.class);
    }
}
