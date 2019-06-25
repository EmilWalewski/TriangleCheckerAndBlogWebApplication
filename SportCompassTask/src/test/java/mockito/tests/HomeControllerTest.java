package mockito.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import task.controllers.HomeController;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	private MockMvc mockMvc;
	
	
	@InjectMocks
	private HomeController homeController;


	@Before
	public void setUp() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(homeController).setViewResolvers(resolver).build();
	}
	
	@Test
	public void home_controller_test() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
           
	}
	
	
	@Test
	public void home_controller_session_invalidate_test() throws Exception {
		mockMvc.perform(get("/home"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
           
	}
	


}

