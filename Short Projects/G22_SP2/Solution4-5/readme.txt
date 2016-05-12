The file Solution.java contains the solutions to problem 4 and 5
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
