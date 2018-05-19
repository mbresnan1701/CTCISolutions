import com.mbresnan.chapter1.com.ChapterOne;
import com.mbresnan.chapter2.com.ChapterTwo;
import com.mbresnan.library.com.LinkedListNode;

public class Main {

  public static void main(String[] args) {
    ChapterOne cp1 = new ChapterOne();
    ChapterTwo cp2 = new ChapterTwo();

    LinkedListNode a = new LinkedListNode(1);
    LinkedListNode b = new LinkedListNode(1);
    LinkedListNode c = new LinkedListNode(2);
    LinkedListNode d = new LinkedListNode(3);
    LinkedListNode e = new LinkedListNode(7);
    LinkedListNode f = new LinkedListNode(1);
    LinkedListNode g = new LinkedListNode(2);
    LinkedListNode h = new LinkedListNode(6);

    a.setNext(b);
    b.setNext(c);
    c.setNext(d);
    d.setNext(e);
    e.setNext(f);
    f.setNext(g);
    g.setNext(h);

    LinkedListNode result = cp2.kthToLast(a, 0);
//    LinkedListNode currNode = a;
//
//    while (currNode != null) {
//      System.out.println(currNode.data);
//      currNode = currNode.next;
//    }
    System.out.println(result.data);
  }
}