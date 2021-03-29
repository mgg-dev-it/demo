package mggdevit.demolibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mggdevit.demolibrary.repo.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

//	private static List<User> persons = new ArrayList<User>();
//	static {
//		persons.add(new Person("John", "Smith"));
//		persons.add(new Person("George", "Tailor"));
//	}

	@RequestMapping("/")
	public String index() {
		return ("index");
	}
	
	@RequestMapping("/viewusers")
	public String view(Model model) {
//		model.addAttribute("message", "Message");
//		model.addAttribute("th_text_example", "<div>bla<br>bla</div>");
//		model.addAttribute("th_utext_example", "<div>bla<br>bla</div>");
//		model.addAttribute("th_date_today", new java.util.Date());
		model.addAttribute("oneuser", userRepository.findById(2L));
		model.addAttribute("users", userRepository.findAll());
		return ("viewusers");
	}

}
