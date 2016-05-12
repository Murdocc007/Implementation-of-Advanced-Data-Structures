import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LargeIntegerArithmetic {

	public static void main(String[] args) {
				
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		List<Integer> list1 = new LinkedList<Integer>();
		List<Integer> list2 = new LinkedList<Integer>();
		List<Integer> sum = new LinkedList<Integer>();
		List<Integer> result = new LinkedList<Integer>();
		Random r = new Random();
		System.out.println("Enter lenght of first number : ");
		int num1 = sc1.nextInt();
		int arr1[] = new int[num1];
		System.out.println("First number is : ");
		//Randomly generate n digit number and store each digit in an array
		for(int i=0;i<num1;i++){
			int n = r.nextInt(9-0+1)+0;
			arr1[i] = n;
			System.out.print(arr1[i]);
		}
		//Add the digits in the list starting with the least significant digit first 
		for(int i=num1-1;i>=0;i--){
			list1.add(arr1[i]);
		}
				
		System.out.println();
		System.out.println("Enter lenght of second number : ");
		int num2 = sc2.nextInt();
		int arr2[] = new int[num2];
		System.out.println("Second number is : ");
		//Randomly generate n digit number and store each digit in an array
		for(int i=0;i<num2;i++){
			int n = r.nextInt(9-0+1)+0;
			arr2[i] = n;
			System.out.print(arr2[i]);
		}
		//Add the digits in the list starting with the least significant digit first 
		for(int i=num2-1;i>=0;i--){
			list2.add(arr2[i]);
		}
		System.out.println();
			 
		sum = add(list1,list2,sum,10);
		System.out.println("The sum of the two entered numbers is : " );
		 for (int i = sum.size() - 1; i >= 0; i--)
	            System.out.print(sum.get(i));
	    System.out.println(); 
	    result = subtract(list1,list2,result,10);
	    System.out.println("Subtraction of given numbers is : ");
	     for (int i = result.size() - 1; i >= 0; i--)
	            System.out.print(result.get(i));
	    System.out.println(); 
		sc1.close();
		sc2.close();
		
	}
	/*
	 * Function to add two large integers stored in lists
	 * */
	public static List<Integer> add(List<Integer> x, List<Integer>  y,List<Integer> z, int b) {
		int list1_lenght = x.size();
		int list2_lenght = y.size();
		int answer_lenght =  list1_lenght > list2_lenght ? list1_lenght : list2_lenght;
		int carry =0;
		for (int i = 0; i < answer_lenght; i++)
        {
            int digit1 = 0, digit2 = 0;            
            try {
                digit1 = x.get(i);
            } 
            catch(Exception e){}            
            try {
                digit2 = y.get(i);
            } 
            catch(Exception e){}                        
            int  addition = digit1 + digit2 + carry;
            z.add(addition % b);
            carry = addition / b;
        }
        /* Adding carry */
        while (carry != 0)
        {
            z.add(carry % 10);
            carry = carry/ 10;
        }
        return z;
	}
	/*
	 * Function to subtract two large integers stored in lists
	 *  
	 * */
	public static List<Integer> subtract(List<Integer> x, List<Integer>  y,List<Integer> z, int b){
		int list1_lenght = x.size();
		int list2_lenght = y.size();
		int answer_lenght =  list1_lenght > list2_lenght ? list1_lenght : list2_lenght;
		int borrow =0;
		for (int i = 0; i < answer_lenght; i++)
        {
			  
			  int digit1 = 0, digit2 = 0;            
	            try {
	                digit1 = x.get(i);
	            } 
	            catch(Exception e){}            
	            try {
	                digit2 = y.get(i);
	            } 
	            catch(Exception e){} 
	            digit1 = digit1 -borrow;
	            borrow =0;
	            if(digit1<digit2){
	            	digit1 =digit1 +b;
	            	borrow =1;
	            }
	            int subtraction = digit1 - digit2;
	            z.add(subtraction);
	            	            
        }
		return z;
	}


}
