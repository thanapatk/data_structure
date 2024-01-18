import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class Sorting {
	private static final int CUTOFF = 10;

	// ------------------------- Auxiliary functions --------------------------
	private static <T> void swap(T[] arr, int a, int b) {
		T tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	private static <T> void swap(List<T> arr, int a, int b) {
		T tmp = arr.get(a);
		arr.set(a, arr.get(b));
		arr.set(b, tmp);
	}

	private static void swap(DListIterator itr1, DListIterator itr2) {
		int tmp = itr1.currentNode.data;

		itr1.set(itr2.currentNode.data);
		itr2.set(tmp);
	}

	private static void swap(DListNode node1, DListNode node2) {
		int tmp = node1.data;

		node1.data = node2.data;
		node2.data = tmp;
	}

	private static DListIterator findCenter(DListIterator left, DListIterator right) throws Exception {
		DListIterator fast = new DListIterator(left.currentNode);
		DListIterator center = new DListIterator(left.currentNode);

		while (fast.currentNode != right.currentNode && fast.currentNode.nextNode != right.currentNode) {
			fast.next();
			fast.next();
			center.next();
		}

		return center;
	}

	// ----------------------------- Bubble Sort ------------------------------

	public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
		for (int i = 1; i <= arr.length - 1; i++)
			for (int j = 0; j <= arr.length - 2; j++)
				if (arr[j].compareTo(arr[j + 1]) > 0)
					swap(arr, j, j + 1);
	}

	public static <T extends Comparable<T>> void bubbleSort(List<T> arr) {
		for (int i = 1; i <= arr.size() - 1; i++)
			for (int j = 0; j <= arr.size() - 2; j++)
				if (arr.get(j).compareTo(arr.get(j + 1)) > 0)
					swap(arr, j, j + 1);
	}

	public static void bubbleSort(CDLinkedList list) throws Exception {
		for (int i = list.size(); i != 0; --i) {
			DListIterator itr = new DListIterator(list.header.nextNode);
			for (int j = i - 1; j != 0; --j)
				if (itr.currentNode.data > itr.next())
					swap(itr.currentNode, itr.currentNode.previousNode);
		}
	}

	// ---------------------------- Selection Sort ----------------------------

	public static <T extends Comparable<T>> void selectionSort(T[] arr) {
		int maxIndex;
		for (int unsorted = arr.length; unsorted > 1; unsorted--) {
			maxIndex = 0;
			for (int i = 1; i < unsorted; i++)
				if (arr[maxIndex].compareTo(arr[i]) < 0)
					maxIndex = i;

			if (arr[maxIndex] != arr[unsorted - 1])
				swap(arr, maxIndex, unsorted - 1);
		}
	}

	public static <T extends Comparable<T>> void selectionSort(List<T> arr) {
		int maxIndex;
		for (int unsorted = arr.size(); unsorted > 1; unsorted--) {
			maxIndex = 0;
			for (int i = 1; i < unsorted; i++)
				if (arr.get(maxIndex).compareTo(arr.get(i)) < 0)
					maxIndex = i;

			if (arr.get(maxIndex) != arr.get(unsorted - 1))
				swap(arr, maxIndex, unsorted - 1);
		}
	}

	public static void selectionSort(CDLinkedList list) throws Exception {
		DListIterator last = new DListIterator(list.header.previousNode);

		for (int i = 0; i != list.size(); ++i) {
			DListNode maxNode = list.header.nextNode;
			DListIterator itr = new DListIterator(maxNode.nextNode);

			while (itr.currentNode != last.currentNode.nextNode) {
				if (itr.currentNode.data > maxNode.data)
					maxNode = itr.currentNode;
				itr.next();
			}

			if (maxNode != last.currentNode)
				swap(maxNode, last.currentNode);
			last.previous();
		}
	}

	// ---------------------------- Insertion Sort ----------------------------

	public static <T extends Comparable<T>> void insertionSort(T[] arr) {
		Sorting.insertionSort(arr, 0, arr.length - 1);
	}

	private static <T extends Comparable<T>> void insertionSort(T[] arr, int l, int r) {
		if (r - l <= 0)
			return;

		int j;
		for (int i = l + 1; i != r + 1; ++i) {
			T tmp = arr[i];
			for (j = i; j != l; j--) {
				if (tmp.compareTo(arr[j - 1]) < 0)
					arr[j] = arr[j - 1];
				else
					break;
			}
			arr[j] = tmp;
		}
	}

	public static <T extends Comparable<T>> void insertionSort(List<T> arr) {
		Sorting.insertionSort(arr, 0, arr.size() - 1);
	}

	private static <T extends Comparable<T>> void insertionSort(List<T> arr, int l, int r) {
		if (r - l <= 0)
			return;

		int j;
		for (int i = l + 1; i != r + 1; ++i) {
			T tmp = arr.get(i);
			for (j = i; j != l; j--) {
				if (tmp.compareTo(arr.get(j - 1)) < 0)
					arr.set(j, arr.get(j - 1));
				else
					break;
			}
			arr.set(j, tmp);
		}
	}

	public static void insertionSort(CDLinkedList list) throws Exception {
		Sorting.insertionSort(list, 0, list.size() - 1);
	}

	private static void insertionSort(CDLinkedList list, int l, int r) throws Exception {
		if (r - l <= 0)
			return;

		DListIterator sorted = new DListIterator(list.header);
		for (int i = -1; i != l; ++i)
			sorted.next();

		for (int i = l + 1; i != r + 1; ++i) {
			DListIterator current = new DListIterator(sorted.currentNode.nextNode);
			for (int j = i; j != l; j--) {
				if (current.previous() < current.currentNode.data) {
					swap(current.currentNode, current.currentNode.nextNode);
				} else {
					break;
				}
			}
			sorted.next();
		}
	}

	// ------------------------------ Merge Sort ------------------------------

	private static <T extends Comparable<T>> T[] merge(T[] a, T[] b) {
		int aIndex = 0, bIndex = 0, cIndex = 0;
		int aLength = a.length;
		int bLength = b.length;
		int cLength = aLength + bLength;

		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), cLength);

		while ((aIndex < aLength) && (bIndex < bLength)) {
			if (a[aIndex].compareTo(b[bIndex]) <= 0)
				c[cIndex++] = a[aIndex++];
			else
				c[cIndex++] = b[bIndex++];
		}

		if (aIndex == aLength)
			while (bIndex < bLength)
				c[cIndex++] = b[bIndex++];
		else
			while (aIndex < aLength)
				c[cIndex++] = a[aIndex++];

		return c;
	}

	private static <T extends Comparable<T>> T[] mergeSort(T[] arr, int left, int right) {
		if (left == right) {
			T[] output = (T[]) Array.newInstance(arr.getClass().getComponentType(), 1);
			output[0] = arr[left];
			return output;
		}

		int center = (left + right) / 2;
		T[] result1 = mergeSort(arr, left, center);
		T[] result2 = mergeSort(arr, center + 1, right);

		return merge(result1, result2);
	}

	public static <T extends Comparable<T>> void mergeSort(T[] arr) {
		if (arr.length < 1)
			return;

		System.arraycopy(Sorting.mergeSort(arr, 0, arr.length - 1), 0, arr, 0, arr.length);
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> a, List<T> b) {
		int aIndex = 0, bIndex = 0;
		int aLength = a.size();
		int bLength = b.size();

		List<T> c = new ArrayList<T>();

		while ((aIndex < aLength) && (bIndex < bLength)) {
			if (a.get(aIndex).compareTo(b.get(bIndex)) < 0)
				c.add(a.get(aIndex++));
			else
				c.add(b.get(bIndex++));
		}

		if (aIndex == aLength)
			c.addAll(b.subList(bIndex, bLength));
		else
			c.addAll(a.subList(aIndex, aLength));

		return c;
	}

	private static <T extends Comparable<T>> List<T> mergeSort(List<T> arr, int left, int right) {
		if (left == right) {
			List<T> output = new ArrayList<T>();
			output.add(arr.get(left));
			return output;
		}

		int center = (left + right) / 2;
		List<T> result1 = mergeSort(arr, left, center);
		List<T> result2 = mergeSort(arr, center + 1, right);

		return merge(result1, result2);
	}

	public static <T extends Comparable<T>> void mergeSort(List<T> arr) {
		if (arr.size() <= 1)
			return;
		List<T> sorted = mergeSort(arr, 0, arr.size() - 1);
		arr.clear();
		arr.addAll(sorted);
	}

	private static CDLinkedList merge(CDLinkedList a, CDLinkedList b) throws Exception {
		DListIterator aItr = new DListIterator(a.header.nextNode);
		DListIterator bItr = new DListIterator(b.header.nextNode);

		CDLinkedList output = new CDLinkedList();
		DListIterator outputItr = new DListIterator(output.header);

		while (aItr.currentNode.data != CDLinkedList.HEADERVALUE && bItr.currentNode.data != CDLinkedList.HEADERVALUE) {
			if (aItr.currentNode.data < bItr.currentNode.data) {
				output.insert(aItr.currentNode.data, outputItr);
				aItr.next();
			} else {
				output.insert(bItr.currentNode.data, outputItr);
				bItr.next();
			}

			outputItr.next();
		}

		if (aItr.currentNode.data == CDLinkedList.HEADERVALUE) {
			outputItr.currentNode.nextNode = bItr.currentNode;
			output.header.previousNode = b.header.previousNode;
		} else {
			outputItr.currentNode.nextNode = aItr.currentNode;
			output.header.previousNode = a.header.previousNode;
		}

		return output;
	}

	private static CDLinkedList mergeSort(DListIterator left, DListIterator right)
			throws Exception {
		if (left.currentNode == right.currentNode) {
			CDLinkedList output = new CDLinkedList();
			output.insert(left.currentNode.data, new DListIterator(output.header));
			return output;
		}

		DListIterator center = findCenter(left, right);

		CDLinkedList result1 = mergeSort(left, center);
		center.next();
		CDLinkedList result2 = mergeSort(center, right);

		return merge(result1, result2);
	}

	public static void mergeSort(CDLinkedList list) throws Exception {
		if (list.size() < 1)
			return;

		list.header = mergeSort(new DListIterator(list.header.nextNode),
				new DListIterator(list.header.previousNode)).header;
	}

	// ------------------------------ Quick Sort ------------------------------

	private static <T extends Comparable<T>> int pivotIndex(T[] arr, int l, int r) {
		int c = (l + r) / 2;

		if ((arr[l].compareTo(arr[r]) <= 0 && arr[l].compareTo(arr[c]) >= 0) ||
				(arr[l].compareTo(arr[r]) >= 0 && arr[l].compareTo(arr[c]) <= 0))
			return l;
		if ((arr[c].compareTo(arr[l]) <= 0 && arr[c].compareTo(arr[r]) >= 0) ||
				(arr[c].compareTo(arr[l]) >= 0 && arr[c].compareTo(arr[r]) <= 0))
			return c;
		return r;
	}

	private static <T extends Comparable<T>> void quickSort(T[] arr, int l, int r) {
		if (l + CUTOFF > r)
			insertionSort(arr, l, r);
		else {
			// find pivot using median of 3.
			int pIndex = pivotIndex(arr, l, r);

			// get pivot out of the way.
			swap(arr, pIndex, r);
			T pivot = arr[r];

			// start partitioning.
			int i = l, j = r - 1;
			while (true) {
				while (i < r && arr[i].compareTo(pivot) < 0)
					i++;
				while (j > l && arr[j].compareTo(pivot) > 0)
					j--;

				if (i < j)
					swap(arr, i++, j--);
				else
					break;
			} // end partitioning.

			// swap pivot into its correct position.
			swap(arr, i, r);

			// quick sort on sublists.
			quickSort(arr, l, i - 1);
			quickSort(arr, i + 1, r);
		}

	}

	public static <T extends Comparable<T>> void quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private static <T extends Comparable<T>> int pivotIndex(List<T> list, int l, int r) {
		int c = (l + r) / 2;
		if ((list.get(l).compareTo(list.get(r)) <= 0 && list.get(l).compareTo(list.get(c)) >= 0) ||
				(list.get(l).compareTo(list.get(r)) >= 0 && list.get(l).compareTo(list.get(c)) <= 0))
			return l;
		if ((list.get(c).compareTo(list.get(l)) <= 0 && list.get(c).compareTo(list.get(r)) >= 0)
				|| (list.get(c).compareTo(list.get(l)) >= 0 && list.get(c).compareTo(list.get(r)) <= 0))
			return c;
		return r;
	}

	private static <T extends Comparable<T>> void quickSort(List<T> list, int l, int r) {
		if (l + CUTOFF > r) {
			insertionSort(list, l, r);
		} else {
			// find pivot using median of 3.
			int pIndex = pivotIndex(list, l, r);

			// get pivot out of the way.
			swap(list, pIndex, r);
			T pivot = list.get(r);

			// start partitioning.
			int i = l, j = r - 1;
			while (true) {
				while (i < r && list.get(i).compareTo(pivot) < 0)
					i++;
				while (j > l && list.get(j).compareTo(pivot) > 0)
					j--;
				if (i < j)
					swap(list, i++, j--);
				else
					break;

			} // end partitioning.

			// swap pivot into its correct position.
			swap(list, i, r);

			// quick sort on sublists.
			quickSort(list, l, i - 1);
			quickSort(list, i + 1, r);
		}
	}

	public static <T extends Comparable<T>> void quickSort(List<T> list) {
		quickSort(list, 0, list.size() - 1);
	}

	private static DListIterator getPivotItr(DListIterator left, DListIterator right) throws Exception {
		DListIterator center = findCenter(left, right);

		int leftValue = left.currentNode.data;
		int centerValue = center.currentNode.data;
		int rightValue = right.currentNode.data;

		if ((leftValue <= rightValue && leftValue >= centerValue)
				|| (leftValue >= rightValue && leftValue <= centerValue))
			return left;

		if ((centerValue <= leftValue && centerValue >= rightValue)
				|| (centerValue >= leftValue && centerValue <= rightValue))
			return center;

		return right;
	}

	private static void quickSort(DListIterator left, DListIterator right) throws Exception {
		if (left.currentNode == right.currentNode || left.currentNode == right.currentNode.nextNode)
			return;

		DListIterator pivot = getPivotItr(left, right);
		swap(pivot, right);

		DListIterator i = new DListIterator(left.currentNode);
		DListIterator j = new DListIterator(right.currentNode.previousNode);

		while (i.currentNode != j.currentNode && i.currentNode.previousNode != j.currentNode) {
			while (i.currentNode != j.currentNode && i.currentNode.data < right.currentNode.data)
				i.next();
			while (i.currentNode != j.currentNode && j.currentNode.data > right.currentNode.data)
				j.previous();

			swap(i, j);
		}

		if (i.currentNode.data < right.currentNode.data)
			i.next();
		swap(i, right);

		quickSort(left, new DListIterator(i.currentNode.previousNode));
		quickSort(new DListIterator(i.currentNode.nextNode), right);
	}

	public static void quickSort(CDLinkedList list) throws Exception {
		quickSort(new DListIterator(list.header.nextNode), new DListIterator(list.header.previousNode));
	}
}