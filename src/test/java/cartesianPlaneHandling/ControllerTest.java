package cartesianPlaneHandling;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cartesianPlaneHandling.beans.PointsListBean;
import cartesianPlaneHandling.controllers.CartesianController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CartesianPlaneApp.class})
@TestPropertySource(locations = "classpath:application.properties")
public class ControllerTest {

	@Autowired
	private PointsListBean testPoints;
	private MockMvc setupHandler;

	@Before
	public void preload_Points() {
		testPoints = mock(PointsListBean.class);
		CartesianController controller = new CartesianController();
		setupHandler = MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void get_points() throws Exception {
		setupHandler.perform(MockMvcRequestBuilders.get("/space")).andExpect(status().is(HttpStatus.OK.value()));
		verify(testPoints).getPointsList();
	}
	@Test
	public void delete_points() throws Exception {
		setupHandler.perform(MockMvcRequestBuilders.delete("/space")).andExpect(status().is(HttpStatus.OK.value()));
		verify(testPoints).deleteAllPoints();
	}
}
