package alpos.dao.imp;

import alpos.dao.HastagDAO;
import alpos.entity.Hastag;
import alpos.service.HastagService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HastagDAOImp extends GenericDAOImp<Hastag, Integer> implements HastagDAO {
    private static Logger log = LoggerFactory.getLogger(HastagService.class);
    public HastagDAOImp() {
        super(Hastag.class);
    }

    public List<Hastag> findhastagByKey(String key) {
        return getHibernateTemplate().execute(new HibernateCallback<List<Hastag>>() {
            public List<Hastag> doInHibernate(Session session) throws HibernateException {
                Query<Hastag> query = session.createQuery("FROM Hastag WHERE name like :key ", Hastag.class);
                query.setParameter("key", "%" + key + "%");
                return query.list();
            }
        });
    }

}
