# Final exam questions

## [1. Ternary min heap (11 points)](final/Q1)

A ternary min heap is a complete ternary tree where each node has up to three children and the nodes are filled in a breadth-first traversal order. All other properties are the same as normal min heap. Implement a ternary heap given a normal heap class.

---

## [2. Treap (11 points)](final/Q2)

The Treap data structure can be classified as a specialized form of a Binary Search Tree (BST) that not only have the fundamental properties of a BST but also enforces the property of having the minimum heap value at the root (Min heap). Given [`TreapNode`](TreapNode.java) class, implement the `Treap` class. You only need to write the `insert(int v, int h)` method

Assume that all pair (bstValue, heapValue) values are unique, and there are no duplicate.

There are also codes for Binary Search Tree and AVL Tree available.

---

## [3. Hash table (8 points)](final/Q3)

The current study considers a **separate chaining hash table** with a size of **7**. This hash table employs a hash function, denoted as **h(x) = x % table.size()**. Each slot within the hash table contains an **open addressing hash table** of size **5**. The open addressing hash table utilizes the same hash function, **h(x) = x % table.size()**. Additionally, a probing function, **f(i) = i \* h2(x)**, is employed, where **h2(x) = 3 - (x % 3)**. The implementation adopts a **lazy deletion** approach. It is important to note that **no rehashing** has occurred during the implementation process.

- Add 5, 26, 12, 40
- Delete 12, delete 5
- Add 47, 12

Given an image, fill the output of this hash table.

| Separate Chaining |     Open Addressing     |
| :---------------: | :---------------------: |
|         0         | `[ _ , _ , _ , _ , _ ]` |
|         1         | `[ _ , _ , _ , _ , _ ]` |
|         2         | `[ _ , _ , _ , _ , _ ]` |
|         3         | `[ _ , _ , _ , _ , _ ]` |
|         4         | `[ _ , _ , _ , _ , _ ]` |
|         5         | `[ _ , _ , _ , _ , _ ]` |
|         6         | `[ _ , _ , _ , _ , _ ]` |

---

# Make-up final exam questions

## [1. Merge Sort Stack (11 points)](make-up/Q1)

Write code for method `sort(Stack s)`, which uses merge sort. This method receives a stack as its parameter, then **sort** the stack so that the smallest value is on top and the largest value is on the bottom. You are **not allowed** to create other data structures apart from Stack

---

## 2. Min-max heap
