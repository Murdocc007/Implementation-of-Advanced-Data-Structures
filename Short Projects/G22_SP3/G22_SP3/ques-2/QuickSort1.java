import java.util.Random;
import java.util.Scanner;

/**
 * @author divyanshu
 * 
 *         Quick Sort using single pivot
 *
 * @param <T>
 */
public class QuickSort1<T extends Comparable<T>> {

	/**
	 * @param A
	 * @param start
	 * @param end
	 */
	public static <T extends Comparable<T>> void QuickSort(T[] A, int start, int end) {
		int pindex = 0;
		if (start < end) {
			pindex = partition1(A, start, end);
			QuickSort(A, start, pindex - 1);
			QuickSort(A, pindex + 1, end);
		}
	}

	/**
	 * @param a
	 * @param start
	 * @param end
	 * @return - pivot index
	 */
	private static <T extends Comparable<T>> int partition1(T[] a, int start, int end) {
		// Random rand = new Random();
		// int index = rand.nextInt(end);
		// T temp1 = a[end];
		// a[end] = a[index];
		// a[index] = temp1;

		T x = a[end];// pivot element
		int i = start - 1;// index of pivot element
		for (int j = start; j < end; j++) {
			if (a[j].compareTo(x) <= 0) {
				i += 1;
				if (i == j) {
					// do nothing
				} else {
					swap(a, i, j);
				}
			}
		}
		swap(a, i + 1, end);
		return i + 1;

	}

	private static <T> void swap(T[] A, int i, int j) {
		T temp = A[i];
		A[i] = A[j];
		A[j] = temp;

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n;
		System.out.println("Enter size of array:");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		Integer[] A = new Integer[n];
		Random rg = new Random();
		for (int i = 0; i < n; i++) {
			A[i] = rg.nextInt(n + 1);
		}
		long e1 = System.currentTimeMillis();
		QuickSort1.QuickSort(A, 0, n - 1);
		long e2 = System.currentTimeMillis();
		// for (int i = 0; i < n; i++) {
		// System.out.print(A[i] + " ");
		// }
		System.out.println();
		System.out.println(e2 - e1);

	}

}
