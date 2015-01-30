package graphs;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link RandomGraph}.
 * 
 * @author Curt Clifton and Matt Boutell
 */
public class RandomGraphTest {

	private RandomGraph twoWithoutEdges;
	private RandomGraph twoWithEdge;
	private RandomGraph fourWithoutEdges;
	private RandomGraph fourWithOneEdge;
	private RandomGraph fourWithTwoEdgesA;
	private RandomGraph fourWithTwoEdgesB;
	private RandomGraph fourWithThreeEdgesA;
	private RandomGraph fourWithThreeEdgesB;
	private RandomGraph fourWithThreeEdgesC;
	private RandomGraph fourWithThreeEdgesD;
	private RandomGraph fourWithSixEdges;

	private static int points = 0;
	
	/**
	 * Sets up some test graphs.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.twoWithoutEdges = new RandomGraph(2);
		this.twoWithEdge = new RandomGraph(2, new int[][] { { 0, 1 } });
		this.fourWithoutEdges = new RandomGraph(4);
		this.fourWithOneEdge = new RandomGraph(4, new int[][] { { 1, 2 } });
		this.fourWithTwoEdgesA = new RandomGraph(4, new int[][] { { 0, 3 }, { 1, 2 } });
		this.fourWithTwoEdgesB = new RandomGraph(4, new int[][] { { 0, 3 }, { 0, 2 } });
		this.fourWithThreeEdgesA = new RandomGraph(4, new int[][] { { 0, 3 },
				{ 1, 2 }, { 2, 3 } });
		this.fourWithThreeEdgesB = new RandomGraph(4, new int[][] { { 0, 3 },
				{ 1, 2 }, { 3, 2 } });
		this.fourWithThreeEdgesC = new RandomGraph(4, new int[][] { { 0, 1 },
				{ 0, 2 }, { 0, 3 } });
		this.fourWithThreeEdgesD = new RandomGraph(4, new int[][] { { 0, 1 },
				{ 1, 2 }, { 0, 2 } });
		this.fourWithSixEdges = new RandomGraph(4, new int[][] { { 0, 1 },
				{ 0, 2 }, { 0, 3 }, { 2, 3 }, { 1, 2 }, {1,3} });
	}

	/** Test */
	@Test
	public void testHasPathFromAtoBUnconnectedGraphs() {
		assertFalse(this.twoWithoutEdges.pathExists(0, 1));
		assertFalse(this.fourWithoutEdges.pathExists(1, 3));
		assertTrue(this.twoWithoutEdges.pathExists(1, 1));
		assertTrue(this.fourWithoutEdges.pathExists(2, 2));
		points += 5;
	}
	
	
	
	/** Test */
	@Test
	public void testHasPathFromAtoBGraphsWithFewEdges() {
		assertTrue(this.fourWithOneEdge.pathExists(1, 2));
		assertFalse(this.fourWithOneEdge.pathExists(0, 2));
		assertFalse(this.fourWithOneEdge.pathExists(0, 3));
		assertFalse(this.fourWithOneEdge.pathExists(1, 3));
		points += 5;
	}
	
	/** Test */
	@Test
	public void testHasPathFromAtoBGraphs3() {
		assertTrue(this.twoWithEdge.pathExists(0, 1));
		assertTrue(this.twoWithEdge.pathExists(1, 0));

		assertTrue(this.fourWithTwoEdgesA.pathExists(1, 2));
		assertTrue(this.fourWithTwoEdgesA.pathExists(0, 3));
		assertFalse(this.fourWithTwoEdgesA.pathExists(1, 3));
		assertFalse(this.fourWithTwoEdgesA.pathExists(0, 2));

		assertTrue(this.fourWithTwoEdgesB.pathExists(0, 2));
		assertTrue(this.fourWithTwoEdgesB.pathExists(0, 3));
		assertTrue(this.fourWithTwoEdgesB.pathExists(2, 3));
		assertFalse(this.fourWithTwoEdgesB.pathExists(1, 2));
	
		assertTrue(this.fourWithThreeEdgesA.pathExists(0, 1));
		assertTrue(this.fourWithThreeEdgesB.pathExists(1, 2));
		assertTrue(this.fourWithThreeEdgesC.pathExists(1, 2));
		assertFalse(this.fourWithThreeEdgesD.pathExists(1, 3));
		assertTrue(this.fourWithSixEdges.pathExists(0, 3));
		points+= 5;
	}
	
	@AfterClass
	public static void showResults() {
		System.out.printf("***** pathExists:   %d / 15\n", points);
	}
	
}
