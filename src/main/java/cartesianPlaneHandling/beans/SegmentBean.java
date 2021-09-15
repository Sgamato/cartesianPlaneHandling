package cartesianPlaneHandling.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SegmentBean {
	private List<PointsListBean> segment = new ArrayList<>();
	
	public SegmentBean() {
	}
	
	public SegmentBean(List<PointsListBean> segment) {
		this.segment = segment;
	}
	public List<PointsListBean> getSegment() {
		return segment;
	}

	public void setSegment(List<PointsListBean> segment) {
		this.segment = segment;
	}

	public void addSubSegment(PointsListBean pointsListBean) {
		if(!segment.contains(pointsListBean)) {
		segment.add(pointsListBean);
		}
	}

}
