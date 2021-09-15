package cartesianPlaneHandling.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartesianPlaneHandling.beans.PointsListBean;
import cartesianPlaneHandling.beans.SegmentBean;

public class SegmentUtils {

	
	/**
	 * given a set of collinear subsegments that pass through at least N points, this function will return every segment possible
	 * to obtain a segment, a couple of subsegment should share a point in common. 
	 * */
	public static List<SegmentBean> getAllSegmentsFromCollinearList(List<PointsListBean> collinearPointsList) {
		List<SegmentBean> segmentList = new ArrayList<>();

		SegmentBean segment = new SegmentBean();
		for(int i = 0; i< collinearPointsList.size();i++) {
			for(int j = 0; j< collinearPointsList.size();j++) {
				if(!Collections.disjoint(collinearPointsList.get(i).getPointsList(), collinearPointsList.get(j).getPointsList()) && 
						!collinearPointsList.get(i).getPointsList().equals(collinearPointsList.get(j).getPointsList())) {
					segment.addSubSegment(collinearPointsList.get(i));
				}	
			}
			segmentList.add(segment);
		}

		return segmentList;
	}
}