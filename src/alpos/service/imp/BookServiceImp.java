package alpos.service.imp;

import alpos.dao.BookDAO;
import alpos.entity.Book;
import alpos.model.BookModel;
import alpos.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookServiceImp implements BookService {

    private static Logger log = LoggerFactory.getLogger(BookServiceImp.class);

    @Autowired
    private BookDAO bookDAO;

    private BookServiceImp() {
    }

    public void setBookDao(BookDAO bookDao) {
        this.bookDAO = bookDao;
    }

    @Transactional
    public BookModel addBook(BookModel bookModel) throws Exception {
        log.info("Adding the book in the database");
        try {
            Book condition = new Book();
            condition.setId(bookModel.getId());
            condition.setName(bookModel.getName());
            condition.setAuthorId(bookModel.getAuthorId());
            condition.setPublisherId(bookModel.getPublisherId());
            condition.setCategoryId(bookModel.getCategoryId());
            condition.setReleaseYear(bookModel.getReleaseYear());
            Book book = bookDAO.makePersistent(condition);
            bookModel = new BookModel();
            BeanUtils.copyProperties(book, bookModel);
            return bookModel;
        } catch (Exception e) {
            log.error("An error occurred while adding the book details to the database", e);
            throw e;
        }
    }
}
