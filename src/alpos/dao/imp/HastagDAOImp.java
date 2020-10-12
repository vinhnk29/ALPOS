package alpos.dao.imp;

import alpos.dao.HastagDAO;
import alpos.entity.Hastag;
import org.springframework.stereotype.Repository;


@Repository
public class HastagDAOImp extends GenericDAOImp<Hastag, Integer> implements HastagDAO {
    public HastagDAOImp() {
        super(Hastag.class);
    }
}
