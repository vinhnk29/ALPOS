package alpos.dao.imp;

import alpos.dao.BlackListDAO;
import alpos.entity.BlackList;
import org.springframework.stereotype.Repository;


@Repository
public class BlackListDAOImp extends GenericDAOImp<BlackList, Integer> implements BlackListDAO {
    public BlackListDAOImp() {
        super(BlackList.class);
    }
}
