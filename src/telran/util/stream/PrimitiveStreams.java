package telran.util.stream;

import java.util.Random;
import telran.util.ArrayList;

public class PrimitiveStreams {
	static public int[] randomUnique(int nNumbers, int minInclusive, int maxExclusive) {
		if (maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive).distinct().limit(nNumbers).toArray();
	}

	static public int[] shuffler(int[] array) {
		ArrayList<Integer> list = new ArrayList<>();
		//with repeating numbers:
//		 new Random().ints(0, array.length).limit(array.length).forEach(num -> list.add(num));
		new Random().ints(0, array.length).distinct().limit(array.length).forEach(num -> list.add(num));
		return list.stream().mapToInt(n -> n).toArray();
	}

}
