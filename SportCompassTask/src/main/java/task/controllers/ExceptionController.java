package task.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String getException(Model model, Exception e) {
		
		return "handleException";
	}

}
