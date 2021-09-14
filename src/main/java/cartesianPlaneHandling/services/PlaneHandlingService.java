package cartesianPlaneHandling.services;

import java.util.List;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;

public interface PlaneHandlingService {
	
	public void addPointToPlane(PlanePointBean pointToAdd) throws AlreadyExistingPointException;
	
	public void deleteAllPoints();
	
	public List<PlanePointBean> getAllPoints();

	public int countTotalPoints();
	

}
