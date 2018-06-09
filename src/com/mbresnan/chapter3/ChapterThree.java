package com.mbresnan.chapter3;

public class ChapterThree {

    // 3.1 Describe how you would use a single array to implement three stacks
//    In order to do this, we would need to partition the array into three chunks.
//    The chunks would be 1. 0 to n/3 exclusive 2. n/3 - 2n/3 exclusive 3. 2n/3 - n exclusive
//    This structure is limited by its ability to only fit a certain number of values within
//    the partitions, then we would need to expand all partitions such that our previously defined
//    partition start/stop points would still be valid. That is, if we need for example 1 more value in partition 1,
//    we need to increase the size of all partitions by 1.
}
