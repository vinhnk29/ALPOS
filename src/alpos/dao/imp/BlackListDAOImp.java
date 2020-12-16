package alpos.dao.imp;

import alpos.dao.BlackListDAO;
import alpos.entity.BlackList;
import alpos.entity.Review;
import alpos.util.CommonUtil;
import alpos.util.SearchQueryTemplate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository
public class BlackListDAOImp extends GenericDAOImp<BlackList, Integer> implements BlackListDAO {
	protected List<BlackList> BlackList;

	public BlackListDAOImp() {
		super(BlackList.class);
	}

	public Review findBlackListedReview(Review review) {

		try {
			List<Review> reviewList = (List<Review>) getHibernateTemplate().findByExample(review);
			if (!CommonUtil.isEmpty(reviewList)) {
				return reviewList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<BlackList> findBlackListedReviewByUserId(Integer userId) {
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<BlackList>>() {
				public List<BlackList> doInHibernate(Session session) throws HibernateException {
					Query<BlackList> query = session.createQuery("FROM BlackList WHERE userId = :userId", BlackList.class);
					query.setParameter("userId", userId);
					return query.getResultList();
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Page<BlackList> paginate(BlackList blackList, Pageable pageable){
		String sql = "FROM BlackList WHERE user_id = :user_id";
		String countSql = "SELECT COUNT(*) FROM BlackList WHERE user_id = :user_id";
		SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate(sql,countSql, pageable);
		searchQueryTemplate.addParameter("user_id", blackList.getUserId());
		return paginate(searchQueryTemplate);
	}
		
}
