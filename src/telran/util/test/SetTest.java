package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Set;

public abstract class SetTest extends CollectionTest {
	protected Set<Integer> set = getSet();

	abstract protected <T> Set<T> getSet();

	@Override
	@Test
	void testAdd() {
		assertFalse(collection.add(numbers[0]));
		assertEquals(numbers.length, collection.size());
	}

	@Override
	protected Collection<Integer> getCollection() {
		return set;
	}

	@Override
	protected Integer[] getActual(Integer[] array, int size) {
		Arrays.sort(array, 0, size);
		return array;
	}
	
	@Override
	protected Integer[] getExpected(Integer[] array) {
		Integer[] result = Arrays.copyOf(array, array.length);
		Arrays.sort(result);
		return result;
	}

//	protected void runTest(Integer[] expected) {
//		Integer[] actual = collection.toArray(new Integer[0]);
//		Integer expectedCopy[] = Arrays.copyOf(expected, expected.length);
//		Arrays.sort(expectedCopy);
//		Arrays.sort(actual);
//		assertEquals(expectedCopy.length, actual.length);
//		assertArrayEquals(expectedCopy, actual);
//	}

}