package mggdevit.demolibrary.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mggdevit.demolibrary.entity.User;
import mggdevit.demolibrary.repo.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

//	@RequestMapping("/")
	@GetMapping("/")
	public String index() {
		return ("index");
	}
	
//	@RequestMapping("/viewusers")
	@GetMapping("/viewusers")
	public String viewusers(Model model) {
		logger.info("get viewusers called");
//		model.addAttribute("message", "Message");
//		model.addAttribute("th_text_example", "<div>bla<br>bla</div>");
//		model.addAttribute("th_utext_example", "<div>bla<br>bla</div>");
//		model.addAttribute("th_date_today", new java.util.Date());
		model.addAttribute("oneuser", userRepository.findById(2L));
		model.addAttribute("users", userRepository.findAll());
		return ("viewusers");
	}

	@PostMapping("/viewusers")
	public String postUser(@ModelAttribute @Valid User user, Model model, Errors errors) {
		logger.info("post viewusers called");
//		System.out.println("---");
//		System.out.println(person.getFirstName());
//		System.out.println(person.getLastName());
		model.addAttribute("oneuser", userRepository.findById(2L));
		model.addAttribute("users", userRepository.findAll());
		return ("viewusers");
	}
}
