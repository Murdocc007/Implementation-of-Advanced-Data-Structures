
import java.util.Comparator;

//Class for implementing merge sort
public class MergeSort {
	// method for calling merge sort
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		mergeSort(a, 0, a.length - 1, c);
	}

	// recursive merge sort
	public static <T> void mergeSort(T[] a, int p, int r, Comparator<? super T> c) {
		int q;
		if (p < r) {
			// getting the middle element and dividing the array into 2 parts
			// and recursively calling
			// the method till it reaches the point where it cannnot be further
			// divided.
			q = (p + r) / 2;
			mergeSort(a, p, q, c);
			mergeSort(a, q + 1, r, c);
			// merging the array in sorted order
			Merge(a, p, q, r, c);
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> void Merge(T[] a, int p, int q, int r, Comparator<? super T> c) {
		// New array of type object for copying elements into a new array
		Object[] L = new Object[r - p + 1];
		// local variables for copying elements into the new array in sorted
		// order
		int i = 0;
		int j = p;
		int k = q + 1;
		// compare elements of input array a and add to object array in sorted
		// order
		while (j <= q && k <= r) {
			if (c.compare(a[j], a[k]) < 0) {
				L[i] = a[j];
				j++;
			} else {
				L[i] = a[k];
				k++;
			}
			i++;
		}
		while (j <= q) {
			L[i] = a[j];
			j++;
			i++;
		}
		while (k <= r) {
			L[i] = a[k];
			k++;
			i++;
		}
		// copy elements from sorted array to original array
		for (i = 0; i < r - p + 1; i++) {
			a[p + i] = (T) L[i];
		}

	}

}
