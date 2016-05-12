/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

public class SinglyLinkedList<T> {
    public class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    Entry<T> header, tail;
    int size;

    SinglyLinkedList() {
        header = new Entry<>(null, null);
        tail = null;
        size = 0;
    }

    void add(T x) {
        if(tail == null) {
            header.next = new Entry<>(x, header.next);
            tail = header.next;
        } else {
            tail.next = new Entry<>(x, null);
            tail = tail.next;
        }
	size++;
    }

    void printList() {
        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
        System.out.println();
    }

    void unzip() {
	if(size < 3) {  // Too few elements.  No change.
	    return;
	}

	Entry<T> tail0 = header.next;
	Entry<T> head1 = tail0.next;
	Entry<T> tail1 = head1;
	Entry<T> c = tail1.next;
	int state = 0;

	// Invariant: tail0 is the tail of the chain of elements with even index.
	// tail1 is the tail of odd index chain.
	// c is current element to be processed.
	// state indicates the state of the finite state machine
	// state = i indicates that the current element is added after taili (i=0,1).
	while(c != null) {
	    if(state == 0) {
		tail0.next = c;
		tail0 = c;
		c = c.next;
	    } else {
		tail1.next = c;
		tail1 = c;
		c = c.next;
	    }
	    state = 1 - state;
	}
	tail0.next = head1;
	tail1.next = null;
    }
    
    //this will unzip the array based on the integer k.
    //It will basically create k separate list. We can
    //store the end points of the k sequences in an array
    //and keep on assigning the element next to the last end
    //point to the current head, iteratively.
    //For example consider 1, 2, 3, 4, 5, 6, 7 and k=3
    //1, 2 and 3 are the heads of the 3 sequences.
    //first we will assign the element next to 3 to the first list,
    //list will become 1,4,2,3,5,6,7 and the heads become 4,2 and 3.
    //Then we will assign element next to 3 to the second sequence.
    //The lis will then become 1,4,2,5,3,6,7 and the heads are 4,5,6.
    //We will continue this process till the element next to last head becomes null
    
    
    void multiUnzip(int k){
        if(size<k+1){
            return;
        }
        //first element of the linked list
        Entry<T> tail1=header.next;
        //creating an arraylist which will store the end pointers of the k sequences created
        ArrayList<Entry<T>>head=new ArrayList<Entry<T>>();
        for(int i=0;i<k;i++){
            head.add(tail1);
            tail1=tail1.next;
        }
        
        while(head.get(k-1)!=null && head.get(k-1).next!=null){
            for(int i=0;i<k-1 && head.get(k-1).next!=null;i++){
                Entry<T> temp=head.get(i).next;//storing the element next to current head
                head.get(i).next=head.get(k-1).next;//assigning the element next to last head to the current head
                head.get(k-1).next=head.get(k-1).next.next;
                head.get(i).next.next=temp;
                head.set(i, head.get(i).next);//setting the new head
            }
            head.set(k-1, head.get(k-1).next);//setting the new head 
        }
    }
    
    //function that reverses the linked list iteratively
    void itrReverse(){
        Entry<T> prev=null;
        Entry<T> curr=header.next;//current node
        Entry<T> next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        header.next=prev;
    }
    
    //utility function that reverses the linked list recursively,takes two arguments, previous and the current node
    void recReverseUtil(Entry<T> prev,Entry<T> curr){
    if (curr.next == null) {
        header.next=curr;
        curr.next=prev;
        return;
    }
        recReverseUtil(curr, curr.next);
        curr.next=prev;
    }
    
    //function that reverses the linked list recursively
    void recReverse(){
        if(header.next!=null)
            recReverseUtil(null,header.next);
    }
    //utility function that prints the reversed linked list recursively
    void printRecReverseUtil(Entry<T> prev,Entry<T> curr){
        if(curr.next==null){
            System.out.println(curr.element);
            return;
        }
        printRecReverseUtil(curr, curr.next);
        System.out.println(curr.element);
    }
    //print the reverse linked list recursively
    void printRecReverse(){
        if(header.next!=null)
            printRecReverseUtil(header,header.next);
    }
    
    //print the reverse linked list iteratively
    void printItrReverse(){
        Stack<Entry<T>> stack=new Stack<Entry<T>>();
        Entry<T> temp;
        temp=header.next;
        while(temp!=null){
            stack.push(temp);
            
            temp=temp.next;
        }
        
        while(!stack.empty()){
            System.out.println(stack.pop().element);
        }
    }

    public static void main(String[] args) {
        int n = 10;
        int k=2;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
            k=Integer.parseInt(args[1]);
        }

        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        //original list
        System.out.println("Original list");
        lst.printList();
        
        //print the list reversed iteratively
        System.out.println("Iteratively printing");
        lst.printItrReverse();
        
        //print the list reversed recursively
        System.out.println("Recursively printing");
        lst.printRecReverse();
        
        //reverse the linked list iteratively
        System.out.println("Reversing the linked list iteratively");
        lst.itrReverse();
        lst.printList();
        
        //reverse the linked list recursively
        System.out.println("Reversing the linked list recursively");
        lst.recReverse();
        lst.printList();
        
        //unzip the linked list
        System.out.println("Unzip the linked list");
	lst.multiUnzip(k);
        lst.printList();
    }
}
