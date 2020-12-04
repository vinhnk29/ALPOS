package alpos.service.imp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import alpos.dao.CommentDAO;
import alpos.dao.ReviewDAO;
import alpos.dao.UserDAO;
import alpos.entity.Comment;
import alpos.model.CommentModel;
import alpos.service.CommentService;

@Component
public class CommentServiceImp implements CommentService {

	private static Logger log = LoggerFactory.getLogger(BookServiceImp.class);

	@Autowired
	private ReviewDAO reviewDao;

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CommentDAO commentDao;

	public ReviewDAO getReviewDao() {
		return reviewDao;
	}

	public void setreviewDao(ReviewDAO reviewDao) {
		this.reviewDao = reviewDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	private CommentServiceImp() {
	}

	@Transactional
	public CommentModel addComment(CommentModel commentModel) throws Exception {
		log.info("Adding comment to the database");
		try {
			Comment condition = new Comment();
			condition.setId(commentModel.getId());
			condition.setReviewId(commentModel.getReviewId());
			condition.setUserId(commentModel.getUserId());
			condition.setContent(commentModel.getContent());
			Comment comment = commentDao.makePersistent(condition);
			commentModel = new CommentModel();
			BeanUtils.copyProperties(comment, commentModel);
			return commentModel;
		} catch (Exception e) {
			log.error("An error occurred while adding the book details to the database", e);
			throw e;
		}
	}

}
