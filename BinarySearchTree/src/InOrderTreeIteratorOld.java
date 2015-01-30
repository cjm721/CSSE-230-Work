/*

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class InOrderTreeIteratorOld<T extends Comparable<T>> implements Iterator<T> {

	private Stack<BinaryNodeOld<T>> stack;
	
	private int modifiedCount;
	
	private BinarySearchTree<T> tree;
	
	private T lastElement;
	
	public InOrderTreeIteratorOld(BinaryNodeOld<T> start, BinarySearchTree<T> tree) {
		this.stack = new Stack<BinaryNodeOld<T>>();
		
		while(start != BinaryNodeOld.NULL_NODE){
			stack.push(start);
			start = start.getLeftChild();
		}
		modifiedCount = tree.getModifiedCount();
		this.tree = tree;
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public T next() {
		if(modifiedCount != this.tree.getModifiedCount()){
			throw new ConcurrentModificationException();
		}
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		
		BinaryNodeOld<T> lastNode = stack.pop();
		BinaryNodeOld<T> node = lastNode.getRightChild();
		while (node != BinaryNodeOld.NULL_NODE){
			stack.push(node);
			node = node.getLeftChild();
		}
		
		lastElement = lastNode.getElement();
		return lastElement;
	}

	@Override
	public void remove() {
		if(lastElement == null)
			throw new IllegalStateException();
		tree.remove(lastElement);
		lastElement = null;
	}

}

*/