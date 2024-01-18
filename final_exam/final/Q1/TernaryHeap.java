public class TernaryHeap extends Heap {
	protected void percolateUp() {
		int parent;
		int child = size - 1;
		int temp;
		while (child > 0) {
			// Change from divide by 2 to 3
			parent = (child - 1) / 3;
			if (mData[parent] <= mData[child])
				break;
			temp = mData[parent];
			mData[parent] = mData[child];
			mData[child] = temp;
			child = parent;
		}
	}

	private int findMinIndex(int start, int end) {
		int min = start;
		for (int i = min + 1; i != end && i < size; i++)
			if (mData[i] < mData[min])
				min = i;

		return min;
	}

	protected void percolateDown(int start) {
		int parent = start;
		// Change the start child index from 2 * i + 1 to 3 * i + 1
		int child = 3 * parent + 1;
		int temp;
		while (child < size) {
			// Use the findMinIndex to find the min heap value child
			child = findMinIndex(child, child + 3);

			if (mData[parent] <= mData[child])
				break;
			temp = mData[child];
			mData[child] = mData[parent];
			mData[parent] = temp;
			parent = child;
			// Change the start child index from 2 * i + 1 to 3 * i + 1
			child = 3 * parent + 1;
		}
	}
}
