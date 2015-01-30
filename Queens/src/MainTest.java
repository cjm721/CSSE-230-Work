import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the non-attacking chess queens solution finder.
 *
 * @author Curt Clifton
 */
public class MainTest {

	/**
	 * Test method for {@link Main#findSolutions(int)}.
	 */
	@Test
	public void testFindSolutions() {
		testAGivenSize(3, 0);
		testAGivenSize(4, 2);
		testAGivenSize(5, 10);
		testAGivenSize(6, 4);
		testAGivenSize(7, 40);
		testAGivenSize(8, 92);
	}

	private void testAGivenSize(int size, int expected) {
		final String msg = "TESTING BOARD OF SIZE " + size;
		System.out.println("------------------------------");
		System.out.println(msg);
		System.out.println("------------------------------");
		assertEquals(msg, expected, Main.findSolutions(size));
	}

}
