package com.spring.sample.dao.imp;

import com.spring.sample.dao.HastagDAO;
import com.spring.sample.entity.Hastag;
import org.springframework.stereotype.Repository;

@Repository
public class HastagDAOImp extends GenericDAOImp<Hastag, Integer> implements HastagDAO {
	public HastagDAOImp() {
		super(Hastag.class);
	}
}
