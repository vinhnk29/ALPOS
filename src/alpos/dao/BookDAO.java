package alpos.dao;

import alpos.entity.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book, Integer> {
    public Book findBookById(Integer id);
    public Book findRecommendBook();
    List<Book> findBookByKey(String key);
}
