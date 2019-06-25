package mockito.tests;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import task.controllers.BlogController;
import task.pojo.Comment;
import task.pojo.Post;
import task.service.Server;

@RunWith(MockitoJUnitRunner.class)
public class BlogControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private BlogController blogController;
	
	@Mock
	private Map<Integer, Post> postMap;
	@Mock
	private Map<Integer, List<Comment>> commentSeparator;
	@Mock
	Server server;
	@Mock
	Comment comment;
	
	
	@Before
	public void setUp() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(blogController).setViewResolvers(resolver).build();
	}
	
	
	@Test
	public void blog_get_controller_test() throws Exception {
		
		List<String> list = new ArrayList<String>();
		
		 mockMvc.perform(get("/blog")
			.param("post", "3")
			.sessionAttr("sessionEditedComment", list))
		 		.andExpect(model().attributeExists("comment"))
		 		.andExpect(model().attributeExists("postMap"))
		 		.andExpect(model().attributeExists("commentsMap"))
		 		.andExpect(model().attributeExists("idComment"))
		 		.andExpect(model().attributeExists("editComment"))
		 		.andExpect(model().size(5))
		 		.andExpect(model().attribute("idComment", Integer.parseInt("3")))
		 		.andExpect(model().attribute("editComment", list))
		 		.andExpect(model().attribute("comment", comment))
		 		.andExpect(model().attribute("postMap", postMap.entrySet()))
		 		.andExpect(model().attribute("commentsMap", commentSeparator.entrySet()))
		 		.andExpect(model().attribute("idComment", 3))
		 		.andExpect(model().attribute("editComment", list))
		 		.andExpect(status().isOk())
		 		.andExpect(view().name("blog"));
		 
		Post post1 = new Post(1, "header", "content");
		when(postMap.put(1, post1)).thenReturn(post1);
		
		Post post2 = new Post(2, "header", "content");
		when(postMap.put(2, post2)).thenReturn(post2);
		
		
		
		List<Comment> commentsList = new ArrayList<Comment>();
		
		
		Comment comment1 = new Comment(1, 1, "header", "content");
		commentsList.add(comment1);
		when(commentSeparator.put(1, commentsList)).thenReturn(commentsList);
		
		Comment comment2 = new Comment(2, 1, "header", "content");
		commentsList.add(comment1);
		when(commentSeparator.put(1, commentsList)).thenReturn(commentsList);
		 
		 
		server.addPost(1, "header", "content");
		server.addPost(2, "header", "content");
		server.addComment(1, 1, "header", "comment");
		server.addComment(2, 1, "header", "comment");
			
		verify(server).addPost(1, "header", "content");
		verify(server).addPost(2, "header", "content");
		verify(server).addComment(1, 1, "header", "comment");
		verify(server).addComment(2, 1, "header", "comment");
	}
	
	@Test
	public void content_get_controller_test() throws Exception {
		
		mockMvc.perform(get("/content")
			.sessionAttr("sessionPostMessage", new Post()))
				.andExpect(redirectedUrl("blog"))
				.andExpect(view().name("redirect:blog"));
	}
	
}

