package com.spring.sample.dao.imp;



import com.spring.sample.dao.RatingDAO;
import com.spring.sample.entity.Rating;
import org.springframework.stereotype.Repository;

@Repository
public class RatingDAOImp extends GenericDAOImp<Rating, Integer> implements RatingDAO {
	public RatingDAOImp() {
		super(Rating.class);
	}
}
