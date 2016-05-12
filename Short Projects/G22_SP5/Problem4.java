
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author aditya,divyanshu,radhika,shweta
 * 
 *         Class to represent a graph
 *
 *
 */

class Graph implements Iterable<Vertex> {

	public List<Vertex> verts; // array of vertices
	public int numNodes; // number of verices in the graph

	/**
	 * Constructor for Graph
	 *
	 * @param size
	 *            : int - number of vertices
	 */
	Graph(int size) {
		numNodes = size;
		verts = new ArrayList<>(size + 1);
		verts.add(0, null);
		// create an array of Vertex objects
		for (int i = 1; i <= size; i++) {
			verts.add(i, new Vertex(i));
		}
	}

	/**
	 * Method to add an edge to the graph
	 *
	 * @param a
	 *            : int - one end of edge
	 * @param b
	 *            : int - other end of edge
	 * @param weight
	 *            : int - the weight of the edge
	 */
	void addEdge(int a, int b, int weight) {
		Vertex u = verts.get(a);
		Vertex v = verts.get(b);
		Edge e = new Edge(u, v, weight);
		u.Adj.add(e);
		v.Adj.add(e);
	}

	/**
	 * Method to add an arc (directed edge) to the graph
	 *
	 * @param a
	 *            : int - the head of the arc
	 * @param b
	 *            : int - the tail of the arc
	 * @param weight
	 *            : int - the weight of the arc
	 */
	void addDirectedEdge(int a, int b, int weight) {
		Vertex head = verts.get(a);
		Vertex tail = verts.get(b);
		Edge e = new Edge(head, tail, weight);
		head.Adj.add(e);
		tail.revAdj.add(e);
		tail.indegree++;
	}

	/**
	 * Method to create an instance of VertexIterator
	 */
	public Iterator<Vertex> iterator() {
		return new VertexIterator();
	}

	/**
	 * A Custom Iterator Class for iterating through the vertices in a graph
	 *
	 *
	 * @param <Vertex>
	 */
	private class VertexIterator implements Iterator<Vertex> {

		private Iterator<Vertex> it;

		/**
		 * Constructor for VertexIterator
		 *
		 * @param v
		 *            : Array of vertices
		 * @param n
		 *            : int - Size of the graph
		 */
		private VertexIterator() {
			it = verts.iterator();
			it.next(); // Index 0 is not used. Skip it.
		}

		/**
		 * Method to check if there is any vertex left in the iteration
		 * Overrides the default hasNext() method of Iterator Class
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * Method to return the next Vertex object in the iteration Overrides
		 * the default next() method of Iterator Class
		 */
		public Vertex next() {
			return it.next();
		}

		/**
		 * Throws an error if a vertex is attempted to be removed
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static Graph readGraph(Scanner in, boolean directed) {
		// read the graph related parameters
		int n = in.nextInt(); // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph

		// create a graph instance
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			if (directed) {
				g.addDirectedEdge(u, v, w);
			} else {
				g.addEdge(u, v, w);
			}
		}
		in.close();
		return g;
	}
}

/**
 * Class that represents an arc in a Graph
 *
 */
class Edge {

	public Vertex From; // head vertex
	public Vertex To; // tail vertex
	public int Weight;// weight of the arc

	/**
	 * Constructor for Edge
	 *
	 * @param u
	 *            : Vertex - The head of the arc
	 * @param v
	 *            : Vertex - The tail of the arc
	 * @param w
	 *            : int - The weight associated with the arc
	 */
	Edge(Vertex u, Vertex v, int w) {
		From = u;
		To = v;
		Weight = w;
	}

	/**
	 * Method to find the other end end of the arc given a vertex reference
	 *
	 * @param u
	 *            : Vertex
	 * @return
	 */
	public Vertex otherEnd(Vertex u) {
		// if the vertex u is the head of the arc, then return the tail else
		// return the head
		if (From == u) {
			return To;
		} else {
			return From;
		}
	}

	/**
	 * Method to represent the edge in the form (x,y) where x is the head of the
	 * arc and y is the tail of the arc
	 */
	public String toString() {
		return "(" + From + "," + To + ")";
	}
}

class Vertex {

	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or
									// ArrayList
	public int indegree;// indegree of the vertex

	/**
	 * Constructor for the vertex
	 *
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); /* only for directed graphs */

		indegree = 0;
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
}

/**
 * @author aditya,divyanshu,radhika,shweta
 *
 */
public class Problem4 {

	public static <T> void swap(ArrayList<T> A, int idx1, int idx2) {
		T temp = A.get(idx1);
		A.set(idx1, A.get(idx2));
		A.set(idx2, temp);
	}

	public static void permuteUtil(ArrayList<Vertex> A, int start, int end, ArrayList<ArrayList<Vertex>> Res) {
		if (start == end) {
			Res.add(new ArrayList<Vertex>(A));
			// System.out.println(Res);
		} else {
			for (int i = end; i >= 0; i--) {
				swap(A, end, i);
				permuteUtil(A, start, end - 1, Res);
				swap(A, end, i);
			}
		}

	}

	public static ArrayList<ArrayList<Vertex>> permute(ArrayList<Vertex> A) {
		ArrayList<ArrayList<Vertex>> Res = new ArrayList<ArrayList<Vertex>>();
		permuteUtil(A, 0, A.size() - 1, Res);
		return Res;
	}

	public static boolean allVisited(Graph g) {
		int count = 0;
		for (Vertex v : g) {
			if (v.seen) {
				count++;
			}
		}
		return count == g.numNodes;
	}

	public static void printQueue(ArrayList<Vertex> q) {
		char a = 'a';
		for (Vertex v : q) {
			System.out.print(v.name);
		}
		System.out.println("");
	}

	public static ArrayList<Vertex> getZeroDegreeVertex(Graph g) {
		ArrayList<Vertex> A = new ArrayList<>();
		for (Vertex v : g) {
			if (v.indegree == 0 && v.seen == false) {
				A.add(v);
			}
		}
		return A;
	}

	public static void removeVertex(Vertex v) {
		for (Edge temp : v.Adj) {
			temp.otherEnd(v).indegree--;
		}
	}

	public static void putBackVertex(Vertex v) {
		for (Edge temp : v.Adj) {
			temp.otherEnd(v).indegree++;
		}
	}

	public static void topologicalSort(Graph g, ArrayList<Vertex> q) {
		if (allVisited(g)) {
			printQueue(q);
		} else {
			ArrayList<Vertex> list = getZeroDegreeVertex(g);
			ArrayList<ArrayList<Vertex>> permutes = permute(list);
			for (ArrayList<Vertex> temp : permutes) {
				for (Vertex v : temp) {
					q.add(v);
					v.seen = true;
					removeVertex(v);
				}
				topologicalSort(g, q);
				for (Vertex v : temp) {
					q.remove(v);
					v.seen = false;
					putBackVertex(v);
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		// create a graph instance
		Graph g = Graph.readGraph(in, true);
		ArrayList<Vertex> q = new ArrayList<>();
		topologicalSort(g, q);

	}

}
