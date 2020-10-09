package com.spring.sample.dao.imp;


import com.spring.sample.dao.UserDAO;
import com.spring.sample.entity.User;
import com.spring.sample.service.imp.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImp extends GenericDAOImp<User, Integer> implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);
	public UserDAOImp() {
		super(User.class);
	}
}
