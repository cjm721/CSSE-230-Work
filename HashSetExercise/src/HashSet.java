
import java.io.Serializable;

/**
 * HashSet implementation. Matches are based on equals; and hashCode must be
 * consistently defined.
 */
public class HashSet extends AbstractCollection implements Set {
	/* MB: added */
	private String probingMethod = "quadratic";

	private int numCollisions;

	private static final int DEFAULT_TABLE_SIZE = 101;

	private int currentSize = 0;

	private int occupied = 0;

	private int modCount = 0;

	private HashEntry[] array;

	/**
	 * Construct an empty HashSet.
	 */
	public HashSet() {
		allocateArray(DEFAULT_TABLE_SIZE);
		clear();
	}

	/**
	 * MB: Create hash set with the given capacity. Useful for simulation.
	 * 
	 * @param capacity
	 */
	public HashSet(int capacity) {
		allocateArray(capacity);
		clear();
	}

	/**
	 * Construct a HashSet from any collection.
	 * 
	 * @param other
	 *            Another collection to hash.
	 */
	public HashSet(Collection other) {
		allocateArray(other.size() * 2);
		clear();

		Iterator itr = other.iterator();
		while (itr.hasNext())
			add(itr.next());
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return this.currentSize;
	}

	/**
	 * This method is not part of standard Java 1.2. Like contains, it checks if
	 * x is in the set. If it is, it returns the reference to the matching
	 * object; otherwise it returns null.
	 * 
	 * @param x
	 *            the object to search for.
	 * @return if contains(x) is false, the return value is null; otherwise, the
	 *         return value is the object that causes contains(x) to return
	 *         true.
	 */
	public Object getMatch(Object x) {
		int currentPos = findPos(x);
		if (this.array[currentPos] == null)
			return null;
		return this.array[currentPos].element;
	}

	/**
	 * Tests if some item is in this collection.
	 * 
	 * @param x Any object.
	 * @return true if this collection contains an item equal to x.
	 */
	@Override
	public boolean contains(Object x) {
		return isActive(this.array, findPos(x));
	}

	/**
	 * Tests if item in pos is active.
	 * 
	 * @param pos A position in the hash table.
	 * @param arr The HashEntry array (can be oldArray during rehash).
	 * @return true if this position is active.
	 */
	private static boolean isActive(HashEntry[] arr, int pos) {
		return arr[pos] != null && arr[pos].isActive;
	}

	/**
	 * Adds an item to this collection.
	 * 
	 * @param x Any object.
	 * @return true if this item was added to the collection.
	 */
	public boolean add(Object x) {
		int currentPos = findPos(x);
		if (isActive(this.array, currentPos))
			return false;

		this.array[currentPos] = new HashEntry(x, true);
		this.currentSize++;
		this.occupied++;
		this.modCount++;

		if (this.occupied > this.array.length / 2)
			rehash();

		return true;
	}

