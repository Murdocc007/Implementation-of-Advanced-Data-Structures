/*
 * Radhika Simant
 * Net Id : rrs150130@utdallas.edu
 * 
 * */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DAGTopologicalOrder {

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.num_of_vertex =7;
		// initialize the vertices
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		Vertex g = new Vertex("G");
		// add the adjacent nodes 
		a.Adj.add(e);
		e.inDegree++;
		a.Adj.add(c);
		c.inDegree++;
		e.Adj.add(b);
		b.inDegree++;
		e.Adj.add(g);
		g.inDegree++;
		c.Adj.add(g);
		g.inDegree++;
		b.Adj.add(d);
		d.inDegree++;
		b.Adj.add(f);
		f.inDegree++;
		g.Adj.add(d);
		d.inDegree++;
		g.Adj.add(f);
		f.inDegree++;
		/*
		 * Uncomment the below two lines to check for a DAG.
		 * The below two lines code add a cycle in the graph
		 * Hence the program should return that the graph is not a DAG.
		 * */
		//f.Adj.add(a);
		//a.inDegree++;
		
		//Add the nodes to the graph
		graph.vertex.add(a);
		graph.vertex.add(b);
		graph.vertex.add(c);
		graph.vertex.add(d);
		graph.vertex.add(e);
		graph.vertex.add(f);
		graph.vertex.add(g);
		
		System.out.println("The topological order of the given graph is by Algorith 1 is : ");
		topologicalOrdering1(graph);
		System.out.println();
		System.out.println("The topological order of the given graph is by Algorith 2 is : ");
		Stack<Vertex> DFSStack = new Stack<Vertex>();
		DFSStack = topologicalOrdering2(graph);
		for(int i=DFSStack.size()-1;i>=0;--i){
			System.out.print("\t" + DFSStack.elementAt(i));
		}
		

	}
	/*
	 * Remove vertices with no incoming edges(inDegree = 0) , one at a
     *	 time, along with their incident edges, and add them to a queue.
     */
	public static void topologicalOrdering1(Graph graph){
		
		Queue<Vertex> sortedList = new LinkedList<Vertex>();
	
		int topVertex = 0;
		boolean checkDAG = checkForDAG(graph);
		if(!checkDAG){
			System.out.println("The given graph is not a DAG");
			
		}
		else{
		for(Vertex vertex : graph.vertex){
			
			if(vertex.inDegree==0){
				++topVertex;
				sortedList.add(vertex);
				
				System.out.print("\t"+ vertex.name);
				
			}
			
		}
		while(!sortedList.isEmpty()){
			Vertex vt = sortedList.poll();
			 for(Vertex adjVertex : vt.Adj){
	                --adjVertex.inDegree;
	                if(adjVertex.inDegree==0){
	                    sortedList.add(adjVertex);
	                    ++topVertex;
	                    System.out.print("\t"+adjVertex.name);
	                }
	            }
		}
		
		}
			
	}
	/*
	 *  Method which runs DFS on gragh and pushes nodes to a stack in the order in which they finish
     */
	public static Stack<Vertex> topologicalOrdering2(Graph graph){
		
		Stack<Vertex> topOrder = new Stack<Vertex>();
		
		boolean checkDAG = checkForDAG(graph);
		if(!checkDAG){
			System.out.println("The given graph is not a DAG");
			
		}
		else{
		for(Vertex vertex : graph.vertex){
			vertex.seen = false;
			vertex.parent = null;
		}
		
		for(Vertex vertex : graph.vertex){
			if(!vertex.seen){
				topOrder = DFSVisit(vertex,topOrder);
			}
		}
		}
		return topOrder;
		
	}
	// Function which is used for every visit of a vertex in a graph
	public static Stack<Vertex> DFSVisit(Vertex vertex, Stack<Vertex> stack){
		vertex.seen = true;
		
		for(Vertex vt : vertex.Adj ){
			if(!vt.seen){
				vt.parent = vertex;
				DFSVisit(vt,stack);
			}
		}
	
		stack.push(vertex);
		return stack;
	}
	
	/* Function which checks whether the given graph is a DAG or not.
	 * Assumption : If there is no node having inDegree = 0 , then the graph is not a DAG
	 */
	public static boolean checkForDAG(Graph graph){
		int count =0;
		for(Vertex vertex : graph.vertex){
			if(vertex.inDegree==0){
				count ++;
			}
		}
		if(count !=0)
			return true;
		else
			return false;
	}

}
