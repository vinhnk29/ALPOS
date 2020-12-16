package alpos.service;

import java.util.List;

import alpos.model.CommentModel;

public interface CommentService {
	 public CommentModel addComment(CommentModel commentModel) throws Exception;
	 public List<CommentModel> findCommentByreviewId(Integer reviewId) throws Exception;
}
