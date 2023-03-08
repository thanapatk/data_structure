package bst;

import static org.junit.Assert.*;

// import java.awt.BasicStroke;

import org.junit.Test;

//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;

public class BSTTest {

	@Test
	public void testFindNextMax() throws Exception {
		BST t0 = new BST();
		assertEquals(20, t0.findNextData(20));

		BSTNode r = new BSTNode(7);
		BST t = new BST(r, 1);
		t.insert(3);
		t.insert(11);
		t.insert(2);
		t.insert(5);
		t.insert(8);
		BTreePrinter.printNode(r);
		assertEquals(2, t.findNextData(1));
		assertEquals(3, t.findNextData(2));
		assertEquals(5, t.findNextData(3));
		assertEquals(5, t.findNextData(4));
		assertEquals(7, t.findNextData(5));
		assertEquals(7, t.findNextData(6));
		assertEquals(8, t.findNextData(7));
		assertEquals(11, t.findNextData(8));
		assertEquals(11, t.findNextData(9));
		assertEquals(11, t.findNextData(10));
		assertEquals(11, t.findNextData(11));
		assertEquals(12, t.findNextData(12));
		assertEquals(13, t.findNextData(13));

	}

	@Test
	public void testCloneTree() {
		BSTNode r1 = new BSTNode(7);
		BST t1 = new BST(r1, 1);
		t1.insert(3);
		t1.insert(11);
		t1.insert(2);
		t1.insert(5);
		t1.insert(8);

		BSTNode r2 = new BSTNode(3);
		BST t2 = new BST(r2, 1);
		t2.insert(21);
		t2.insert(11);
		t2.insert(70);
		t2.insert(39);
		t2.insert(40);

		// t1.printAllData();
		// t2.printAllData();
		//
		// t2.cloneTree(t1);
		//
		// t1.printAllData();
		// t2.printAllData();

	}

	@Test
	public void testFindMax() throws Exception {
		BSTNode r = new BSTNode(7);
		BST t = new BST(r, 1);
		assertEquals(7, t.findMax(t.root).currentNode.data);
		t.insert(3);
		assertEquals(7, t.findMax(t.root).currentNode.data);
		t.insert(11);
		assertEquals(11, t.findMax(t.root).currentNode.data);
		t.insert(2);
		assertEquals(11, t.findMax(t.root).currentNode.data);
		t.insert(15);
		assertEquals(15, t.findMax(t.root).currentNode.data);
		t.insert(8);
		assertEquals(15, t.findMax(t.root).currentNode.data);

	}

}
