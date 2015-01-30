package heaps;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the BinaryNode class.
 *
 * @author Matt Boutell.
 *         Created Feb 23, 2014.
 */
public class HeapArrayTest {
	private static int points = 0;

	static BinaryNode t1, t2, t3, t4, t5, t6, t7, t8, t9;
	
	
	/**
	 * Creates some trees.
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	    t1 = BuildTree.buildTree("", "");     
	    t2 = BuildTree.buildTree("a", "0");  
	    t3 = BuildTree.buildTree("acb", "200"); 
	    t4 = BuildTree.buildTree("bfhje", "22000"); 
	    t5 = BuildTree.buildTree("adfecgh", "2200200");  
	    t6 = BuildTree.buildTree("adfzecgh", "22L00200");  
	    t7 = BuildTree.buildTree("adfecg", "2200L0"); 
	
	}

	/** Test */
	@Test
	public void testT3() {
		String[] array = BinaryNode.buildHeapArrayFromHeapTree(t3);
		points += checkArray(array, 4, "[null, a, c, b]", 3);
	}

	/** Test */
	@Test
	public void testT4() {
		String[] array = BinaryNode.buildHeapArrayFromHeapTree(t4);
		points += checkArray(array, 6, "[null, b, f, e, h, j]", 3);
	}

	/** Test */
	@Test
	public void testT5() {
		String[] array = BinaryNode.buildHeapArrayFromHeapTree(t5);
		points += checkArray(array, 8, "[null, a, d, c, f, e, g, h]", 3);
	}

	/** Test */
	@Test
	public void testT6() {
		String[] array = BinaryNode.buildHeapArrayFromHeapTree(t6);
		points += checkArray(array, 9, "[null, a, d, c, f, e, g, h, z]", 3);
	}

	/** Test */
	@Test
	public void testT7() {
		String[] array = BinaryNode.buildHeapArrayFromHeapTree(t7);
		points += checkArray(array, 7, "[null, a, d, c, f, e, g]", 3);
	}

	
	/** Test */
	@Test
	public void testBuildHeapArrayFromHeapTreeSingleNode() {
		BinaryNode single = new BinaryNode("single", null, null);
		String[] array = BinaryNode.buildHeapArrayFromHeapTree(single);
		points += checkArray(array, 2, "[null, single]", 3);
	}

	/** Test */
	@Test
	public void testBuildHeapArrayFromHeapTreeNullNode() {
		BinaryNode empty = null;
		String[] emptyArray = BinaryNode.buildHeapArrayFromHeapTree(empty);
		points += checkArray(emptyArray, 1, "[null]", 2);
	}

	
	
	// Make method static. Not sure if I like it better or not. But it
	
	private int checkArray(String [] array, int expectedLength, String expectedString, int points) {
		assertEquals(expectedLength, array.length);
		assertEquals(expectedString, Arrays.toString(array));
		// Passes both, so assign points.
		return points;
	}

	@AfterClass
	public static void showResults() {
		System.out.printf("***** HeapArray:    %d / 20\n", points);
	}
}
