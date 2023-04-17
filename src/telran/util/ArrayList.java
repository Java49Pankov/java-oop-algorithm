package telran.util;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;

	private T[] array;

	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size] = obj;
		size++;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public void add(int index, T obj) {
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	@Override
	public T remove(int index) {
		T removeRef = array[index];
		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;
		return removeRef;
	}

	@Override
	public T get(int index) {
		T res = array[index];
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			remove(index);
			res = true;
		}
		return res;
	}

	@Override
	public T[] toArray(T[] array) {
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		System.arraycopy(this.array, 0, array, 0, size);
		if (array.length > size) {
			array[size] = null;

		}
		return array;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int index = 0;
		while (index < size && res == -1) {
			if (isEqual(array[index], pattern)) {
				res = index;
			}
			index++;
		}
		return res;
	}

	private boolean isEqual(T object, T pattern) {
		return pattern == null ? object == pattern : pattern.equals(object);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int index = size;
		while (index >= 0 && res == -1) {
			if (isEqual(array[index], pattern)) {
				res = index;
			}
			index--;
		}
		return res;
	}

	@Override
	public void sort() {
		Arrays.sort(array, 0, size);
	}

	@Override
	public void sort(Comparator<T> comp) {
//	Arrays.sort(array, 0, size, comp);
		for (int i = 0; i < size() - 1; i++) {
			for (int j = 0; j < size() - i - 1; j++) {
				if (comp.compare(array[i + 1], array[j]) > 0) {
					T temp = array[i - 1];
					array[i - 1] = array[i];
					array[i] = temp;
				}
			}
		}
	}

	@Override
	public void sort1(Comparator<T> comp) {
		Arrays.sort(array, 0, size, comp);		
	}

}
