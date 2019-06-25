package mockito.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import task.controllers.ScoreController;
import task.pojo.Triangle;

@RunWith(MockitoJUnitRunner.class)
public class ScoreControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	Triangle triangle;
	
	@InjectMocks
	private ScoreController scoreController;


	@Before
	public void setUp() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(scoreController).setViewResolvers(resolver).build();
	}
	
	@Test
	public void triangle_post_controller_test() throws Exception {
			
		mockMvc.perform(post("/triangle")
			.flashAttr("triangle", new Triangle()))
				.andExpect(model().attributeExists("triangle"))
				.andExpect(model().size(2))
				.andExpect(status().isOk())
				.andExpect(view().name("score"));
		
		triangle.setaParam("a");
		triangle.setbParam("b");
		triangle.setcParam("c");
		
		verify(triangle).setaParam(anyString());
		verify(triangle).setbParam(anyString());
		verify(triangle).setcParam(anyString());
		
			
	}
	
	@Test
	public void back_to_trianglecalculator() throws Exception {
		
		mockMvc.perform(get("/backToTriangleData"))
			.andExpect(redirectedUrl("/triangle"))
			.andExpect(view().name("redirect:/triangle"));
		
	}

}
