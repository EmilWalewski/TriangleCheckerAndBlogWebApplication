package task.controllers;

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

import task.pojo.Post;
import task.service.Server;
import task.validator.PostsValidator;

/*
 * 
 * 		Controller processing requests get from BlogController, it is in charge of editing and deleting posts
 * 
 */


@Controller
@SessionAttributes("sessionPostMessage")
public class PostController {
	
	@Autowired
	Server server;
	
	@Autowired
	Post post;
	
	String idModifiedPost;
	
	/*
	 * 
	 *  Comments validator
	 * 
	 */
	
	@Autowired
	PostsValidator postsCommentsValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(postsCommentsValidator);
	}
	
	/*
	 * 
	 *  Comments validator end
	 * 
	 */
	
	
	//return id of editing post
	@GetMapping(value = "/edit")
	public String editPost(@RequestParam(value = "id") String edit, Model model) throws Exception {
		
		idModifiedPost = edit;
		
		return "redirect:addPost";
	}
	
	//delete post from database
	@GetMapping(value = "/delete")
	public String deletePost(@RequestParam(value = "id") String delete, Model model) throws Exception {
		
		server.deletePost(Integer.parseInt(delete));
		
		return "redirect:/blog";
	}
	
	//post form use Post class fields as variables which will be assign after sending a request to post method
	@GetMapping(value = "/addPost")
	public String getPostPage(Model model) {

		model.addAttribute("post", post);
		return "addPost";
		
	}
	
	//send edited post to database and redirect it to BlogController where post is added to blog page 
	//or new post is added and it is convey in session variable to BlogController where post is added to blog page
	@PostMapping(value = "/addPost")
	public String sendPost(@ModelAttribute("post") @Validated Post post, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "addPost";
		}
		
		if(idModifiedPost != null) {
			server.editPost(Integer.parseInt(idModifiedPost), post.getPostHeader(), post.getPostContent());
			idModifiedPost = null;
			return "redirect:/blog";
		}
		
		model.addAttribute("sessionPostMessage", post);
		return "redirect:/content";
	}
	
	
	

}
