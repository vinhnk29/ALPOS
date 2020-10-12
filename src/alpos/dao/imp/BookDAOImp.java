package alpos.dao.imp;

import alpos.dao.BookDAO;
import alpos.entity.Book;
import org.springframework.stereotype.Repository;


@Repository
public class BookDAOImp extends GenericDAOImp<Book, Integer> implements BookDAO {
    public BookDAOImp() {
        super(Book.class);
    }
}
