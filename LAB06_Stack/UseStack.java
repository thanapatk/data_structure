
public class UseStack {

	// implement this method.
	public static Stack sort(Stack s) throws Exception {
		Stack sorted = new ArrayListStack();

		while (!s.isEmpty()) {
			int tmp = s.poptop();
			while (!sorted.isEmpty() && sorted.top() < tmp) {
				s.push(sorted.poptop());
			}
			sorted.push(tmp);
		}

		return sorted;
	}

}
