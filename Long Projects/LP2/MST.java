/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MST {

	/**
	 * @param g
	 * @return weight of minimum spanning tree.
	 *
	 */
	static int PrimMSTBinary(Graph g) {
		int wmst = 0;
		Vertex src = g.verts.get(1);

		// Code for Prim's algorithm needs to be written
		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
		}
		src.seen = true;
		// PriorityQueue<Edge> q = new PriorityQueue<>();
		ComparatorEdge ce = new ComparatorEdge();
		// comparator for comparing edges.
		BinaryHeap<Edge> q = new BinaryHeap<>(g.noOfEdges, ce);
		Vertex temp;
		for (Edge e : src.Adj) {
			q.add(e);
		}
		while (q.size > 0) {

			Edge e = q.remove();
			if (e.From.seen && e.To.seen) {
				continue;
			}

			if (e.From.seen == true) {
				temp = e.To;
				e.To.seen = true;
			} else {
				temp = e.From;
				e.From.seen = true;
			}

			wmst = wmst + e.Weight;

			for (Edge f : temp.Adj) {
				Vertex w = f.otherEnd(temp);
				if (!w.seen) {
					q.add(f);
				}
			}
		}

		return wmst;
	}

	static int PrimMSTIndexed(Graph g) {
		int wmst = 0;
		Vertex src = g.verts.get(1);

		// Code for Prim's algorithm needs to be written
		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
			u.d = Integer.MAX_VALUE;
		}
		src.d = 0;
		// PriorityQueue<Edge> q = new PriorityQueue<>();
		ComparatorVertex ce = new ComparatorVertex();
		// comparator for comparing edges.
		IndexedHeap<Vertex> q = new IndexedHeap<>(g.numNodes, ce);
		Vertex temp;
		for (Vertex u : g) {
			q.add(u);
		}

		while (q.size > 0) {
			Vertex u = q.remove();
			u.seen = true;
			wmst = wmst + u.d;
			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				if (!v.seen && e.Weight < v.d) {
					v.d = e.Weight;
					v.parent = u;
					q.percolateUp(v.getIndex());
				}
			}
		}

		return wmst;
	}

	static void MakeSet(Vertex u) {
		u.parent = u;
		u.rank = 0;
		u.seen = false;
	}

	static Vertex Find(Vertex x) {
		if (x != x.parent) {
			x.parent = Find(x.parent);
		}
		return x.parent;
	}

	static void Union(Vertex x, Vertex y) {
		if (x.rank > y.rank)
			y.parent = x;
		else if (x.rank < y.rank)
			x.parent = y;
		else {
			x.parent = y;
			y.rank++;
		}

	}

	static int Kruskals(Graph g) {
		ComparatorEdge ce = new ComparatorEdge();
		ArrayList<Edge> edges = new ArrayList<>();
		int wmst = 0;
		for (Vertex u : g) {
			MakeSet(u);
		}
		BinaryHeap<Edge> q = new BinaryHeap<>(g.noOfEdges, ce);
		// IndexedHeap<Edge> q = new IndexedHeap<>(g.noOfEdges, ce);
		// sort in non-decreasing order
		// for (Vertex u : g) {
		// for (Edge e : u.Adj) {
		// Vertex v = e.otherEnd(u);
		// if (!v.seen)
		// q.add(e);
		// }
		// u.seen = true;
		// }
		for (Edge e : g.edges) {
			q.add(e);
		}
		int i = g.noOfEdges;
		// add edges in non-decreasing order to list
		while (i >= 1) {
			edges.add(q.remove());
			i--;
		}
		for (Edge e : edges) {
			Vertex ru = Find(e.From);
			Vertex rv = Find(e.To);
			if (ru != rv) {
				wmst = wmst + e.Weight;
			}
			Union(ru, rv);
		}

		return wmst;

	}

	public static void main(String[] args) throws FileNotFoundException {

		// input file
		File f = new File("C:/Users/divya/workspace/LP2/src/input1.txt");
		Scanner in = new Scanner(f);
		System.out.println("Prims");
		Graph g = Graph.readGraph(in, false);
		long e1 = System.currentTimeMillis();
		// System.out.println("MST weight using binary heap");
		// System.out.println(PrimMSTBinary(g));
		long e2 = System.currentTimeMillis();
		System.out.println("Total Time:");
		System.out.println(e2 - e1);
		// e1 = System.currentTimeMillis();
		// System.out.println("MST weight using indexed heap");
		// System.out.println(PrimMSTIndexed(g));
		// e2 = System.currentTimeMillis();
		// System.out.println("Total Time:");
		// System.out.println(e2 - e1);
		System.out.println("MST weight using kruskal's algorithm");
		e1 = System.currentTimeMillis();
		System.out.println(Kruskals(g));
		e2 = System.currentTimeMillis();
		System.out.println("Total Time:");
		System.out.println(e2 - e1);
	}
}
