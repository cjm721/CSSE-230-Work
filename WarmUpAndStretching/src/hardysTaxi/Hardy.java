package hardysTaxi;

import java.util.List;
import java.util.ArrayList;

import static hardysTaxi.TaxicabNumber.cube;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all
 * "taxicab numbers" that are less than n.  These are represented as TaxicabNumber objects:
 * each includes the sum and two different ways of writing that sum as
 * a sum of two cubes.
 * 
 * @author anderson  November, 2010.
 *
 */
public class Hardy {
	
	/**
	 * Returns floor of the cube root of n.
	 * Can you see why this method is useful for this problem?
	 * This is not a very efficient implementation.  
	 * (Not required) Can you think of a more efficient approach?
	 * 
	 * @param n a positive integer
	 * @return integer cube root of n
	 */
	public static int cubeRootFloor(int n){
		// Very inefficient, but quick to write.
		int i=0;
		while (cube(i) <= n)
			i++;
		return i-1; 
	}
		
	/**
	 * Computes and returns a sorted list of all taxicab numbers less than n.
	 * @param n a positive integer
	 * @return a List<TaxicabNumber>  object.  Each object contains the sum and two different ways to write it as a sum of cubes.
	 */
	
	public static List<TaxicabNumber> hardySolutionsLessThan(int n) {
		List<TaxicabNumber> result = new ArrayList<TaxicabNumber>();  // You are to populate this list.

		int i; 
		int j, k = 0;
		
		i = 1;
		while(i < n) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			//Cube Root
			int number = (int)Math.pow(i, 1/3.0);
			
			for (j=1; j<=number; j++) {
				for(k=j+1; k<=number; k++) {
					if(j*j*j+k*k*k == i){
						list.add(j);
						list.add(k);
					}
				}
			}
			
			if(list.size() == 4) {
				result.add(new TaxicabNumber(i, list.remove(0), list.remove(0), list.remove(0), list.remove(0)));
			}else{
				list.clear();
			}
			i++;
		}

		java.util.Collections.sort(result);
		return result;
	}
	
	/**
	 * main() is provided in case you want to test your code other than 
	 * with unit tests. Just put try various arguments in the method call below.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(hardySolutionsLessThan(1730));
	}

}
