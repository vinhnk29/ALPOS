package alpos.service.imp;

import alpos.dao.BlackListDAO;
import alpos.dao.ReviewHastagDAO;
import alpos.entity.BlackList;
import alpos.entity.ReviewHastag;
import alpos.model.*;
import alpos.service.ReviewService;
import java.util.ArrayList;
import java.util.List;
import alpos.dao.ReviewDAO;
import alpos.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ReviewServiceImp implements ReviewService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private BlackListDAO blackListDAO;

	public void setBlackListDao(BlackListDAO blackListDAO) {
		this.blackListDAO = blackListDAO;
	}

	@Autowired
	private ReviewDAO reviewDAO;


	private ReviewServiceImp() {
	}

    @Autowired
    private ReviewHastagDAO reviewHastagDAO;



	public void setReviewDao(ReviewDAO reviewDao) {
		this.reviewDAO = reviewDao;
	}

    public void setReviewHastagDAO(ReviewHastagDAO reviewHastagDAO) {
        this.reviewHastagDAO = reviewHastagDAO;
    }

    @Transactional
	public void blackList(Integer reviewId, Integer userId) throws Exception {
		BlackList blackList = new BlackList();
		blackList.setReviewId(reviewId);
		blackList.setUserId(userId);
		blackListDAO.makePersistent(blackList);
	}

	public List<BlackListModel> findBlackListedReviewByUserId(Integer userId) {
		List<BlackListModel> blackListModelList = new ArrayList<BlackListModel>();
		try {
			List<BlackList> blackListList = blackListDAO.findBlackListedReviewByUserId(userId);
			for (BlackList blackList : blackListList) {
				BlackListModel blackListModel = new BlackListModel();
				BeanUtils.copyProperties(blackList, blackListModel);
				blackListModelList.add(blackListModel);
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return blackListModelList;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BlackListModel> paginate(BlackListModel blackListModel) {
		try {
			BlackList condition = new BlackList();
			condition.setUserId(blackListModel.getUser_id());
			Page<BlackList> blackLists = blackListDAO.paginate(condition, blackListModel.getPageable());
			return blackLists.map(blackList -> {
				BlackListModel model = new BlackListModel();
				BeanUtils.copyProperties(blackList, model);
				UserModel user = new UserModel();
				BeanUtils.copyProperties(blackList.getUser(), user);
				model.setUser(user);
				return model;

			});

		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}

    public List<ReviewModel> findAll() {
        log.info("Fetching all reviews in the database");
        List<ReviewModel> reviewModelList = new ArrayList<ReviewModel>();
        try {
            List<Review> reviewList = reviewDAO.findAll();
            for (Review review : reviewList) {
                ReviewModel reviewModel = new ReviewModel();
                BeanUtils.copyProperties(review, reviewModel);
                reviewModelList.add(reviewModel);
                ReviewHastagModel reviewHastagModel = new ReviewHastagModel();
                BeanUtils.copyProperties(review, reviewModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all reviews from the database", e);
        }
        return reviewModelList;
    }

	@Override
	@Transactional(readOnly = true)
	public Page<ReviewModel> paginate(ReviewModel reviewModel) {
		log.info("Fetching the reviews in the database");
		try {
			Review condition = new Review();
			condition.setUserId(reviewModel.getUserId());
			Page<Review> reviews = reviewDAO.paginate(condition, reviewModel.getPageable());
			return reviews.map(review -> {
				ReviewModel model = new ReviewModel();
				BeanUtils.copyProperties(review, model);
				UserModel user = new UserModel();
				BeanUtils.copyProperties(review.getUser(), user);
				model.setUser(user);
				BookModel book = new BookModel();
				AuthorModel author = new AuthorModel();
				BeanUtils.copyProperties(review.getBook(), book);
				BeanUtils.copyProperties(review.getBook().getAuthor(), author);
				book.setAuthor(author);
				model.setBook(book);
				return model;
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	@Transactional(readOnly = true)
	public ReviewModel findReviewById(Integer reviewId) throws Exception {
		log.info("Find the review in the database");
		try {
			Review review = reviewDAO.find(reviewId);
			ReviewModel reviewModel = new ReviewModel();
			BeanUtils.copyProperties(review, reviewModel);

			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(review.getUser(), userModel);
			reviewModel.setUser(userModel);
			
			BookModel bookModel = new BookModel();
			BeanUtils.copyProperties(review.getBook(), bookModel);
			reviewModel.setBook(bookModel);
			

			return reviewModel;
		} catch (Exception e) {
			log.error("An error occurred while finding the review details in the database", e);
			throw e;
		}
	}

    @Transactional
    public ReviewModel addReview(ReviewModel reviewModel) throws Exception {
        log.info("Adding the review in the database");
        try {
            Review condition = new Review();
            condition.setUserId(reviewModel.getUserId());
            condition.setBookId(reviewModel.getBookId());
            condition.setContent(reviewModel.getContent());
            Review review = reviewDAO.makePersistent(condition);

            for (Integer hashtagId: reviewModel.getHastagId()) {
                ReviewHastag reviewHastag = new ReviewHastag();
                reviewHastag.setReviewId(review.getId());
                reviewHastag.setHastagId(hashtagId);
                reviewHastagDAO.makePersistent(reviewHastag);
            }
            reviewModel = new ReviewModel();
            return reviewModel;
        } catch (Exception e) {
            log.error("An error occurred while adding the review details to the database", e);
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public ReviewModel findReview(Integer id) {
        log.info("Checking the review in the database");
        try {
            Review review = reviewDAO.find(id);
            ReviewModel reviewModel = new ReviewModel();
            if (review != null) {
                List<HastagModel> hastagModelList = new ArrayList<HastagModel>();
                BeanUtils.copyProperties(review, reviewModel);
                for(ReviewHastag reviewHastag : review.getReviewHastags()) {
                    if(reviewHastag == null || reviewHastag.getHastag() == null) {
                        continue;
                    }
                    HastagModel hastagModel = new HastagModel();
                    BeanUtils.copyProperties(reviewHastag.getHastag(), hastagModel);
                    hastagModelList.add(hastagModel);
                }
             reviewModel.setHastagModels(hastagModelList);
            }
            return reviewModel;
        } catch (Exception e) {
            log.error("An error occurred while fetching the review details from the database", e);
            return null;
        }
    }

    @Transactional
    public ReviewModel editReview(ReviewModel reviewModel) throws Exception {
        log.info("Updating the review in the database");
        try {
            Review condition = reviewDAO.find(reviewModel.getId(), true);
            condition.setContent(reviewModel.getContent());

            Review review = reviewDAO.makePersistent(condition);
            log.info("------------------------------------------1");

            for (ReviewHastag reviewHastag : review.getReviewHastags()) {
                if(reviewHastag == null) {
                    continue;
                }
                reviewHastagDAO.makeTransient(reviewHastag);
                log.info("------------------------------------------2");
            }

            for (Integer hashtagId: reviewModel.getHastagId()) {
                ReviewHastag reviewHastag = new ReviewHastag();
                reviewHastag.setReviewId(review.getId());
                reviewHastag.setHastagId(hashtagId);
                reviewHastagDAO.makePersistent(reviewHastag);
                log.info("------------------------------------------3");
            }
            reviewModel = new ReviewModel();
            log.info("updated");
            return reviewModel;
        }
        catch (Exception e) {
            log.error("An error occurred while updating the user details to the database", e);
            throw e;
        }
    }

}
