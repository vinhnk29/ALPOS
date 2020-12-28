package alpos.service.imp;

import alpos.dao.CategoryDAO;
import alpos.dao.HastagDAO;
import alpos.entity.Category;
import alpos.entity.Hastag;
import alpos.model.CategoryModel;
import alpos.model.HastagModel;
import alpos.service.CategoryService;
import alpos.service.HastagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class HastagServiceImp implements HastagService {

    private static Logger log = LoggerFactory.getLogger(HastagServiceImp.class);

    @Autowired
    private HastagDAO hastagDAO;

    private HastagServiceImp() {
    }

    public void setHastagDao(HastagDAO hastagDao) {
        this.hastagDAO = hastagDao;
    }

    public List<HastagModel> findAll() {
        log.info("Fetching all hastags in the database");
        List<HastagModel> hastagModelList = new ArrayList<HastagModel>();
        try {
            List<Hastag> hastagList = hastagDAO.findAll();
            for (Hastag hastag : hastagList) {
                HastagModel hastagModel = new HastagModel();
                BeanUtils.copyProperties(hastag, hastagModel);
                hastagModelList.add(hastagModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all hastags from the database", e);
        }
        return hastagModelList;
    }

    @Transactional(readOnly = true)
    public List<HastagModel> findHastagByKey(String key){
        log.info("Filter hastag for key in the database");
        List<HastagModel> hastagModelList = new ArrayList<HastagModel>();
        try {
            List<Hastag> hastagList = hastagDAO.findhastagByKey(key);
            for (Hastag hastag : hastagList) {
                HastagModel hastagModel = new HastagModel();
                hastagModel.setId(hastag.getId());
                hastagModel.setName(hastag.getName());
                //BeanUtils.copyProperties(hastag, hastagModel);
                hastagModelList.add(hastagModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all hastags from the database", e);
        }
        return hastagModelList;
    }

}
