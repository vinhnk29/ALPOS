package alpos.service.imp;

import alpos.dao.*;
import alpos.entity.Book;
import alpos.entity.Hastag;
import alpos.entity.Review;
import alpos.entity.ReviewHastag;
import alpos.model.*;
import alpos.service.BookService;
import alpos.service.ReviewHastagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewHastagServiceImp implements ReviewHastagService {

    private static Logger log = LoggerFactory.getLogger(ReviewHastagServiceImp.class);

    @Autowired
    private ReviewHastagDAO reviewHastagDAO;

    @Autowired
    private HastagDAO hastagDAO;

	public ReviewHastagDAO getReviewHastagDAO() {
		return reviewHastagDAO;
	}

    public void setReviewHastagDAO(ReviewHastagDAO reviewHastagDAO) {
        this.reviewHastagDAO = reviewHastagDAO;
    }


    private ReviewHastagServiceImp() {
    }

    @Transactional
    public ReviewHastagModel addReviewHastag(ReviewHastagModel reviewHastagModel) throws Exception {
        try {
            ReviewHastag condition = new ReviewHastag();
            condition.setId(reviewHastagModel.getId());
            condition.setReviewId(reviewHastagModel.getReviewId());
            condition.setHastagId(reviewHastagModel.getHastagId());
            ReviewHastag reviewHastag = reviewHastagDAO.makePersistent(condition);
            reviewHastagModel = new ReviewHastagModel();
            BeanUtils.copyProperties(reviewHastag, reviewHastagModel);
            return reviewHastagModel;
        } catch (Exception e) {
            log.error("An error occurred while adding the review details to the database", e);
            throw e;
        }
    }
}
