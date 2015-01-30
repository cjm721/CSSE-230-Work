package heaps;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * A simple BinaryNode class.
 * 
 * @author Matt Boutell. Created Feb 23, 2014.
 */
public class BinaryNode {
	/** Data for this node */
	String element;

	/** Left child */
	BinaryNode left;

	/** Right child */
	BinaryNode right;

	/**
	 * Creates a binary node from the given arguments.
	 * 
	 * @param element
	 * @param left
	 * @param right
	 */
	public BinaryNode(String element, BinaryNode left, BinaryNode right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return (this.left == null ? "" : this.left.toString()) + this.element
				+ (this.right == null ? "" : this.right.toString());
	}

	/**
	 * 
	 * Returns the number of nodes in the tree rooted at this node.
	 * 
	 * @param node
	 * @return The size of the subtree rooted at the given node.
	 */
	public static int size(BinaryNode node) {
		if (node == null) {
			return 0;
		}

		return size(node.left) + size(node.right) + 1;
	}

	/**
	 * Takes a binary tree rooted at the given node and returns an array that
	 * contains the elements of the heap tree in the order they would appear in
	 * the array representation of the heap. (This includes having null for the
	 * first element.)
	 * @param node The root of the tree.
	 * @return The array representation of this heap.
	 */
	public static String[] buildHeapArrayFromHeapTree(BinaryNode node) {
		ArrayList<String> baseArray = new ArrayList<String>();
		
		LinkedList<BinaryNode> nodeList = new LinkedList<BinaryNode>();
		
		nodeList.add(node);
		
		while(!nodeList.isEmpty()){
			BinaryNode tempNode = nodeList.poll();
			
			if(tempNode == null)
				continue;
			
			baseArray.add(tempNode.element);
			
			nodeList.offer(tempNode.left);
			nodeList.offer(tempNode.right);
		}
		String[] toReturn = new String[baseArray.size()+1];
		for(int i = 1; i < toReturn.length; i++)
			toReturn[i] = baseArray.get(i-1);
		
		return toReturn;
	}
}
