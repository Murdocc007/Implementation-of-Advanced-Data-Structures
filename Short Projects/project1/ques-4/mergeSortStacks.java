

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
/*
 * Merge Sort using Stack
 * */
public class mergeSortStacks<T>{
	/*
	 * Taking an array as input and using two stacks to sort them without recursion
	 * @param A: array - integer
	 * */
	public static int[] mergeSortStacks(int[] A) throws EmptyStackException {
        Stack<int[]> stack = new Stack<int[]>();
        Stack<int[]> stack2 = new Stack<int[]>();
        //adding array elements to the 1st stack
        for (int i = 0; i < A.length; i++)
        {
            stack.push(new int[]{A[i]});
        }
/*
 * runs until all the elements are sorted and stored in one of the two stacks
 * */
        while (stack.size()>1)
        {
        	//while stack size is greater than 1, pop two elements from 1st stack
            //use merge algorithm on them
            //push them to the 2nd stack
            while (stack.size()>1)
            {

                int[] r = stack.pop();
                int[] l = stack.pop();
                int[] merged=merge(l, r);
                stack2.push(merged);
            }
            //if there is any left element in the end, pop it from the 1st stack and push it to 2nd stack
            if(stack.size()>0){
            	int[] left = stack.pop();
            	stack2.push(left);
            }
            //while stack size is greater than 1, pop two elements from 2nd stack
            //use merge algorithm on them
            //push them to the 1st stack
            while (stack2.size()>1)
            {

                int[] r = stack2.pop();
                int[] l = stack2.pop();
                int[] merged=merge(l, r);
                stack.push(merged);
            }
            //if there is any left element in the end, pop it from the 2nd stack and push it to 1st stack
            if(stack2.size()> 0){
            	int[] left = stack2.pop();
            	stack.push(left);
            }
        }
        //return the stack storing the elements
        return stack.isEmpty() ? stack2.pop() : stack.pop();


    }
	//Merge elements
	public static int[] merge(int[] l, int[] r) throws EmptyStackException{
		//total size of array of elements to be returned as sorted
        int N = l.length + r.length;
        //array to be returned
        int[] a = new int[N];
        /*
         * index of final array - i
         * index of left array - li
         * index of right array - ri
         * */
        int i,li,ri;
        i = li = ri = 0;
        /*
         * run till the end of the final list is reached
         * */
        while ( i < N) {
        	/*
        	 * if both array have elements check for the smaller element and increment the index of that array and the final array
        	 * */
            if ((li < l.length) && (ri<r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                }
                else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }
            /*
             * if only one array has an element left, add them to the final array
             * increment index value of the array and final array
             * */
            else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        return a;

    }
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		in = new Scanner(System.in);
		System.out.println("Enter the size of the array:");
		int n = in.nextInt();
		//check if the array has 0 elements
		if(n == 0){
			System.out.println("Array has zero elements");
			System.exit(0);
		}
		int [] A = new int[n];
		int [] B;
		//printing first 10 elements of the input
		System.out.println("Starting 10 elements of the input array");
		int k = 10;
		for(int i = 0; i< n;i++){
			A[i] = n-i;
			if(k > 0){
				System.out.print(A[i]+" ");
				k--;
			}
		}
		System.out.println();
		//printing first 10 elements of the output
		System.out.println("Starting 10 elements of the output array");
		B = mergeSortStacks(A);
		int j =10;
		for(int i = 0; i < B.length;i++){
			if(j > 0){
				System.out.print(B[i]+" ");
				j--;
			}
		}
	}
}
