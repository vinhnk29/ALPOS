package alpos.dao;

import alpos.entity.Hastag;

import java.util.List;

public interface HastagDAO extends GenericDAO<Hastag, Integer> {
    public List<Hastag> findhastagByKey(String key);
}
