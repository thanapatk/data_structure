
public class PriorityQueue {
	MyQueue q;

	public PriorityQueue(MyQueue q) {
		super();
		this.q = q;
	}

	// implement this.
	public void push(int x) throws Exception {

		MyQueue newQueue = new QueueArray();
		boolean inserted = false;

		while (!q.isEmpty()) {
			if (!inserted && x < q.front()) {
				newQueue.insertLast(x);
				inserted = true;
			}
			newQueue.insertLast(q.removeFirst());
		}

		if (!inserted) {
			newQueue.insertLast(x);
		}

		this.q = (MyQueue) newQueue;

	}

	// implement this.
	public void pop() throws Exception {
		if (q.isEmpty())
			throw new EmptyQueueException();

		q.removeFirst();
	}

	// implement this
	public int top() throws Exception {
		if (q.isEmpty())
			throw new EmptyQueueException();

		return q.front();
	}

}
