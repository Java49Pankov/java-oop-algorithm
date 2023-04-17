package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer num1, Integer num2) {
		if (num1 % 2 == 0 && num2 % 2 == 0) {
			return num1.compareTo(num2);
		} else if (num1 % 2 != 0 && num2 % 2 != 0) {
			return num2.compareTo(num1);
		} else {
			return num1 % 2 == 0 ? -1 : 1;
		}

	}
}
