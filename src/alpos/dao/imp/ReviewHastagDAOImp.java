package alpos.dao.imp;

import alpos.dao.ReviewHastagDAO;
import alpos.entity.ReviewHastag;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewHastagDAOImp extends GenericDAOImp<ReviewHastag, Integer> implements ReviewHastagDAO {
    public ReviewHastagDAOImp() { super(ReviewHastag.class); }

}
