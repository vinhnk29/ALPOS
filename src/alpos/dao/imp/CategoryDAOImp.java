package alpos.dao.imp;

import alpos.dao.CategoryDAO;
import alpos.entity.Category;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryDAOImp extends GenericDAOImp<Category, Integer> implements CategoryDAO {
    public CategoryDAOImp() {
        super(Category.class);
    }
}
