package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTest {

	@Test
	void testAdd() {
		ArrayList<Integer> numbers = new ArrayList<>();
		ArrayList<String> strings = new ArrayList<>();
		numbers.add(5);
		numbers.add(10);
		strings.add("ABC");
		assertEquals(2, numbers.size());
		assertEquals(1, strings.size()); 
		}
	
	@Test
	void testAddIndex() {
		ArrayList<Integer> object = new ArrayList<>();
		object.add(0);
		object.add(1);
		object.add(2);
		assertEquals(3, object.size());
		object.add(3, 3);
		object.add(4, 4);
		assertEquals(5, object.size());
		assertEquals(0, object.get(0));
		assertEquals(3, object.get(3));		
	}

	@Test
	void testRemoveIndex() {
		ArrayList<Integer> object = new ArrayList<>();
		object.add(0);
		object.add(1);
		object.add(2);
		object.add(3);
		object.add(4);
		object.add(5);
		object.remove(0);
		object.remove(3);
		assertEquals(1, object.get(0));
		assertEquals(5, object.get(3));
		assertEquals(4, object.size());
		
	}
}
