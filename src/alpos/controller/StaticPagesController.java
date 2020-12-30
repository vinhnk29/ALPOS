package alpos.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import alpos.model.BookModel;
import alpos.model.ReviewModel;
import alpos.model.UserModel;
import alpos.service.BookService;
import alpos.service.ReviewService;
import alpos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaticPagesController {

	private static final Logger logger = LoggerFactory.getLogger(StaticPagesController.class);

	@Autowired
	@Qualifier("bookService")
	BookService bookService;

	@Autowired
	@Qualifier("reviewService")
	ReviewService reviewService;

	@Autowired
	@Qualifier("userService")
	UserService userService;


	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public String root(Locale locale, Model model, final RedirectAttributes redirectAttributes,
//			HttpServletRequest request) {
//		logger.info("Home Page Requested, locale = " + locale);
//		redirectAttributes.addFlashAttribute("css", "success");
//		redirectAttributes.addFlashAttribute("flash", "user.create.success");
//		flash.info("user.create.success");
//		flash.keep();
//		return "redirect: " + request.getContextPath() + "/users";
////		return "home.page";
//	}

	@RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, @RequestParam(name = "page", required = false) Optional<Integer> page) {
		model.addAttribute("book", bookService.findRecommendBook());
		ReviewModel reviewModel = new ReviewModel();
		reviewModel.setUserId(4);
		reviewModel.setPage(page.orElse(1));
		Page<ReviewModel> reviews = reviewService.paginate(reviewModel);
		model.addAttribute("reviews", reviews);

		return "static_pages/home";
	}
	
	

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/help";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/about";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/contact";
	}
	
//	@PostMapping(value = "/contacts")
//	public String create(@ModelAttribute("contact") ContactModel contactModel, BindingResult bindingResult,
//			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
//		
//		ContactModel contact = contactService.addContact(contactModel);
//		// Add message to flash scope
//		return "static_pages/contact";
//	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/info";
	}


	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public String accessDenied() {
		logger.info("Access denied");
		return "access_denied";
	}

	@GetMapping(value = "/search", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<BookModel> search(Locale locale, Model model, Authentication authentication,
								  @RequestParam String key)
	{
		List<BookModel> books = bookService.findBookByKey(key);
		return books;
	}
}
