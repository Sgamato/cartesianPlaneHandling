package cartesianPlaneHandling.utils;

import java.util.ArrayList;
import java.util.List;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.beans.PointsListBean;

public class Combinator {
	private static Combinator combinator;
	private static List<PointsListBean> list = new ArrayList<>();
	public Combinator() {}
	public static Combinator getInstance() {
		if(combinator == null) {
			return new Combinator();
		}
		else {
			return combinator;
		}
	}

	protected void loadListRecursively(PlanePointBean pointsArray[], PlanePointBean data[], int startIndex, int endIndex, int dataIndex, int combinationCount)
	{
		// Current combination is ready to be printed, print it
		if (dataIndex == combinationCount)
		{
			List<PlanePointBean> temporaryList = new ArrayList<>();
			for (int j=0; j<combinationCount; j++) {
				temporaryList.add(data[j]);}
			list.add(new PointsListBean(temporaryList));
			return;
		}
		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		for (int i=startIndex; i<=endIndex && endIndex-i+1 >= combinationCount-dataIndex; i++)
		{
			data[dataIndex] = pointsArray[i];
			loadListRecursively(pointsArray, data, i+1, endIndex, dataIndex+1, combinationCount);
		}
	}
	/**
	 * recursively find every combination to be used as a pool for segments or to be displayed as is
	 * */
	protected void iterateForCombination(PlanePointBean arr[], int arrayLength, int combinationCount)
	{
		PlanePointBean data[]=new PlanePointBean[combinationCount];
		loadListRecursively(arr, data, 0, arrayLength-1, 0, combinationCount);
	}

	protected void loadCombinations (PlanePointBean pointsArray[], int arrayLength, int combinationCount ) {
		iterateForCombination(pointsArray, arrayLength, combinationCount);
	}

	protected List<PointsListBean> getList() {
		return list;
	}
	protected void clearCombinations() {
		list.clear();
	}
}

