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
	/**
	 * @param pointsArray -> input arrray
	 * @param data-> temporary array for recursion
	 * @param start&end index -> used in recursion to start and terminate iteration in a correct way
	 * 							respectively: start -> 0, incrementing every recursion
	 * 										  end 	-> array length -1
	 * @param dataIndex -> last index for data array, in order to avoid overwriting records
	 * @param combinationCount -> the number of element a combination should provide
	 * @param data-> temporary array for recursion
	 * 
	 * */
	private void loadListRecursively(PlanePointBean pointsArray[], PlanePointBean data[], int startIndex, int endIndex, int dataIndex, int combinationCount){
		if (dataIndex == combinationCount){
			List<PlanePointBean> temporaryList = new ArrayList<>();
			for (int j=0; j<combinationCount; j++) {
				temporaryList.add(data[j]);
			}
			list.add(new PointsListBean(temporaryList));
			return;
		}
		for (int i=startIndex; i<=endIndex && endIndex-i+1 >= combinationCount-dataIndex; i++){
			data[dataIndex] = pointsArray[i];
			loadListRecursively(pointsArray, data, i+1, endIndex, dataIndex+1, combinationCount);
		}
	}
	/**
	 * recursively find every combination to be used as a pool for segments or to be displayed as is
	 * */
	private void iterateForCombination(PlanePointBean arr[], int arrayLength, int combinationCount)
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

