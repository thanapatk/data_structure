public class SepChaining {
	private static int DEFAULT_SIZE = 101;
	private DoubleHashing[] lists;
	private int currentSize = 0;

	public SepChaining() {
		this(DEFAULT_SIZE);
	}

	public SepChaining(int size) {
		this(size, DoubleHashing.DEFAULT_SIZE);
	}

	public SepChaining(int SepChainingSize, int DoubleHashingSize) {
		lists = new DoubleHashing[SepChainingSize];
		for (int i = 0; i != SepChainingSize; i++)
			lists[i] = new DoubleHashing(DoubleHashingSize);
	}

	public int hash(int data) {
		return data % lists.length;
	}

	public int find(int data) throws Exception {
		int pos = hash(data);
		return lists[pos].find(data);
	}

	public void add(int data) throws Exception {
		int pos = hash(data);
		DoubleHashing list = lists[pos];

		list.add(data);
		currentSize++;
	}

	public void remove(int data) {
		int pos = hash(data);
		DoubleHashing list = lists[pos];

		if (list.find(data) != -1) {
			list.remove(data);
			currentSize--;
		}
	}

	public void printList() {
		for (int i = 0; i != lists.length; i++)
			System.out.println(String.format("%d: %s", i, lists[i].toString()));
	}

	// driver code
	public static void main(String[] args) throws Exception {
		SepChaining table = new SepChaining(7, 5);

		table.add(5);
		table.add(26);
		table.add(12);
		table.add(40);

		table.remove(12);
		table.remove(5);

		table.add(47);
		table.add(12);

		table.printList();
	}

}
