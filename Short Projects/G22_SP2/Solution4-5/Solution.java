import java.io.*;
import java.util.*;
import java.lang.Exception;

/**
 *
 * @author rbk
 */
class Graph implements Iterable<Vertex> {
	public Vertex[] V; // array of vertices
	public int N; // number of verices in the graph

	/** 
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	Graph(int size) {
	    N = size;
	    V = new Vertex[size + 1];
	    // create an array of Vertex objects
	    for (int i = 1; i <= size; i++)
		V[i] = new Vertex(i);
	}

	/**
	 * Method to add an arc to the graph
	 * 
	 * @param a
	 *            : int - the head of the arc
	 * @param b
	 *            : int - the tail of the arc
	 * @param weight
	 *            : int - the weight of the arc
	 */
	void addEdge(int a, int b, int weight) {
	    Edge e = new Edge(V[a], V[b], weight);
	    V[a].Adj.add(e);
	    V[b].Adj.add(e);
	}

	/**
	 * Method to create an instance of VertexIterator
	 */
	public Iterator<Vertex> iterator() {
	    return new VertexIterator<Vertex>(V, N);
	}

	/**
	 * A Custom Iterator Class for iterating through the vertices in a graph
	 * 
	 *
	 * @param <Vertex>
	 */
	private class VertexIterator<Vertex> implements Iterator<Vertex> {
	    private int nodeIndex = 0;
	    private Vertex[] iterV;// array of vertices to iterate through
	    private int iterN; // size of the array

	    /**
	     * Constructor for VertexIterator
	     * 
	     * @param v
	     *            : Array of vertices
	     * @param n
	     *            : int - Size of the graph
	     */
	    private VertexIterator(Vertex[] v, int n) {
		nodeIndex = 0;
		iterV = v;
		iterN = n;
	    }

	    /**
	     * Method to check if there is any vertex left in the iteration
	     * Overrides the default hasNext() method of Iterator Class
	     */
	    public boolean hasNext() {
		return nodeIndex != iterN;
	    }

	    /**
	     * Method to return the next Vertex object in the iteration
	     * Overrides the default next() method of Iterator Class
	     */
	    public Vertex next() {
		nodeIndex++;
		return iterV[nodeIndex];
	    }

	    /**
	     * Throws an error if a vertex is attempted to be removed
	     */
	    public void remove() {
		throw new UnsupportedOperationException();
	    }
	}

    }

/**
 *
 * @author rbk
 */
class Vertex {
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj; // adjacency list; use LinkedList or ArrayList

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
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
	    return Integer.toString(name);
	} 
        
}
/**
 *
 * @author rbk
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
	    // if the vertex u is the head of the arc, then return the tail else return the head
	    if (From == u) {
		return To;
	    } else {
		return From;
	    }
	}

	/**
	 * Method to represent the edge in the form (x,y) where x is the head of
	 * the arc and y is the tail of the arc
	 */
	public String toString() {
	    return "(" + From + "," + To + ")";
	}
}

public class Solution {

    /**
    *
    * @author-axm156630
    */
    void testEulerian(Graph g) { 
        //Initializing the distances,parent and the seen flag
        for (Vertex u : g) {
            u.seen = false;
            u.parent = null;
            u.distance = Integer.MAX_VALUE;
        }    
        
        //running BFS on the graph 
        Queue<Vertex> Q = new LinkedList<>();
	// Run BFS on every component
	for (Vertex src : g) {
	    if (!src.seen) {
		src.distance = 0;
		Q.add(src);
		src.seen = true;

		while (!Q.isEmpty()) {
		    Vertex u = Q.remove();
		    for (Edge e : u.Adj) {
			Vertex v = e.otherEnd(u);
			if (!v.seen) {
			    v.seen = true;
			    v.parent = u;
			    v.distance = u.distance + 1;
			    Q.add(v);
			}
		    }
		}
	    }
	} 
        
        //check if any vertex of the graph has remained unseen.
        //If it is the graph is not connected
        for (Vertex u : g) {
            if(u.seen == false)
            {
                System.out.println("Graph is not Connected");
                return ;
            }
        }
        
        int nodd=0; //number of vertices with odd degree
        List<Integer> l=new ArrayList<Integer>();//an array which stores the name of vertices with odd degree
        
        for(Vertex u:g){
            //if the size of the adjacency list is odd add it to l
            if(u.Adj.size()%2==1){
                nodd=+1;
                l.add(u.name);
            }
        }
        
        //if all the nodes have even degree then the graph is eularian
        if(nodd==0){
            System.out.println("Graph is Eularian");
            return ;
        }
        
        //if there are only two vertices with odd  degree then the eularian path
        //is between those two vertices
        if(nodd==2){
            System.err.println("Graph has Eularian path bewteen vertices "+Integer.toString(l.get(0))+" and "+Integer.toString(l.get(1)));
            return;
        }
        
        //if the number of odd vertices is more than 2 then there is no eularian path
        System.out.println("Graph is not Eularian.It has "+Integer.toString(nodd)+" odd "+(nodd==1?"vertex":"vertices"));
    
    }
    
