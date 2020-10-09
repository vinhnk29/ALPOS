package com.spring.sample.dao.imp;

import com.spring.sample.dao.AuthorDAO;
import com.spring.sample.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDAOImp extends GenericDAOImp<Author, Integer> implements AuthorDAO {
	public AuthorDAOImp() {
		super(Author.class);
	}
}
