import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aditya
 */
public class Driver{

    
    static <T> void firstTen(List<T> A) {
        int n = Math.min(A.size(), 10);
        
        for(int i=0; i<n; i++) 
        {
        System.out.print(A.get(i) + " ");
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        List<Integer> A = new ArrayList<Integer>();
	 
	//Adding the integers in reverse to the arraylist
        for(int i=0; i<n; i++) {
            A.add(n-i);
        }
 		
	//Merge Sort       
	System.out.println("Merge Sort");
        firstTen(A);
        Sorting.timer();
        List<Integer> ans=Sorting.mergeSort(A);
        Sorting.timer();
        firstTen(ans);

	//Heap Sort
	System.out.println("Heap Sort");
        firstTen(A);
        Sorting.timer();
        List<Integer> ans1=Sorting.heapSort(A);
        Sorting.timer();
        firstTen(ans1);
	

        
    }
    
    
}