package alpos.dao.imp;

import alpos.dao.UserDAO;
import alpos.entity.User;
import alpos.service.imp.UserServiceImp;
import alpos.util.CommonUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImp extends GenericDAOImp<User, Integer> implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);
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
    
	public User findUser(User user) {
		log.info("Finding the user in the database");
		try {
			List<User> userList = (List<User>) getHibernateTemplate().findByExample(user);
			if (!CommonUtil.isEmpty(userList)) {
				return userList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
			return null;
		}
	}

}


