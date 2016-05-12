Long Project 3 - Level 2:

The files for level2 are included in Level2 folder.

To execute level2 of Long Project 3 , follow the below steps :

>> cd Level2
>> javac *.java
>> java LP3_Level2 file

The program has to be given input graph from the command prompt :

Sample Input : (Without negative cycle)

7 8
1 2 2
1 3 3
2 4 5
3 4 4
4 5 1
5 1 -7
6 7 -1
7 6 -1

The graph doesn't have a negative cycle, hence the output for the above input will be :

7
1 0 1
2 2 1
3 3 1
4 7 2
5 8 2
6 INF 0
7 INF 0

Sample Input : (With negative cycle)

7 8
1 2 2
1 3 3
2 4 5
3 4 4
4 5 1
5 1 -9
6 7 -1
7 6 -1

The graph has a negative cycle, hence the output for the above input will be :

(4,5)

(3,4)

(1,3)

(5 1)
Non-positive cycle in graph. DAC is not applicable





