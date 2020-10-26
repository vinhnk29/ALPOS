package alpos.service.imp;

import alpos.dao.AuthorDAO;
import alpos.entity.Author;
import alpos.model.AuthorModel;
import alpos.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorServiceImp implements AuthorService {

    private static Logger log = LoggerFactory.getLogger(AuthorServiceImp.class);

    @Autowired
    private AuthorDAO authorDAO;

    private AuthorServiceImp() {
    }

    public void setAuthorDao(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }


    public List<AuthorModel> findAll() {
        log.info("Fetching all authors in the database");
        List<AuthorModel> authorModelList = new ArrayList<AuthorModel>();
        try {
            List<Author> authorList = authorDAO.findAll();
            for (Author author : authorList) {
                AuthorModel authorModel = new AuthorModel();
                BeanUtils.copyProperties(author, authorModel);
                authorModelList.add(authorModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all authors from the database", e);
        }
        return authorModelList;
    }

}
