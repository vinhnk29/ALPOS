package com.spring.sample.dao.imp;


import com.spring.sample.dao.ReviewDAO;
import com.spring.sample.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOImp extends GenericDAOImp<Review, Integer> implements ReviewDAO {
	public ReviewDAOImp() {
		super(Review.class);
	}
}
