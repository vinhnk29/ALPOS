package alpos.service.imp;

import alpos.dao.BookDAO;
import alpos.entity.Book;
import alpos.model.AuthorModel;
import alpos.model.BookModel;
import alpos.model.CategoryModel;
import alpos.model.PublisherModel;
import alpos.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alpos.dao.AuthorDAO;
import alpos.dao.CategoryDAO;
import alpos.dao.PublisherDAO;

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

    @Autowired
    private AuthorDAO authorDao;

    @Autowired
    private CategoryDAO categoryDao;

    @Autowired
    private PublisherDAO publisherDao;

	public BookDAO getBookDao() {
		return bookDao;
	}

    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    
    public AuthorDAO getAuthorDao() {
		return authorDao;
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
            condition.setImage(bookModel.getImage());
            Book book = bookDao.makePersistent(condition);
            bookModel = new BookModel();
            BeanUtils.copyProperties(book, bookModel);
            return bookModel;
        } catch (Exception e) {
            log.error("An error occurred while adding the book details to the database", e);
            throw e;
        }
    }

	
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


    @Transactional(readOnly = true)
    public BookModel findBook(Integer id) {
        log.info("Checking the book in the database");
        try {
            Book book = bookDao.findBookById(id);
            BookModel bookModel = new BookModel();
            if (book != null) {
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
            }
            return bookModel;
        } catch (Exception e) {
            log.error("An error occurred while fetching the book details from the database", e);
            return null;
        }
    }


    @Transactional(readOnly = true)
    public BookModel findRecommendBook() {
        log.info("Find recommend book");
        BookModel bookModel = new BookModel();
        try {
            Book book = bookDao.findRecommendBook();
            if (book != null) {
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
            }
            return bookModel;
        } catch (Exception e) {
            log.error("An error occurred while fetching the book details from the database", e);
            return null;
        }
    }


    @Transactional(readOnly = true)
    public List<BookModel> findBookByKey(String key){
        log.info("Filter book for key in the database");
        List<BookModel> bookModelList = new ArrayList<BookModel>();
        try {
            List<Book> bookList = bookDao.findBookByKey(key);
            for (Book book : bookList) {
                BookModel bookModel = new BookModel();
                bookModel.setId(book.getId());
                bookModel.setName(book.getName());
                bookModelList.add(bookModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all hastags from the database", e);
        }
        return bookModelList;
    }
}
