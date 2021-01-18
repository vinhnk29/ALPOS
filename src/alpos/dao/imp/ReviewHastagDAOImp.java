package alpos.dao.imp;

import alpos.dao.ReviewHastagDAO;
import alpos.entity.ReviewHastag;
import alpos.model.ReviewHastagModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewHastagDAOImp extends GenericDAOImp<ReviewHastag, Integer> implements ReviewHastagDAO {
    public ReviewHastagDAOImp() { super(ReviewHastag.class); }
}
