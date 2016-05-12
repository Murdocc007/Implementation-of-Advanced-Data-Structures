import java.util.*;
/**
 * Class to represent a graph
 * 
 *
 */

public class Graph implements Iterable<Vertex>{
	public List<Vertex> verts;//array of vertices
	public int numNodes;//no. of vertices in the graph
	
	/**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
	
	Graph(int size){
		numNodes = size;
		verts = new ArrayList<>(size+1);
		verts.add(0, null);
		// create an array of Vertex objects
		for(int i =1; i <=size;i++){
			verts.add(i, new Vertex(i));
		}
	}
	/**
     * Method to add a directed edge to the graph
     * 
     * @param a
     *            : int - head of the arc
     * @param b
     *            : int - tail of the arc
     */
	void addDirectedEdge(int a, int b){
		Vertex head = verts.get(a);
		Vertex tail = verts.get(b);
		Edge e = new Edge(head, tail);
		head.Adj.add(e);
		tail.revAdj.add(e);
		
	}
	/**
     * Method to create an instance of VertexIterator
     */
	public Iterator<Vertex> iterator(){
		return new VertexIterator();
	}
	
	
	/**
     * A Custom Iterator Class for iterating through the vertices in a graph
     * 
     * @param <Vertex>
     */
	private class VertexIterator implements Iterator<Vertex>{
		
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
			it.next();
		}

		/**
		 * Method to check if there is any vertex left in the iteration
		 * Overrides the default hasNext() method of Iterator Class
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * Method to return the next Vertex object in the iteration
		 * Overrides the default next() method of Iterator Class
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
	//reading no. of vertices and edges.
	//reading head and tail vertices and adding directed edge to head and tail vertex.
	public Graph readGraph(Scanner in){
		System.out.println("Enter the no. of vertices:");
		int n = in.nextInt();//no. of vertices
		System.out.println("Enter the no. of edges:");
		int m = in.nextInt();//no. of edges
		
		Graph g = new Graph(n);
		for(int i = 0; i < m; i++){
			System.out.println("Enter the head vertex:");
			int u = in.nextInt();//head vertex
			System.out.println("Enter the tail vertex");
			int v = in.nextInt();//tail vertex
			g.addDirectedEdge(u, v);
		}
		in.close();
		return g;
		
	}
}
