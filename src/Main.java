import com.mbresnan.chapter1.com.ChapterOne;

public class Main {

  public static void main(String[] args) {
    ChapterOne cp1 = new ChapterOne();
    // pale, ple = true | pales, pale = true | pale, bale = true | pale, bake = false
    System.out.println(cp1.isOneAway("pale", "ple"));
    System.out.println(cp1.isOneAway("pales", "pale"));
    System.out.println(cp1.isOneAway("pale", "bale"));
    System.out.println(cp1.isOneAway("pale", "bake"));
  }
}