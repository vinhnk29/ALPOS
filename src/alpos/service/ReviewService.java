package alpos.service;

import java.util.List;

import org.springframework.data.domain.Page;

import alpos.model.BlackListModel;

public interface ReviewService {
	
	public void blackList(Integer reviewId, Integer userId) throws Exception;
	
	public List<BlackListModel> findBlackListedReviewByUserId(Integer userId);
	
	public Page<BlackListModel> paginate(BlackListModel blackListModel);
}
