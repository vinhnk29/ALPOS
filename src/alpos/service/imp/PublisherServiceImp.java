package alpos.service.imp;

import alpos.dao.PublisherDAO;
import alpos.entity.Publisher;
import alpos.model.PublisherModel;
import alpos.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PublisherServiceImp implements PublisherService {

    private static Logger log = LoggerFactory.getLogger(PublisherServiceImp.class);

    @Autowired
    private PublisherDAO publisherDAO;

    private PublisherServiceImp() {
    }

    public void setPublisherDao(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    public List<PublisherModel> findAll() {
        log.info("Fetching all publishers in the database");
        List<PublisherModel> publisherModelList = new ArrayList<PublisherModel>();
        try {
            List<Publisher> publisherList = publisherDAO.findAll();
            for (Publisher publisher : publisherList) {
                PublisherModel publisherModel = new PublisherModel();
                BeanUtils.copyProperties(publisher, publisherModel);
                publisherModelList.add(publisherModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all publishers from the database", e);
        }
        return publisherModelList;
    }

}
