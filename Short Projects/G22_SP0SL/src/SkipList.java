/**
 * Implements SkipList
 * @author Shweta, Radhika, Divyanshu, Aditya
 */

import java.util.ArrayList;

public class SkipList<T extends Comparable<? super T>>{
	private SkipEntry head;
	private SkipEntry tail;
	private SkipEntry level;
	int maxLevel = 0;
	public static final int INF = Integer.MAX_VALUE;// infinity
	//Constructor
	public SkipList(){
		maxLevel = 10;
		//Define Head and tail elements
		tail = new SkipEntry<Integer>(INF,maxLevel);
		head = new SkipEntry<Integer>(0,maxLevel);
		for(int i = 0; i <= maxLevel -1; i++){
			tail.prev.add(i, head);
			head.next.add(i, tail);
		}
	}
	
	//Find helper function
	public <T> ArrayList<SkipEntry> find(T x){
		ArrayList<SkipEntry> prevs = new ArrayList<SkipEntry>();
		for(int i = 0; i <= maxLevel -1; i++){
			prevs.add(new SkipEntry(0,0));
		}
		SkipEntry p = head;
		//For each level in the skip entry
		int i = 0;
		for(i = maxLevel-1; i >= 0; i--){
			while(((SkipEntry)p.next.get(i)).element.compareTo(x)<0){
				p =(SkipEntry) p.next.get(i);
			}
			prevs.set(i, p);
		}
		return prevs;
	}
	
	/*
	 * Contains will search for the element and return true if present otherwise false
	 * @param x 
	 */	
	
	public boolean contains(T  x){
		@SuppressWarnings("rawtypes")
		ArrayList<SkipEntry> prev = find(x);
		return (((SkipEntry)prev.get(0).next.get(0)).element == x );
	}
	/*
	 * Add funtion to insert the given element
	 * @param x 
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(T x){
		int lev= choice();
		ArrayList<SkipEntry> prevs = find(x);
		if(((SkipEntry)((SkipEntry)prevs.get(0)).next.get(0)).element.compareTo(x) ==0){
			((SkipEntry)prevs.get(0).next.get(0)).element=x;  
		}	
		else{
			SkipEntry n= new SkipEntry(x,lev);
			if(prevs.size()-1 < lev) {
				resetNextPrevForAdd(prevs, n, 0, prevs.size()-1);
			}else {
				for(int i = 0; i <= lev; i++){
					SkipEntry temp = prevs.get(i);				
					n.next.add(temp.next.get(i));
					temp.next.set(i, n);
					((SkipEntry)n.next.get(i)).prev.set(i, n);
					n.prev.add(temp);
				}
			}
		}
	}	
	
	//Helper function to add the prev and next field to the nodes
	private void resetNextPrevForAdd(ArrayList<SkipEntry> prevs, SkipEntry n, int startLevel, int endLevel) {
		int i = 0;
		for(i = startLevel;i <= endLevel; i++){
			SkipEntry temp = prevs.get(i);				
			n.next.add(temp.next.get(i));
			temp.next.set(i, n);
			((SkipEntry)n.next.get(i)).prev.set(i, n);
			n.prev.add(temp);
		}
		if(prevs.size()-endLevel+1 < n.next.size()-endLevel+1) {
			resetNextPrevForAdd(prevs.get(i).prev, n, endLevel+1, (prevs.get(i).prev.size() < n.next.size())?prevs.get(i).prev.size()-1:n.next.size()-1);
		}
	}
	
	//generates a random lvel for the new element
	private int choice() {
			int level = 0 + (int)(Math.random() * maxLevel); 
		return level;
	}
	
	/*
	 * Remove function will delete the element from the SkipList and indicate
	 * @param x
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SkipEntry remove(T x){
		ArrayList<SkipEntry> prevs = find(x);
		SkipEntry n = (SkipEntry)prevs.get(0).next.get(0);
		if (n.element != x){
			return null;
		}
		else{
			for (int i = 0; i <= maxLevel; i++){
				if (((SkipEntry)((SkipEntry)prevs.get(i)).next.get(i)) == n){
					ArrayList<SkipEntry> temp = prevs.get(i).next;
					temp.set(i, (SkipEntry) n.next.get(i)); 
				}
				else break;
			}
			System.out.println("returned"+n.element);
			return n;
		}
	}

	/*
	 * SkipEntry class for the data-structure
	 */	
	class SkipEntry<T extends Comparable<? super T>>{
		T element;
		ArrayList<SkipEntry> prev;
		ArrayList<SkipEntry> next;

		public SkipEntry(T x, int lev) {
			element = x;
			this.prev = new ArrayList<SkipEntry>(lev);
			this.next = new ArrayList<SkipEntry>(lev);
		}
	}
}