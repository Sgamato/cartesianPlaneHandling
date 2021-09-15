package cartesianPlaneHandling.services;

import java.util.List;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.beans.PointsListBean;
import cartesianPlaneHandling.beans.SegmentBean;
import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;

public interface PlaneHandlingService {
	
	public void addPointToPlane(PlanePointBean pointToAdd) throws AlreadyExistingPointException;
	
	public void deleteAllPoints();
	
	public List<PlanePointBean> getAllPoints();

	public int countTotalPoints();

	public List<PointsListBean> getCollinearPointsList(int pointsToBeTouched);

	public List<SegmentBean> getAllSegments(List<PointsListBean> collinearPointsList);
	

}
