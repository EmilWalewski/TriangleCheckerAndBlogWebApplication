package mockito.tests;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import task.controllers.TriangleController;
import task.pojo.Triangle;

@RunWith(MockitoJUnitRunner.class)
public class TriangleControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	Triangle triangle;

	@InjectMocks
	private TriangleController triangleController;


	@Before
	public void setUp() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(triangleController).setViewResolvers(resolver).build();
	}
	
	@Test
	public void triangle_get_controller_test() throws Exception {
		
		mockMvc.perform(get("/triangle"))
			.andExpect(model().attributeExists("tri"))
			.andExpect(model().size(1))
			.andExpect(model().attribute("tri", triangle))
			.andExpect(status().isOk())
			.andExpect(view().name("triangle"));

			
	}
	
	


}
