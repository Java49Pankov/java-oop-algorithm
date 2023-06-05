package telran.algorithm.recursion;

public class LineRecursion {
	public static long factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if (n > 1) {
			res = n * factorial(n - 1);
		}
		return res;
	}

	public static long pow(int a, int b) {
		// a - any number
		// b - any positive number
		// TODO HW #18
		// Limitations:
		// 1. no cycles
		// 2. only + or - for arithmetic operations
		if (b < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if (b > 0) {
			res = a * pow(a, b - 1);// a^b = a * a^(b-1)
		}
		return res;
	}

	public static long sum(int[] array) {
		return sum(0, array);
	}

	private static long sum(int firstInd, int[] array) {
		long sum = 0;
		if (firstInd < array.length) {
			sum = array[firstInd] + sum(firstInd + 1, array);
		}
		return sum;
	}

	public static int[] reverse(int[] array) {
		return reverse(array, 0, array.length - 1);
	}

	private static int[] reverse(int[] array, int left, int right) {
		int[] res = array;
		if (left < right) {
			array[left] = array[left] + array[right];
			array[right] = array[left] - array[right];
			array[left] = array[left] - array[right];
			reverse(array, left + 1, right - 1);
		}
		return res;
	}

	public static long square(int x) {
		// TODO
		// x any number
		// no cycles
		// only operation +;
		// no additional function
		// no static fields
		return 0;
	}

	public static boolean isSubstring(String string, String substr) {
//TODO  write function
// returns true if a given 'substr' is indeed the 
//substring of a given `string`
		/*
		 * Challenges: 1. To apply only following methods of the class String: charAt(
		 * intind); String substring( int ind); intlength(); 2. No cycles;
		 */
		return false;
	}
}