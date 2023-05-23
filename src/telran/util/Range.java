package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	public LinkedList<Integer> listRemoved = new LinkedList<>();
	private int min;
	private int max;

	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.min = min;
		this.max = max;
	}

	private class RangeIterator implements Iterator<Integer> {
		int current = min;
		int curObj = min - 1;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			while (listRemoved.contains(current)) {
				current++;
			}
			return current < max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			curObj = current++;
			return curObj;
		}

		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			if (!listRemoved.contains(curObj)) {
				listRemoved.add(curObj);
				current--;
			}
			flNext = false;
		}
	}

	public boolean removeIf(Predicate<Integer> predicate) {
		int oldSize = listRemoved.size();
		Iterator<Integer> itr = iterator();
		while (itr.hasNext()) {
			int obj = itr.next();
			if (predicate.test(obj)) {
				itr.remove();
			}
		}
		return oldSize > listRemoved.size();
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