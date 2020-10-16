package alpos.controller;

import alpos.model.UserModel;
import alpos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;


@Controller
@EnableWebMvc
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @GetMapping(value = { "/users/add", "/signup" })
    public String add(Locale locale, Model model) {
        model.addAttribute("user", new UserModel());
        return "users/add";
    }


    @PostMapping(value = "/users")
    public String create(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
                         Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        if (bindingResult.hasErrors()) {
            logger.info("Returning register.jsp page, validate failed");
            return "users/add";
        }
        UserModel user = userService.addUser(userModel);
        return "static_pages/home";
    }

}
