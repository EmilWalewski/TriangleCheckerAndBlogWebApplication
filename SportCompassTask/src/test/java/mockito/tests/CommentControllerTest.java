package mockito.tests;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import task.controllers.CommentController;
import task.controllers.PostController;
import task.pojo.Comment;
import task.pojo.Post;
import task.service.Server;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private CommentController commentController;
	
	@Mock
	Server server;
	
	@Mock
	List<String> list;
	
	
	@Before
	public void setUp() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(commentController).setViewResolvers(resolver).build();
	}
	
	@Test
	public void commentEdit_get_controller_test() throws Exception {
		
		list.add("1");
		list.add("2");
		
		
		mockMvc.perform(get("/commentEdit")
			.param("id", "6")
			.param("idpost", "8")
			.sessionAttr("sessionEditedComment", list))
				.andExpect(model().attributeExists("sessionEditedComment"))
				.andExpect(model().size(1))
				.andExpect(redirectedUrl("/blog?sessionEditedComment=6&sessionEditedComment=8"))
				.andExpect(view().name("redirect:/blog"));
		
		verify(list, times(1)).add("1");
		verify(list, times(1)).add("2");		
		
	}
	
	@Test
	public void commentDelete_get_controller_test() throws Exception {
		
		doNothing().when(server).deleteComment(6);
		server.deleteComment(6);
		
		mockMvc.perform(get("/commentDelete")
			.param("id", "6"))
				.andExpect(redirectedUrl("/blog"))
				.andExpect(view().name("redirect:/blog"));
		
		verify(server, times(2)).deleteComment(6);	
		
	}
	
	@Test
	public void saveEditedComment_post_controller_test() throws Exception {
		
		doNothing().when(server).editComment(4, "content4");
		server.editComment(4, "content4");
		
		mockMvc.perform(post("/saveComment")
			.param("commentID", "4")
			.flashAttr("comment", new Comment()))
				.andExpect(redirectedUrl("/blog"))
				.andExpect(view().name("redirect:/blog"));
		
		verify(server, atLeastOnce()).editComment(4, "content4");
		
	}
	

}
