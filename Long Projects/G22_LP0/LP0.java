import java.io.*;
import java.util.*;
import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aditya
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
            V[a].adjSize++;
            V[b].adjSize++;
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
 class Edge {
	public Vertex From; // head vertex
	public Vertex To; // tail vertex
	public int Weight;// weight of the arc
        public boolean visited;//boolean value checking if the edge has been visted or not
        

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
class Vertex {
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj; // adjacency list; use LinkedList or ArrayList
        public int idxAdj=0;
        public int adjSize=0;

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

public class LP0 {

    
    static Vertex start;//stores the starting vertex of the path or the tour
    
    

     static List<Edge> findHierholzerPath(Vertex start) {
        //maintaing two stacks,stack1 and stack2
        //stack1 stores the current path and the 
        //stack2 is used to stich together the various
        //paths we store in stack1
        Stack<Edge> stack1 =new Stack<Edge>();
        Stack<Edge> stack2 =new Stack<Edge>();
        Edge e = getUnvisitedEdge(start);
        
        //prev stores the vertex of the edge we are currently at
        //next stores the other end of the edge we are currently at
        Vertex prev=start;
        Vertex next;
        while (e!=null) {
            stack1.push(e);
            next=e.otherEnd(prev);
            //setting the order of the vertices, the way they are visited
            e.From=prev;
            e.To=next;
            e = getUnvisitedEdge(next);
            prev=next;
        }
          
        prev=stack1.peek().To;

        while ( ( !stack1.isEmpty() ) ) {
            e = stack1.pop();
            stack2.push(e);
            next=e.otherEnd(prev);
            e = getUnvisitedEdge(next);
            prev=next;
            while (e!=null) {
                stack1.push(e);
                next=e.otherEnd(prev);
                e.From=prev;
                e.To=next;
                e = getUnvisitedEdge(next);
                prev=next;
            }
        }
 
        List<Edge> path = new LinkedList<Edge>();
        while(!stack2.isEmpty()) {
            Edge edge = stack2.pop();
            path.add(edge);
        }
        return path;
    }
     
    //finds the first unvisited vertex adjacent to the given vertex
       static Edge getUnvisitedEdge(Vertex n) {
        while(n.idxAdj<n.adjSize ){
            if(n.Adj.get(n.idxAdj++).visited==false)
            {
                n.Adj.get(n.idxAdj-1).visited=true;
                return n.Adj.get(n.idxAdj-1);
            }
        }
        return null;
    }
    
    //check if edge exists between two vertices
    static boolean edgeExists(Vertex A,Vertex B){
        for (Edge e:A.Adj){
            if(e.otherEnd(A)==B)
                return true;
        }
        return false;
    }   
   

    
    public void printPath(List<Edge> path){
       
        for (int i=0; i<path.size(); i++) {
            System.out.println("("+path.get(i).From.name + "," + path.get(i).To.name + ")");
        }
       
    }

    
    //checks whether an edge has the given vertex
    static boolean hasVertex(Edge a,Vertex v){
        if(a.From==v || a.To==v)
        return true;
        return false;
    }
    //check whether a tour is valid or not
    static boolean verifyTour(Graph g, List<Edge> tour,Vertex start) {
        Vertex temp=start;
        for(Edge e:tour){
                if(hasVertex(e, temp))//checking whether the given edge has the ending edge of the previous vertex
                    temp=e.otherEnd(temp);//update the ending vertex
                else
                    return false;
           }
        for(Vertex v :g){
            if(getUnvisitedEdge(v)!=null){
                return false;
            }
        }
        //if the starting vertex is the same as the ending vertex
        if(temp==start){
        return true;
        }
        return false;
    }
    
    
    //running BFS on the graph 
    void runBfs(Graph g){
      Queue<Vertex> Q = new LinkedList<>();
	// Run BFS on every component
                Vertex src=g.V[1];
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
    

    //Initializing the distances,parent and the seen flag
    void initDistPar(Graph g){
            for (Vertex u : g) {
            u.seen = false;
            u.parent = null;
            u.distance = Integer.MAX_VALUE;
        } 
    }
    

   static List<Edge> findEulerTour(Graph g) { 
        
       new LP0().initDistPar(g);
       new LP0().runBfs(g);
        
        //check if any vertex of the graph has remained unseen.
        //If it is the graph is not connected
        for (Vertex u : g) {
            if(u.seen == false)
            {
                System.out.println("Graph is not Connected");
                return null;
            }
        }
        
        int nodd=0; //number of vertices with odd degree
        List<Vertex> l=new ArrayList<Vertex>();//an array which stores the name of vertices with odd degree
        
        for(Vertex u:g){
            //if the size of the adjacency list is odd add it to l
            if(u.Adj.size()%2==1){
                nodd=nodd+1;
                l.add(u);
            }
        }
        
        //if all the nodes have even degree then the graph is eularian
        if(nodd==0){
            System.out.println("Graph is Eularian");
            start=g.V[1];
            return findHierholzerPath(g.V[1]);
        }
        
        //if there are only two vertices with odd  degree then the eularian path
        //is between those two vertices
        if(nodd==2){
            
            System.out.println("Graph has two odd vertices");
            if(l.get(0).name<l.get(1).name)
            {
                start=l.get(0);
                return findHierholzerPath(l.get(0));
            }
            else
            {
                start=l.get(1);
                return findHierholzerPath(l.get(1));
            }
        }
        
        //if the number of odd vertices is more than 2 then there is no eularian path
        System.out.println("Graph is not Eularian.");
        return null;
    
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
        long e1 = System.currentTimeMillis();
        List<Edge> path= new LP0().findEulerTour(g);
        long e2 = System.currentTimeMillis();
        System.out.print("Time taken in ms :");
        System.out.println(e2-e1);
        if(path!=null){

              new LP0().printPath(path);

                if(verifyTour(g, path,start)){
                    System.out.println("It's a valid tour");
                }
                else{
                    System.out.println("It's an euler path not a valid tour");
                }
        }
        


    }
    
}
