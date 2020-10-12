package alpos.dao.imp;

import alpos.dao.RatingDAO;
import alpos.entity.Rating;
import org.springframework.stereotype.Repository;


@Repository
public class RatingDAOImp extends GenericDAOImp<Rating, Integer> implements RatingDAO {
    public RatingDAOImp() {
        super(Rating.class);
    }
}
