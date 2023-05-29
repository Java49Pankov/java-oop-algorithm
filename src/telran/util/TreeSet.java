package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements Set<T> {

	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> root;
	private Comparator<T> comp;
	private int size;

	public TreeSet(Comparator<T> comparator) {
		this.comp = comparator;
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current;
		Node<T> prev;
		boolean flNext = false;

		TreeSetIterator() {
			current = root == null ? null : getLeast(root);
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			prev = current;
			current = getCurrent(current);
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removeNode(prev);
			flNext = false;
		}
	}

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		boolean res = false;
		if (size == 0) {
			root = node;
			res = true;
		} else {
			Node<T> parent = getParent(obj);
			if (parent != null) {
				res = true;
				node.parent = parent;
				if (comp.compare(obj, parent.obj) > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}
		}
		if (res) {
			size++;
		}
		return res;
	}

	private Node<T> getCurrent(Node<T> current) {
		return current.right != null ? getLeast(current.right) : getGreaterParent(current);
	}

	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current == current.parent.right) {
			current = current.parent;
		}
		return current.parent;
	}

	private Node<T> getLeast(Node<T> node) {
		Node<T> current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	private Node<T> getNodeParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while (current != null && (compRes = comp.compare(obj, current.obj)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}

	private Node<T> getNode(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		if (node != null && comp.compare(obj, node.obj) == 0) {
			res = node;
		}
		return res;

	}

	private Node<T> getParent(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		if (node != null && comp.compare(obj, node.obj) != 0) {
			res = node;
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}

		return res;
	}

	private void removeNode(Node<T> node) {
		if (node.left == null) {
			if (node.parent == null) {
				root = node.right;
			} else {
				node = getRightChild(node);
			}
		} else if (node.right == null) {
			if (node.parent == null) {
				root = node.left;
			} else {
				node = getLeftChild(node);
			}
		}
		size--;
	}

	private Node<T> getLeftChild(Node<T> node) {
		if (node == node.parent.left) {
			node.parent.left = node.left;
		} else {
			node.parent.right = node.left;
		}
		if (node.left != null) {
			node.left.parent = node.parent;
		}
		return node;
	}

	private Node<T> getRightChild(Node<T> node) {
		if (node == node.parent.right) {
			node.parent.right = node.right;
		} else {
			node.parent.left = node.right;
		}
		if (node.right != null) {
			node.right.parent = node.parent;
		}
		return node;
	}

	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

}