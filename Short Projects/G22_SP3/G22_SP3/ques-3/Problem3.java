import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * @author divyanshu
 *
 */
public class Problem3 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n;
		System.out.println("Enter size of array:");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		Integer[] A = new Integer[n];
		Comparator<Integer> c = new Comparator<Integer>() {// Comparator for
															// MergeSort

			@Override
			public int compare(Integer o1, Integer o2) {

				return o1.compareTo(o2);
			}
		};
		Random rg = new Random();
		for (int i = 0; i < n; i++) {
			A[i] = rg.nextInt(n + 1);// Random input array with duplicate
										// elements
		}
		long e1 = System.currentTimeMillis();
		QuickSort1.QuickSort(A, 0, n - 1);// QuickSort
		long e2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("QuickSort: " + (e2 - e1));
		e1 = System.currentTimeMillis();
		MergeSort.sort(A, c);// MergeSort
		e2 = System.currentTimeMillis();
		System.out.println("MergeSort: " + (e2 - e1));

	}

}
