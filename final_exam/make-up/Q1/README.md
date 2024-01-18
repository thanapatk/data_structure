# 1. Merge sort stack (11 points)

Write code for method `sort(Stack s)`, which uses merge sort. This method receives a stack as its parameter, then **sort** the stack so that the smallest value is on top and the largest value is on the bottom. You are **not allowed** to create other data structures apart from Stack

---

## **Answer**

This is similar to [Lab06](../../../LAB06_Stack) Question 2, which ask us to sort a stack, but this time the sort algorithm is set.

- So first, we copy the test cases from the lab, and we copy ajarn's Stack implementation from the course.
- Now we just implement merge sort as usual, but the thing we have to remember is that stack follows a Last-In-First-Out (LIFO) principle.
  - As for sorting in an ascending order (smallest to largest), the thing we have to change is putting the larger number in first, so it will be in the bottom.
    ```java
    while (!s.isEmpty() && !tmp.isEmpty()) {
    // putting the largest element in first
    if (s.top() > tmp.top()) {
    sorted.push(s.top());
    s.pop();
    } else {
    sorted.push(tmp.top());
    tmp.pop();
    }
    }
    ```
  - And before merging, we have to reverse the whole stack, so the largest will be at the top for the algorithm to continue to put the largest element in first.

The solution is available [here](UseStack.java).
