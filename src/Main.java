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

    a.setNext(b);
    b.setNext(c);
    c.setNext(d);

    cp2.removeDupes(a);
    LinkedListNode currNode = a;

    while (currNode != null) {
      System.out.println(currNode.data);
      currNode = currNode.next;
    }
  }
}