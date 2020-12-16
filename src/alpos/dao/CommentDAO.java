package alpos.dao;

import java.util.List;

import alpos.entity.Comment;

public interface CommentDAO extends GenericDAO<Comment, Integer>{
	public List<Comment> findCommentByReviewId(Integer reviewId);
}
