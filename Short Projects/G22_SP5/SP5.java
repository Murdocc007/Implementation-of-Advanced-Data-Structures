
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;

/**
 *
 * @author aditya,divyanshu,radhika,shweta
 */
public class SP5 {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void printCombination(Integer[] A, Boolean[] B) {
		int n = B.length;
		for (int i = 0; i < n; i++) {
			if (B[i] == true) {
				System.out.println(Integer.valueOf(A[i]));
			}
		}
	}

	public static void combinationsUtil(Integer[] A, int n, int k, Boolean[] B) {
		if (k == 0) {
			printCombination(A, B);
			return;
		}

		if (n < 0) {
			return;
		}
		// current element is not included
		combinationsUtil(A, n - 1, k, B);
		B[n] = true;
		combinationsUtil(A, n - 1, k - 1, B);
		B[n] = false;

	}

	public static void combinations(Integer[] A, int n, int k) {
		// array which stores the info if our element will be included in the
		// combination or not
		Boolean[] B = new Boolean[A.length];
		// intializing
		for (int i = 0; i < A.length; i++) {
			B[i] = false;
		}
		combinationsUtil(A, n - 1, k, B);
	}

	public static void printUtil(Integer[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(Integer.valueOf(A[i]));
		}
		System.out.println();
	}

	public static void swap(Integer[] A, int idx1, int idx2) {
		int temp = A[idx1];
		A[idx1] = A[idx2];
		A[idx2] = temp;
	}

	public static void permuteUtil(Integer[] A, int start, int end) {
		if (end == start) {
			printUtil(A);
			return;
		}
		for (int i = end; i >= start; i--) {
			swap(A, end, i);
			permuteUtil(A, start, end - 1);
			// restore the arrangement back
			swap(A, end, i);
		}
	}

	// permutations take 2
	public static void permuteTake2(Integer[] A) {
		permuteUtil(A, 0, A.length - 1);
	}

	// permutations take 3
	public static void permuteTake3(Integer[] A, int n) {
		if (n == 1) {
			printUtil(A);
			return;
		} else {
			for (int i = 0; i < n; i++) {
				permuteTake3(A, n - 1);
				if (i % 2 == 0) {
					swap(A, i, n - 1);
				} else {
					swap(A, 0, n - 1);
				}
			}
		}
	}

	// reversing an array between two indexes, both inclusive
	public static void reverse(Integer[] A, int idx1, int idx2) {
		while (idx1 < idx2) {
			swap(A, idx1++, idx2--);
		}
	}

	public static void KnuthL1(Integer[] A) {
		int n = A.length;
		Arrays.sort(A);

		while (true) {
			int j = n - 2;
			while (j > -1 && A[j] >= A[j + 1]) {
				//
				if (j == 0) {
					return;
				}
				j--;
			}
			int l = n - 1;
			while (A[j] >= A[l]) {
				l--;
			}
			swap(A, j, l);
			reverse(A, j + 1, n - 1);
			printUtil(A);
		}

	}

	public static void KnuthL(Integer[] A) {
		int j; // max index such that A[j]<A[j+1]
		int l;// max index such that A[j]<A[l]
		int n = A.length;
		Arrays.sort(A);
		while (true) {

			// starting from the second last element
			j = n - 2;
			while (j > -1 && A[j] >= A[j + 1]) {
				j--;
			}

			// exit condition
			if (j == 0) {
				break;
			}

			// starting from the last element
			l = n - 1;
			while (A[j] >= A[l]) {
				l--;
			}

			swap(A, j, l);
			reverse(A, j + 1, n - 1);
			printUtil(A);
		}
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[4];
		for (int i = 0; i < 4; i++) {
			a[i] = i + 1;
		}
		// a[0] = 1;
		// a[1] = 2;
		// a[2] = 3;
		// a[3] = 4;
		KnuthL1(a);
		combinations(a, 4, 2);
		System.out.println("Permute Take 2");
		long e1 = System.currentTimeMillis();
		permuteTake2(a);
		long e2 = System.currentTimeMillis();
		System.out.println("Time taken by Take 2: " + (e2 - e1) + " msec");
		System.out.println("Permute Take 3");
		e1 = System.currentTimeMillis();
		permuteTake3(a, 4);
		e2 = System.currentTimeMillis();
		System.out.println("Time taken by Take 3: " + (e2 - e1) + " msec");
	}

}
