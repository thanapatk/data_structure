public class Treap {
	TreapNode root;
	int size;

	public Treap() {
		this(null, 0);
	}

	public Treap(TreapNode root, int size) {
		this.root = root;
		this.size = size;
	}

	public TreapNode rotateLeftChild(TreapNode n) {
		TreapNode l = n.left;
		TreapNode lr = n.left.right; // can be null

		TreapNode parent = n.parent;
		boolean isLeft = parent != null && parent.left == n;

		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		if (parent == null)
			root = l;
		else {
			if (isLeft)
				parent.left = l;
			else
				parent.right = l;

			l.parent = parent;
		}

		return l;
	}

	public TreapNode rotateRightChild(TreapNode n) {
		TreapNode r = n.right;
		TreapNode rl = n.right.left; // can be null

		TreapNode parent = n.parent;
		boolean isLeft = parent != null && parent.left == n;

		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		if (parent == null)
			root = r;
		else {
			if (isLeft)
				parent.left = r;
			else
				parent.right = r;

			r.parent = parent;
		}

		return r;
	}

	public TreapNode insert(int v, int h) {
		TreapNode parent = null;
		TreapNode temp = root;

		// This first part is almost the same as find,
		// but it has an extra pointer called parent.
		while (temp != null && temp.bstValue != v) {
			if (v < temp.bstValue) {
				parent = temp;
				temp = temp.left;

			} else {
				parent = temp;
				temp = temp.right;

			}
		}

		// if found, don't do anything
		if (temp != null)
			return null;

		TreapNode node = new TreapNode(v, h, null, null, parent);
		if (parent == null) {
			root = node;
		} else if (v < parent.bstValue) {
			parent.left = node;
		} else {
			parent.right = node;
		}
		size++;

		while (node.parent != null && node.heapValue < node.parent.heapValue) {
			if (node == node.parent.left)
				node = rotateLeftChild(node.parent);
			else
				node = rotateRightChild(node.parent);
		}

		return node;
	}
}
