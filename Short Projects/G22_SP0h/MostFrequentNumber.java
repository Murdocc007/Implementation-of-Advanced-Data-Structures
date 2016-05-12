/*
 * @Author : Radhika Simant
 * NetId : rrs150130
 * 
 * This class implements two methods to find the most frequent number
 * occurring in the array.
 * One method uses hashmap
 * Another method uses Array.sort() method
 * 
 * */
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class MostFrequentNumber {

	public static void main(String[] args) {
		
		int[] array = new int[1000000];
		Random rand = new Random();
		for(int k=0;k<array.length;k++){
			
			array[k] = rand.nextInt((99-1)+1)+ 1;
				
		}
		
		long start = System.currentTimeMillis();
		
		int mostFrequentArray = mostFrequentUsingArraySort(array);
		
		long end = System.currentTimeMillis();
		
		long timeDiff = end - start;
		
		System.out.println("Max frequent element by Array Sort is : " + mostFrequentArray);
		
		System.out.println("Time Required by Array.sort() : "+timeDiff + " millisecs");
		
		start = System.currentTimeMillis();
		
		int mostFrequentMap = mostFrequentUsingMap(array);
		
		end = System.currentTimeMillis();
		
		timeDiff = end - start;
		
		System.out.println("Most frequent element by Map is : " + mostFrequentMap);
		
		System.out.println("Time Required by Map implementation : "+timeDiff + " millisecs");
		

	}
	/*
	 * Map implementation to find the maximum occuring number
	 * 
	 * */
	
	public static int mostFrequentUsingMap(int[] array){
		
		System.out.println("*** Map implementation ***");
		
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		int mostFreqNum;
		
		for(int i=0;i<array.length;i++){
			
			int count =0;
			if(map.containsKey(array[i])){
				count = map.get(array[i]);
			}
			count = count +1;
			map.put(array[i], count);
		}
		
				
		int maxValue = Collections.max(map.values());
		
		for(Entry<Integer,Integer> entry : map.entrySet()){
			if(entry.getValue() == maxValue){
				mostFreqNum = entry.getKey();
				return mostFreqNum;
			}
			
		}
		
		return -1;
		
	}
	
	/*
	 * Array.sort implementation to find the maximum occuring element in an array
	 * 
	 * */
	
	public static int mostFrequentUsingArraySort(int[] array){
		
		System.out.println("*** Arary.sort() implementation ***");
		
		int maxElement=-1;
		Arrays.sort(array);
		int count=0;
		int maxcount =0;
		for(int i=1;i<array.length;i++){
			if(array[i]==array[i-1]){
				count = count + 1;
			}
			else
				count = 1;
			if(maxcount < count){
				maxcount = count;
				maxElement = array[i];
			}
		}
		return maxElement;
	}
	
	
	

}
