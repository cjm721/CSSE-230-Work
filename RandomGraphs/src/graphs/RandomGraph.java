package graphs;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class implements an undirected graph to which edges can be added
 * randomly. It's for experimenting with the theory of random graphs, developed
 * by Paul Erdos and Alfred Renyi in 1959.
 * 
 * @author TODO: put your name here
 */
public class RandomGraph {
	// TODO: add any necessary fields and classes for your chosen representation

	private Vertex[] vertices;
	private int size;

	/**s
	 * Constructs a new graph of the given size with no edges.
	 * 
	 * @param size
	 */
	public RandomGraph(int size) {
		this(size, new int[][] {});
	}

	/**
	 * Constructs a new graph of the given size with the given edges
	 * 
	 * @param size
	 * @param edges
	 *            each element is a pair giving the indices of the two nodes to
	 *            be connected
	 */
	public RandomGraph(int size, int[][] edges) {
		this.size = size;
		this.vertices = new Vertex[size];
		for(int i = 0; i < size; i++){
			this.vertices[i] = new Vertex(i);
		}

		for(int[] edge: edges){
			int v1 = edge[0];
			int v2 = edge[1];

			this.vertices[v1].neighbors.add(this.vertices[v2]);
			this.vertices[v2].neighbors.add(this.vertices[v1]);
		}
	}

	/**
	 * @return the size of the largest connected component of this graph
	 */
	public int largestConnectedComponentSize() {
		int max = 0;
		for(Vertex v: this.vertices){
			int compSize = componetSize(v);
			if(compSize > max)
				max = compSize;
		}
		return max;
	}

	private int componetSize(Vertex v){
		int toReturn = 0;
		Stack<Vertex> stack = new Stack<Vertex>();

		stack.push(v);

		while(!stack.isEmpty()){
			Vertex vertex = stack.pop();
			if(!vertex.wasVisited){
				toReturn++;
				vertex.wasVisited = true;
				for(Vertex nbr: vertex.neighbors){
					if(!nbr.wasVisited)
						stack.push(nbr);
				}
			}
		}
		return toReturn;
	}

	/**
	 * Adds a new edge, chosen uniformly from the set of missing edges.
	 * 
	 * @throws IllegalStateException
	 *             if this.isComplete()
	 */
	public void addRandomEdge() throws IllegalStateException {
		// TODO: implement this method
	}

	/**
	 * Returns whether this graph is connected, that is, whether there is a path
	 * from any node in the graph to any other node.
	 * 
	 * @return true iff this graph is connected
	 */
	public boolean isConnected() {
		return this.size == largestConnectedComponentSize();
	}

	/**
	 * Returns whether this graph is complete, that is, any two distinct
	 * vertices are neighbors of each other.
	 * 
	 * @return true iff this graph is complete
	 */
	public boolean isComplete() {
		// TODO: implement this method
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Vertex v: this.vertices){
			sb.append(v.toString() + "\n");

		}
		return sb.toString();
	}

	private class Vertex {
		ArrayList<Vertex> neighbors;
		boolean wasVisited;
		int index;

		Vertex(int index){
			this.index = index;
			this.wasVisited = false;
			this.neighbors = new ArrayList<Vertex>();
		}

	}
}
