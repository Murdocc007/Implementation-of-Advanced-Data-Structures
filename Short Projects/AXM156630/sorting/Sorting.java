import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author aditya
 */
public class Sorting {

public static int phase=0;
public static long startTime, endTime, elapsedTime;
    
//Heapsort function
public static<T extends Comparable<? super T>> List<T> heapSort(List<T> A) {
    int n=A.size();
    if(n>1)
    {
        PriorityQueue<T> queue=new PriorityQueue<T>();
        for(int i=0;i<n;i++){
            queue.offer(A.get(i));
        }
        
        A.clear();
        
        while(queue.size()>0){
            A.add(queue.poll());
        }
    }
    return A;
}


//merge sort function, return a sorted list
public static<T extends Comparable<? super T>> List<T> mergeSort(List<T> A) {
    int n=A.size();
    if(n<=1){
    return A;
    }
    
    int mid=n/2;
    
    List<T> left=A.subList(0, mid);
    List<T> right=A.subList(mid, n);
    
    right=mergeSort(right);//sorting the first half of the list
    left=mergeSort(left);//sorting the second half of the list
    
    List<T> ans=merge(left,right);   //merging both the sorted lists
    
    return ans;
}

public static<T extends Comparable<? super T>> List<T> merge(List<T> left,List<T> right){

    List<T> answer=new ArrayList<T>();
    int n=left.size();
    int m=right.size();
    int i=0;
    int j=0;
    while(i<n && j<m){//loop continue till both the pointers are less than the array sizes
        if(left.get(i).compareTo(right.get(j))<0){
            answer.add(left.get(i));//if element in left array is smaller add it to the resulting list
            i++;
        }
        else{
            answer.add(right.get(j));//array element in right array is smaller
            j++;
        }
    }
    
    //adding all the elements of the remaining bigger array
    while(i<n){
        answer.add(left.get(i));
        i++;
    }
    
    while(j<m){
        answer.add(right.get(j));
        j++;
    }
    
    return answer;
}


public static void memory() {
    long memAvailable = Runtime.getRuntime().totalMemory();
    long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
    System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
}


public static void timer()
{
    if(phase == 0) {
	startTime = System.currentTimeMillis();
	phase = 1;
	} else {
	endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        System.out.println("Time: " + elapsedTime + " msec.");
        memory();
        phase = 0;
    }
}

    
}
