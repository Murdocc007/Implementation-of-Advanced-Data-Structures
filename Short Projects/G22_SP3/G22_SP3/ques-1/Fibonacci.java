import java.util.Scanner;

/**
 * @author divyanshu
 *
 */
public class Fibonacci {

	/**
	 * @param n
	 *            - nth fibonacci number
	 * @param p
	 *            - prime number
	 * @return - n % p
	 */
	public static long linearFibonacci(long n, long p) {
		// f_0 : 0th fibonacci number
		// f_1 : 1st fibonacci number
		// f_n : nth fibonacci number
		long f_0, f_1, f_n = 0;
		f_0 = 0;
		f_1 = 1;
		// iterate to linearly add nth fibonacci number
		for (long i = 2; i <= n; i++) {
			f_n = (f_0 + f_1) % p;
			// f_0 : n-2 fibonacci number
			// f_1 : n-1 fibonacci number
			f_0 = f_1;
			f_1 = f_n;
		}
		return f_n;

	}

	/**
	 * 
	 * Multiplication of two matrices
	 * 
	 * @param A
	 *            - matrix
	 * @param B
	 *            - matrix
	 * @return - matrix A*B
	 */
	public static long[][] multiply(long[][] A, long[][] B, long p) {
		long[][] temp = new long[A.length][A.length];
		long sum = 0;
		for (int i = 0; i < A.length; i++) {
			temp[i] = new long[A[i].length];
			for (int j = 0; j < B[i].length; j++) {
				sum = 0;
				for (int k = 0; k < A.length; k++) {
					sum += A[i][k] * B[k][j];
				}
				temp[i][j] = sum % p;
			}
		}
		return temp;
	}

	/**
	 * 
	 * Matrix to the power n
	 * 
	 * @param A
	 *            - matrix
	 * @param n
	 *            - power
	 * @return matrix A^(n)
	 */
	public static long[][] mathPower(long[][] A, long n, long p) {
		if (n == 1) {
			return A;
		} else {
			long[][] temp = mathPower(A, n / 2, p);
			if (n % 2 == 0) {
				return multiply(temp, temp, p);
			} else {
				return multiply(multiply(temp, temp, p), A, p);
			}
		}
	}

	/**
	 * 
	 * Fibonacci in log time
	 * 
	 * @param n-
	 *            nth fibonacci number
	 * @param p
	 *            - prime number
	 * @return - f_n % p
	 */
	public static long logFibonacci(long n, long p) {
		long[] V_n = new long[2];// resultant matrix 2*2
		long[] V_1 = { 1, 0 };// 1-D array of size 2
		long[][] A = { { 1, 1 }, { 1, 0 } };// input matrix 2*2
		long[][] A_hat = new long[2][2];// A^(n-1) matrix 2*2
		A_hat = mathPower(A, n - 1, p);
		V_n[0] = A_hat[0][0] * V_1[0];
		return V_n[0] % p;

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		long result;
		System.out.println("Enter the number:");
		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		if (n < 2) {
			System.out.println("Pls enter value > 1");
			System.exit(0);
		}
		System.out.println("Enter a prime number");
		long p = in.nextLong();
		if (p < 1) {
			System.out.println("Pls enter value > 0");
			System.exit(0);
		}
		System.out.println();
		// calculating computation time of linear algorithm
		long e1 = System.currentTimeMillis();
		result = Fibonacci.linearFibonacci(n, p);
		long e2 = System.currentTimeMillis();
		System.out.println("result:"+result);
		System.out.println("time:"+(e2 - e1));
		result = 0;
		// e1 = e2 = 0;
		// calculating computation time of log algorithm
		e1 = System.currentTimeMillis();
		result = Fibonacci.logFibonacci(n, p);
		e2 = System.currentTimeMillis();
		System.out.println("result:"+result);
		System.out.println("time:"+(e2 - e1));

	}

}
