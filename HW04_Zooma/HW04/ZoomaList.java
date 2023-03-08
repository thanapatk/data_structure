package HW04;

public class ZoomaList extends CDLinkedList {
	int score = 0;

	public ZoomaList() {
		super();
	}

	public ZoomaList(CDLinkedList l) {
		header = l.header;
		size = l.size;
	}

	private void checkCollistion(int value, Iterator p) throws Exception {
		int count = 0;

		DListIterator start = (DListIterator) p;
		DListIterator end = new DListIterator(start.currentNode);

		while (start.currentNode.data == value) {
			start.previous();
			++count;
		}

		while (end.next() == value) {
			++count;
		}

		if (count >= 3) {
			score += count;

			removeBetween(start, end, count);

			if (start.currentNode.data == end.currentNode.data)
				checkCollistion(start.currentNode.data, start);
		}
	}

	public void insert(int value, Iterator p) throws Exception {
		super.insert(value, p);

		checkCollistion(value, p);
	}

	public void removeBetween(DListIterator left, DListIterator right, int inc) {
		// no data
		if (inc < 1 || left.currentNode == right.currentNode || left.currentNode.nextNode == right.currentNode)
			return;

		this.size -= inc;

		left.currentNode.nextNode = right.currentNode;
		right.currentNode.previousNode = left.currentNode;
	}

}
