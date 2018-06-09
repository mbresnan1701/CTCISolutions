package com.mbresnan.library;

public class LinkedListNode {
  public LinkedListNode next, prev, last;
  public int data;

  public LinkedListNode() {
  }

  public LinkedListNode(int d) {
    data = d;
  }

  public LinkedListNode(int d, LinkedListNode n, LinkedListNode p) {
    data = d;
    setNext(n);
    setPrevious(p);
  }

  public void setNext(LinkedListNode n) {
    next = n;
    if (this == last) {
      last = n;
    }
    if (n != null && n.prev != this) {
      n.setPrevious(this);
    }
  }

  public void setPrevious(LinkedListNode p) {
    prev = p;
    if (p != null && p.next != this) {
      p.setNext(this);
    }
  }

  public LinkedListNode clone() {
    LinkedListNode next2 = null;
    if (next != null) {
      next2 = next.clone();
    }
    LinkedListNode head2 = new LinkedListNode(data, next2, null);
    return head2;
  }

  public boolean append(int x) {
    return append(this, x);
  }

  public boolean append(LinkedListNode node, int x) {
    if(node.next == null) {
      node.setNext(new LinkedListNode(x));
      return true;
    } else {
      return node.next.append(node.next, x);
    }
  }

  public boolean appendNode(LinkedListNode newNode) {
    return appendNode(this, newNode);
  }

  public boolean appendNode(LinkedListNode node, LinkedListNode newNode) {
    if(node.next == null) {
      node.setNext(newNode);
      return true;
    } else {
      return node.next.appendNode(node.next, newNode);
    }
  }

  public int size() {
    return size(this, 1);
  }

  public int size(LinkedListNode node, int x) {
    if (node.next == null) {
      return x;
    } else {
      return size(node.next, x + 1);
    }
  }
}
