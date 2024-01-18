public class CDLinkedList {
	DListNode header;
	int size;
	static final int HEADERVALUE = -9999999;

	public CDLinkedList() {
		header = new DListNode(HEADERVALUE);
		makeEmpty();// necessary, otherwise next/previous node will be null
	}

	public boolean isEmpty() {
		return header.nextNode == header;
	}

	public boolean isFull() {
		return false;
	}

	/** make the list empty. */
	public void makeEmpty() {
		header.nextNode = header;
		header.previousNode = header;
		size = 0;
	}

	// put in new data after the position of p.
	public void insert(int value, Iterator p) throws Exception {
		if (p == null || !(p instanceof DListIterator))
			throw new Exception();
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();

		DListIterator p3 = new DListIterator(p2.currentNode.nextNode);
		DListNode n = new DListNode(value, p3.currentNode, p2.currentNode);
		p2.currentNode.nextNode = n;
		p3.currentNode.previousNode = n;
		size++;
	}

	// return position number of value found in the list.
	// otherwise, return -1.
	public int find(int value) throws Exception {
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				return -1;
			if (v == value)
				return index; // return the position of value.
		}
		return -1;
	}

	// return data stored at kth position.
	public int findKth(int kthPosition) throws Exception {
		if (kthPosition < 0 || kthPosition > size - 1)
			throw new Exception();// exit the method if the position is
		// beyond the first/last possible
		// position, throwing exception in the process.
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				throw new Exception();
			if (index == kthPosition)
				return v;
		}
		throw new Exception();
	}

	// Return iterator at position before the first position that stores value.
	// If the value is not found, return null.
	public Iterator findPrevious(int value) throws Exception {
		if (isEmpty())
			return null;
		Iterator itr1 = new DListIterator(header);
		Iterator itr2 = new DListIterator(header);
		int currentData = itr2.next();
		while (currentData != value) {
			currentData = itr2.next();
			itr1.next();
			if (((DListIterator) itr2).currentNode == header)
				return null;
		}
		return itr1;
	}

	// remove content at position just after the given iterator. Skip header if
	// found.
	public void remove(Iterator p) {
		if (isEmpty())
			return;
		if (p == null || !(p instanceof DListIterator))
			return;
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			return;
		if (p2.currentNode.nextNode == header)
			p2.currentNode = header;
		if (p2.currentNode.nextNode == null)
			return;
		DListIterator p3 = new DListIterator(p2.currentNode.nextNode.nextNode);
		p2.currentNode.nextNode = p3.currentNode;
		p3.currentNode.previousNode = p2.currentNode;
		size--;
	}

	// remove the first instance of the given data.
	public void remove(int value) throws Exception {
		Iterator p = findPrevious(value);
		if (p == null)
			return;
		remove(p);
	}

	// remove data at position p.
	// if p points to header or the list is empty, do nothing.
	public void removeAt(Iterator p) throws Exception {
		if (isEmpty() || p == null || !(p instanceof DListIterator) || ((DListIterator) p).currentNode == null
				|| ((DListIterator) p).currentNode == header)
			return;

		DListIterator p2 = (DListIterator) (findPrevious(p));
		remove(p2);

	}

	// Print each contact out, one by one.
	// To be completed by students.
	public void printList() throws Exception {
		Iterator itr = new DListIterator(header);

		while (itr.next() != HEADERVALUE) {
			Object data = ((DListIterator) itr).currentNode.data;

			System.out.println(data);
		}
		System.out.println();
	}

	public int size() throws Exception {
		return size;
	}

	// return iterator pointing to location before position.
	public Iterator findPrevious(Iterator position) throws Exception {
		if (position == null)
			return null;
		if (!(position instanceof DListIterator))
			return null;
		if (((DListIterator) position).currentNode == null)
			return null;

		DListIterator p = ((DListIterator) position);
		DListIterator p2 = new DListIterator(p.currentNode.previousNode);
		return p2;

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

	private void bubbleSort() throws Exception {
		for (int i = size; i != 0; --i) {
			DListIterator itr = new DListIterator(header.nextNode);
			for (int j = i - 1; j != 0; --j) {
				if (itr.currentNode.data > itr.next())
					swap(itr.currentNode, itr.currentNode.previousNode);
			}
		}
	}

	private void selectionSort() throws Exception {
		DListIterator last = new DListIterator(header.previousNode);
		for (int i = 0; i != size; ++i) {
			DListNode maxNode = header.nextNode;
			DListIterator itr = new DListIterator(header.nextNode.nextNode);

			while (itr.currentNode != last.currentNode.nextNode) {
				if (itr.currentNode.data > maxNode.data) {
					maxNode = itr.currentNode;
				}
				itr.next();
			}

			if (maxNode != last.currentNode)
				swap(maxNode, last.currentNode);
			last.previous();
		}
	}

	private void insertionSort() throws Exception {
		this.insertionSort(0, size - 1);
	}

	private void insertionSort(int l, int r) throws Exception {
		if (r - l <= 0)
			return;

		DListIterator sorted = new DListIterator(header);
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

	private static CDLinkedList merge(CDLinkedList a, CDLinkedList b) throws Exception {
		DListIterator aItr = new DListIterator(a.header.nextNode);
		DListIterator bItr = new DListIterator(b.header.nextNode);

		CDLinkedList output = new CDLinkedList();
		DListIterator outputItr = new DListIterator(output.header);

		while (aItr.currentNode.data != HEADERVALUE && bItr.currentNode.data != HEADERVALUE) {
			if (aItr.currentNode.data < bItr.currentNode.data) {
				output.insert(aItr.currentNode.data, outputItr);
				aItr.next();
			} else {
				output.insert(bItr.currentNode.data, outputItr);
				bItr.next();
			}

			outputItr.next();
		}

		DListIterator remainingItr = aItr.currentNode.data != HEADERVALUE ? aItr : bItr;
		while (remainingItr.currentNode.data != HEADERVALUE) {
			output.insert(remainingItr.currentNode.data, outputItr);

			remainingItr.next();
			outputItr.next();
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

	private void mergeSort() throws Exception {
		if (size < 1)
			return;

		this.header = mergeSort(new DListIterator(header.nextNode), new DListIterator(header.previousNode)).header;
	}

	private DListIterator getPivotItr(DListIterator left, DListIterator right) throws Exception {
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

	private void quickSort() throws Exception {
		quickSort(new DListIterator(header.nextNode), new DListIterator(header.previousNode));
	}

	private void quickSort(DListIterator left, DListIterator right) throws Exception {
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

	// write the sort method below
	public void sort() throws Exception {
		// this.bubbleSort();
		// this.selectionSort();
		// this.insertionSort();
		this.mergeSort();
		// this.quickSort();
	}

}