/*Diameter of a tree.
   In this problem, you are given an unrooted tree as input.  Since
   the tree may not be a binary tree, we will represent it with an
   adjacency list (i.e., it is a graph that happens to be a tree).
*/
//considering distance between two nodes is number of edges between them
//if you want number of nodes as distance then please intialize count=1 in BFS2 method
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Problem3 {

	public int diameter(Graph g) {
		//choose arbitrary root for BFS
	
		Random rand=new Random();
	    int randomRoot=rand.nextInt(g.numNodes-1)+1; // choose random root
	    Vertex source=g.verts.get(randomRoot);
	        
	    Vertex z=BFS(source);		
		 
	   
		for(int i=1;i<=g.numNodes;i++) //making visibility of vertices to false for BFS2 for giving correct result
		{
			g.verts.get(i).seen=false;
		}
	  
		int diameterDistance=BFS2(z);
		return diameterDistance;
		
	
	}

	private Vertex BFS(Vertex source) {
	Vertex u=null;

	Queue<Vertex> q= new LinkedList<>(); // queue contains vertex that are seen
	q.add(source);
	source.seen=true;
	while(!q.isEmpty()){
	u=q.remove();
	for(Edge e:u.Adj){
		Vertex v=e.otherEnd(u);
		if(v.seen==false){
		v.seen=true;
		q.add(v);
		}		
	}				
}

	
	return u;	
	
	
	
	}

	private int BFS2(Vertex source) {
		Vertex u=null;
		int count=0;
     	Queue<Vertex> q= new LinkedList<>(); // queue contains vertex that are seen
		q.add(source);
		source.seen=true;
		
		while(!q.isEmpty()){
		u=q.remove();
	
		for(Edge e:u.Adj){
			Vertex v=e.otherEnd(u);
			if(v.seen==false){
			v.seen=true;
			v.parent=u;
			q.add(v);
			}		
		}	
				
		}	
		// for finding max distance from last element in queue and source
		while(u!=source){
			u=u.parent;
			count++;
		}	
		
		return count;		
	}

}
