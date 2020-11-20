package alpos.controller;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticPagesController {

	private static final Logger logger = LoggerFactory.getLogger(StaticPagesController.class);



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
	public String home(Locale locale, Model model) {
		
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
}
