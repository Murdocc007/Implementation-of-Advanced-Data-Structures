import java.util.Random;
import java.util.Scanner;

/**
 * @author divyanshu
 * 
 * @param <T>
 * 
 *            Quick Sort using dual pivot
 *
 */
public class QuickSort2<T extends Comparable<T>> {

	/**
	 * @param A
	 *            - array
	 * @param p
	 *            - starting element
	 * @param r
	 *            - ending element
	 */
	public static <T extends Comparable<T>> void QuickSort(T[] A, int p, int r) {
		int[] v;
		if (p < r) {
			v = dualPartition(A, p, r);
			QuickSort(A, p, v[0] - 1);
			if (v[0] < v[1]) {
				QuickSort(A, v[0] + 1, v[1] - 1);
			}
			QuickSort(A, v[1] + 1, r);
		}
	}

	// swap A[i] and A[j]
	private static <T> void swap(T[] A, int i, int j) {
		T temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	// circular swap A[i] -> A[j] -> A[k] -> A[i]
	private static <T> void circularSwap(T[] A, int i, int j, int k) {
		T temp = A[i];
		A[i] = A[k];
		A[k] = A[j];
		A[j] = temp;
	}

	/**
	 * @param a
	 *            - array
	 * @param p
	 *            - starting index
	 * @param r
	 *            - last index
	 * @return - array containing index of pivot elements
	 */
	public static <T extends Comparable<T>> int[] dualPartition(T[] a, int p, int r) {
		int[] v = { p, r };

		// Random rand = new Random();
		// int index1 = rand.nextInt(r + 1);
		// int index2 = rand.nextInt(r + 1);
		// T temp1 = a[index1];
		// a[index1] = a[p];
		// a[p] = temp1;
		//
		// temp1 = a[index2];
		// a[index2] = a[r];
		// a[r] = temp1;

		// Array has been generated randomly. Thus, element x1 and x2 taken
		// deterministically.
		if (a[p].compareTo(a[r]) > 0) {
			T temp = a[p];
			a[p] = a[r];
			a[r] = temp;
		}
		if (r == p + 1) {// if array has only two elements then
							// return index {p,r}
			return v;
		}
		// x1 is lower element
		// x2 is larger element
		T x1 = a[p];
		T x2 = a[r];

		int i = p + 1;// starting index of unprocessed elements
		int l = p + 1;// position of first pivot element
		int j = r - 1;// ending index of unprocessed element
		int unproc = j - i + 1;// no. of unprocessed elements

		while (unproc > 0) {
			while (a[i].compareTo(x2) <= 0 && unproc > 0) {
				if (a[i].compareTo(x1) >= 0) {// case 1 - S_2(x1-x2)
					i++;
					unproc--;
				} else {// case 2 - S_1(<x1)
					swap(a, l, i);
					l++;
					i++;
					unproc--;
				}
			}
			if (unproc <= 0) {
				break;
			}
			while (a[j].compareTo(x2) > 0 && unproc > 0) {// case 3 - S_3(>x2)
				j--;
				unproc--;
			}
			if (unproc <= 0) {
				break;
			}
			if (a[j].compareTo(x1) < 0) {// case 4 - S_1(<x1)
				if (l != i) {// circular swap a[j] -> a[l] -> a[i] -> a[j]
					T temp = a[l];
					a[l] = a[j];
					a[j] = a[i];
					a[i] = temp;
				} else {// Edge case when S_2(x1-x2) is empty
					swap(a, i, j);
				}
				l++;
			} else {// case 5 - S_2(x1-x2)
				swap(a, i, j);
			}
			i++;
			j--;
			unproc -= 2;
		}

		swap(a, p, l - 1);
		swap(a, j + 1, r);
		v[0] = l - 1;
		v[1] = j + 1;
		return v;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);// std input
		System.out.println("Enter the size of the array:");
		int n = in.nextInt();// size of the array
		Integer[] A = new Integer[n];// Integer array of size of n taken as
										// input
		Random rg = new Random();
		for (int i = 0; i < n; i++) {
			A[i] = rg.nextInt(n + 1);// random values to each element with
			// range to the size of n
		}
		long e1 = System.currentTimeMillis();
		QuickSort2.QuickSort(A, 0, n - 1);
		long e2 = System.currentTimeMillis();
		// for (int i = 0; i < n; i++) {
		// System.out.print(A[i] + " ");
		// }
		System.out.println();
		System.out.println(e2 - e1);
	}

}
