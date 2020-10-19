package alpos.dao;

import alpos.entity.User;

public interface UserDAO extends GenericDAO<User, Integer> {
	
	public User findUserByEmail(String email) ;
	
	public User findUser(User user);
}
