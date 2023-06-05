package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {

	protected Integer[] numbers = { 10, -20, 7, 50, 100, 30 };
	protected Collection<Integer> collection;
	protected static final int BIG_LENGTH = 100000;

	protected abstract Integer[] getExpected(Integer[] numbers);

	protected abstract Integer[] getActual(Integer[] actual, int size);

	@BeforeEach
	void setUp() {
		collection = getCollection();
		for (int i = 0; i < numbers.length; i++) {
			collection.add(numbers[i]);
		}
	}

	abstract protected Collection<Integer> getCollection();

	@Test
	void testAdd() {
		assertTrue(collection.add(numbers[0]));
		assertEquals(numbers.length + 1, collection.size());
	}

	@Test
	void testRemovePattern() {
		Integer[] expectedNo10 = { -20, 7, 50, 100, 30 };
		Integer[] expectedNo10_50 = { -20, 7, 100, 30 };
		Integer[] expectedNo10_50_30 = { -20, 7, 100 };
		assertTrue(collection.remove(numbers[0]));
		runTest(expectedNo10);
		Integer objToRemove = 50;
		assertTrue(collection.remove(objToRemove));
		runTest(expectedNo10_50);
		assertTrue(collection.remove((Integer) 30));
		runTest(expectedNo10_50_30);
		assertFalse(collection.remove((Integer) 50));
	}

	@Test
	void testRemoveIfPredicate() {
		Integer[] expected = { 10, -20, 50, 100, 30 };
		assertFalse(collection.removeIf(a -> a % 2 != 0 && a >= 10));
		assertTrue(collection.removeIf(a -> a % 2 != 0));
		runTest(expected);
	}

	@Test
	void testRemoveIfAll() {
		assertTrue(collection.removeIf(a -> true));
		assertEquals(0, collection.size());
	}

	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for (int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = getActual(collection.toArray(bigArray), collection.size());
		Integer expected[] = getExpected(numbers);
		int size = collection.size();
		for (int i = 0; i < size; i++) {
			assertEquals(expected[i], actualArray[i]);
		}
		assertNull(actualArray[size]); 
		assertTrue(bigArray == actualArray);
	}

	@Test
	void testToArrayForEmptyArray() {		
		Integer actualArray[] = getActual(collection.toArray(new Integer[0]), collection.size());
		Integer expected[] = getExpected(numbers);
		assertArrayEquals(expected, actualArray);
	}

	@Test
	void testIterator() {
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		it1.next();
		while (it2.hasNext()) {
			it2.next();
		}
		assertTrue(collection.contains(it1.next()));
		assertThrowsExactly(NoSuchElementException.class, () -> it2.next());
	}

	@Test
	void testIteratorRemove() {
		Iterator<Integer> it = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		Integer removed = it.next();
		assertTrue(collection.contains(removed));
		it.remove();
		assertFalse(collection.contains(removed));
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		while (it.hasNext()) {
			removed = it.next();
		}
		assertTrue(collection.contains(removed));
		it.remove();
		assertFalse(collection.contains(removed));
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertTrue(collection.contains(numbers[3]));
		assertTrue(collection.contains(numbers[numbers.length - 1]));
		assertFalse(collection.contains(1000000));
	}

	@Test
	void clearFunctionalTest() {
		collection.clear();
		assertEquals(0, collection.size());
	}

	@Test
	void clearPerformance() {
		Collection<Integer> bigCollection = getCollection();
		Random gen = new Random();
		for (int i = 0; i < 1_000_000; i++) {
//			bigCollection.add(i);
			bigCollection.add(gen.nextInt());
		}
		bigCollection.clear();
		assertEquals(0, bigCollection.size());
	}

	protected void runTest(Integer[] expectedP) {
		Integer[] actual = getActual(collection.toArray(new Integer[0]), collection.size());	
		Integer expected[] = getExpected(expectedP);
		assertArrayEquals(expected, actual);
	}
}