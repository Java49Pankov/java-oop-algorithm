package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Set;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set = getSet();

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

	protected void runTest(Integer[] expected) {
		Integer[] actual = collection.toArray(new Integer[0]);
		Integer expectedCopy[] = Arrays.copyOf(expected, expected.length);
		Arrays.sort(expectedCopy);
		Arrays.sort(actual);

		assertArrayEquals(expectedCopy, actual);

	}

	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for (int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		Arrays.sort(actualArray, 0, collection.size());
		int size = collection.size();
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		for (int i = 0; i < size; i++) {
			assertEquals(expected[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}

	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] = collection.toArray(new Integer[0]);
		Arrays.sort(actualArray);
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, actualArray);
	}

	@Test
	void testIterator() {
		Iterator<Integer> it1 = collection.iterator();
		it1.next();
		assertEquals(it1.next(), collection.toArray(new Integer[0])[1]);
		Iterator<Integer> it2 = collection.iterator();
		while (it2.hasNext()) {
			it2.next();
		}
		assertEquals(-20, it1.next());
		assertThrowsExactly(NoSuchElementException.class, () -> it2.next());
	}

	@Test
	void testRemoveIterator() {
		Integer[] actual = collection.toArray(new Integer[0]);
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = new Integer[actual.length - 1];
		System.arraycopy(actual, 1, expectedFirst, 0, actual.length - 1);
		Integer[] expectedLast = new Integer[actual.length - 2];
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		System.arraycopy(actual, 1, expectedFirst, 0, actual.length - 2);
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		runTest(expectedFirst);
		while (it.hasNext()) {
			it.next();
		}
		it.remove();
		runTest(expectedLast);
	}
}