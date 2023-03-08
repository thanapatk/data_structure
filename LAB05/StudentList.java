
public class StudentList extends CDLinkedList {
	// you can write additional methods.

	// implement this method
	public void swapNode(DListIterator i1, DListIterator i2) {
		Object tmp = i1.currentNode.data;

		i1.currentNode.data = i2.currentNode.data;
		i2.currentNode.data = tmp;
	}

	// implement this method
	public void insertList(DListIterator i1, CDLinkedList lst) {
		if (lst.isEmpty())
			return;
		try {
			DListNode currentNode = i1.currentNode;
			DListIterator cIt = new DListIterator(lst.header);

			i1.next();
			cIt.next();

			currentNode.nextNode = cIt.currentNode;
			cIt.currentNode.previousNode = currentNode;

			System.out.println(lst.size());

			while (cIt.currentNode.nextNode != lst.header)
				cIt.next();

			cIt.currentNode.nextNode = i1.currentNode;
			i1.currentNode.previousNode = cIt.currentNode;

		} catch (Exception e) {
		}

	}

	// implement this method
	public void gender(String g) throws Exception {
		CDLinkedList tmp = new CDLinkedList();
		DListIterator tmpIt = new DListIterator(tmp.header);

		DListIterator it = new DListIterator(header);
		it.next();
		while (it.currentNode != header) {
			Student currentNode = (Student) it.currentNode.data;
			if (currentNode.getSex().equals(g)) {
				tmp.insert(currentNode, tmpIt);
				tmpIt.next();
				removeAt(it);
			}
			it.next();
		}

		insertList(it, tmp);
	}
}
