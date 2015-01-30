import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * A Binary Search Tree can can be for any type T that extends Comparable 
 *
 * @author millerc5.
 *         Created Jan 10, 2014.
 * @param <T> an Object for the tree to accept only.
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>{
	
	@SuppressWarnings("rawtypes")
	private BinaryNode<T> root;
	private int modifiedCount;

	/**
	 * Constructs a BinarySearchTree with no entries.
	 *
	 */
	public BinarySearchTree(){
		this.root = BinaryNode.NULL_NODE;
		this.modifiedCount = 0;
	}

	/**
	 * Constructs a BinarySearchTree with the root as the given param.
	 *
	 * @param n
	 */
	public BinarySearchTree(BinaryNode<T> n){
		this.root = n;
	}

	/**
	 * Returns the height of the tree.
	 *
	 * @return the height of the tree.
	 */
	public int height(){
		return this.root.height();
	}

	/**
	 * Returns the size of the tree.
	 *
	 * @return the size of the tree.
	 */
	public int size() {
		return this.root.size();
	}

	
	/**
	 * Returns if this tree is empty.
	 *
	 * @return is the tree empty
	 */
	public boolean isEmpty() {
		return this.root == BinaryNode.NULL_NODE;
	}

	/**
	 * Returns an ArrayList containing the elements in an inorder list.
	 *
	 * @return an inorder list of the elements.
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> toReturn = new ArrayList<T>();
		this.root.toArrayList(toReturn);
		return toReturn;
	}

	
	/**
	 * Returns an array of type Object containing the elements of this tree.
	 *
	 * @return an Object array containing the elements of this tree.
	 */
	public Object[] toArray() {
		return toArrayList().toArray();
	}

	/**
	 * Returns wither the paramatater is in the tree
	 *
	 * @param o a non Null object
	 * @return true if the three contains 'o', false otherwise.
	 */
	public boolean contains(T o){
		return this.root.contains(o);
	}

	/**
	 * Returns an iterator that goes in preOrder threw the tree.
	 *
	 * @return an iterator that goes in preOrder threw the tree.
	 */
	public Iterator<T> preOrderIterator() {
		return new PreOrderTreeIterator<T>(this.root, this);
	}

	@Override
	public Iterator<T> iterator() {
		return new InOrderTreeIterator<T>(this.root, this);
	}

	@Override
	public String toString() {
		return toArrayList().toString();
	}

	/**
	 * Adds the given element to the tree if it is not already
	 *
	 * @param o
	 * @return true if adding was successful.
	 */
	public boolean insert(T o){
		if(o == null){
			throw new IllegalArgumentException();
		}
		try{
			this.root = this.root.insert(o);
			this.modifiedCount++;
			return true;
		}catch(Duplicate e) {
			return false;
		}
	}

	/**
	 * Removes object 'i' and returns a boolean of successes.
	 *
	 * @param i
	 * @return true if the object was removed, otherwise false.
	 */
	public boolean remove(T i) {
		if(i == null)
			throw new IllegalArgumentException();
		try{
			this.root = this.root.remove(i);
			this.modifiedCount++;
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
	}

	/**
	 * Returns the amount of times this tree has been edited since creation.
	 *
	 * @return the amount of times this tree has been edited since creation.
	 */
	protected int getModifiedCount() {
		return this.modifiedCount;
	}

	
	/**
	 * Returns 'o' if 'o' is in the tree, otherwise null.
	 *
	 * @param o
	 * @return 'o' if 'o' is in the tree, otherwise null. 
	 */
	public T get(T o){
		if(contains(o))
			return o;
		return null;
	}
}

/**
 * Node for Binary Trees which holds one element of type T
 *
 * @author millerc5.
 *         Created Jan 10, 2014.
 * @param <T>
 */
class BinaryNode<T extends Comparable<T>> {

	public static BinaryNode NULL_NODE= new BinaryNode<Integer>(1);

	private T element;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;

	public BinaryNode(T element){
		this.element = element;
		this.leftChild = NULL_NODE;
		this.rightChild = NULL_NODE;		
	}

	public void setLeftChild(BinaryNode<T> leftChild){
		this.leftChild = leftChild;
	}

	public void setRightChild(BinaryNode<T> rightChild){
		this.rightChild = rightChild;
	}


	public int height(){
		if(this == NULL_NODE)
			return -1;
		return 1 + Math.max(this.leftChild.height(),this.rightChild.height());

	}

	public int size() {
		if(this == NULL_NODE) 
			return 0;
		return 1 + this.leftChild.size() + this.rightChild.size();
	}

	public void toArrayList(ArrayList<T> toReturn) {
		if(this == NULL_NODE)
			return;

		this.leftChild.toArrayList(toReturn);
		toReturn.add(this.element);
		this.rightChild.toArrayList(toReturn);
	}

	public boolean contains(T o){
		if(this == NULL_NODE)
			return false;
		return this.element.equals(o) || this.leftChild.contains(o) || this.rightChild.contains(o); 
	}


	@Override
	public String toString() {
		return this.element.toString();
	}

	public BinaryNode<T> getLeftChild(){
		return this.leftChild;
	}

	public BinaryNode<T> getRightChild() {
		return this.rightChild;
	}

	public BinaryNode<T> insert(T o) throws Duplicate {
		if(this == NULL_NODE){
			return new BinaryNode<T>(o);
		}

		int value = this.element.compareTo(o);
		if(value > 0){
			this.leftChild = this.leftChild.insert(o);
		}else if(value < 0){
			this.rightChild = this.rightChild.insert(o);
		}else{
			throw new Duplicate();
		}
		return this;
	}

	public T getElement(){
		return this.element;
	}

	public BinaryNode<T> remove(T o){
		if(this == NULL_NODE){
			throw new NoSuchElementException();
		}

		int value = this.element.compareTo(o);
		if(value > 0){
			this.leftChild = this.leftChild.remove(o);
		}else if(value < 0){
			this.rightChild = this.rightChild.remove(o);
		}else{
			if(this.leftChild == NULL_NODE){
				return this.rightChild;
			}else if (this.rightChild == NULL_NODE){
				return this.leftChild;
			}else{
				T temp = this.leftChild.rightMost();
				remove(temp);
				this.element = temp;
			}
		}
		return this;
	}

	public T rightMost(){
		if(this.rightChild == NULL_NODE)
			return this.element;
		return this.rightChild.rightMost();
	}

}

class InOrderTreeIterator<T extends Comparable<T>> implements Iterator<T> {

	private Stack<BinaryNode<T>> stack;

	private int modifiedCount;

	private BinarySearchTree<T> tree;

	private T lastElement;

	public InOrderTreeIterator(BinaryNode<T> start, BinarySearchTree<T> tree) {
		this.stack = new Stack<BinaryNode<T>>();

		while(start != BinaryNode.NULL_NODE){
			this.stack.push(start);
			start = start.getLeftChild();
		}
		this.modifiedCount = tree.getModifiedCount();
		this.tree = tree;
	}

	@Override
	public boolean hasNext() {
		return !this.stack.isEmpty();
	}

	@Override
	public T next() {
		if(this.modifiedCount != this.tree.getModifiedCount()){
			throw new ConcurrentModificationException();
		}
		if(!hasNext()){
			throw new NoSuchElementException();
		}

		BinaryNode<T> lastNode = this.stack.pop();
		BinaryNode<T> node = lastNode.getRightChild();
		while (node != BinaryNode.NULL_NODE){
			this.stack.push(node);
			node = node.getLeftChild();
		}

		this.lastElement = lastNode.getElement();
		return this.lastElement;
	}

	@Override
	public void remove() {
		if(this.lastElement == null)
			throw new IllegalStateException();
		this.tree.remove(this.lastElement);
		this.lastElement = null;
	}

}

class PreOrderTreeIterator<T extends Comparable<T>> implements Iterator<T> {

	private Stack<BinaryNode<T>> stack;

	private int modifiedCount;

	private BinarySearchTree<T> tree;

	private T lastElement;

	public PreOrderTreeIterator(BinaryNode<T> start, BinarySearchTree<T> tree) {
		this.stack = new Stack<BinaryNode<T>>();

		if(start != BinaryNode.NULL_NODE)
			this.stack.push(start);

		this.modifiedCount = tree.getModifiedCount();
		this.tree = tree;
	}

	@Override
	public boolean hasNext() {
		return !this.stack.isEmpty();
	}

	@Override
	public T next() {
		if(this.modifiedCount != this.tree.getModifiedCount()){
			throw new ConcurrentModificationException();
		}
		if(!hasNext()){
			throw new NoSuchElementException();
		}

		BinaryNode<T> lastNode = this.stack.pop();
		BinaryNode<T> node;
		if((node = lastNode.getRightChild()) != BinaryNode.NULL_NODE){
			this.stack.push(node);
		}
		if((node = lastNode.getLeftChild()) != BinaryNode.NULL_NODE){
			this.stack.push(node);
		}

		this.lastElement = lastNode.getElement();
		return this.lastElement;
	}

	@Override
	public void remove() {
		if(this.lastElement == null)
			throw new IllegalStateException();
		this.tree.remove(this.lastElement);
		this.lastElement = null;
	}

}


