package priorityQueue;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * This class implements a priority queue.
 * 
 * @author <TODO: put your name here>
 * @param <T>
 *            the type of elements in the priority queue
 */
public class PQ<T> {

	// You are not allowed to change this instance variable or add any
	// additional instance variables.
	private TreeSet<PQElement<T>> treeSet;

	/**
	 * Constructs a new priority queue.
	 */
	public PQ() {
		treeSet = new TreeSet<PQElement<T>>();
	}

	/**
	 * @return an element with the smallest priority
	 */
	public T findMin() {
		Iterator<PQElement<T>> iterator = treeSet.descendingIterator();
		
		PQElement<T> min = iterator.next();
		
		while(iterator.hasNext()){
			PQElement<T> next = iterator.next();
			if(min.getPriority() >= next.getPriority()){
				min = next;
			}
		}
		
		return min.getElement();
	}

	/**
	 * Deletes the minimum-priority element.
	 */
	public void deleteMin() {
		Iterator<PQElement<T>> iterator = treeSet.descendingIterator();
		
		PQElement<T> min = iterator.next();
		
		while(iterator.hasNext()){
			PQElement<T> next = iterator.next();
			if(min.getPriority() >= next.getPriority()){
				min = next;
			}
		}
		treeSet.remove(min);
	}

	/**
	 * Inserts the given element with the given priority.
	 *
	 * @param priority
	 * @param element
	 */
	public void insert(int priority, T element) {
		treeSet.add(new PQElement<T>(priority, element));
	}

}
