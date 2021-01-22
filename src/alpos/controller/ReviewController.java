package alpos.controller;

import alpos.model.CommentModel;
import alpos.model.HastagModel;
import alpos.model.ReviewModel;
import alpos.model.UserModel;
import alpos.service.BookService;
import alpos.service.CommentService;
import alpos.service.HastagService;
import alpos.service.ReviewService;
import alpos.service.UserService;
import alpos.entity.Review;
import alpos.interceptor.Flash;
import alpos.model.BookModel;
import alpos.model.HastagModel;
import alpos.model.ReviewModel;
import alpos.model.UserModel;
import alpos.service.*;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@EnableWebMvc
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	@Qualifier("reviewService")
	ReviewService reviewService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@Autowired
	@Qualifier("bookService")
	BookService bookService;

	@Autowired
	@Qualifier("hastagService")
	HastagService hastagService;
	
	@Autowired
	@Qualifier("commentService")
	CommentService commentService;

	public ReviewService getReviewService() {
		return reviewService;
	}
    @Resource
    Flash flash;


	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping(value = { "/reviews/add" })
	public String add(@RequestParam Integer bookId, Locale locale, Model model) {
		List<HastagModel> hastags = hastagService.findAll();
		model.addAttribute("hastags", hastags);
		model.addAttribute("review", new ReviewModel());
		model.addAttribute("book", bookService.findBook(bookId));
		return "reviews/add";
	}

	@PostMapping(value = "/reviews")
	public String create(@ModelAttribute("review") ReviewModel reviewModel, BindingResult bindingResult,
						 Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		reviewModel.setUserId(userModel.getId());
		reviewService.addReview(reviewModel);
		return "redirect: " + request.getContextPath() + "/home";
	}

	@GetMapping(value = "/reviews/{id}")
	public String showReview(@PathVariable Integer id, Model model) throws Exception {
		ReviewModel review = reviewService.findReviewById(id);
		model.addAttribute("review", review);
		model.addAttribute("comment", new CommentModel());
		List<CommentModel> comments = commentService.findCommentByreviewId(1);
		model.addAttribute("comments", comments);
		return "reviews/show";

	}


    @GetMapping(value = "/reviews/{id}/edit")
    public String edit(@PathVariable Integer id,Model model) {
        model.addAttribute("review", reviewService.findReview(id));
        return "reviews/edit";
    }

    /*Edit review */

    @PutMapping(value = "/reviews/{id}/edit")
    public String update(@ModelAttribute("review") @Validated ReviewModel reviewModel, BindingResult bindingResult,
						 Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            logger.info("Returning edit.jsp page, validate failed");
            return "reviews/edit";
        }
        logger.info("review model");
        ReviewModel review = reviewService.editReview(reviewModel);
        logger.info("doneee");
        flash.success("user.update.success");
        flash.keep();
        return "redirect: " + request.getContextPath() + "/home";
    }
}
