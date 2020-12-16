package alpos.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import alpos.dao.RelationshipDAO;
import alpos.entity.Relationship;
import alpos.entity.User;

@Repository
public class RelationshipDAOImp extends GenericDAOImp<Relationship, Integer> implements RelationshipDAO {
	private HibernateTemplate hibernateTemplate;
	private static Logger log = LoggerFactory.getLogger(RelationshipDAOImp.class);

	public RelationshipDAOImp() {
		super(Relationship.class);
	}

	public Long countFollowers(Integer followedId) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Relationship WHERE followedId = :followedId",
						Long.class);
				query.setParameter("followedId", followedId);
				return query.uniqueResult();
			}
		});
	}
	
	public Long countFollowings(Integer followerId) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Relationship WHERE followerId = :followerId",
						Long.class);
				query.setParameter("followerId", followerId);
				return query.uniqueResult();
			}
		});
	}
	
	public List<User> followings(Integer userId){
		return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
			public List<User> doInHibernate(Session session) throws HibernateException {
				Query<User> query = session.createQuery("SELECT b FROM Relationship a JOIN User b ON a.followerId = b.id WHERE a.followerId = :followerId",
						User.class);
				query.setParameter("followerId", userId);
				return query.getResultList();
			}
		});
	}
}
