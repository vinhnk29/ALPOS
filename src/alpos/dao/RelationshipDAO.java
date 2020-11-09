package alpos.dao;

import alpos.entity.Relationship;

public interface RelationshipDAO extends GenericDAO<Relationship, Integer> {

	public Long countFollowers(Integer followedId);
	
	public Long countFollowings(Integer followerId);
}
