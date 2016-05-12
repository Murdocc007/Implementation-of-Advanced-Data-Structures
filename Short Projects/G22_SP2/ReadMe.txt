Following is the content of the ReadMe file :

1) Solution1 folder - has the solution to problem 1

Solution1 folder contains 3 java files :
1)	DAGTopologicalOrder.java
2)	Vertex.java
3)	Graph.java
 
The input is given in the program iteself. Both the algorithms return the topological ordering of the same graph.

To execute program follow the below commands :
>> javac DAGTopologicalOrder.java
>> java DAGTopologicalOrder

The program will give as an output topologically sorted array.
Two algorithms are implemented in the program - DAGTopologicalOrder.java
Vertex.java - is a class for Vertex 
Graph.java - is a class for Graph 

-------------------------------------------------------------------------------------------------------------------------------------------------------

2) Solution2 folder - has the solution to problem 2

Solution2 folder contains following files :
- Program3.java
- Graph.java
- Vertex.java
- Edge.java
- input.txt 

The program directly takes txt file as input.

To execute the program run the following commands:
>> javac Graph.java
>> java Graph

Output of the program looks like :

Diameter of Given Tree is(#edges between two farthest Nodes )  7

-------------------------------------------------------------------------------------------------------------------------------------------------------

3) Solution3 folder - has the solution to problem 3

- ConnectedComponents.java is the main class.
- Graph.java is for representing a graph
- Vertex.java is for representing a vertex of the graph
- Edge.java is for representing an edge of the graph

to execute the program run the following commands:

>> javac ConnectedComponents.java
>> java ConnectedComponents

Sample command line test case:

>javac ConnectedComponents.java
>java ConnectedComponents


Enter the size of graph:
4
Enter the no. of vertices:
4
Enter the no. of edges:
4
Enter the head vertex:
1
Enter the tail vertex
2
Enter the head vertex:
2
Enter the tail vertex
3
Enter the head vertex:
3
Enter the tail vertex
1
Enter the head vertex:
2
Enter the tail vertex
4
Vertex:1 Component:1
Vertex:2 Component:1
Vertex:3 Component:1
Vertex:4 Component:2

--------------------------------------------------------------------------------------------------------------------------------------------------------

4) Solution4-5 folder - has the solution to problem 4 and problem 5

The file Solution.java contains the solutions to problem 4 and 5

To execute the program run the following commands :

>> javac Solution.java
>> java Solution

It contains two functions 
1.testEulerian: This takes graph as an argument and returns one of the four possible outcomes;
		that are:
			a)Graph is Eulerian.
   			b)Graph has an Eulerian Path between vertices ?? and ??.
   			c)Graph is not connected.
   			d)Graph is not Eulerian.  It has ?? vertices of odd degree.
   		Example: Input: 3 3
				1 2 3
				2 3 4
				1 3 5
			Output:Graph is Eularian
   			
2.oddLengthCycle: This take the graph as an argument an return a cycle of odd length.The first and the last node is the LCA 
		  of the vertices on the same level. 
		Example: Input: 3 3
				1 2 3
				2 3 4
				1 3 5
			Output:	1
				2
				3
				1
