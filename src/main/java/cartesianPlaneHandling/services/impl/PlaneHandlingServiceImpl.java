package cartesianPlaneHandling.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.beans.PointsListBean;
import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;
import cartesianPlaneHandling.services.PlaneHandlingService;

@Service
public class PlaneHandlingServiceImpl implements PlaneHandlingService {
	
	private final PointsListBean pointsList;
	
	
	@Autowired
	public PlaneHandlingServiceImpl(PointsListBean pointsList) {
		this.pointsList = pointsList;
	}

	@Override
	public void addPointToPlane(PlanePointBean pointToAdd) throws AlreadyExistingPointException {
		pointsList.addPointToList(pointToAdd);
	}

	@Override
	public void deleteAllPoints() {
		pointsList.deleteAllPoints();
	}

	@Override
	public List<PlanePointBean> getAllPoints() {
		return pointsList.getPointsList();
	}

	@Override
	public int countTotalPoints() {
		return pointsList.getPointsList().size();
	}

}
