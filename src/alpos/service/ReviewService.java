package alpos.service;

import java.util.List;

import org.springframework.data.domain.Page;

import alpos.model.BlackListModel;

import alpos.model.ReviewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {

	public void blackList(Integer reviewId, Integer userId) throws Exception;

	public List<BlackListModel> findBlackListedReviewByUserId(Integer userId);

	public Page<BlackListModel> paginate(BlackListModel blackListModel);

	public List<ReviewModel> findAll();

	public Page<ReviewModel> paginate(ReviewModel reviewModel);

	public ReviewModel findReviewById(Integer reviewId) throws Exception;
	public ReviewModel findReview(Integer id);
	public ReviewModel addReview(ReviewModel reviewModel) throws Exception;
	public ReviewModel editReview(ReviewModel reviewModel) throws Exception;
}
