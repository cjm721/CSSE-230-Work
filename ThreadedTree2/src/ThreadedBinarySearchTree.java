/**
 * This class implements a binary search tree, with additional features to make
 * some traversal operations more efficient.
 * 
 * @author millerc5.
 * @param <T>
 *            the type of stored elements
 */
public class ThreadedBinarySearchTree<T extends Comparable<? super T>> {

	// The only instance variable; do not add others.
	private ThreadedBinaryNode<T> root;

	/**
	 * Constructs a new empty threaded binary search tree.
	 */
	public ThreadedBinarySearchTree() {
		this.root = null;
	}

	/**
	 * Package-protected method to testing.
	 *
	 * @return the root node of this tree, or null if the tree is empty
	 */
	ThreadedBinaryNode<T> getRoot() {
		return this.root;
	}
	
	/**
	 * Inserts a node containing x into its proper position in the tree. No
	 * balancing is required. Updates left and right thread information
	 * properly. Throws the appropriate exception is the user attempts to insert
	 * a duplicate item.
	 * 
	 * @param x
	 * @throws DuplicateItem
	 *             if item is already in tree
	 */
	public void insert(T x) throws DuplicateItem {
		if (this.root == null)
			this.root = new ThreadedBinaryNode<T>(x);
		else
			insert(x, this.root);
	}

	/**
	 * Inserts the given item, x, into the given tree, t.
	 * 
	 * @param x
	 * @param t
	 * @throws DuplicateItem
	 *             if the item is already in the tree
	 */
	private void insert(T x, ThreadedBinaryNode<T> t) throws DuplicateItem {
		int comp = x.compareTo(t.element);
		if (comp == 0) {
			throw new DuplicateItem(x.toString());
		} else if (comp < 0) {
			// go left young man!
			if (t.isLeftThread) {
				ThreadedBinaryNode<T> newNode = new ThreadedBinaryNode<T>(x);
				newNode.right = t;
				newNode.left = t.left;
				t.left = newNode;
				t.isLeftThread = false;
			} else {
				insert(x, t.left);
			}
		} else {
			// right it is
			if (t.isRightThread) {
				ThreadedBinaryNode<T> newNode = new ThreadedBinaryNode<T>(x);
				newNode.left = t;
				newNode.right = t.right;
				t.right = newNode;
				t.isRightThread = false;
			} else {
				insert(x, t.right);
			}
		}
	}

	/**
	 * Returns a string representing the nodes in an in-order traversal of this
	 * tree. The text for each individual node is formed by calling the
	 * toString() method of the appropriate ThreadedBinaryNode object. In the
	 * result text, the text for each individual node is followed by a newline
	 * character, '\n'.
	 * 
	 * Apart from a StringBuilder object that is only appended to, the
	 * implementation may not use a stack, recursion, or extra storage space for
	 * more than a constant number of node contents.
	 * 
	 * @return a string representing the nodes in an in-order traversal of this
	 *         tree
	 */
	public String toStringInOrder() {
		ThreadedBinaryNode<T> t = this.root;
		if (t == null)
			return ""; // print nothing if tree is empty.

		while (!t.isLeftThread)
			t = t.left; // We now have the first in-order node.

		StringBuilder result = new StringBuilder();

		do {
			result.append(t);
			result.append('\n');
			boolean realLink = !t.isRightThread;
			t = t.right;
			if (realLink) {
				// followed a real right link, so need to slide left
				while (!t.isLeftThread) {
					t = t.left;
				}
			}
		} while (t != null);

		return result.toString();
	}

	/**
	 * Returns a string representing the nodes in a reverse in-order traversal
	 * of this tree. The text for each individual node is formed by calling the
	 * toString() method of the appropriate ThreadedBinaryNode object. In the
	 * result text, the text for each individual node is followed by a newline
	 * character, '\n'.
	 * 
	 * Apart from a StringBuilder object that is only appended to, the
	 * implementation may not use a stack, recursion, or extra storage space for
	 * more than a constant number of node contents.
	 * 
	 * @return a string representing the nodes in an in-order traversal of this
	 *         tree
	 */
	public String toStringInReverseOrder() {
		ThreadedBinaryNode<T> t = this.root;
		if (t == null)
			return ""; // print nothing if tree is empty.

		while (!t.isRightThread)
			t = t.right; // We now have the last in-order node.

		StringBuilder result = new StringBuilder();

		do {
			result.append(t);
			result.append('\n');
			boolean realLink = !t.isLeftThread;
			t = t.left;
			if (realLink) {
				// followed a real left link, so need to slide right
				while (!t.isRightThread) {
					t = t.right;
				}
			}
		} while (t != null);

		return result.toString();
	}

	// You don't really need to do anything with the following code.
	// It is simply here to give you some insight into working with
	// threaded binary trees.

	/**
	 * Searches this tree for the given item.
	 * 
	 * @param x
	 * @return true iff the item is found
	 */
	public boolean find(T x) {
		if (this.root == null) {
			return false;
		} else {
			return find(x, this.root);
		}
	}

	private boolean find(T x, ThreadedBinaryNode<T> t) {
		if (x.compareTo(t.element) < 0) {
			if (t.isLeftThread) {
				// nowhere else to look
				return false;
			} else {
				return find(x, t.left);
			}
		} else if (x.compareTo(t.element) > 0) {
			if (t.isRightThread) {
				// nowhere else to look
				return false;
			} else {
				return find(x, t.right);
			}
		} else {
			// comparison == 0, so we found it
			return true;
		}
	}

	// The following two methods are only here to show how the
	// four methods you are to write could be used.

	/**
	 * Prints each node in an in-order traversal of this tree.
	 */
	public void traverseInOrder() {
		ThreadedBinaryNode<T> t = firstInOrderNode();
		while (t != null) {
			System.out.println(t);
			t = t.inOrderSuccessor();
		}
	}

	/**
	 * Prints each node in a reverse in-order traversal of this tree.
	 */
	public void traverseInOrderBackwards() {
		ThreadedBinaryNode<T> t = lastInOrderNode();
		while (t != null) {
			System.out.println(t);
			t = t.inOrderPredecessor();
		}
	}

	/**
	 * @return a reference to the first node in an in-order traversal of this
	 *         tree
	 */
	public ThreadedBinaryNode<T> firstInOrderNode() {
		ThreadedBinaryNode<T> node = this.root;
		while(!node.isLeftThread) node = node.left;
		return node;
	}

	/**
	 * @return a reference to the last node in an in-order traversal of this
	 *         tree
	 */
	public ThreadedBinaryNode<T> lastInOrderNode() {
		ThreadedBinaryNode<T> node = this.root;
		while(!node.isRightThread) node = node.right;
		return node;
	}

}
