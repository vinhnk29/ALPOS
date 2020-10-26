package alpos.service.imp;

import alpos.dao.CategoryDAO;
import alpos.entity.Category;
import alpos.model.CategoryModel;
import alpos.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImp implements CategoryService {

    private static Logger log = LoggerFactory.getLogger(CategoryServiceImp.class);

    @Autowired
    private CategoryDAO categoryDAO;

    private CategoryServiceImp() {
    }

    public void setCategoryDao(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<CategoryModel> findAll() {
        log.info("Fetching all categories in the database");
        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        try {
            List<Category> categoryList = categoryDAO.findAll();
            for (Category category : categoryList) {
                CategoryModel categoryModel = new CategoryModel();
                BeanUtils.copyProperties(category, categoryModel);
                categoryModelList.add(categoryModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all categories from the database", e);
        }
        return categoryModelList;
    }


}
