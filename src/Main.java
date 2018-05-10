import com.mbresnan.chapter1.com.ChapterOne;

public class Main {

  public static void main(String[] args) {
    ChapterOne cp1 = new ChapterOne();
    int[][] a = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
    int[][] b = cp1.rotateMatrix(a);

    for (int i = 0; i < b.length; i++) {
      for (int j = 0; j < b[i].length; j++) {
        System.out.print(b[i][j] + " ");
      }
      System.out.println();
    }
  }
}