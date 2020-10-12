package alpos.dao.imp;

import alpos.dao.AuthorDAO;
import alpos.entity.Author;
import org.springframework.stereotype.Repository;


@Repository
public class AuthorDAOImp extends GenericDAOImp<Author, Integer> implements AuthorDAO {
    public AuthorDAOImp() {
        super(Author.class);
    }
}
