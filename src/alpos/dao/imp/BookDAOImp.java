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

    public Book findRecommendBook() {
//        DetachedCriteria avgWeight = DetachedCriteria.forClass(Rating.class)
//                .setProjection(Projections.projectionList()
//                        .add(Projections.groupProperty("book_id"))).addOrder(Order.desc("avg(point)"));
//        DetachedCriteria bookCrit = DetachedCriteria.forClass(Book.class).add(Property.forName("weight").eq(avgWeight));
//        getHibernateTemplate().findByCriteria()
//        return getHibernateTemplate().execute(new HibernateCallback<Book>() {
//            public Book doInHibernate(Session session) throws HibernateException {
////                DetachedCriteria avgWeight = DetachedCriteria.forClass(Rating.class)
////                        .setProjection(Projections.projectionList()
////                                .add(Projections.groupProperty("book_id"))).addOrder(Order.desc("avg(point)"));
//                Query<Book> query = session.createQuery("FROM Book WHERE book.id = (SELECT book_id FROM Rating group by book_id order by avg(point) desc)", Book.class).setMaxResults(1);
//                return query.uniqueResult();
//            }
//        });
        Integer bookId = getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException {
                Query<Integer> query = session.createQuery("SELECT bookId FROM Rating group by bookId order by avg(point) desc", Integer.class);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        });
        return this.find(bookId);
    }

}
