# 3. Hash table (8 points)

The current study considers a **separate chaining hash table** with a size of **7**. This hash table employs a hash function, denoted as **h(x) = x % table.size()**. Each slot within the hash table contains an **open addressing hash table** of size **5**. The open addressing hash table utilizes the same hash function, **h(x) = x % table.size()**. Additionally, a probing function, **f(i) = i \* h2(x)**, is employed, where **h2(x) = 3 - (x % 3)**. The implementation adopts a **lazy deletion** approach. It is important to note that **no rehashing** has occurred during the implementation process.

- Add 5, 26, 12, 40
- Remove 12, 5
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

## **Answer**

We can copy both separate chaning hash table and open addressing hash table from ajarn and tweek it.

Given that there is no rehashing, we delete all the rehashing part. Then, we fix the `hash2` function to be:

```java
public int hash2(int x) {
	return 3 - (x % 3);
}
```

After we done fixing the code, we can add this driver code in [SepChaining](SepChaining.java):

```java
public static void main(String[] args) throws Exception {
	SepChaining table = new SepChaining(7, 5);

	table.add(5);
	table.add(26);
	table.add(12);
	table.add(40);

	table.remove(12);
	table.remove(5);

	table.add(47);
	table.add(12);

	table.printList();
}
```

Running the code, we can get this result.

```
0: [0, 0, 0, 0, 0]
1: [0, 0, 0, 0, 0]
2: [0, 0, 0, 0, 0]
3: [0, 0, 0, 0, 0]
4: [0, 0, 0, 0, 0]
5: [12, 26, 47, 0, 40]
6: [0, 0, 0, 0, 0]
```

### So the answer is:

| Separate Chaining |       Open Addressing       |
| :---------------: | :-------------------------: |
|         0         |   `[ _ , _ , _ , _ , _ ]`   |
|         1         |   `[ _ , _ , _ , _ , _ ]`   |
|         2         |   `[ _ , _ , _ , _ , _ ]`   |
|         3         |   `[ _ , _ , _ , _ , _ ]`   |
|         4         |   `[ _ , _ , _ , _ , _ ]`   |
|         5         | `[ 12 , 26 , 47 , _ , 40 ]` |
|         6         |  `[  _ , _ , _ , _ , _  ]`  |
