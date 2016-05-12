
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author aditya,divyanshu,radhika,shweta
 */
public class CombinePermute {

	public static void permute(Integer[] A, int n, int k) {
		combinations(A, n, k);
	}

	public static Integer[] getArray(Integer[] A, Boolean[] B) {
		int n = B.length;
		ArrayList<Integer> C = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (B[i] == true) {
				C.add(A[i]);
			}
		}
		Integer[] D = new Integer[C.size()];
		C.toArray(D);
		return D;
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

	public static void combinationsUtil(Integer[] A, int n, int k, Boolean[] B) {
		if (k == 0) {
			Integer[] temp = getArray(A, B);
			permuteUtil(temp, 0, temp.length - 1);
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

	public static void main(String[] args) {
		Integer[] a = new Integer[5];
		a[0] = 1;
		a[1] = 5;
		a[2] = 3;
		a[3] = 4;
		permute(a, 4, 4);
	}

}
