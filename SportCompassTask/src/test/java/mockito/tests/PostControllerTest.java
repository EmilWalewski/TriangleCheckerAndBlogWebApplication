package mockito.tests;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import task.controllers.PostController;
import task.pojo.Post;
import task.service.Server;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private PostController postController;
	
	@Mock
	Server server;
	
	@Mock
	Post post;
	
	
	@Before
	public void setUp() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(postController).setViewResolvers(resolver).build();
	}

	@Test
	public void edit_get_controller_test() throws Exception {
		
		mockMvc.perform(get("/edit")
			.param("id", "6"))
				.andExpect(redirectedUrl("addPost"))
				.andExpect(view().name("redirect:addPost"));
	}
	
	@Test
	public void delete_get_controller_test() throws Exception {
		
		
		doNothing().when(server).deletePost(7);
		
		server.deletePost(7);
		
		mockMvc.perform(get("/delete")
			.param("id", "7"))
				.andExpect(redirectedUrl("/blog"))
				.andExpect(view().name("redirect:/blog"));
		
		verify(server, times(2)).deletePost(7);
	}
	
	@Test
	public void addPost_get_controller_test() throws Exception {
		
		
		mockMvc.perform(get("/addPost"))
				.andExpect(model().attributeExists("post"))
				.andExpect(model().size(1))
				.andExpect(model().attribute("post", post))
				.andExpect(status().isOk())
				.andExpect(view().name("addPost"));
	}
	
	@Test
	public void addPost_post_controller_test() throws Exception {
		
		
		mockMvc.perform(post("/addPost")
			.flashAttr("post", post))
				.andExpect(model().attributeExists("sessionPostMessage"))
				.andExpect(model().size(2))
				.andExpect(model().attribute("sessionPostMessage", post))
				.andExpect(redirectedUrl("/content"))
				.andExpect(view().name("redirect:/content"));
	}
	
	
}
