package cartesianPlaneHandling.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;
import cartesianPlaneHandling.services.PlaneHandlingService;

@Controller
@RequestMapping(value = "/")
public class CartesianController {
	private static final Logger log = LoggerFactory.getLogger(CartesianController.class);
	
	@Autowired
	private PlaneHandlingService planeService;

	@RequestMapping(value = "point", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addPointToPlane(@RequestBody PlanePointBean pointToAdd){
		try {
			planeService.addPointToPlane(pointToAdd);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(AlreadyExistingPointException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@RequestMapping(value = "space", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> removeAllPoints() {
		int pointsCount = planeService.countTotalPoints();
		planeService.deleteAllPoints();
		log.info("Deleted " + pointsCount + " points.");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "space", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlanePointBean>> getPoints() {
		return new ResponseEntity<>(planeService.getAllPoints(), HttpStatus.OK);
	}
}
