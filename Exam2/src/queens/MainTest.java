package queens;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;


/**
 * Tests the non-attacking chess queens solution finder.
 *
 * @author Curt Clifton
 */
public class MainTest {
	
	public static int points = 0;

	
	@Test
	public void testFindPermutations3() {
		String[] result = Main.findPermutations(3);
		String resultString = Main.permsToString(result);
		System.out.println("findPermutation(3)");
		System.out.println(resultString);
		assertEquals("{123, 132, 213, 231, 312, 321}", resultString);
		points += 8;
	}
	
	@Test
	public void testFindPermutations4() {
		String[] result = Main.findPermutations(4);
		String resultString = Main.permsToString(result);
		System.out.println("findPermutation(4)");
		System.out.println(resultString);
		assertEquals("{1234, 1243, 1324, 1342, 1423, 1432, 2134, 2143, 2314, 2341", 
				resultString.substring(0, 59));
		assertEquals(24, result.length);
		assertEquals("4312", result[22]);
		points += 8;
	}
	
	@Test
	public void testFindPermutations5() {
		String[] result = Main.findPermutations(5);
		String resultString = Main.permsToString(result);
		System.out.println("findPermutation(5)");
		System.out.println(resultString);
		assertEquals(120, result.length);
		assertEquals("13542", result[11]);
		assertEquals("15432", result[23]);
		assertEquals("53214", result[110]);
		points += 8;
	}
	
	@AfterClass
	public static void summary() {
		System.out.println("------------------------------------------------------");
		System.out.printf("#3 QUEENS/PERMUTATIONS earned %d/24 points\n", points);
		System.out.println("------------------------------------------------------");
		System.out.println();
	}


	
	
}
