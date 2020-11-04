package alpos.dao;

import alpos.entity.Book;

public interface BookDAO extends GenericDAO<Book, Integer> {
    public Book findBookById(Integer id);
}
