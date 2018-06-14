package com.mbresnan.chapter3;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class ChapterThree {

    // 3.1 Describe how you would use a single array to implement three stacks
//  In order to do this, we would need to partition the array into three chunks.
//  The chunks would be 1. 0 to n/3 exclusive 2. n/3 - 2n/3 exclusive 3. 2n/3 - n exclusive
//  This structure is limited by its ability to only fit a certain number of values within
//  the partitions, then we would need to expand all partitions such that our previously defined
//  partition start/stop points would still be valid. That is, if we need for example 1 more value in partition 1,
//  we need to increase the size of all partitions by 1.

    // 3.2 How would you design a stack which in addition to push and pop, has a function 'min' which returns the
    // minimum element? Push, pop, and min should all operate in O(1) time.
    public class stack3_2 extends Stack<Integer> {
        // This question depends on whether or not we want to create a second stack which stores the min stack or not.
        // We can either a) create a stack of objects which store the min value in additon to their data, or else b)
        // store the mins in a seperate stack. I'll do b because it seems like it might potentially be more space efficient

        private Stack<Integer> minimums;

        public stack3_2() {
            super();
            minimums = new Stack<>();
        }

        public void pushValue(Integer x) {
            super.push(x);
            if (x <= min()) {
                minimums.push(x);
            }
        }

        public Integer popValue() {
            int stackTop = super.pop();
            if (stackTop == min()) {
                minimums.pop();
            }
            return stackTop;
        }

        public int min() {
            if (minimums.isEmpty()) {
                return Integer.MAX_VALUE;
            }

            return minimums.peek();
        }
    }

    // 3.3 Imagine a stack of plates. If the stack gets too high, it might topple. Therefore, IRL, we would likely
    // start a new stack when the previous stack exceeds some threshold. Implement a data structure, SetOfStacks, that
    // mimics this. SetOfStacks should be composed of several stacks, and create a new stack once the previous one
    // exceeds capacity. Push and Pop should behave identically to a single stack.
    class SetOfStacks {
        private int capacity;
        private ArrayList<Stack> stacks = new ArrayList<>();

        public SetOfStacks() {
            this(10);
        }

        public SetOfStacks(Integer max) {
            this.capacity = max;
            stacks.add(new Stack<Integer>());
        }

        public void push(Integer x) {
            Stack<Integer> currentStack = stacks.get(stacks.size() - 1);
            if (currentStack.size() == capacity) {
                stacks.add(new Stack<Integer>());
                currentStack = stacks.get(stacks.size() - 1);
            }

            currentStack.push(x);
        }

        public int pop() {
            Stack<Integer> currentStack = stacks.get(stacks.size() - 1);

            // No values in any stack. Just return a junk value
            if (currentStack.size() == 0 && stacks.size() == 1) {
                throw new EmptyStackException();
            }

            if (currentStack.size() == 0 && stacks.size() > 1) {
                stacks.remove(stacks.size() - 1);
                currentStack = stacks.get(stacks.size() - 1);
            }

            return currentStack.pop();
        }

        public int stackCount() {
            return this.stacks.size();
        }

    }

    // 3.4 Implement a MyQueue class which implements a queue using two stacks
    class MyQueue<T> {
        private Stack<T> stack0, stack1;

        MyQueue() {
            // Stack 0 is the newest values, stack 1 is the oldest.
            stack0 = new Stack<>();
            stack1 = new Stack<>();
        }

        public void add(T x) {
            stack0.push(x);
        }

        public T remove() {
            transferStacks();
            return stack1.pop();
        }

        public T pop() {
            transferStacks();
            return stack1.pop();
        }

        public T peek() {
            transferStacks();
            return stack1.peek();
        }

        public void transferStacks() {
            if (stack1.isEmpty()) {
                while (!stack0.isEmpty()) {
                    stack1.push(stack0.pop());
                }
            }
        }

        public int size() {
            return stack0.size() + stack1.size();
        }

    }
}
