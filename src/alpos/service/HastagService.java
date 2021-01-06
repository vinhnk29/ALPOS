package alpos.service;



import alpos.model.HastagModel;

import java.util.List;

public interface HastagService {
    public List<HastagModel> findAll();
    public List<HastagModel> findHastagByKey(String key);
}
