/**
 * @author Shweta
 * This class contains a main method which generates 20 random numbers 
 * in the range 1-50 added to a linked list which are then sorted using merge sort.
 */
public class SinglyLinkedList<T extends Comparable<? super T>> {
	
	/**
	 * Internal class to create Entry for each node of a Linked List.
	 * @author shweta
	 * @param <T>
	 */
    public class Entry<T extends Comparable<? super T>> {

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
        header = new Entry<T>(null, null);
        tail = null;
        size = 0;
    }
    
    /**
     * Method to add an Entry to a linked list
     * @param x
     */
    void add(T x) {
        if (header.element == null) {
            header = new Entry<>(x, null);
            // tail = header.next;
        } else if (tail == null) {
            header.next = new Entry<>(x, header.next);
            tail = header.next;
        } else {
            tail.next = new Entry<>(x, null);
            tail = tail.next;
        }
        size++;
    }
    
    /**
     * Method to print the elements of a linked list
     */
    void printList() {
        Entry<T> x = header;
        while (x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
        System.out.println();
    }
    
    /**
     * public method to sort any given singlylinkedlist using MergeSort
     */
    public <T extends Comparable<? super T>> void MergeSort() {
        header = MergeSort(header);
    }
    
    /**
     * private method that subdivides the linkedlist and then sorted and merged.
     */
    private <T extends Comparable<? super T>> Entry<T> MergeSort(Entry<T> node) {
        if (node == null || node.next == null) {
            return node;
        }
        
        //Find the middle element.
        Entry<T> a = node;
        Entry<T> b = node.next;
        while ((b != null) && (b.next != null)) {
            node = node.next;
            b = (b.next).next;
        }
        
        //b points to the middle of the list and a points to the head of the list
        b = node.next;
        node.next = null;
        return merge(MergeSort(a), MergeSort(b));
    }

    /**
     * private method to merge the sorted linkedlists
     * @param l Entry 1
     * @param r Entry 2
     * @return Merged Entry
     */
    private <T extends Comparable<? super T>> Entry<T> merge(Entry<T> l, Entry<T> r) {
        //creating a dummy pointer
        Entry<T> dummy = new Entry<T>(null, null);
        Entry<T> temp = dummy;
        while ((l != null) && (r != null)) {
            if (l.element.compareTo(r.element) < 0) {
                //if the element in the left list is less than the element in the right
                temp.next = l;
                temp = l;
                l = l.next;
            } else {
                temp.next = r;
                temp = r;
                r = r.next;
            }
        }
        temp.next = (l == null) ? r : l;
        return dummy.next;
    }

    public static void main(String[] args) {
        int n = 20;
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for (int i = 1; i <= n; i++) {
        	lst.add(1 + (int)(Math.random()*(50-1)+1));
        }
        System.out.println("Sample Input:");
        lst.printList();
        lst.MergeSort();
        System.out.println("Sample Output:");
        lst.printList();
    }
}

/*Sample Input: 
 * 22 50 20 35 10 31 44 22 43 42 46 23 27 23 45 22 21 11 36 31 
 *Sample Output: 
 *10 11 20 21 22 22 22 23 23 27 31 31 35 36 42 43 44 45 46 50
*/
