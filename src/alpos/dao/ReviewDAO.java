package alpos.dao;

import alpos.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewDAO extends GenericDAO<Review, Integer> {
	public Long countReview(Integer userId);
	public Page<Review> paginate(Review review, Pageable pageable);
}
