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
    int size = listSize(head);
    int targetIndex = (size - 1) - k;
    int currentIndex = 0;
    LinkedListNode currentNode = head;

    // If k is greater than the list size, we go "out of bounds" so to speak. Just return the head.
    if(targetIndex <= 0) {
      return head;
    }

    while (currentNode != null) {
      if (currentIndex == targetIndex) {
        break;
      }
      currentIndex++;
      currentNode = currentNode.next;
    }
    return currentNode;
    // Time complexity O(n)
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
}