	/**
	 * Private routine to perform rehashing. Can be called by both add and
	 * remove.
	 */
	private void rehash() {
		HashEntry[] oldArray = this.array;

		// Create a new, empty table
		allocateArray(nextPrime(4 * size()));
		this.currentSize = 0;
		this.occupied = 0;

		// Copy table over
		for (int i = 0; i < oldArray.length; i++)
			if (isActive(oldArray, i))
				add(oldArray[i].element);
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param x
	 *            any object.
	 * @return true if this item was removed from the collection.
	 */
	@Override
	public boolean remove(Object x) {
		int currentPos = findPos(x);
		if (!isActive(this.array, currentPos))
			return false;

		this.array[currentPos].isActive = false;
		this.currentSize--;
		this.modCount++;

		if (this.currentSize < this.array.length / 8)
			rehash();

		return true;
	}

	/**
	 * Change the size of this collection to zero.
	 */
	@Override
	public void clear() {
		this.currentSize = this.occupied = 0;
		this.modCount++;
		for (int i = 0; i < this.array.length; i++)
			this.array[i] = null;
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public Iterator iterator() {
		return new HashSetIterator();
	}

	/**
	 * This is the implementation of the HashSetIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the
	 * HashSet.
	 */
	private class HashSetIterator implements Iterator {
		private int expectedModCount = HashSet.this.modCount;

		private int currentPos = -1;

		private int visited = 0;

		/**
		 * Determines whether or not the collection has a next element.
		 * 
		 * @return Whether or not the collection has a next element.
		 */
		public boolean hasNext() {
			if (this.expectedModCount != HashSet.this.modCount) {
				throw new ConcurrentModificationException();
			}
			return this.visited != size();
		}

		/**
		 * Advances the iterator and returns the next element.
		 * 
		 * @return The next element.
		 */
		public Object next() {
			if (!hasNext())
				throw new NoSuchElementException();

			do {
				this.currentPos++;
			} while (this.currentPos < HashSet.this.array.length 
					&& !isActive(HashSet.this.array, this.currentPos));

			this.visited++;
			return HashSet.this.array[this.currentPos].element;
		}

		/**
		 * Removes the item currently referenced by the iterator.
		 * 
		 */
		public void remove() {
			if (this.expectedModCount != HashSet.this.modCount)
				throw new ConcurrentModificationException();
			if (this.currentPos == -1 || !isActive(HashSet.this.array, this.currentPos))
				throw new IllegalStateException();

			HashSet.this.array[this.currentPos].isActive = false;
			HashSet.this.currentSize--;
			this.visited--;
			HashSet.this.modCount++;
			this.expectedModCount++;
		}
	}

	private static class HashEntry implements Serializable {
		/** The element. */
		public Object element;

		/** false if marked deleted */
		public boolean isActive;

		/**
		 * Constructs a hash entry from the given object.
		 * 
		 * @param e
		 *            The object used to fill the hash entry.
		 */
		public HashEntry(Object e) {
			this(e, true);
		}

		/**
		 * Constructs a hash entry from the given object, setting it active or
		 * not.
		 * 
		 * @param e
		 *            The given object.
		 * @param isActive
		 */
		public HashEntry(Object e, boolean isActive) {
			this.element = e;
			this.isActive = isActive;
		}
	}

	/**
	 * MB: Modified to package access to expose to test program. Method that
	 * performs quadratic probing resolution.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the position where the search terminates.
	 */
	int findPos(Object x) {
		int collisionNum = 0;
		int currentPos = (x == null) ? 0 : Math.abs(x.hashCode()
				% this.array.length);

		while (this.array[currentPos] != null) {
			if (x == null) {
				if (this.array[currentPos].element == null)
					break;
			} else if (x.equals(this.array[currentPos].element))
				break;

			collisionNum++;
			currentPos += 2 * collisionNum - 1; // Compute ith probe
			if (currentPos >= this.array.length) // Implement the mod function.
				currentPos -= this.array.length;
		}

		this.numCollisions += collisionNum;
		return currentPos;
	}

	/**
	 * Internal method to allocate array.
	 * 
	 * @param arraySize
	 *            the size of the array.
	 */
	private void allocateArray(int arraySize) {
		this.array = new HashEntry[arraySize];
	}

	/**
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * @param n
	 *            the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2) {
			// purposefully empty
		}

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n
	 *            the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	/**
	 * MB: Added this public method to view the contents of the hash table by
	 * direct index (vs. a hash value).
	 * 
	 * @param pos
	 *            The position to look for the item.
	 * @return The item at the array position.
	 */
	public Object getItemAt(int pos) {
		return this.array[pos] == null ? null : this.array[pos].element;
	}

	/**
	 * MB: Return the total number of collisions.
	 * 
	 * @return The number of collisions.
	 */
	public int getNumCollisions() {
		return this.numCollisions;
	}

	/**
	 * MB: Reset the number of collisions to zero.
	 * 
	 */
	public void resetNumCollisions() {
		this.numCollisions = 0;
	}

	/**
	 * MB: Returns the capacity of the array. Used for simulations.
	 * 
	 * @return The capacity of the array.
	 */
	public int getCapacity() {
		return this.array.length;
	}

	/**
	 * MB: Sets the probingMethod field to the given parameter.
	 * 
	 * @param probingMethod
	 *            The probing method to be used.
	 */
	public void setProbingMethod(String probingMethod) {
		this.probingMethod = probingMethod;
	}

	/**
	 * MB: Returns the value of the probingMethod field.
	 * 
	 * @return The value of the probingMethod field.
	 */
	public String getProbingMethod() {
		return this.probingMethod;
	}
}
