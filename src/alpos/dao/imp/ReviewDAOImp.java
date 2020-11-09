package alpos.dao.imp;

import alpos.dao.ReviewDAO;
import alpos.entity.Review;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOImp extends GenericDAOImp<Review, Integer> implements ReviewDAO {
	public ReviewDAOImp() {
		super(Review.class);
	}

	public Long countReview(Integer userId) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Review WHERE userId = :userId",
						Long.class);
				query.setParameter("userId", userId);
				return query.uniqueResult();
			}
		});
	}
}
