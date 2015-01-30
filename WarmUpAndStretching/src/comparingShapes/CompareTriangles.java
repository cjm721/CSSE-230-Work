package comparingShapes;

import java.util.Comparator;


public class CompareTriangles implements Comparator<Triangle> {

	@Override
	public int compare(Triangle arg0, Triangle arg1) {
		return (int) (arg0.area()-arg1.area());
	}

}
