package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer num1, Integer num2) {
		int res = Integer.compare(Math.abs(num1 % 2), Math.abs(num2 % 2));
		if (res == 0) {
			res = num1 % 2 == 0 ? Integer.compare(num1, num2) : Integer.compare(num2, num1);
		}
		return res; 
	}
}
