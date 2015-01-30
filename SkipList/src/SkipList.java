import java.util.ArrayList;
import java.util.Random;

/**
 * A SkipList is a randomized multi-level linked list
 * 
 * @param <T>
 *            The generic type of the list.
 */
public class SkipList<T extends Comparable<? super T>> {

	private Node root;
	private int numNodesVisited;
	private Random randomizer;

	private static int MAX_LINKS = 10;

	/**
	 * Creates a skip list with truly random numbers. We won't use this
	 * constructor for our tests.
	 */
	public SkipList() {
		this.randomizer = new Random();
		reset();
	}

	/**
	 * Creates a SkipList with a given fixed seed. This is better for testing.
	 * 
	 * @param seed
	 *            A random seed.
	 */
	public SkipList(int seed) {
		this.randomizer = new Random(seed);
		reset();
	}

	// Creates the skip list's root and end nodes and does any other
	// initialization needed.
	private void reset() {
		this.root = makeNode(null, MAX_LINKS-1);
		Node tail = makeNode(null, MAX_LINKS-1);

		for(int i = 0; i < MAX_LINKS; i++){
			this.root.links.set(i, tail);
		}
	}

	/**
	 * Grabs the next random integer from the array
	 * 
	 * @return 0 or 1.
	 */
	public int getRand() {
		int temp = this.randomizer.nextInt(2);
		// Uncomment to see random values generated.
		// System.out.println("Random = " + temp);
		return temp;
	}

	/**
	 * @return the root node
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * Inserts the elements in the array in the SkipList in order, then iterates
	 * through the list, copying them back into the array, thus sorting the
	 * array.
	 * 
	 * @param array
	 */
	public void sort(T[] array) {
		// (For the next assignment, not this one.)

		// TODO: Increase MAX_LINKS, since 10 will be too small when sorting
		// large arrays.

		this.reset();
		// TODO: finish implementing sort.

	}

	/**
	 * Inserts the given element in the list
	 * 
	 * @param e
	 * @return true if successful; false otherwise
	 */
	public boolean insert(T e) {		
		Node newNode = makeNode(e, getNewNodeHeight());

		int currentHeight = MAX_LINKS - 1; //Index of maxheight is 9

		Node node = this.root;

		ArrayList<Node> toChange = new ArrayList<Node>();
		while(true){
			int difference;

			if(node.links.get(currentHeight).getElement() == null){
				difference = 1;
			}else{
				difference = node.links.get(currentHeight).getElement().compareTo(e);
			}

			//System.out.println(currentHeight + " " + difference + " " + node.links.get(currentHeight).getElement() + " " + e);
			switch(difference){ //CompareTo for next element.
			case -1:
				node = node.links.get(currentHeight);
				this.numNodesVisited++;
				break;
			case 0:
				//Same element so place to left of.
				//Treating next element as bigger.
				//Just have no break and will do the same as case bellow;
			case 1:
				if(currentHeight < newNode.getHeight()){
					toChange.add(node);
				}
				currentHeight--;
				if(currentHeight < 0){
					for(currentHeight = 0;currentHeight < newNode.links.size(); currentHeight++){
						node = toChange.remove(toChange.size()-1);
						newNode.links.set(currentHeight, node.links.get(currentHeight));
						node.links.set(currentHeight, newNode);
					}
					return true;
				}
				//Go down. Set links.
				
				break;
			}
		}

	}

	private Node makeNode(T element, int height){
		Node toReturn = new Node(element);

		// Setup node so that the array size is its height
		ArrayList<Node> nullLinks = new ArrayList<Node>();

		// Go threw and make all links null for the height;
		for(int i = 0; i <= height; i++){
			nullLinks.add(null);
		}

		toReturn.links = nullLinks;

		return toReturn;
	}

	private int getNewNodeHeight(){
		int height = 0;
		while( getRand()!= 0){
			height++;
			if(height >= MAX_LINKS -1)
				break;
		}
		return height;
	}

	/**
	 * Removes the given element from the list
	 * 
	 * @param e
	 * @return true if successful; false otherwise
	 */
	public boolean remove(T e) {
		int currentHeight = MAX_LINKS - 1;
		Node node = this.root;

		ArrayList<Node> toChange = new ArrayList<Node>();


		while (true) {
			int difference;

			if(node.links.get(currentHeight).getElement() == null){ // Looking at tail node
				difference = 1;
			}else{
				difference = node.links.get(currentHeight).getElement().compareTo(e);
			}

			switch(difference){
			case 1: //Next element is larger so go down and add this node to change list
				currentHeight--;
				toChange.add(node);
				if(currentHeight < 0)
					return false;
				break;
			case 0: //Found the element, but might not be time to delete (unless height is == 0)
				toChange.add(node);

				if(currentHeight <= 0){ //it is time to delete
					node = node.getLinks().get(0);
					System.out.println(node.links.size() + " " + toChange.size());
					for(; currentHeight < node.links.size(); currentHeight++){
						//System.out.println(currentHeight);
						toChange.remove(toChange.size()-1).links.set(currentHeight, node.links.get(currentHeight));
					}

					return true;
				}
				currentHeight--;

				break;
			case -1: //Next element is smaller so move to it;
				node = node.links.get(currentHeight);
				this.numNodesVisited++;
				break;
			}
		}
	}

	/**
	 * @return the number of nodes visited during insertion and removal
	 */
	public int getNodesVisited() {
		return this.numNodesVisited;
	}

		@Override
		public String toString() {
			StringBuilder string = new StringBuilder();
			Node node = this.root.getLinks().get(0);
			while (node != null && node.getElement() != null){
				string.append(node.getElement().toString());
				node = node.getLinks().get(0);
			}
			return string.toString();
		}

	/**
	 * A Node holds data and links to the next node on its levels.
	 * 
	 */
	public class Node {

		private T element;
		private ArrayList<Node> links;

		// TODO: implement whatever else is needed for this class.
		// You should not add any more fields.
		/**
		 * Instantiates this node with the given element and links
		 * 
		 * @param e
		 * @param size
		 */
		public Node(T e) {
			this.element = e;
		}

		/**
		 * Returns the list of links this node is holding
		 * 
		 * @return a list of links
		 */
		public ArrayList<Node> getLinks() {
			return this.links;
		}

		/**
		 * @return this node's element
		 */
		public T getElement() {
			return this.element;
		}

		/**
		 * Returns the height of this node.
		 *
		 * @return the height of this node.
		 */
		public int getHeight(){
			return this.links.size();
		}

		@Override
		public String toString() {
			return element.toString();
		}
	}
}