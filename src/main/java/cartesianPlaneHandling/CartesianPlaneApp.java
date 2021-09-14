package cartesianPlaneHandling;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cartesianPlaneHandling.beans.PointsListBean;

@SpringBootApplication
public class CartesianPlaneApp {

	public static void main(String[] args) {
		SpringApplication.run(CartesianPlaneApp.class, args);
	}
	@Bean
	public PointsListBean getPoints() {
		return new PointsListBean(Collections.emptyList());
	}
}