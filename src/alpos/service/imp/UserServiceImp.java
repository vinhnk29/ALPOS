package alpos.service.imp;

import alpos.dao.RelationshipDAO;
import alpos.dao.ReviewDAO;
import alpos.dao.UserDAO;
import alpos.entity.Review;
import alpos.entity.User;
import alpos.model.BookModel;
import alpos.model.UserModel;
import alpos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserServiceImp() {
	}

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	private RelationshipDAO relationshipDAO;

	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setReviewDao(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}
	
	public void setRelationshipDao(RelationshipDAO relationshipDAO) {
		this.relationshipDAO = relationshipDAO;
	}

	@Transactional
	public UserModel addUser(UserModel userModel) throws Exception {
		log.info("Adding the user in the database");
		try {
			User condition = new User();
			condition.setId(userModel.getId());
			condition.setName(userModel.getName());
			condition.setEmail(userModel.getEmail());
			condition.setPassword(passwordEncoder.encode(userModel.getPassword()));
			User user = userDAO.makePersistent(condition);
			userModel = new UserModel();
			BeanUtils.copyProperties(user, userModel);
			return userModel;
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}

	public UserModel findUserByEmail(String email) {

		try {
			User user = userDAO.findUserByEmail(email);
			UserModel userModel = null;
			if (user != null) {
				userModel = new UserModel();
				BeanUtils.copyProperties(user, userModel);
			}
			return userModel;
		} catch (Exception e) {

			return null;
		}
	}

	public UserModel findUser(Integer id) {
		log.info("Checking the user in the database");
		try {
			User user = userDAO.find(id);
			UserModel userModel = null;
			if (user != null) {
				userModel = new UserModel();
				BeanUtils.copyProperties(user, userModel);
			}

			Long reviewNumbers = reviewDAO.countReview(id);
			userModel.setReviewNumbers(reviewNumbers);
			
			Long followers = relationshipDAO.countFollowers(id);
			userModel.setFollowers(followers);
			
			Long followings = relationshipDAO.countFollowings(id);
			userModel.setFollowings(followings);
			
			return userModel;
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
			return null;
		}
	}

	@Transactional
	public UserModel editUser(UserModel userModel) throws Exception {
		log.info("Updating the user in the database");
		try {
			User user = userDAO.find(userModel.getId(), true);
			user.setName(userModel.getName());
			user.setDateOfBirth(userModel.getDateOfBirth());
			user.setEmail(userModel.getEmail());
			user.setImage(userModel.getImage());
			userDAO.makePersistent(user);
			return userModel;
		} catch (Exception e) {
			log.error("An error occurred while updating the user details to the database", e);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<UserModel> findAll() {
		log.info("Fetching all users in the database");
		List<UserModel> userModelList = new ArrayList<UserModel>();
		try {
			List<User> userList = userDAO.findAll();
			for (User user : userList) {
				UserModel userModel = new UserModel();
				BeanUtils.copyProperties(user, userModel);
				userModelList.add(userModel);
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return userModelList;
	}

}
