package alpos.dao.imp;

import alpos.dao.UserDAO;
import alpos.entity.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImp extends GenericDAOImp<User, Integer> implements UserDAO {
    public UserDAOImp() {
        super(User.class);
    }
    
    public User findUserByEmail(String email) {
    	
    	try {
    		return getHibernateTemplate().execute(new HibernateCallback<User>() {
    			public User doInHibernate(Session session) throws HibernateException {
    				Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
    				query.setParameter("email", email);
    				return query.uniqueResult();
    			}
    		});
    	} catch (Exception e) {
    		
    		return null;
    	}
    }

}


