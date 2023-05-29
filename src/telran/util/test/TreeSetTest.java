package telran.util.test;

import java.util.Comparator;

import telran.util.*;

public class TreeSetTest extends SetTest {

	@Override
	protected <T> Set<T> getSet() {
		@SuppressWarnings("unchecked")
		Comparator<T> comp = (Comparator<T>) Comparator.naturalOrder();
		return new TreeSet<>(comp);
	}

} 
