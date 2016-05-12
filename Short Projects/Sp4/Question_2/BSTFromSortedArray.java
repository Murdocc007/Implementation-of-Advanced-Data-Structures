/*
 * Group : 22
 * Names : Radhika Simant
 *         Divyanshu Paliwal
 *         Aditya Mahajan
 *         Shweta Nair
 * 
 * This class has the method to generate the Binary search tree from a sorted array
 * The sorted array provided : 1,2,3,4,5,6,7
 * 
 * The tree which should be constructed is :
 * 
 * 				4
 *         2         6
 *       1   3     5   7
 * */
public class BSTFromSortedArray {

	public static void main(String[] args) {
		int[] sortedArray = {1,2,3,4,5,6,7};
		Node<Integer> root;
		// calling function sortArrayToBST 
		root = sortedArrayToBST(sortedArray,0,sortedArray.length-1);
		System.out.println("The pre-order traversal of the tree is : ");
		//Printing the tree in pre-order through which the tree generated can be verified
		preOrder(root);
			
	}
	/*
	 * This method takes in an arr and the lower and upper index of the array
	 * and generates a BST from that array.
	 * 
	 * The logic here is : 
	 * If the array is sorted, then the middle element is the root,
	 * the elements left to it are in its left sub tree
	 * and the elements on the right are in its right subtree
	 * 
	 * Hence, we have to recursively check for the middle element in the array,
	 * and keep on building the tree, unless we reach to the root node.
	 * */
	public static Node<Integer> sortedArrayToBST(int[] arr,int low,int high){
	
		if(low>high)
			return null;
		// calculating the middle index for the array
		int mid = (high + low)/2;
		Node<Integer> node = new Node<Integer>(arr[mid]);
		//recursively call sortedArrayToBST on left and right side of the middle element
		node.setLeft(sortedArrayToBST(arr,low,mid-1));
		node.setRight(sortedArrayToBST(arr,mid+1,high));
		return node;
	}
	/*
	 * This method gives a pre-order traversal of the nodes.
	 * This method is used to verify the correct construction of the tree
	 * 
	 * in pre-order traversal :
	 * 1) Visit the node
	 * 2) Visit left subtree
	 * 3) Visit right subtree
	 * 
	 *  
	 * */
	 public static void preOrder(Node<?> node) {
	        if (node == null) {
	            return;
	        }
	        System.out.print(node.getData() + " ");
	        preOrder(node.getLeft());
	        preOrder(node.getRight());
	    }
}
