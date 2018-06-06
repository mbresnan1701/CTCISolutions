import com.mbresnan.chapter1.com.ChapterOne;
import com.mbresnan.chapter2.com.ChapterTwo;
import com.mbresnan.library.com.LinkedListNode;

public class Main {

  public static void main(String[] args) {
    ChapterOne cp1 = new ChapterOne();
    ChapterTwo cp2 = new ChapterTwo();

    LinkedListNode a = new LinkedListNode(6);
    LinkedListNode b = new LinkedListNode(9);
    LinkedListNode c = new LinkedListNode(6);
    LinkedListNode d = new LinkedListNode(6);
    LinkedListNode e = new LinkedListNode(7);
    LinkedListNode f = new LinkedListNode(1);
    LinkedListNode g = new LinkedListNode(2);
    LinkedListNode h = new LinkedListNode(6);

    a.setNext(b);
    b.setNext(d);

//    c.setNext(d);
    d.setNext(e);
    e.setNext(f);
//    f.setNext(g);
//    g.setNext(h);

//    LinkedListNode currNode = cp2.sumLists(a,c);
    System.out.println(cp2.intersection(a, d));
//    while (currNode != null) {
//      System.out.println("D is : " + currNode.data);
//      currNode = currNode.next;
//    }
  }
}