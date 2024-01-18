# 1. Ternary min heap (11 points)

A ternary min heap is a complete ternary tree where each node has up to three children and the nodes are filled in a breadth-first traversal order. All other properties are the same as normal min heap. Implement a ternary heap given a normal heap class.

---

## **Answer**

- Because almost all the properties are the same, we can just extend the `TernaryHeap` class from `Heap`.
- Then, we can fix the `percolateUp` and `percolateDown` function
- For `percolateUp`

  - With the hint, we can know that the parent node is at $(i-1)/3$, so we can change the code to:

    ```java
    protected void percolateUp() {
    	int parent;
    	int child = size - 1;
    	int temp;
    	while (child > 0) {
    		// Change from divide by 2 to 3
    		parent = (child - 1) / 3;
    		if (mData[parent] <= mData[child])
    			break;
    		temp = mData[parent];
    		mData[parent] = mData[child];
    		mData[child] = temp;
    		child = parent;
    	}
    }
    ```

- For `percolateDown`

  - With a binary min heap, there is only two child to compare to, which the old `percolateDown` check like this

    ```java
    if (child < size - 1 && mData[child] > mData[child + 1])
    	child++;
    ```

  - Which wouldn't work with ternary min heap, so we create `findMinIndex` function

    ```java
    private int findMinIndex(int start, int end) {
    	int min = start;
    	for (int i = min + 1; i != end && i < size; i++)
    	if (mData[i] < mData[min])
    		min = i;

    	return min;
    }
    ```

  - Finally, from the hint, we know that the child nodes are at $3i+1$, $3i+2$, $3i+3$. Using all these knowledge, we change the `percolateDown` function to:

    ```java
    protected void percolateDown(int start) {
    	int parent = start;
    	// Change the start child index from 2 * i + 1 to 3 * i + 1
    	int child = 3 * parent + 1;
    	int temp;
    	while (child < size) {
    		// Use the `findMinIndex` to find the min heap value child
    		child = findMinIndex(child, child + 3);

    		if (mData[parent] <= mData[child])
    			break;
    		temp = mData[child];
    		mData[child] = mData[parent];
    		mData[parent] = temp;
    		parent = child;
    		// Change the start child index same as above
    		child = 3 * parent + 1;
    	}
    }
    ```
