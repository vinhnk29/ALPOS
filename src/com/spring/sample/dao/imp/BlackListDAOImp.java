package com.spring.sample.dao.imp;

import com.spring.sample.dao.BlackListDAO;
import com.spring.sample.entity.BlackList;
import org.springframework.stereotype.Repository;

@Repository
public class BlackListDAOImp extends GenericDAOImp<BlackList, Integer> implements BlackListDAO {
	public BlackListDAOImp() {
		super(BlackList.class);
	}
}
