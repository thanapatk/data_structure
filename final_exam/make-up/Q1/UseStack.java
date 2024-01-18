public class UseStack {
	public static Stack reverse(Stack s) throws Exception {
		Stack rev = new StackArray(s.size());

		while (!s.isEmpty()) {
			rev.push(s.top());
			s.pop();
		}

		return rev;
	}

	public static Stack sort(Stack s) throws Exception {
		if (s.size() <= 1)
			return s;

		int midPoint = s.size() / 2;
		Stack tmp = new StackArray(midPoint);

		while (midPoint-- != 0) {
			tmp.push(s.top());
			s.pop();
		}

		s = reverse(sort(s));
		tmp = reverse(sort(tmp));
		Stack sorted = new StackArray(s.size() + tmp.size());

		while (!s.isEmpty() && !tmp.isEmpty()) {
			if (s.top() > tmp.top()) {
				sorted.push(s.top());
				s.pop();
			} else {
				sorted.push(tmp.top());
				tmp.pop();
			}
		}

		Stack remaining = s.isEmpty() ? tmp : s;
		while (!remaining.isEmpty()) {
			sorted.push(remaining.top());
			remaining.pop();
		}

		return sorted;
	}
}
