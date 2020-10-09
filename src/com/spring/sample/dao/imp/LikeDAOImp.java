package com.spring.sample.dao.imp;


import com.spring.sample.dao.LikeDAO;
import com.spring.sample.entity.Like;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDAOImp extends GenericDAOImp<Like, Integer> implements LikeDAO {
	public LikeDAOImp() {
		super(Like.class);
	}
}
