import java.util.ArrayList;

public class ArrayListStack implements Stack {
	private ArrayList<Integer> a;
	static final int MAX_SIZE = 9999; // not sure

	// implement a default constructor and all methods from interface Stack.
	// Additional methods maybe required in order to run tests.
	public ArrayListStack() {
		a = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getA() {
		return a;
	}

	@Override
	public boolean isEmpty() {
		return a.isEmpty();
	}

	@Override
	public boolean isFull() {
		return a.size() > MAX_SIZE;
	}

	@Override
	public void makeEmpty() {
		a.clear();
	}

	@Override
	public int top() throws EmptyStackException {
		if (a.size() == 0) {
			throw new EmptyStackException();
		}
		return a.get(a.size() - 1);
	}

	@Override
	public void pop() throws EmptyStackException {
		if (a.size() == 0) {
			throw new EmptyStackException();
		}

		a.remove(a.size() - 1);
	}

	public int poptop() throws EmptyStackException {
		if (a.size() == 0) {
			throw new EmptyStackException();
		}

		int tmp = this.top();
		a.remove(a.size() - 1);
		return tmp;
	}

	@Override
	public void push(int data) throws Exception {
		if (a.size() > MAX_SIZE)
			return;

		a.add(data);
	}

}
