package nullNodeUsed;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> 
			 implements Iterable<T> {

	private BinaryNode root;
	private int modcount = 0;
	
	private final BinaryNode NULL_NODE = new BinaryNode(null);
	
	public BinarySearchTree(){
		root = NULL_NODE;
		modcount = 0;
	}
	
	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean that 
	 * each node has exactly one child, except for the leaf. In addition, the nodes 
	 * alternate between being a left and a right child. An empty tree or a tree 
	 * consisting of just the root are both said to form a zigzag pattern. The 
	 * following tree forms a zig-zag pattern:
	 *  
	 * 					10
	 * 				 3
	 *  			    8
	 *               4
	 *               	7
	 *               6
	 *               
	 * @return True if the tree forms a zigzag and false otherwise.
	 */
	
	public boolean isZigZag(){
		// This simple return statement passes many test cases.
		// In order to be awarded points, you must implement the method as
		// described above.
		// TODO: implement
		return this.root.isZigZag(-1);
		
	}
	
	/**
	 * This method counts the number of occurrences of 
	 * positive Integers in the tree that is of type Integer.
	 * 
	 * @return The number of positive integers in the tree.
	 */
	
	public int countPositives(){
		if(this.root == this.NULL_NODE)
			return 0;
		if(this.root.element instanceof Integer){
			return this.root.countPositives();
		}
		return 0;
	}
	
	
	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the following
	 * tree, the method returns the string: "2200110"
	 * 
	 * 				10
	 *         5          15
	 *      2     7          18
	 *                          10 
	 * 
	 * @return A string representing the number of children of each node
	 * when the nodes are visited in pre-order.
	 */
	
	public String numChildrenOfEachNode(){
		if(this.root == this.NULL_NODE)
			return "";
		StringBuilder b = new StringBuilder();
		this.root.numChildrenOfEachNode(b);
		return b.toString();
	}
	
	// A slightly different implementation than we did in class. 
	public boolean insert(T e){
		if (e == null) throw new IllegalArgumentException();
		if (root == NULL_NODE) {
			root = new BinaryNode(e);
			return true;
		} else {
			return root.insert(e);
		}
	}
	
	public boolean isEmpty(){
		return root == NULL_NODE;
	}

	@Override
	public Iterator<T> iterator() {
		return new PreOrderIterator();
	}
	
///////////////// BinaryNode 

	public class BinaryNode {
		
		public T element;
		public BinaryNode leftChild;
		public BinaryNode rightChild;
		
		public BinaryNode(T element){
			this.element = element;
			this.leftChild = NULL_NODE;
			this.rightChild = NULL_NODE;		
		}
		

		private boolean isZigZag(int i) {
			if(this == NULL_NODE)
				return true;
			switch (i){
			case -1: //Starting case, Only send to root
				if(leftChild == rightChild)
					return true;
				if(leftChild == NULL_NODE)
					return rightChild.isZigZag(1);
				if(rightChild != NULL_NODE){
					return false;
				}else{
					return leftChild.isZigZag(0);
				}
				
			case 0: // last Element was to the left
				if(leftChild != NULL_NODE)
					return false;
				return rightChild.isZigZag(1);
				
			case 1: // last element was to the right
				if(rightChild != NULL_NODE)
					return false;
				return leftChild.isZigZag(0);
			}
			return false;
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param b
		 * @return
		 */
		public void numChildrenOfEachNode(StringBuilder b) {
			if(this == NULL_NODE) 
				return;
			else{
				int num = 0;
				if(this.leftChild != NULL_NODE){
					num++;
				}
				if(this.rightChild != NULL_NODE){
					num++;
				}
				b.append(num);
				this.leftChild.numChildrenOfEachNode(b);
				this.rightChild.numChildrenOfEachNode(b);
			}
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public int countPositives() {
			if(this == NULL_NODE)
				return 0;
			Integer number = (Integer)this.element;
			if(number.compareTo(new Integer(0)) <= 0){
				return this.rightChild.countPositives();
			}else{
				return 1 + this.leftChild.countPositives() + this.rightChild.countPositives();
			}
		}

		public boolean insert(T e){
			if (e.compareTo(element) < 0) {
				if (leftChild != NULL_NODE){
					return leftChild.insert(e);
				} else {
					leftChild = new BinaryNode(e);
					modcount++;
					return true;
				}
			}
			if (e.compareTo(element) > 0) {
				if (rightChild != NULL_NODE){
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

///////////////////// PreOrderIterator
	
	private class PreOrderIterator implements Iterator<T> {
		private Stack<BinaryNode> nodes;
		private int savedModCount;

		public PreOrderIterator(){
			nodes = new Stack<BinaryNode>();
			if (root != NULL_NODE) {
				nodes.push(root);
				savedModCount = modcount;
			}
		}
		@Override
		public boolean hasNext() {
			return !nodes.isEmpty();
		}
		
		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (savedModCount != modcount) throw new ConcurrentModificationException();
			BinaryNode temp = nodes.pop();
			if (temp.rightChild != NULL_NODE) nodes.push(temp.rightChild);
			if (temp.leftChild != NULL_NODE) nodes.push(temp.leftChild);
			return temp.element;
		}

		@Override
		public void remove() {
			// Not implemented	
		}

	}

}