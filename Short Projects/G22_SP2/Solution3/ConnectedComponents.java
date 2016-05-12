import java.util.*;
/*
 * Class to find strongly connected components in the graph
 * */
public class ConnectedComponents {
	
	Stack<Vertex> stack = new Stack<>();
	
//DFS on graph called inside method DFS2()
	public void DFS(Graph G){
		
		for(Vertex u:G){
			u.seen = false;
			u.parent = null;
		}
		//call DFSVisit if vertex not seen
		for(Vertex u:G){
			if(!u.seen){
				DFSVisit(u);
			}
			
		}
	}
//recursively calling method to add each vertex to the stack in finish time order
	public void DFSVisit(Vertex u){
		u.seen = true;
		//for each directed edge getting vertex at the other end.
		for(Edge e:u.Adj){
			Vertex v= e.otherEnd(u);
			if(!v.seen){
				v.parent = u;
				//recur if not visited before
				DFSVisit(v);
			}
		}
		stack.push(u);
	}
//DFS on transposed graph
	public void DFS2(Graph G){
//running DFS and adding each vertex of graph to the stack in finish time order
		DFS(G);
		//No. of components in the graph
		int cno = 0;
		//seen = false for each vertex in the graph
		for(Vertex u:G){
			u.seen = false;
			u.parent = null;
		}
		//pop element from stack and call DFSVisit2()
		while(!stack.empty()){
			Vertex u = stack.pop();
			if(!u.seen)
				DFSVisit2(u, ++cno);
		}
		//printing each vertex along with its component
		for(Vertex u:G){
			System.out.println("Vertex:"+u+" Component:"+u.cno);
		}
		
	}
	//recursively calling method to find strongly connected components.
	public void DFSVisit2(Vertex u, int cno){
		u.seen = true;
		//adding component to the vertex
		u.cno = cno;
		//running on reverse edge
		for(Edge e:u.revAdj){
			Vertex v= e.otherEnd(u);
			if(!v.seen){
				DFSVisit2(v, cno);
			}
		}
	}
	
	/*
	 * Main method creating an object of class Graph to read the graph and
	 * class ConnectedComponents to find strongly connected components in the graph
	 * */

	public static void main(String[] args) {
		
		Scanner in;
		int size = 0;
		//size: No. of vertices
		System.out.println("Enter the size of graph:");
		Graph g = new Graph(size);
		in = new Scanner(System.in);
		size = in.nextInt();//size of the graph
		ConnectedComponents c = new ConnectedComponents();
		c.DFS2(g.readGraph(in));
		in.close();

	}

}
