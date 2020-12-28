package alpos.controller;


import alpos.dao.HastagDAO;
import alpos.model.HastagModel;
import alpos.service.HastagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@EnableWebMvc
public class HastagController {
    private static final Logger logger = LoggerFactory.getLogger(HastagController.class);

    @Autowired
    @Qualifier("hastagService")
    HastagService hastagService;

    @GetMapping(value = "/hastags", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<HastagModel> projects(@RequestParam(name = "key") String key, Locale locale,
                                      Model model) {
        List<HastagModel> hastags = hastagService.findHastagByKey(key);
        return hastags;
    }
}
