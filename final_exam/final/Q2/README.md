# 2. Treap (11 points)

The Treap data structure can be classified as a specialized form of a Binary Search Tree (BST) that not only have the fundamental properties of a BST but also enforces the property of having the minimum heap value at the root (Min heap). Given [`TreapNode`](TreapNode.java) class, implement the `Treap` class. You only need to write the `insert(int v, int h)` method

Assume that all pair (bstValue, heapValue) values are unique, and there are no duplicate.

There are also codes for Binary Search Tree and AVL Tree available.

--- 

## **Answer**

- Because it is a type of a Binary Search Tree (BST), we just copy the properties and constructor from BST.
	```java
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

		public TreapNode insert(int v, int h) {
			// To implement
		}
	}
	```
- For the insertion
  - First, we just add the bstValue like we normally add in a BST (copy and tweak from BST)
	```java
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
	```
  - Then, to keep the min heap properties, we need to rotate the node up until the current heap value is more than the parent's heap value
	```java
	while (node.parent != null && node.heapValue < node.parent.heapValue) {
		if (node == node.parent.left)
			node = rotateLeftChild(node.parent);
		else
			node = rotateRightChild(node.parent);
	}
	```
  - For the rotation, we can copy and tweak code from AVL Tree. Because AVLNode doesn't keep track of the parent, we need to change the parent node when rotating.
	```java
	public TreapNode rotateLeftChild(TreapNode n) {
		TreapNode l = n.left;
		TreapNode lr = n.left.right; // can be null

		// keep the parent node, and also the location of the current node
		TreapNode parent = n.parent;
		boolean isLeft = parent != null && parent.left == n;

		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		// if the parent is null, the rotated value is the root.
		if (parent == null)
			root = l;
		// else, we just update the nodes
		else {
			if (isLeft)
				parent.left = l;
			else
				parent.right = l;

			l.parent = parent;
		}

		return l;
	}

	// the same apply to `rotateRightChild`
	```
