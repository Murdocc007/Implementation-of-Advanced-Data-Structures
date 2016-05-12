/*
 * Group : 22
 * Names : Radhika Simant
 *         Divyanshu Paliwal
 *         Aditya Mahajan
 *         Shweta Nair
 * 
 * This class has the method of level order traversal of a BST implemented.
 * 
 * */
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

	public static void main(String[] args) {
		/*
		 * Initializing the tree
		 * 
		 * */
		Node<Integer> root = new Node<Integer>(12);
		Node<Integer> left = new Node<Integer>(10);
		Node<Integer> leftleft = new Node<Integer>(8);
		Node<Integer> leftright = new Node<Integer>(11);
		Node<Integer> right = new Node<Integer>(15);
		Node<Integer> rightleft = new Node<Integer>(13);
		Node<Integer> rightright = new Node<Integer>(17);
		root.setLeft(left);
		root.setRight(right);
		left.setLeft(leftleft);
		left.setRight(leftright);
		right.setLeft(rightleft);
		right.setRight(rightright);
		Comparable[] levelOrder = new Comparable[7];
		System.out.println("The level order traversal of the given tree is : ");
		
		levelOrder = levelOrderTraversal(root,levelOrder);
	}
	/*
	 * This method takes generates a level order traversal of the BST defined.
	 * The tree given in the main method is : 
	 *             12
	 *          10    15  
	 *         8  11 13 17
	 * The level order traversal of the above tree will be :
	 * 12 10 15 8 11 13 17        
	 * 
	 * */
	public static Comparable[] levelOrderTraversal(Node<Integer> root,Comparable[] arr){
		
		// Initialize a queue. 
		Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();
		//index to the array
		int index =0;
		//keep on adding the root node in the queue
		queue.add(root);
		//continue till the queue is not empty
		while(!queue.isEmpty()){
			Node<Integer> node = queue.poll();
			arr[index] = node.getData();
			System.out.print(arr[index] + "\t");
			index = index +1;
			//if the node has left node add that to the queue
			if(node.getLeft()!=null){
				queue.add(node.getLeft());
			}
			// if the node has right node add that to the queue
			if(node.getRight()!=null){
				queue.add(node.getRight());
			}
		}
		
		return arr;
		
	}

}
