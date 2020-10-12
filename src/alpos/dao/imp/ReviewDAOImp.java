package alpos.dao.imp;

import alpos.dao.ReviewDAO;
import alpos.entity.Review;
import org.springframework.stereotype.Repository;


@Repository
public class ReviewDAOImp extends GenericDAOImp<Review, Integer> implements ReviewDAO {
    public ReviewDAOImp() {
        super(Review.class);
    }
}
