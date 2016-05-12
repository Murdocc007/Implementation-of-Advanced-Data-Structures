/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a vertex of a graph
 *
 */
public class Vertex implements Index {
	public int name;// name of the vertex
	public int cno;// component no. of the vertex
	public Vertex parent;// parent vertex of the current vertex
	public Boolean seen;// flag to check whether vertex as has been visited or
						// not
	public List<Edge> Adj, revAdj;// List of incoming and outgoing edges
									// adjacent to the vertex.
	public int d;// distance of the vertex
	public int index;// index of the vertex in the priority queue
	public int rank;// rank of the vertex for kruskals algo

	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		cno = 1;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<>();
		rank = 0;
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}

	@Override
	public void putIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return this.index;
	}

}
