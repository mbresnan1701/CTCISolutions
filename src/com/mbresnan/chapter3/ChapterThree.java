package com.mbresnan.chapter3;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
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

    // 3.5 Write a program to sort a stack such that the smallest items are on top. You can use an
    // additional temporary stack, but you may not copy the elements into any other data structure. The stack
    // supports the following ops: push, pop, peek, isEmpty
    class SortableStack {
        private Stack<Integer> stack;

        SortableStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            Stack<Integer> tempStack = new Stack<>();
            if (stack.isEmpty() || x <= stack.peek()) {
                stack.push(x);
            } else {
                while (!stack.isEmpty() && stack.peek() < x) {
                    tempStack.push(stack.pop());
                }
                stack.push(x);
                while (!tempStack.isEmpty()) {
                    stack.push(tempStack.pop());
                }
            }
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    // 3.6 An animal shelter, which holds only dogs and cats, operates on a strictly FIFO basis. People must adopt
    // the "oldest"(arrival time) of all animals at the shelter, or they can select whether they prefer dogs or cats
    // and will receive the oldest animal of that type. Create the data structures to maintain the system, and implement
    // operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built in LinkedList data struct
    class Animal {
        // We could make separate classes for dogs and cats, but in this case it doesn't much matter.
        // We only care about two values which are both shared between cats and dogs.
        private int timeAdded;
        private String type;

        Animal(String type, int time) {
            this.type = type;
            this.timeAdded = time;
        }

        public int getTimeAdded() {
            return timeAdded;
        }

        public String getType() {
            return type;
        }
    }

    class AnimalShelter {
        private LinkedList<Animal> catQueue;
        private LinkedList<Animal> dogQueue;
        private Animal oldestAnimal = null; // TODO: refactor this to string. No need to store whole object
        private int animalsAdded = 0; // For determining our "time". Oldest animals are lower numbers.

        AnimalShelter() {
            this.catQueue = new LinkedList<>();
            this.dogQueue = new LinkedList<>();
        }

        public void enqueue(String type) {
            Animal newAnimal = new Animal(type, animalsAdded++);

            if (oldestAnimal == null) {
                oldestAnimal = newAnimal;
            }

            if (type.equals("dog")) {
                dogQueue.push(newAnimal);
            } else if (type.equals("cat")) {
                catQueue.push(newAnimal);
            }
        }

        public Animal dequeueType(String type) {
            Animal removedAnimal = null;
            if (type.equals("dog")) {
                if (dogQueue.size() > 0) {
                    removedAnimal = dogQueue.removeLast();
                }
            } else if (type.equals("cat")) {
                if (catQueue.size() > 0) {
                    removedAnimal = catQueue.removeLast();
                }
            }

            this.calculateOldest();
            return removedAnimal;
        }

        public Animal dequeueAny() {
            if (oldestAnimal == null) {
                return null;
            } else {
                Animal removedAnimal = null;
                if (oldestAnimal.getType().equals("dog")) {
                    removedAnimal = dogQueue.removeLast();
                } else if (oldestAnimal.getType().equals("cat")) {
                    removedAnimal = catQueue.removeLast();
                }
                this.calculateOldest();
                return removedAnimal;
            }
        }

        private void calculateOldest() {
            if (catQueue.size() == 0 && dogQueue.size() == 0) {
                oldestAnimal = null;
            } else if (catQueue.size() > 0 && dogQueue.size() == 0) {
                oldestAnimal = catQueue.peekLast();
            } else if (catQueue.size() == 0) {
                oldestAnimal = dogQueue.peekLast();
            } else {
                Animal oldestCat = catQueue.peekLast();
                Animal oldestDog = dogQueue.peekLast();
                oldestAnimal = oldestCat.getTimeAdded() < oldestDog.getTimeAdded() ?
                        oldestCat : oldestDog;
            }
        }
    }


}
