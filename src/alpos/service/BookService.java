package alpos.service;

import java.util.List;

import alpos.model.BookModel;

public interface BookService {
    public BookModel addBook(BookModel bookModel) throws Exception;
    public List<BookModel> findAll();
    public BookModel findBook(Integer id);
    public BookModel findRecommendBook();
}
