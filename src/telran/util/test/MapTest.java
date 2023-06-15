package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Map;

abstract class MapTest {
	String[] keys = { "lmn", "abc", "ab", "a" };
	Integer[] values = { 3, 2, 2, 1 };
	protected Map<String, Integer> map;

	@BeforeEach
	void setUp() {
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
	}

	@Test
	void getTest() {
		for (int i = 0; i < keys.length; i++) {
			assertEquals(values[i], map.get(keys[i]));
		}
	}

	@Test
	void containsKeyTest() {
		for (int i = 0; i < keys.length; i++) {
			assertTrue(map.containsKey(keys[i]));
		}
		assertFalse(map.containsKey("cba"));
	}

	@Test
	void containsValueTest() {
		for (int i = 0; i < values.length; i++) {
			assertTrue(map.containsValue(values[i]));
		}
		assertFalse(map.containsValue(32));
	}

	@Test
	void removeTest() {
		assertEquals(3, map.remove("lmn"));
		assertEquals(null, map.get("lmn"));
		assertFalse(map.containsKey("lmn"));
		assertEquals(2, map.remove("ab"));
		assertEquals(null, map.get("ab"));
		assertFalse(map.containsKey("ab"));
		assertEquals(2, map.entrySet().size());
	}

	@Test
	void getOrDefaultTest() {
		assertEquals(3, map.getOrDefault("lmn", 1));
		assertEquals(2, map.getOrDefault("abc", 10));
		assertEquals(10, map.getOrDefault("bc", 10));
	}

	@Test
	void putIfAbsentTest() {
		assertEquals(2, map.putIfAbsent("abc", 5));
		assertEquals(1, map.putIfAbsent("a", 4));
		assertNull(map.putIfAbsent("bc", 10));
		assertEquals(5, map.entrySet().size());
	}

	@Test
	void entryTest() {
	}

	@Test
	void valuesTest() {
		assertEquals(4, map.values().size());
	}
}
