package cartesianPlaneHandling.beans;

import java.util.ArrayList;
import java.util.List;

import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;

public class PointsListBean {
	private final List<PlanePointBean> pointList = new ArrayList<>();

	public PointsListBean(List<PlanePointBean> list) {
		pointList.addAll(list);
	}
	
	public void addPointToList(PlanePointBean pointToAdd) throws AlreadyExistingPointException {
		if (!pointList.contains(pointToAdd)) {
			pointList.add(pointToAdd);
		} else {
			throw new AlreadyExistingPointException("Point ("+ pointToAdd.getX()+","+pointToAdd.getY()+") already exists.");
		}
	}
	
	public List<PlanePointBean> getPointsList() {
		return pointList;
	}

	public void deleteAllPoints() {
		pointList.clear();
	}
}
