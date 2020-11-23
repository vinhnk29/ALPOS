package alpos.controller;

import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import alpos.model.BlackListModel;
import alpos.model.UserModel;
import alpos.service.ReviewService;

@Controller
@EnableWebMvc
public class BlacklistController {
	private static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);

	@Autowired
	@Qualifier("reviewService")
	ReviewService reviewService;

	@PostMapping(value = "/blackList")
	public String blackList(@RequestParam Integer review_id, HttpServletRequest request,
			Authentication authentication) throws Exception {
		System.out.println("abc");
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		reviewService.blackList(review_id, userModel.getId());
		
		return "redirect: " + request.getContextPath() + "/users/" + userModel.getId();
		//Can sua thanh 
		//return "redirect: " + request.getContextPath() + "/reviews/" + userModel.getreviewId()
	}
	
	@GetMapping("/blackLists")
	public String index(Locale locale, Model model, HttpServletRequest request,
			@RequestParam(name = "page", required = false) Optional<Integer> page) {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		BlackListModel blackListModel = new BlackListModel();
		blackListModel.setUser_id(userModel.getId());
		blackListModel.setPage(page.orElse(1));
		Page<BlackListModel> blackLists = reviewService.paginate(blackListModel);
		model.addAttribute("blackLists", blackLists);
		return "blackLists/index";
	}
}
