package list;

import java.util.Iterator;

/**
 * 
 * @author anderson
 * 
 * @param <T>
 *            Any Comparable type
 * 
 *            A linked list whose elements are kept in sorted order.
 */
public class SortedLinkedList<T extends Comparable<T>> extends
		DoublyLinkedList<T> {

	/**
	 * Create an empty list
	 * 
	 */
	public SortedLinkedList() {
		super();
	}

	/**
	 * Creates a sorted list containing the same elements as the parameter
	 * 
	 * @param list
	 *            the input list
	 */
	public SortedLinkedList(DoublyLinkedList<T> list) {
		super();
		DLLIterator<T> iterator = list.iterator();
		while(iterator.hasNext()){
			this.add(iterator.next());
		}
	}

	/**
	 * Adds the given element to the list, keeping it sorted.
	 */
	@Override
	public void add(T element) {
		if (element == null)
			return;
		
		Node node = this.head;
		
		if(node.next == this.tail){
			node.addAfter(element);
			return;
		}else{
			node = node.next;
		}
		
		while(node.data.compareTo(element) <= 0){
			if(node.next.data == null || node.next == this.tail){
				node.addAfter(element);
				return;
			}else{
				node = node.next;
			}
		}

		node.prev.addAfter(element);
	}

	@Override
	public void addFirst(T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addLast(T element) {
		throw new UnsupportedOperationException();
	}
}
