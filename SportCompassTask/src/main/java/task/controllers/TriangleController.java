package task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import task.pojo.Triangle;


/*
 * 
 * 		Controller display page with three inputs, which get typed parameters and convey them  to ScoreController 
 * 		in @PostMapping annotated method
 * 		GetMapping annotated method use Triangle class fields as variables which will be assign after sending a request to post method
 * 
 */

@Controller
public class TriangleController {
	
	@Autowired
	Triangle triangle;
	
	
	@GetMapping(value = "/triangle")
	public String mainPageGet(Model model) {

		model.addAttribute("tri", triangle);
		
		return "triangle";
	}
		
	
	
	

}
