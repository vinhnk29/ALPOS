package alpos.service.imp;

import alpos.dao.UserDAO;
import alpos.entity.User;
import alpos.model.UserModel;
import alpos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    private UserServiceImp() {
    }

    @Autowired
    private UserDAO userDAO;

    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public UserModel addUser(UserModel userModel) throws Exception {
        log.info("Adding the user in the database");
        try {
            User condition = new User();
            condition.setId(userModel.getId());
            condition.setName(userModel.getName());
            condition.setEmail(userModel.getEmail());
            condition.setPassword(userModel.getPassword());
            User user = userDAO.makePersistent(condition);
            userModel = new UserModel();
            BeanUtils.copyProperties(user, userModel);
            return userModel;
        } catch (Exception e) {
            log.error("An error occurred while adding the user details to the database", e);
            throw e;
        }
    }
}
