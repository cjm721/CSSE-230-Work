package noNullNodeUsed;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements
		Iterable<T> {

	private BinaryNode root;
	private int modcount = 0;

	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean
	 * that each node has exactly one child, except for the leaf. In addition,
	 * the nodes alternate between being a left and a right child. An empty tree
	 * or a tree consisting of just the root are both said to form a zigzag
	 * pattern. The following tree forms a zig-zag pattern:
	 * 
	 * 		10 
	 * 3 
	 * 		8 
	 * 4 	
	 * 		7
	 * 6
	 * 
	 * @return True if the tree forms a zigzag and false otherwise.
	 */

	public boolean isZigZag() {
		// This simple return statement passes many test cases.
		// In order to be awarded points, you must implement the method as
		// described above.
		return false;
	}

	/**
	 * This method counts the number of occurrences of positive Integers in the
	 * tree that is of type Integer.
	 * 
	 * @return The number of positive integers in the tree.
	 */

	public int countPositives() {
		return -17;
	}

	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the
	 * following tree, the method returns the string: "2200110"
	 * 
	 * 				10 
	 * 	5 					15 
	 * 2 7 						18 
	 * 								10
	 * 
	 * @return A string representing the number of children of each node when
	 *         the nodes are visited in pre-order.
	 */

	public String numChildrenOfEachNode() {
		return "NOT DONE";
	}

	public boolean insert(T e) {
		if (e == null)
			throw new IllegalArgumentException();
		if (root == null) {
			root = new BinaryNode(e);
			return true;
		} else
			return root.insert(e);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Iterator<T> iterator() {
		return new PreOrderIterator();
	}

	// /////////////// BinaryNode

	public class BinaryNode {

		public T element;
		public BinaryNode leftChild;
		public BinaryNode rightChild;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;
		}

		public boolean insert(T e) {
			if (e.compareTo(element) < 0) {
				if (leftChild != null) {
					return leftChild.insert(e);
				} else {
					leftChild = new BinaryNode(e);
					modcount++;
					return true;
				}
			}
			if (e.compareTo(element) > 0) {
				if (rightChild != null) {
					return rightChild.insert(e);
				} else {
					rightChild = new BinaryNode(e);
					modcount++;
					return true;
				}
			}
			return false;
		}

	}

	// /////////////////// PreOrderIterator

	private class PreOrderIterator implements Iterator<T> {
		private Stack<BinaryNode> nodes;
		private int savedModCount;

		public PreOrderIterator() {
			nodes = new Stack<BinaryNode>();
			if (root != null) {
				nodes.push(root);
				savedModCount = modcount;
			}
		}

		public boolean hasNext() {
			return !nodes.isEmpty();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			if (savedModCount != modcount)
				throw new ConcurrentModificationException();
			BinaryNode temp = nodes.pop();
			if (temp.rightChild != null)
				nodes.push(temp.rightChild);
			if (temp.leftChild != null)
				nodes.push(temp.leftChild);
			return temp.element;
		}

		public void remove() {
			// TODO Auto-generated method stub
		}

	}

}