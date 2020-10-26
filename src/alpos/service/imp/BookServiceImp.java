package alpos.service.imp;

import alpos.dao.BookDAO;
import alpos.entity.Book;
import alpos.model.BookModel;
import alpos.service.AuthorService;
import alpos.service.BookService;
import alpos.service.CategoryService;
import alpos.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alpos.dao.AuthorDAO;
import alpos.dao.CategoryDAO;
import alpos.dao.PublisherDAO;
import model.AuthorModel;
import model.CategoryModel;
import model.PublisherModel;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookServiceImp implements BookService {

    private static Logger log = LoggerFactory.getLogger(BookServiceImp.class);

    @Autowired
    private BookDAO bookDao;

	public BookDAO getBookDao() {
		return bookDao;
	}

    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    
    public AuthorDAO getAuthorDao() {
		return authorDao;
	}

    private BookServiceImp() {
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
            Book book = bookDao.makePersistent(condition);
            bookModel = new BookModel();
            BeanUtils.copyProperties(book, bookModel);
            return bookModel;
        } catch (Exception e) {
            log.error("An error occurred while adding the book details to the database", e);
            throw e;
        }
    }
	
	public void setAuthorDao(AuthorDAO authorDao) {
		this.authorDao = authorDao;
	}

	public CategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public PublisherDAO getPublisherDao() {
		return publisherDao;
	}

	public void setPublisherDao(PublisherDAO publisherDao) {
		this.publisherDao = publisherDao;
	}

	@Autowired
	private AuthorDAO authorDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private PublisherDAO publisherDao;
	
	@Transactional(readOnly = true)
	public List<BookModel> findAll(){
		List<BookModel> bookModels = new ArrayList<BookModel>();
		try {
			List<Book> books = bookDao.findAll();
			for (Book book: books) {
				BookModel bookModel = new BookModel();
				BeanUtils.copyProperties(book, bookModel);
				
				AuthorModel authorModel = new AuthorModel();
				BeanUtils.copyProperties(book.getAuthor(), authorModel);
				bookModel.setAuthor(authorModel);
				
				PublisherModel publisherModel = new PublisherModel();
				BeanUtils.copyProperties(book.getPublisher(), publisherModel);
				bookModel.setPublisher(publisherModel);
				
				CategoryModel categoryModel = new CategoryModel();
				BeanUtils.copyProperties(book.getCategory(), categoryModel);
				bookModel.setCategory(categoryModel);
				
				bookModels.add(bookModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bookModels;
	}
}
