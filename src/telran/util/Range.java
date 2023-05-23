package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	private int min;
	private int max;
	public LinkedList<Integer> listRemoved = new LinkedList<>();

	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.min = min;
		this.max = max;
	}

	private class RangeIterator implements Iterator<Integer> {
		int current = min;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current < max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			while (listRemoved.contains(current)) {
				current++;
			}
			flNext = true;
			return current++;
		}

		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			int obj = --current;
			listRemoved.add(obj);
			flNext = false;
			current--;
		}
	}

	public void removeIf(Predicate<Integer> predicate) {
		Iterator<Integer> itr = iterator();
		while (itr.hasNext()) {
			int obj = itr.next();
			if (predicate.test(obj)) {
				itr.remove();
				max--;
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	public Integer[] toArray() {
		Integer[] array = new Integer[max - min - listRemoved.size()];
		int index = 0;
		for (Integer num : this) {
			array[index++] = num;
		}
		return array;
	}

}