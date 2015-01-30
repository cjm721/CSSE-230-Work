/*

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class PreOrderTreeIteratorOld<T extends Comparable<T>> implements Iterator<T> {

	private Stack<BinaryNodeOld<T>> stack;
	
	private int modifiedCount;
	
	private BinarySearchTree<T> tree;
	
	private T lastElement;
	
	public PreOrderTreeIteratorOld(BinaryNodeOld<T> start, BinarySearchTree<T> tree) {
		this.stack = new Stack<BinaryNodeOld<T>>();
		
		if(start != BinaryNodeOld.NULL_NODE)
			stack.push(start);
		
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
		BinaryNodeOld<T> node;
		if((node = lastNode.getRightChild()) != BinaryNodeOld.NULL_NODE){
			stack.push(node);
		}
		if((node = lastNode.getLeftChild()) != BinaryNodeOld.NULL_NODE){
			stack.push(node);
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