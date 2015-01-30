package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * This class implements an undirected graph to which edges can be added
 * randomly. 
 */

public class RandomGraph {

	private Vertex[] vertices;
	private int size;
	
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
	
	/**
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
	 * Checks to see if vertex A and vertex B are connected via some path of vertices.  
	 * Returns true if and only if a path exists. Any vertex is connected to itself.
	 * @param vertexA The label of a vertex. 
	 * 			You may assume that this vertex is stored in index A, as done in class.
	 * @param vertexB The label of a vertex. Same as vertex A. 
	 *
	 * @return True if there is a path from vertex A to vertex B, and false otherwise.
	 */
	public boolean pathExists(int vertexA, int vertexB) {
		//Reset the wasVisited tags before continue so multiple calls to one object will not fail.
		for(Vertex v: this.vertices){
			v.wasVisited = false;
		}
		
		Stack<Vertex> stack = new Stack<Vertex>();

		stack.push(this.vertices[vertexA]);

		while(!stack.isEmpty()){
			Vertex vertex = stack.pop();
			if(vertex == this.vertices[vertexB])
				return true;
			if(!vertex.wasVisited){
				vertex.wasVisited = true;
				for(Vertex nbr: vertex.neighbors){
					if(!nbr.wasVisited)
						stack.push(nbr);
				}
			}
		}
		return false;
	}
}
