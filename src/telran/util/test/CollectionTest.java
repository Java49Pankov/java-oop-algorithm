package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {

	private static final int BIG_LENGTH = 100000;

	protected Integer[] numbers = { 10, -20, 7, 50, 100, 30 };

	protected Collection<Integer> collection;

	abstract protected Collection<Integer> getCollection();

	protected void runTest(Integer[] expected) {
		Integer[] actual = collection.toArray(new Integer[0]);
		assertArrayEquals(expected, actual);
	}

	@BeforeEach
	void setUp() {
		collection = getCollection();
		for (int i = 0; i < numbers.length; i++) {
			collection.add(numbers[i]);
		}
	}

	@Test
	void testAdd() {
		assertTrue(collection.add(numbers[0]));
		assertEquals(numbers.length + 1, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(10));
		assertTrue(collection.contains(numbers[3]));
		assertTrue(collection.contains(numbers[numbers.length -1]));
		assertFalse(collection.contains(15));
		assertFalse(collection.contains(-50));
	}

	@Test
	void testToArray() {
		Integer[] expected = { 10, -20, 7, 50, 100, 30 };
		Integer[] expected1 = { 10, -20, 7, 50, 100, 30, null };
		Integer[] arrActual = collection.toArray(new Integer[6]);
		assertArrayEquals(expected, arrActual);
		Integer[] arrActual1 = collection.toArray(new Integer[7]);
		assertArrayEquals(expected1, arrActual1);
		Iterator<Integer> it = collection.iterator();
		for (int i = 0; i < numbers.length; i++) {
			it.next();
		}
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	@Test
	void testIterator() {
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		it1.next();
		while(it2.hasNext()) {
			it2.next();
		}
		assertEquals(-20,it1.next());
		assertThrowsExactly(NoSuchElementException.class, () -> it2.next());
	}
	
	@Test
	void testRemoveIterator() {
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = { -20, 7, 50, 100, 30 };
		Integer[] expectedLast = { -20, 7, 50, 100 };
		assertThrowsExactly(IllegalStateException.class,()->it.remove());
		it.next();
		it.remove();		
		runTest(expectedFirst);
		assertThrowsExactly(IllegalStateException.class,()->it.remove());
		while(it.hasNext()) {
			it.next();
		}
		it.remove();
		runTest(expectedLast);
	}

	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for (int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		int size = collection.size();
		for (int i = 0; i < size; i++) {
			assertEquals(numbers[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}

	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] = collection.toArray(new Integer[0]);
		assertArrayEquals(numbers, actualArray);
	}

	@Test
	void testRemovePatern() {
		Integer[] expectedNo10 = { -20, 7, 50, 100, 30 };
		Integer[] expectedNo10_50 = { -20, 7, 100, 30 };
		Integer[] expectedNo10_50_30 = { -20, 7, 100 };
		assertTrue(collection.remove(numbers[0]));
		runTest(expectedNo10);
		assertTrue(collection.remove((Integer) 50));
		runTest(expectedNo10_50);
		assertTrue(collection.remove((Integer) 30));
		runTest(expectedNo10_50_30);
		assertEquals(numbers.length - 3, collection.size());
	}

	@Test
	void testRemoveIfAll() {
		assertTrue(collection.removeIf(a -> true));
		assertEquals(0, collection.size());
	}

	@Test
	void testRemoveIfPredicate() {
		assertFalse(collection.removeIf(a -> a % 2 != 0 && a >= 10));
		Integer[] expected = { 10, -20, 50, 100, 30 };
		assertTrue(collection.removeIf(a -> a % 2 != 0));
		runTest(expected);
	}
	
	@Test
	void clearFunctionalTest() {
		collection.clear();
		assertEquals(0, collection.size());
	}

}
