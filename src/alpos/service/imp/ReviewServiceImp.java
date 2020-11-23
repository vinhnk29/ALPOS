package alpos.service.imp;

import alpos.dao.BlackListDAO;
import alpos.entity.BlackList;
import alpos.model.BlackListModel;
import alpos.model.UserModel;
import alpos.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
public class ReviewServiceImp implements ReviewService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private BlackListDAO blackListDAO;

	public void setBlackListDao(BlackListDAO blackListDAO) {
		this.blackListDAO = blackListDAO;
	}

	@Transactional
	public void blackList(Integer reviewId, Integer userId) throws Exception {
		BlackList blackList = new BlackList();
		blackList.setReviewId(reviewId);
		blackList.setUserId(userId);
		blackListDAO.makePersistent(blackList);
	}

	public List<BlackListModel> findBlackListedReviewByUserId(Integer userId) {
		List<BlackListModel> blackListModelList = new ArrayList<BlackListModel>();
		try {
			List<BlackList> blackListList = blackListDAO.findBlackListedReviewByUserId(userId);
			for (BlackList blackList : blackListList) {
				BlackListModel blackListModel = new BlackListModel();
				BeanUtils.copyProperties(blackList, blackListModel);
				blackListModelList.add(blackListModel);
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return blackListModelList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<BlackListModel> paginate(BlackListModel blackListModel) {
		try {
			BlackList condition = new BlackList();
			condition.setUserId(blackListModel.getUser_id());
			Page<BlackList> blackLists = blackListDAO.paginate(condition, blackListModel.getPageable());
			return blackLists.map(blackList -> {
				BlackListModel model = new BlackListModel();
				BeanUtils.copyProperties(blackList, model);
				UserModel user = new UserModel();
				BeanUtils.copyProperties(blackList.getUser(), user);
				model.setUser(user);
				return model;

			});
			
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}

}
