import com.mbresnan.chapter1.ChapterOne;
import com.mbresnan.chapter2.ChapterTwo;
import com.mbresnan.chapter3.ChapterThree;
import com.mbresnan.library.LinkedListNode;

import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        ChapterOne cp1 = new ChapterOne();
        ChapterTwo cp2 = new ChapterTwo();
        ChapterThree cp3 = new ChapterThree();

//    LinkedListNode a = new LinkedListNode(6);
//    LinkedListNode b = new LinkedListNode(9);
//    LinkedListNode c = new LinkedListNode(6);
//    LinkedListNode d = new LinkedListNode(6);
//    LinkedListNode e = new LinkedListNode(7);
//    LinkedListNode f = new LinkedListNode(1);
//    LinkedListNode g = new LinkedListNode(2);
//    LinkedListNode h = new LinkedListNode(6);
//
//    a.setNext(b);
//    b.setNext(c);
//
//    c.setNext(d);
//    d.setNext(b);
//    e.setNext(f);
//    f.setNext(g);
//    g.setNext(h);

//    LinkedListNode currNode = cp2.sumLists(a,c);
//    System.out.println(cp2.loopDetection(a).data);
//    while (currNode != null) {
//      System.out.println("D is : " + currNode.data);
//      currNode = currNode.next;
//    }

        System.out.println(cp1.numberOfDecodes("12345"));
        // 1 2 3 4 5
        // 12 3 4 5
        // 1 23 4 5


    }
}