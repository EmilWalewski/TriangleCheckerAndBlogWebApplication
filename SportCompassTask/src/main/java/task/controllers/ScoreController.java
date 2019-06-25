package task.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import task.pojo.Triangle;


/*
 * 
 * 		Controller gets a request, counts score and displays it
 * 
 */



@Controller
public class ScoreController {

	
	@PostMapping(value = "/triangle")
	public String mainPagePost(@ModelAttribute("triangle") Triangle triangle, Model model) {
		
		model.addAttribute("check", triangle.checker());
		return "score";
	}
	
	@GetMapping(value = "/backToTriangleData")
	public String backToData() {
		return "redirect:/triangle";
	}
	

}
