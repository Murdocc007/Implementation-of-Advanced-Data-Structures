/*
 * Group : 22
 * Names : Radhika Simant
 *         Divyanshu Paliwal
 *         Aditya Mahajan
 *         Shweta Nair
 * 
 * This class is for a node of a tree.
 * A node has 3 attributes : data , left node , right node
 * 
 * */
public class Node<T> {
	private T data;
	private Node<T> left;
	private Node<T> right;
	
	public Node(T data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * @return the left
	 */
	public Node<T> getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public Node<T> getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + data;
	}
	
	
}
