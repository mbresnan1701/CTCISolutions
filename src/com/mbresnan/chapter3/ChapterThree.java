package com.mbresnan.chapter3;

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

    public stack3_2 () {
      super();
      minimums = new Stack<>();
    }

    public void pushValue(Integer x) {
      super.push(x);
      if(x <= min()) {
        minimums.push(x);
      }
    }

    public Integer popValue() {
      int stackTop = super.pop();
      if(stackTop == min()) {
        minimums.pop();
      }
      return stackTop;
    }

    public int min() {
      if(minimums.isEmpty()) {
        return Integer.MAX_VALUE;
      }
      
      return minimums.peek();
    }
  }
}
