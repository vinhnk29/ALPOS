package alpos.controller;

import alpos.interceptor.Flash;
import alpos.model.UserModel;
import alpos.service.UserService;
import alpos.uploader.ImageUpload;
import alpos.uploader.ImageUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

	@Autowired
	@Qualifier("imageUploader")
	ImageUploader imageUploader;

	@Resource
	Flash flash;

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

	@GetMapping(value = "/users/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request, Authentication authentication)
			throws Exception {
		System.out.println("Show user");
		model.addAttribute("user", userService.findUser(id));
		return "users/show";
	}

	@GetMapping(value = "/users/{id}/edit")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.findUser(id));
		return "users/edit";
	}

	@PutMapping(value = "/users/{id}/edit")
	public String update(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
						 Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		ImageUpload imageUpload = imageUploader.uploadFile(userModel.getFile());
		if (imageUpload != null) {
			userModel.setUpload(imageUpload);
		}

		if (bindingResult.hasErrors()) {
			logger.info("Returning edit.jsp page, validate failed");
			return "users/edit";
		}
		UserModel user = userService.editUser(userModel);
		// Add message to flash scope
		flash.success("user.update.success");
		flash.keep();
		return "redirect: " + request.getContextPath() + "/users/" + user.getId();
	}
}
