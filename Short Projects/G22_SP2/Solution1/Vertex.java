/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex {
    public String name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Vertex> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    int inDegree =0;
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(String n) {
	name = n;
	seen = false;
	parent = null;
	inDegree =0;
	Adj = new ArrayList<Vertex>();
	revAdj = new ArrayList<Vertex>();   /* only for directed graphs */
    }
    
    
    public void addAdjVertex(final Vertex toVertex){
        Adj.add(toVertex);
        toVertex.inDegree++;
    }
    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return name;
    }
}