    /**
    *
    * @author-axm156630
    */
    //function which takes the two vertices which are connected
    //on the same level and return a list of vertices representing
    //a cycle , in which the first and the last vertex is the lca
    //of the two vertices given
    List<Vertex> oddLengthCycleUtil(Vertex a,Vertex b){
        List<Vertex> stack1=new ArrayList<Vertex>();
        List<Vertex> stack2=new ArrayList<Vertex>();
        stack1.add(a);
        stack2.add(b);
        Vertex temp1=a;
        Vertex temp2=b;
        //keep looping till the parents of both the vertices are the same
        while(temp1.parent!=temp2.parent){
            stack1.add(temp1.parent);
            stack2.add(temp2.parent);
            temp1=temp1.parent;
            temp2=temp2.parent;
        }
        
        List<Vertex> stack3=new ArrayList<Vertex>();
        stack3.add(temp1.parent);
        
        int n=stack1.size();
        for (int i=0;i<n;i++){
            stack3.add(stack1.get(n-i-1));
        }
        for(int i=0;i<n;i++){
            stack3.add(stack2.get(i));
        }
        stack3.add(temp1.parent);
        return stack3;
    
    }
    
    /**
    *
    * @author-axm156630
    */
    //Return a list of vertex in which the first and 
    //the last element is the LCA of the two same level nodes
    List<Vertex> oddLengthCycle(Graph g) { 
        for (Vertex u : g) {
            u.seen = false;
            u.parent = null;
            u.distance = Integer.MAX_VALUE;
        }    
        
        Queue<Vertex> Q = new LinkedList<>();
	// Run BFS on every vertex
	for (Vertex src : g) {
	    if (!src.seen) {
		src.distance = 0;
		Q.add(src);
		src.seen = true;

		while (!Q.isEmpty()) {
		    Vertex u = Q.remove();
		    for (Edge e : u.Adj) {
			Vertex v = e.otherEnd(u);
			if (!v.seen) {
			    v.seen = true;
			    v.parent = u;
			    v.distance = u.distance + 1;
			    Q.add(v);
			} else {
                            //if we have got whose distance is the same 
                            //then that means they are on the same level
                            //and since they are also connected, which means 
                            //have got an odd length cycle
			    if (u.distance == v.distance) {
                                //function which returns the cycle
				return oddLengthCycleUtil(u, v);
			    }
			}
		    }
		}
	    }
	}
	return new ArrayList<Vertex>();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
	if (args.length > 0) {
	    File inputFile = new File(args[0]);
	    in = new Scanner(inputFile);
	} else {
	    in = new Scanner(System.in);
	}

	// read the graph related parameters
	int n = in.nextInt(); // number of vertices in the graph
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = new Graph(n);
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    g.addEdge(u, v, w);
	}

        List<Vertex> cycle= new Solution().oddLengthCycle(g);
        if(cycle.size()!=0){
		for (Vertex v:cycle){
        	    System.out.println(v.name);
        	}
         }
	else{
		System.out.println("Graph is bipartite");
	}
        new Solution().testEulerian(g);
    }
    
}

