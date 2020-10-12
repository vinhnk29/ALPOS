package alpos.dao.imp;

import alpos.dao.LikeDAO;
import alpos.entity.Like;
import org.springframework.stereotype.Repository;


@Repository
public class LikeDAOImp extends GenericDAOImp<Like, Integer> implements LikeDAO {
    public LikeDAOImp() {
        super(Like.class);
    }
}
