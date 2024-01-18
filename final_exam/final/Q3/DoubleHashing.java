
public class DoubleHashing extends OpenAddressing {
	int occupiedSlots = 0;

	public DoubleHashing() {
		this(DEFAULT_SIZE);
	}

	public DoubleHashing(int size) {
		super(size);
	}

	public int hash(int data) {
		return data % array.length;
	}

	public int hash2(int x) {
		return 3 - (x % 3);
	}

	public int find(int data) {
		final int smallNum = 5;
		int h = hash(data);
		int hash2Result = hash2(data);
		for (int i = 0; i < currentSize + smallNum; i++) {
			if (array[h] == 0 || array[h] == data)
				return h;
			h = (h + hash2Result) % array.length;
		}
		return -1;
	}

	public void add(int data) throws Exception {
		int h = hash(data);
		int hash2Result = hash2(data);
		int emptySlotPosition = -1;
		int i;
		final int smallNum = 5; // a small threshold
		for (i = 0; i < currentSize + smallNum; i++) {
			if (array[h] == 0 || array[h] == data)
				break;
			if (array[h] == DELETED && emptySlotPosition == -1) {
				emptySlotPosition = h;
			}
			h = (h + hash2Result) % array.length;
		}
		if (i >= currentSize + smallNum) {
			add(data);
		} else {
			if (array[h] == 0) {
				if (emptySlotPosition != -1) {
					array[emptySlotPosition] = data;
				} else {
					array[h] = data;
					occupiedSlots++;
				}
				currentSize++;
			}
		}
	}

	public void remove(int data) {
		int index = find(data);
		if (index != -1 && array[index] != 0) {
			array[index] = DELETED;
			currentSize--;
		}
	}

}
