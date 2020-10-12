package alpos.dao.imp;

import alpos.dao.PublisherDAO;
import alpos.entity.Publisher;
import org.springframework.stereotype.Repository;


@Repository
public class PublisherDAOImp extends GenericDAOImp<Publisher, Integer> implements PublisherDAO {
    public PublisherDAOImp() {
        super(Publisher.class);
    }
}
