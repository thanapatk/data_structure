
public class MaxIntHeap extends Heap {
	public void add(Object element) {
		// Add your code here
		if (!(element instanceof Integer)) {
			System.err.println("Not a int");
			return;
		}

		if (++size == mData.length) {
			Object[] newHeap = new Object[2 * mData.length];
			System.arraycopy(mData, 0, newHeap, 0, size);
			mData = newHeap;
		}
		mData[size - 1] = (int) element * -1;
		percolateUp();

	}

	public Object pop() throws Exception {
		// Add your code here
		if (size == 0)
			throw new Exception("Priority queue empty.");
		Object minElem = mData[0];
		mData[0] = mData[size - 1];
		size--;
		percolateDown(0);
		return (int) minElem * -1;
	}
}
