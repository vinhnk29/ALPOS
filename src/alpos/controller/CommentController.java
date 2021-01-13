package alpos.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import alpos.model.CommentModel;
import alpos.model.UserModel;
import alpos.service.CommentService;
import alpos.service.ReviewService;
import alpos.service.UserService;

@Controller
@EnableWebMvc
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    @Qualifier("reviewService")
    private ReviewService reviewService;


    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;


	@PostMapping(value = "/comments/add")
	public String create(@ModelAttribute("comment") @Validated CommentModel commentModel, BindingResult bindingResult,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		if (bindingResult.hasErrors()) {
			logger.info("Returning register.jsp page, validate failed");
			model.addAttribute("user", userService.findUser(userModel.getId()));
			model.addAttribute("comment", commentModel);
			return "reviews/show";
		}
		commentModel.setUserId(userModel.getId());
		CommentModel comment = commentService.addComment(commentModel);
		//return "reviews/show";
		return "redirect: " + request.getContextPath() + "/reviews/" + comment.getReviewId();
	}
}
