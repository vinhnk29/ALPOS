package alpos.dao;

import java.util.List;

import alpos.entity.Relationship;
import alpos.entity.User;

public interface RelationshipDAO extends GenericDAO<Relationship, Integer> {

	public Long countFollowers(Integer followedId);
	
	public Long countFollowings(Integer followerId);
	
	public List<User> followings(Integer userId);
}
