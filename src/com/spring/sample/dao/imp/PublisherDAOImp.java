package com.spring.sample.dao.imp;


import com.spring.sample.dao.PublisherDAO;
import com.spring.sample.entity.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherDAOImp extends GenericDAOImp<Publisher, Integer> implements PublisherDAO {
	public PublisherDAOImp() {
		super(Publisher.class);
	}
}
