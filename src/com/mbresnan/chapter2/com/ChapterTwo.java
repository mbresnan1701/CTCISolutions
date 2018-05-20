package com.mbresnan.chapter2.com;

import com.mbresnan.library.com.LinkedListNode;

import java.util.HashMap;
import java.util.HashSet;

public class ChapterTwo {
  // 2.1 Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temp
  // buffer is not allowed?
  public static LinkedListNode removeDupes(LinkedListNode list) {
    // First part -  remove dupes, temp buffer allowed
//    LinkedListNode currentNode = list;
//    HashSet<Integer> seenValues = new HashSet<>();
//    while (currentNode != null) {
//      if (seenValues.contains(currentNode.data) && currentNode.prev != null) {
//        currentNode.prev.setNext(currentNode.next);
//      } else {
//        seenValues.add(currentNode.data);
//      }
//      currentNode = currentNode.next;
//    }
//    return list;

    // Second part - remove dupes, no temp buffer allowed
    // This will be more inefficient, due to no buffer, meaning we'll have to traverse the list
    // many times. For each element, traverse list from next element and remove dupes.
    // This approach would be O(n^2) time, but potentially saves some space depending on list size
    LinkedListNode currentNode = list;

    while (currentNode != null) {
      if (currentNode.next != null) {
        iterateAndRemove(currentNode.next, currentNode.data);
      }
      currentNode = currentNode.next;
    }
    return list;
  }

  // Helper function for 2.1b
  public static void iterateAndRemove(LinkedListNode node, int targetData) {
    LinkedListNode currentNode = node;
    while (currentNode != null) {
      if (currentNode.data == targetData) {
        currentNode.prev.setNext(currentNode.next);
      }
      currentNode = currentNode.next;
    }
  }

  // 2.2 Implement an algo to find the kth to last element of a singly linked list
  public static LinkedListNode kthToLast(LinkedListNode head, int k) {
    // Iterate through the list once to get the size of the list, once we have the size,
    // we can calculate the "index" of the kth to last element and return that
//    int size = listSize(head);
//    int targetIndex = (size - 1) - k;
//    int currentIndex = 0;
//    LinkedListNode currentNode = head;
//
//    // If k is greater than the list size, we go "out of bounds" so to speak. Just return the head.
//    if(targetIndex <= 0) {
//      return head;
//    }
//
//    while (currentNode != null) {
//      if (currentIndex == targetIndex) {
//        break;
//      }
//      currentIndex++;
//      currentNode = currentNode.next;
//    }
//    return currentNode;
    // Time complexity O(n)
    // On reflection, it would be more efficient to iterate only once, but store two pointers and move them both
    // Move a pointer k nodes ahead, then iterate from there and when we hit the end, we have our result

    LinkedListNode p0 = head;
    LinkedListNode p1 = head;

    // Set up our second pointer with incremented position
    for(int i = 0; i < k; i++) {
      if(p1.next == null) {
        // OOB
        return head;
      }
      p1 = p1.next;
    }

    while (p1.next != null) {
      p0 = p0.next;
      p1 = p1.next;
    }

    return p0;

    // This is still O(n) simplified, but more efficient than our last algo.
  }

  // Helper func for 2.2, though it'll probably have more uses
  public static Integer listSize(LinkedListNode head) {
    LinkedListNode currentNode = head;
    int count = 0;
    while (currentNode != null) {
      count++;
      currentNode = currentNode.next;
    }
    return count;
  }

  // 2.3 Implement and algo to delete a node in the middle (ie any node but first and last but
  // not necessarily the exact middle) of a SLL given only access to that node
  public static void deleteMiddleNode(LinkedListNode node) {
    // Since we cannot update the ref to this node in the prev node, we cannot "delete" it.
    // We'll have to update the node in place and then delete the next node
    if (node == null || node.next == null) {
      // Someone input the last element in a list or node is null
      return;
    }

    node.data = node.next.data;
    node.next = node.next.next;
    // Time complexity O(1).
  }

  // 2.4 Write code to partition a LL around a value x, such that all nodes less than x come before all nodes GTE
  // to x. If x is contained within the list, the values of x only need to be after the elements less than x. The
  // partition element x can appear anywhere in the "right partition"; it does not need to appear between the left
  // and right partitions
  public static LinkedListNode partition(LinkedListNode head, int x) {
    // First thought is to construct two new linked lists and join them at the end. Takes up N space, but is far
    // simpler than trying to rearrange the list in place.
    LinkedListNode currentNode = head;
    LinkedListNode leftPartition = null;
    LinkedListNode rightPartition = null;
    while (currentNode != null) {
      if(currentNode.data < x) {
        if(leftPartition == null) {
          leftPartition = new LinkedListNode(currentNode.data);
        } else {
          leftPartition.append(currentNode.data);
        }
      } else {
        if(rightPartition == null) {
          rightPartition = new LinkedListNode(currentNode.data);
        } else {
          rightPartition.append(currentNode.data);
        }
      }
      currentNode = currentNode.next;
    }

    if (leftPartition == null) {
      // All values GTE x
      return rightPartition;
    } else if (rightPartition == null) {
      // All values LT x
      return leftPartition;
    } else {
      // Else merge the partitions
      leftPartition.appendNode(rightPartition);
      return leftPartition;
    }
  }
}
