package graphs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link RandomGraph}.
 * 
 * @author Curt Clifton
 */
public class RandomGraphTest {

	private RandomGraph twoWithoutEdges;
	private RandomGraph fourWithoutEdges;
	private RandomGraph fourWithOneEdge;
	private RandomGraph fourWithTwoEdgesA;
	private RandomGraph fourWithThreeEdgesA;
	private RandomGraph fourWithThreeEdgesB;
	private RandomGraph fourWithThreeEdgesC;
	private RandomGraph fourWithFourEdges;
	private RandomGraph fourWithTwoEdgesB;
	private RandomGraph twoWithEdge;
	private RandomGraph fourWithSixEdges;

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
		this.fourWithTwoEdgesA = new RandomGraph(4, new int[][] { { 0, 3 },
				{ 1, 2 } });
		this.fourWithTwoEdgesB = new RandomGraph(4, new int[][] { { 0, 3 },
				{ 0, 2 } });
		this.fourWithThreeEdgesA = new RandomGraph(4, new int[][] { { 0, 3 },
				{ 1, 2 }, { 2, 3 } });
		this.fourWithThreeEdgesB = new RandomGraph(4, new int[][] { { 0, 3 },
				{ 1, 2 }, { 3, 2 } });
		this.fourWithThreeEdgesC = new RandomGraph(4, new int[][] { { 0, 1 },
				{ 0, 2 }, { 0, 3 } });
		this.fourWithFourEdges = new RandomGraph(4, new int[][] { { 0, 1 },
				{ 0, 2 }, { 0, 3 }, { 2, 3 } });
		this.fourWithSixEdges = new RandomGraph(4, new int[][] { { 0, 1 },
				{ 0, 2 }, { 0, 3 }, { 2, 3 }, { 1, 2 }, {1,3} });
	}

	/**
	 * Test method for
	 * {@link graphs.RandomGraph#largestConnectedComponentSize()}.
	 */
	@Test(timeout=100)
	public void testLargestConnectedComponentSize() {
		assertEquals(1, this.twoWithoutEdges.largestConnectedComponentSize());
		assertEquals(2, this.twoWithEdge.largestConnectedComponentSize());
		assertEquals(1, this.fourWithoutEdges.largestConnectedComponentSize());
		assertEquals(2, this.fourWithOneEdge.largestConnectedComponentSize());
		assertEquals(2, this.fourWithTwoEdgesA.largestConnectedComponentSize());
		assertEquals(3, this.fourWithTwoEdgesB.largestConnectedComponentSize());
		assertEquals(4, this.fourWithThreeEdgesA
				.largestConnectedComponentSize());
		assertEquals(4, this.fourWithThreeEdgesB
				.largestConnectedComponentSize());
		assertEquals(4, this.fourWithThreeEdgesC
				.largestConnectedComponentSize());
		assertEquals(4, this.fourWithFourEdges.largestConnectedComponentSize());
		assertEquals(4, this.fourWithSixEdges.largestConnectedComponentSize());
	}

	/**
	 * Test method for {@link graphs.RandomGraph#isConnected()}.
	 */
	@Test(timeout=100)
	public void testIsConnected() {
		assertFalse(this.twoWithoutEdges.isConnected());
		assertTrue(this.twoWithEdge.isConnected());
		assertFalse(this.fourWithoutEdges.isConnected());
		assertFalse(this.fourWithOneEdge.isConnected());
		assertFalse(this.fourWithTwoEdgesA.isConnected());
		assertFalse(this.fourWithTwoEdgesB.isConnected());
		assertTrue(this.fourWithThreeEdgesA.isConnected());
		assertTrue(this.fourWithThreeEdgesB.isConnected());
		assertTrue(this.fourWithThreeEdgesC.isConnected());
		assertTrue(this.fourWithFourEdges.isConnected());
		assertTrue(this.fourWithSixEdges.isConnected());
	}

	/**
	 * Test method for {@link graphs.RandomGraph#addRandomEdge()}.
	 */
	@Test(timeout=100)
	public void testAddRandomEdge() {
		this.twoWithoutEdges.addRandomEdge();
		assertEquals(2, this.twoWithoutEdges.largestConnectedComponentSize());

		this.fourWithoutEdges.addRandomEdge();
		assertEquals(2, this.fourWithoutEdges.largestConnectedComponentSize());
		this.fourWithoutEdges.addRandomEdge();
		this.fourWithoutEdges.addRandomEdge();
		this.fourWithoutEdges.addRandomEdge();
		assertEquals(4, this.fourWithoutEdges.largestConnectedComponentSize());
	}

	/**
	 * Test method for {@link graphs.RandomGraph#addRandomEdge()}.
	 */
	@Test(timeout=100, expected = IllegalStateException.class)
	public void testAddRandomEdgeException() {
		this.fourWithSixEdges.addRandomEdge();
	}

	/**
	 * Test method for {@link graphs.RandomGraph#isComplete()}.
	 */
	@Test(timeout=100)
	public void testisComplete() {
		assertFalse(this.twoWithoutEdges.isComplete());
		assertTrue(this.twoWithEdge.isComplete());
		assertFalse(this.fourWithoutEdges.isComplete());
		assertFalse(this.fourWithOneEdge.isComplete());
		assertFalse(this.fourWithTwoEdgesA.isComplete());
		assertFalse(this.fourWithTwoEdgesB.isComplete());
		assertFalse(this.fourWithThreeEdgesA.isComplete());
		assertFalse(this.fourWithThreeEdgesB.isComplete());
		assertFalse(this.fourWithThreeEdgesC.isComplete());
		assertFalse(this.fourWithFourEdges.isComplete());
		assertTrue(this.fourWithSixEdges.isComplete());
	}


	/**
	 * Tests efficiency by making repeated calls to
	 * {@link graphs.RandomGraph#addRandomEdge()} and
	 * {@link graphs.RandomGraph#largestConnectedComponentSize()}.
	 */
	@Test(timeout=10000)
	public void testEfficiency() {
		final int size = 100;
		RandomGraph bigger = new RandomGraph(size);
		final int edges = (size -1)*size / 2;
		long sum = 0;
		for (int i=0; i<edges; i++) {
			sum += bigger.largestConnectedComponentSize();
			bigger.addRandomEdge();
		}
		assertTrue(sum > 0);
	}
}
