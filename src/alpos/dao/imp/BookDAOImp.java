package alpos.dao.imp;

import alpos.dao.BookDAO;
import alpos.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository
public class BookDAOImp extends GenericDAOImp<Book, Integer> implements BookDAO {
    public BookDAOImp() {
        super(Book.class);
    }

    public Book findBookById(Integer id) {

        try {
            return getHibernateTemplate().execute(new HibernateCallback<Book>() {
                public Book doInHibernate(Session session) throws HibernateException {
                    Query<Book> query = session.createQuery("FROM Book WHERE id = :id", Book.class);
                    query.setParameter("id", id);
                    return query.uniqueResult();
                }
            });
        } catch (Exception e) {
            return null;
        }
    }
}
