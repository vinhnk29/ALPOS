package alpos.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import alpos.entity.BlackList;
import alpos.entity.Review;

public interface BlackListDAO extends GenericDAO<BlackList, Integer> {
	
	public Review findBlackListedReview(Review review);
	
	public List<BlackList> findBlackListedReviewByUserId(Integer userId) ;

	public Page<BlackList> paginate(BlackList blackList, Pageable pageable);
	
}
