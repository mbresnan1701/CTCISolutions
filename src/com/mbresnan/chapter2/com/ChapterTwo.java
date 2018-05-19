package com.mbresnan.chapter2.com;

import com.mbresnan.library.com.LinkedListNode;

import java.util.HashMap;
import java.util.HashSet;

public class ChapterTwo {
  // 2.1 Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temp
  // buffer is not allowed?
  public LinkedListNode removeDupes(LinkedListNode list) {
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
  public void iterateAndRemove(LinkedListNode node, int targetData) {
    LinkedListNode currentNode = node;
    while (currentNode != null) {
      System.out.println("CURR NODE IS " + currentNode.data);
      if (currentNode.data == targetData) {
        currentNode.prev.setNext(currentNode.next);
      }
      currentNode = currentNode.next;
    }
  }
  
}
