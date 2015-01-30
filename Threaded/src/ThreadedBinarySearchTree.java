/**
 * TODO Put here a description of what this class does.
 * 
 * @author <TODO: your username>.
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
		// System.out.println("In insert(" + x + t + ")");
		if (x.compareTo(t.element) < 0) {
			if (t.isLeftThread) {
				// nowhere else to look
				t.left = new ThreadedBinaryNode<T>(x, t.left, t, true, true);
				t.isLeftThread = false;
			} else {
				insert(x, t.left);
			}
		} else if (x.compareTo(t.element) > 0) {
			if (t.isRightThread) {
				// nowhere else to look
				t.right = new ThreadedBinaryNode<T>(x, t, t.right, true, true);
				t.isRightThread = false;
			} else {
				insert(x, t.right);
			}
		} else {
			// comparison == 0, so we found it
			throw new DuplicateItem();
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

		while(t != null){
			result.append(t.toString() + "\n");
			if(t.isRightThread){
				t = t.right;
			}else{
				t = t.right;
				while (!t.isLeftThread)
					t = t.left;
			}
		}
		
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

		while(t != null){
			result.append(t.toString() + "\n");
			if(t.isLeftThread){
				t = t.left;
			}else{
				t = t.left;
				while (!t.isRightThread)
					t = t.right;
			}
		}
		
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

}
