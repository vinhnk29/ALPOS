package alpos.dao.imp;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import alpos.dao.CommentDAO;
import alpos.entity.Comment;


@Repository
public class CommentDAOImp extends GenericDAOImp<Comment, Integer> implements CommentDAO {
    public CommentDAOImp() {
		super(Comment.class);
	}
    
	public List<Comment> findCommentByReviewId(Integer reviewId) {
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Comment>>() {
				public List<Comment> doInHibernate(Session session) throws HibernateException {
					Query<Comment> query = session.createQuery("FROM Comment WHERE reviewId = :reviewId", Comment.class);
					query.setParameter("reviewId", reviewId);
					return query.getResultList();
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
    
}