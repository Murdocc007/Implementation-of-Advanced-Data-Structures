import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
	for (int i = 1; i <= size; i++)
	    verts.add(i, new Vertex(i));
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
    u.degree++;
    v.degree++;
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
	Vertex tail = verts.get(a);
	Vertex head = verts.get(b);
	Edge e = new Edge(tail, head, weight);
	tail.Adj.add(e);
	head.revAdj.add(e);
    tail.outDegree++;
	head.incomingDegree++;
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
	    it.next();  // Index 0 is not used.  Skip it.
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

    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
	//System.out.println("Enter Number of Vertices");
    int n = in.nextInt(); // number of vertices in the graph
   // System.out.println("Enter Number of Edges ");
    int m = in.nextInt(); // number of edges in the graph

    if(m>=n) {
	System.out.println("Input graph is not Tree");
  System.exit(0);
	}

	// create a graph instance
	Graph g = new Graph(n);
	for (int i = 0; i < m; i++) {
	//    System.out.println("Enter u     v     w");
		int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    if(directed) {
		g.addDirectedEdge(u, v, w);
	    } else {
		g.addEdge(u, v, w);
	    }
	}
	in.close();
	return g;
    }
    
public static void main(String[] args) throws FileNotFoundException{
File f=new File("input.txt");
Scanner scan = new Scanner(f);
boolean directed=false;  // keep false for undirected graph and true for directed graph
Graph graph=readGraph(scan,directed);

/*
Problem1 p1=new Problem1();
p1.toplogicalOrder1(graph);
System.out.println("Topological Order using Algorithm 1");
p1.display();

Problem2 p2=new Problem2();
p2.toplogicalOrder2(graph);
System.out.println("\nTopological Order using Algorithm 2");
p2.display();
*/
Problem3 p3=new Problem3();
System.out.println("Diameter of Given Tree is(#edges between two farthest Nodes )"+"  "+p3.diameter(graph));;
/*
Problem4 p4=new Problem4();
p4.findStringlyConnectedComponent(graph);
p4.display();

Problem5 p5=new Problem5();
p5.findOddLengthCycle(graph);

Problem6 p6=new Problem6();
p6.testEulerian(graph);
*/
}

 
    
    
    
}