package com.mbresnan.chapter2.com;

import com.mbresnan.library.com.LinkedListNode;

import java.util.HashMap;

public class ChapterTwo {
  // 2.1 Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temp
  // buffer is not allowed?
  public LinkedListNode removeDupes(LinkedListNode list) {
    // First part -  remove dupes, temp buffer allowed
    LinkedListNode currentNode = list;
    HashMap<Object, String> seenValues = new HashMap();
    while (currentNode != null) {
      System.out.println("CURR NODE IS " + currentNode.data);
      if (seenValues.containsKey(currentNode.data) && currentNode.prev != null) {
        currentNode.prev.setNext(currentNode.next);
      } else {
        seenValues.put(currentNode.data, "FOOBAR");
      }
      currentNode = currentNode.next;
    }
    return list;

    // Second part - remove dupes, no temp buffer allowed

  }
}
