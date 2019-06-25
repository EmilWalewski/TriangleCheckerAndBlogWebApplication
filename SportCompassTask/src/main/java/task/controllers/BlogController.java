package task.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import task.pojo.Comment;
import task.pojo.Post;
import task.service.Server;
import task.validator.CommentsValidator;
import task.validator.PostsValidator;

/*
 * 
 * 		Controller displays main page of blog and allows adding new posts and comments, editing and deleting them
 * 
 */


@Controller
public class BlogController {
	
	private Map<Integer, Post> postMap;
	private Map<Integer, List<Comment>> commentSeparator;
	private List<Integer> commentsID;
	int size;
	private String actualPostCommentSection;
	
	@Autowired
	Comment comment;
	
	@Autowired
	Server server;
	
	
	/*
	 * 
	 *  Comments validator
	 * 
	 */
	@Autowired
	CommentsValidator commentsValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(commentsValidator);
	}
	
	/*
	 * 
	 *  Comments validator end
	 * 
	 */
	
	
	//add comment to blog
	@PostMapping(value = "/addComment")
	public String getComment(@ModelAttribute("comment") @Validated Comment comment, BindingResult bindingResult, Model model){
		
		if (bindingResult.hasErrors()) {
			return "redirect:blog?post="+actualPostCommentSection;
		}
		
		server.addComment(getCommentsID(commentsID), Integer.parseInt(actualPostCommentSection), comment.getAuthor(), comment.getContent());
		
		return "redirect:blog";
	}
	
	//add post to blog
	@GetMapping(value = "/content")
	public String postBlog(@SessionAttribute("sessionPostMessage") Post post, Model model) {
		
		server.addPost(getPostID(postMap), post.getPostHeader(), post.getPostContent());
		
		return "redirect:blog";
	}
	
	//always called when main page of blog is requested
	//when post parameter is send comment menu is expanded down below indicated post
	//when sessionEditedComment is send the obtained list possess information which comment in which post is being edited 
	@GetMapping(value = "/blog")
	public String getBlog(@RequestParam(value = "post", required = false) String param, @SessionAttribute(value = "sessionEditedComment", required = false) List<String> editListComment, HttpSession session, Model model) throws Exception {
		
		
		if(postMap != null) postMap.clear();
		if(commentSeparator != null) commentSeparator.clear();
		if(commentsID != null) commentsID.clear();
		
		
		if(postMap == null  ||  postMap.size() == 0) {
			
			postMap = new HashMap<Integer, Post>();
			
			for(Post postList : server.getPosts()) 
				postMap.put(postList.getId(), postList);
			
		}
		
		if(commentSeparator == null || commentSeparator.isEmpty()) {
			
			
			commentSeparator = new HashMap<Integer, List<Comment>>();
			commentsID = new ArrayList<Integer>();
			
			for (Comment comment : server.getComments()) {
				
				if(!commentSeparator.containsKey(comment.getIdpost())) {
					
					commentSeparator.put(comment.getIdpost(), new ArrayList<Comment>());
					commentSeparator.get(comment.getIdpost()).add(comment);
					
					commentsID.add(comment.getId());
				}
				else { commentSeparator.get(comment.getIdpost()).add(comment); commentsID.add(comment.getId());}
				
			}
			
		}
		
		
		if(param != null) {
			
			model.addAttribute("idComment", Integer.parseInt(param));
			actualPostCommentSection = param;
			
		}
		
		if(editListComment != null) {
			
			model.addAttribute("editComment", editListComment);
			session.invalidate();
			
		}
		
		
		
		model.addAttribute("comment", comment);
		model.addAttribute("postMap", postMap.entrySet());
		model.addAttribute("commentsMap", commentSeparator.entrySet());
		
		return "blog";
	}
	
	@GetMapping(value = "/mainBlogView")
	public String getBlog(Model model) {
		
		return "redirect:blog";
	}
	
	
	
	public int getCommentsID(List<Integer> commentsID) {
		
		size = 0;
			
			for(Integer var : commentsID) {
				if(size < var) size = var;
			}
		
		return ++size;
	}
	
	public int getPostID(Map<Integer, Post> map) {
		
		size = 0;
		
		map.keySet().forEach(key -> {
			if (size < key) size = key;
		});
		
		return ++size;
	}

}
