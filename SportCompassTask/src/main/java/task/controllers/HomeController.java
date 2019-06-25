package task.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 
 * 		Controller displays main page of whole application and allows switching between triangle calculator and blog
 * 
 */


@Controller
public class HomeController {
	
	@GetMapping(value = "/")
	public String home(Model model) {
		
		return "home";
	}
	
	@GetMapping(value = "/home")
	public String home(Model model, HttpSession session) {
		
		session.invalidate();
		return "home";
	}
	

}
