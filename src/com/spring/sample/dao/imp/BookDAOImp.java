package com.spring.sample.dao.imp;

import com.spring.sample.dao.BookDAO;
import com.spring.sample.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImp extends GenericDAOImp<Book, Integer> implements BookDAO {
	public BookDAOImp() {
		super(Book.class);
	}
}
