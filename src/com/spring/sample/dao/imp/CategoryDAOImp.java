package com.spring.sample.dao.imp;

import com.spring.sample.dao.CategoryDAO;
import com.spring.sample.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImp extends GenericDAOImp<Category, Integer> implements CategoryDAO {
	public CategoryDAOImp() {
		super(Category.class);
	}
}
