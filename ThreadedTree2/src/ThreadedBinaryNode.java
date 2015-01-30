/**
 * A node of a threaded binary tree.
 * 
 * @author Claude Anderson
 * @param <T>
 */
class ThreadedBinaryNode<T> {

	/**
	 * The stored element.
	 */
	T element;

	/**
	 * The left child tree.
	 */
	ThreadedBinaryNode<T> left;

	/**
	 * The right child tree.
	 */
	ThreadedBinaryNode<T> right;

	/**
	 * When isLeftThread is true, it means that the left reference is a thread;
	 * it points to the node's in-order predecessor.
	 */
	boolean isLeftThread;

	/**
	 * When isRightThread is true, it means that the right reference is a
	 * thread; it points to the node's in-order successor.
	 */
	boolean isRightThread;

	/**
	 * Creates a single node threaded binary tree.
	 * 
	 * @param theElement
	 */
	ThreadedBinaryNode(T theElement) {
		this(theElement, null, null, true, true);
	}

	/**
	 * Creates a threaded binary tree with the given left and right references,
	 * both assumed to be threads.
	 * 
	 * @param theElement
	 * @param lt
	 * @param rt
	 */
	ThreadedBinaryNode(T theElement, ThreadedBinaryNode<T> lt,
			ThreadedBinaryNode<T> rt) {
		this(theElement, lt, rt, true, true);
	}

	/**
	 * Creates a threaded binary tree with the given left and right references
	 * and the given values of isLeftThread and isRightThread
	 * 
	 * @param theElement
	 * @param lt
	 * @param rt
	 * @param isLeftThread
	 * @param isRightThread
	 */
	ThreadedBinaryNode(T theElement, ThreadedBinaryNode<T> lt,
			ThreadedBinaryNode<T> rt, boolean isLeftThread,
			boolean isRightThread) {
		this.element = theElement;
		this.left = lt;
		this.right = rt;
		this.isLeftThread = isLeftThread;
		this.isRightThread = isRightThread;
	}

	// A somewhat strange implementation to facilitate debugging.
	@Override
	public String toString() {
		return "(" + this.element + "      "
				+ (this.left == null ? "null" : this.left.element.toString())
				+ " "
				+ (this.right == null ? "null" : this.right.element.toString())
				+ "      " + this.isLeftThread + " " + this.isRightThread + ")";
	}

	/**
	 * @return the next in-order node of the threaded tree in which this node
	 *         lives
	 */
	public ThreadedBinaryNode<T> inOrderSuccessor() {
		if(this.isRightThread)
			return this.right;
		else{
			ThreadedBinaryNode<T> node = this.right;
			while(!node.isLeftThread) node = node.left;
			return node;
		}
	}

	/**
	 * @return the previous in-order node of the threaded tree in which this
	 *         node lives
	 */
	public ThreadedBinaryNode<T> inOrderPredecessor() {
		if(this.isLeftThread) 
			return this.left;
		else{
			ThreadedBinaryNode<T> node = this.left;
			while(node.right != this) 
				node = node.right;
			return node;
		}
	}

}
