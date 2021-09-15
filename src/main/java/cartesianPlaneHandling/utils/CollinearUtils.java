package cartesianPlaneHandling.utils;

import java.util.Collections;
import java.util.List;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.SingularOps_DDRM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import cartesianPlaneHandling.beans.PlanePointBean;
import cartesianPlaneHandling.beans.PointsListBean;

public class CollinearUtils {
	private static final Logger log = LoggerFactory.getLogger(CollinearUtils.class);

	/**
	 * By definition, two points are always collinear. 
	 * Three or more points are collinear if and only if their matrix rank is less than or equal 2. 
	 * */
	public static boolean arePointsCollinear(PointsListBean pointsList) {
		Preconditions.checkNotNull(pointsList);
		List<PlanePointBean> points = pointsList.getPointsList();
		Preconditions.checkNotNull(points);
		if (points.size() < 3) {
			return true;
		}
		double[][] pointMatrix = adaptPointListToDoubleMatrix(points);
		//using a matrix library to obtain easily its rank
		return SingularOps_DDRM.rank(new DMatrixRMaj(pointMatrix)) <= 2;
	}
	
	/**
	 * Adapting a PlanePointBean List to a double matrix to fit matrix library  
	 */
	private static double[][] adaptPointListToDoubleMatrix(List<PlanePointBean> pointList) {
		 double[][] convertedMatrix = new double[pointList.size()][pointList.size()];
		 for (int i = 0; i < pointList.size(); i++) {
				loadMatrix(pointList, convertedMatrix, i);
			}
		return convertedMatrix;
	}
	/**
	 * This method loads a matrix
	 * the third column is populated by default with 1 to determine the matrix rank correctly
	 */
	private static void loadMatrix(List<PlanePointBean> pointList, double[][] convertedMatrix, int i) {
		PlanePointBean point = pointList.get(i);
		convertedMatrix[i][0] = point.getX();
		convertedMatrix[i][1] = point.getY();
		convertedMatrix[i][2] = 1;
	}
	/**
	 *Given a point list and a point count, returns every possible combination
	 */
	public static List<PointsListBean> getAllPointsCombinationsFromList(int pointsToBeTouchedCount,
			PointsListBean pointsList) {
		try {
		Combinator.getInstance().clearCombinations();
		Combinator.getInstance().loadCombinations(pointsList.getPointsList().toArray(new PlanePointBean[pointsList.getPointsList().size()]), 
												   pointsList.getPointsList().size(),
												   pointsToBeTouchedCount);
		return Combinator.getInstance().getList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Collections.emptyList();
		}
	}

}
