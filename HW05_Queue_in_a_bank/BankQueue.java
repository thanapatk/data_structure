
public class BankQueue { // must work for any implementation of DeQ
	DeQ[] counters;
	DeQ special;

	public BankQueue(DeQ[] counters, DeQ special) {
		super();
		this.counters = counters;
		this.special = special;
	}

	private int calculateNeededQueueLength() {
		int count = 0;

		for (int i = 0; i != counters.length; ++i) {
			count += counters[i].size();
		}

		return Math.round(count / ((float) counters.length + 1f));
	}

	// Write this method
	public void distribute() throws Exception {
		int neededQueueLength = this.calculateNeededQueueLength();
		int tmp;
		DeQ currentCounter;

		for (int i = 0; special.size() != neededQueueLength && i != counters.length; ++i) {
			currentCounter = counters[i];

			tmp = Integer.MIN_VALUE;
			if (currentCounter.size() == neededQueueLength)
				continue;
			else if (currentCounter.size() != neededQueueLength + 1) {
				tmp = currentCounter.removeLast();
			}

			special.insertLast(currentCounter.removeLast());

			if (tmp != Integer.MIN_VALUE) {
				if (special.size() != neededQueueLength)
					special.insertLast(tmp);
				else {
					currentCounter.insertLast(tmp);
					return;
				}
			}
		}
		if (special.size() == 0) {
			special.insertLast(counters[counters.length - 1].removeLast());
			return;
		}
	}
}
