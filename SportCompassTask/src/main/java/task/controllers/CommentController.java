package task.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import task.pojo.Comment;
import task.service.Server;
import task.validator.CommentsValidator;
import task.validator.PostsValidator;

/*
 * 
 * 		Controller processing requests get from BlogController, it is in charge of editing and deleting comments
 * 
 */


@Controller
@SessionAttributes("sessionEditedComment")
public class CommentController {
	
	@Autowired
	Server server;
	
	List<String> list;
	
	//delete comment from database
	@GetMapping(value = "/commentDelete")
	public String getComment(@RequestParam(value = "id") String id, Model model){
		
		server.deleteComment(Integer.parseInt(id));
		
		return "redirect:/blog";
	}
	
	//save edited comment in database
	@PostMapping(value = "/saveComment")
	public String getComment(@ModelAttribute("comment") Comment comment, @RequestParam(value = "commentID") String id, Model model) throws Exception{
		
		server.editComment(Integer.parseInt(id), comment.getContent());
		
		return "redirect:/blog";
	}
	
	//return information about comment which is being edited -- they are used to expand down edit comment menu at blog page
	@GetMapping(value = "/commentEdit")
	public String editPost(@RequestParam(value = "id") String commentID, @RequestParam(value = "idpost") String postID, Model model) throws Exception {
		
		list = new ArrayList<String>();
		list.add(commentID);
		list.add(postID);
		model.addAttribute("sessionEditedComment", list);
		
		return "redirect:/blog";
	}

}
