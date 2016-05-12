
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aditya
 */
public class test {

    //swaps index a and index b
    public static <T extends Comparable<? super T>> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    
    public static <T extends Comparable<? super T>> int select(T[] arr, int p, int r, int k) {
        //if starting is less than end return -1
        if (p > r) {
            return -1;
        }
        
        T pivot = arr[r];
        int start = p - 1;
        for (int i = p; i < r ; i++) {
            if (pivot.compareTo(arr[i]) < 0) {//swapping all the elements greater than pivot to the left
                swap(arr, ++start, i);
            }
        }
        swap(arr, r, ++start);
        if (start - p + 1 == k) {
            return start;
        } else {
            if (start - p + 1 < k && start + 1 <= r) {//if the number of elements on the left is less than k
                return select(arr, start + 1, r, k - start + p - 1);
            } else if (start - p + 1 > k && start - 1 >= p) {//if the number of elements on the left is greater than k
                return select(arr, p, start - 1, k);
            } else {//if sufficient number of elements are not present
                return -1;
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Integer[] s;// array which stores the numbers
        int k;// k largest numbers
        int size;//size of the array
        if (args.length>0) {
            File f = new File(args[0]);
            Scanner in = new Scanner(f);
            size = in.nextInt();
            k = in.nextInt();
            s = new Integer[size];
            int count = 0;
            while (in.hasNextInt()) {
                s[count++] = in.nextInt();
            }
        } else {
            s = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
            size = 8;
            k = 4;
        }
        long e1 = System.currentTimeMillis();
        System.out.println(select(s, 0, size-1, k));
        long e2 = System.currentTimeMillis();
        
        System.out.println("Time for selection "+(e2-e1));
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return x.intValue() - y.intValue();
            }
        };

        BinaryHeap<Integer> bh = new BinaryHeap<Integer>(size, comp);
        e1=System.currentTimeMillis();
        for (int i = 0; i < k; i++) {
            bh.add(s[i]);
        }
        for (int i = k; i < size; i++) {
            if (bh.peek() < s[i]) {
                bh.pq[1] = s[i];
                bh.percolateDown(1);
            }
        }

        while (bh.size > 0) {
//            System.out.println(bh.remove());
            bh.remove();
        }
        e2=System.currentTimeMillis();
        System.out.println("Time for Heap selection "+(e2-e1));
    }
}

