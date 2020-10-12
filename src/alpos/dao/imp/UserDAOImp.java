package alpos.dao.imp;

import alpos.dao.UserDAO;
import alpos.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImp extends GenericDAOImp<User, Integer> implements UserDAO {
    public UserDAOImp() {
        super(User.class);
    }
}
