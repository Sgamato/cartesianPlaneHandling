package cartesianPlaneHandling.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.beans.PointsListBean;
import cartesianPlaneHandling.beans.SegmentBean;
import cartesianPlaneHandling.exceptions.AlreadyExistingPointException;
import cartesianPlaneHandling.services.PlaneHandlingService;
import cartesianPlaneHandling.utils.CollinearUtils;
import cartesianPlaneHandling.utils.SegmentUtils;

@Service
public class PlaneHandlingServiceImpl implements PlaneHandlingService {
	
	private final PointsListBean pointsList;
	
	@Autowired
	public PlaneHandlingServiceImpl(PointsListBean pointsList) {
		this.pointsList = pointsList;
	}
	
	@Override
	public List<PointsListBean> getCollinearPointsList(int pointsToBeTouchedCount) {
		List<PointsListBean> collinearPointsList = new ArrayList<>();
		List<PointsListBean> pointscombinations =
				CollinearUtils.getAllPointsCombinationsFromList(pointsToBeTouchedCount, pointsList);
		for (PointsListBean points : pointscombinations) {
			if (CollinearUtils.arePointsCollinear(points)) {
				collinearPointsList.add(points);
			}
		}
		return collinearPointsList;
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

	@Override
	public List<SegmentBean> getAllSegments(List<PointsListBean> collinearPointsList) {
		List<SegmentBean> segmentList = new ArrayList<>();
		segmentList = SegmentUtils.getAllSegmentsFromCollinearList(collinearPointsList);
		return segmentList;
	}

}
