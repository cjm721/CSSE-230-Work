import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the additional methods added for this assignment.
 * 
 * @author Curt Clifton, based on earlier work by Claude Anderson
 */
public class ThreadedTree2Test {
	private ThreadedBinarySearchTree<String> tree1;
	private ThreadedBinarySearchTree<String> tree2;
	private ThreadedBinarySearchTree<String> tree3;
	private List<String> list1;
	private List<String> list2;
	private List<String> list3;

	/**
	 * Uses {@link ThreadedBinarySearchTree#insert(Comparable)} to create some
	 * test trees.
	 */
	@Before
	public void setUp() {
		String s1 = "a recursive method is a method that either directly "
				+ "or indirectly makes a call to itself";
		this.tree1 = buildTestTree(s1);
		this.list1 = buildTestList(s1);
		String s2 = "Now is the time for all good men to come to the "
				+ "aid of their country";
		this.tree2 = buildTestTree(s2);
		this.list2 = buildTestList(s2);
		String s3 = "It is in vain, sir, to extenuate the matter. "
				+ "Gentlemen may cry, Peace, Peace--but there is no peace. "
				+ "The war is actually begun! The next gale that sweeps from "
				+ "the north will bring to our ears the clash of resounding "
				+ "arms! Our brethren are already in the field! Why stand we "
				+ "here idle? What is it that gentlemen wish? What would they "
				+ "have? Is life so dear, or peace so sweet, as to be purchased "
				+ "at the price of chains and slavery? Forbid it, Almighty God! I "
				+ "know not what course others may take; but as for me, give me "
				+ "liberty or give me death!  -- Patrick Henry";
		this.tree3 = buildTestTree(s3);
		this.list3 = buildTestList(s3);
	}

	private ThreadedBinarySearchTree<String> buildTestTree(String s) {
		ThreadedBinarySearchTree<String> result = new ThreadedBinarySearchTree<String>();
		/*
		 * Uses this set to avoid attempting to insert duplicates. A separate
		 * test checks the duplicate avoidance code:
		 */
		Set<String> alreadyInserted = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(s, " ");
		String current = null;
		while (st.hasMoreTokens()) {
			current = st.nextToken();
			try {
				if (current != null && alreadyInserted.add(current)) {
					result.insert(current);
				}
			} catch (DuplicateItem e) {
				System.out.println("While processing string: " + s);
				System.out.println("Got unexpected duplicate item: " + current);
			}
		}

		return result;
	}

