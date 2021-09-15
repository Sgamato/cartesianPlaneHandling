package cartesianPlaneHandling.beans;

import java.util.ArrayList;
import java.util.List;

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
		segment.add(pointsListBean);
	}
}
