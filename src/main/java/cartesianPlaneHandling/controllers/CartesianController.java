package cartesianPlaneHandling.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.beans.PointsListBean;
import cartesianPlaneHandling.beans.SegmentBean;
import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;
import cartesianPlaneHandling.services.PlaneHandlingService;

@Controller
@RequestMapping(value = "/")
public class CartesianController {
	private static final Logger log = LoggerFactory.getLogger(CartesianController.class);

	@Autowired
	private PlaneHandlingService planeService;

	@RequestMapping(value = "point", method = RequestMethod.POST)
	public ResponseEntity<String> addPointToPlane(@RequestBody PlanePointBean pointToAdd){
		try {
			planeService.addPointToPlane(pointToAdd);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(AlreadyExistingPointException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.ALREADY_REPORTED);
		}
	}

	@RequestMapping(value = "space", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeAllPoints() {
		int pointsCount = planeService.countTotalPoints();
		planeService.deleteAllPoints();
		String deleteMessage = "Deleted " + pointsCount + " points.";
		log.info(deleteMessage);
		return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
	}

	@RequestMapping(value = "space", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlanePointBean>> getPoints() {
		return new ResponseEntity<>(planeService.getAllPoints(), HttpStatus.OK);
	}


	@RequestMapping(value = "lines/{n}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegmentBean>> getAllSegmentsPassingThrough(@PathVariable(value = "n") int pointsToBeTouched) {
		List<PointsListBean> collinearPointsList = planeService.getCollinearPointsList(pointsToBeTouched);
		List<SegmentBean> segmentList = planeService.getAllSegments(collinearPointsList);
		return new ResponseEntity<>(segmentList, HttpStatus.OK);
	}
	

	@RequestMapping(value = "allLines/{n}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PointsListBean>> getAllLinesWithCollinearPoints(@PathVariable(value = "n")  int pointsToBeTouched) {
		List<PointsListBean> collinearPointsList = planeService.getCollinearPointsList(pointsToBeTouched);
		return new ResponseEntity<>(collinearPointsList, HttpStatus.OK);
	}

	@RequestMapping(value = "preload", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> loadCartesianPlane() {
		try {
			planeService.addPointToPlane(new PlanePointBean(1,1));
			planeService.addPointToPlane(new PlanePointBean(0,0));
			planeService.addPointToPlane(new PlanePointBean(2,2));
			planeService.addPointToPlane(new PlanePointBean(-1,0));
			planeService.addPointToPlane(new PlanePointBean(-2,0));
			planeService.addPointToPlane(new PlanePointBean(-3,0));
			planeService.addPointToPlane(new PlanePointBean(6,2));
		}
		catch (AlreadyExistingPointException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