	private List<String> buildTestList(String s) {
		// Uses this set to avoid attempting to insert duplicates:
		Set<String> alreadyInserted = new HashSet<String>();
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s, " ");
		String current = null;
		while (st.hasMoreTokens()) {
			current = st.nextToken();
			if (current != null && alreadyInserted.add(current)) {
				list.add(current);
			}
		}
		Collections.sort(list);
		return list;
	}

	private ThreadedBinaryNode<String> findNode(String x,
			ThreadedBinaryNode<String> n) {
		if (x.compareTo(n.element) < 0) {
			if (n.isLeftThread) {
				// nowhere else to look
				throw new IllegalArgumentException(
						"test method expects to find string");
			} else {
				return findNode(x, n.left);
			}
		} else if (x.compareTo(n.element) > 0) {
			if (n.isRightThread) {
				// nowhere else to look
				throw new IllegalArgumentException(
						"test method expects to find string");
			} else {
				return findNode(x, n.right);
			}
		} else {
			// comparison == 0, so we found it
			return n;
		}
	}

	/**
	 * Displays the traversal results.
	 */
	@Test
	public void testTree1a() {
		System.out.println("tree1 forward:");
		this.tree1.traverseInOrder();
		System.out.println("tree1 backward:");
		this.tree1.traverseInOrderBackwards();
	}

	/**
	 * Some forward tests.
	 */
	@Test
	public void testTree1ForwardA() {
		ThreadedBinaryNode<String> n = findNode("makes", this.tree1.getRoot());

		assertEquals("method", n.inOrderSuccessor().element);
		assertEquals("or", n.inOrderSuccessor().inOrderSuccessor().element);
		assertEquals("recursive", n.inOrderSuccessor().inOrderSuccessor()
				.inOrderSuccessor().element);
	}

	/**
	 * More forward tests.
	 */
	@Test
	public void testTree1ForwardB() {
		ThreadedBinaryNode<String> n = this.tree1.firstInOrderNode();
		Iterator<String> listIter = this.list1.iterator();
		while (n != null) {
			assertTrue("list has fewer items than your traversal", listIter
					.hasNext());
			String nextInList = listIter.next();
			assertEquals(nextInList, n.element);
			n = n.inOrderSuccessor();
		}
		assertFalse("list has more items than your traversal", listIter
				.hasNext());
	}

	/**
	 * Some backward tests.
	 */
	@Test
	public void testTree1BackwardA() {
		ThreadedBinaryNode<String> n = findNode("makes", this.tree1.getRoot());

		assertEquals("itself", n.inOrderPredecessor().element);
		assertEquals("is", n.inOrderPredecessor().inOrderPredecessor().element);
		assertEquals("indirectly", n.inOrderPredecessor().inOrderPredecessor()
				.inOrderPredecessor().element);
	}

	/**
	 * More backward tests.
	 */
	@Test
	public void testTree1BackwardB() {
		ThreadedBinaryNode<String> n = this.tree1.lastInOrderNode();
		ListIterator<String> listIter = this.list1.listIterator(this.list1
				.size());
		while (n != null) {
			assertTrue("list has fewer items than your traversal", listIter
					.hasPrevious());
			String nextInList = listIter.previous();
			assertEquals(nextInList, n.element);
			n = n.inOrderPredecessor();
		}
		assertFalse("list has more items than your traversal", listIter
				.hasPrevious());
	}

	/**
	 * Displays the traversal results.
	 */
	@Test
	public void testTree2a() {
		System.out.println("tree2 forward:");
		this.tree2.traverseInOrder();
		System.out.println("tree2 backward:");
		this.tree2.traverseInOrderBackwards();
	}

	/**
	 * Some forward tests.
	 */
	@Test
	public void testTree2ForwardA() {
		ThreadedBinaryNode<String> n = findNode("is", this.tree2.getRoot());

		assertEquals("men", n.inOrderSuccessor().element);
		assertEquals("of", n.inOrderSuccessor().inOrderSuccessor().element);
		assertEquals("the", n.inOrderSuccessor().inOrderSuccessor()
				.inOrderSuccessor().element);
	}

	/**
	 * More forward tests.
	 */
	@Test
	public void testTree2ForwardB() {
		ThreadedBinaryNode<String> n = this.tree2.firstInOrderNode();
		Iterator<String> listIter = this.list2.iterator();
		while (n != null) {
			assertTrue("list has fewer items than your traversal", listIter
					.hasNext());
			String nextInList = listIter.next();
			assertEquals(nextInList, n.element);
			n = n.inOrderSuccessor();
		}
		assertFalse("list has more items than your traversal", listIter
				.hasNext());
	}

	/**
	 * Some backward tests.
	 */
	@Test
	public void testTree2BackwardA() {
		ThreadedBinaryNode<String> n = findNode("is", this.tree2.getRoot());

		assertEquals("good", n.inOrderPredecessor().element);
		assertEquals("for", n.inOrderPredecessor().inOrderPredecessor().element);
		assertEquals("country", n.inOrderPredecessor().inOrderPredecessor()
				.inOrderPredecessor().element);
	}

	/**
	 * More backward tests.
	 */
	@Test
	public void testTree2BackwardB() {
		ThreadedBinaryNode<String> n = this.tree2.lastInOrderNode();
		ListIterator<String> listIter = this.list2.listIterator(this.list2
				.size());
		while (n != null) {
			assertTrue("list has fewer items than your traversal", listIter
					.hasPrevious());
			String nextInList = listIter.previous();
			assertEquals(nextInList, n.element);
			n = n.inOrderPredecessor();
		}
		assertFalse("list has more items than your traversal", listIter
				.hasPrevious());
	}

	/**
	 * Displays the traversal results.
	 */
	@Test
	public void testTree3a() {
		System.out.println("tree3 forward:");
		this.tree3.traverseInOrder();
		System.out.println("tree3 backward:");
		this.tree3.traverseInOrderBackwards();
	}

	/**
	 * Some forward tests.
	 */
	@Test
	public void testTree3ForwardA() {
		ThreadedBinaryNode<String> n = findNode("but", this.tree3.getRoot());

		assertEquals("chains", n.inOrderSuccessor().element);
		assertEquals("clash",
				n.inOrderSuccessor().inOrderSuccessor().element);
		assertEquals("course", n.inOrderSuccessor().inOrderSuccessor()
				.inOrderSuccessor().element);

	}

	/**
	 * More forward tests.
	 */
	@Test
	public void testTree3ForwardB() {
		ThreadedBinaryNode<String> n = this.tree3.firstInOrderNode();
		Iterator<String> listIter = this.list3.iterator();
		while (n != null) {
			assertTrue("list has fewer items than your traversal", listIter
					.hasNext());
			String nextInList = listIter.next();
			assertEquals(nextInList, n.element);
			n = n.inOrderSuccessor();
		}
		assertFalse("list has more items than your traversal", listIter
				.hasNext());
	}

	/**
	 * Some backward tests.
	 */
	@Test
	public void testTree3BackwardA() {
		ThreadedBinaryNode<String> n = findNode("but", this.tree3.getRoot());

		assertEquals("bring", n.inOrderPredecessor().element);
		assertEquals("brethren",
				n.inOrderPredecessor().inOrderPredecessor().element);
		assertEquals("begun!", n.inOrderPredecessor().inOrderPredecessor()
				.inOrderPredecessor().element);
	}

	/**
	 * More backward tests.
	 */
	@Test
	public void testTree3BackwardB() {
		ThreadedBinaryNode<String> n = this.tree3.lastInOrderNode();
		ListIterator<String> listIter = this.list3.listIterator(this.list3
				.size());
		while (n != null) {
			assertTrue("list has fewer items than your traversal", listIter
					.hasPrevious());
			String nextInList = listIter.previous();
			assertEquals(nextInList, n.element);
			n = n.inOrderPredecessor();
		}
		assertFalse("list has more items than your traversal", listIter
				.hasPrevious());
	}
}
