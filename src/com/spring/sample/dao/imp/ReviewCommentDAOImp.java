package com.spring.sample.dao.imp;


import com.spring.sample.dao.ReviewCommentDAO;
import com.spring.sample.entity.ReviewComment;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewCommentDAOImp extends GenericDAOImp<ReviewComment, Integer> implements ReviewCommentDAO {
	public ReviewCommentDAOImp() {
		super(ReviewComment.class);
	}
}
