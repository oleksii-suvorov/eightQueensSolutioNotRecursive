
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BoardTest {

  public static void main(String[] args) {
    StringBuilder boardStartPosition = new StringBuilder();
    StringBuilder boardEndPosition = new StringBuilder();
    List<String> redanduntNums = new ArrayList<>();
    Integer maxNum = Integer.parseInt(args[0]);
    if(args[0] == null || args[0].equals("")) {
      System.out.println("Please insert board size square arg. For example type: \n<java BoardTest 8> ");
      return;
    }
    try {
      for (int i = 0; i < maxNum; i++) {
        boardStartPosition.append("1");
        boardEndPosition.append(args[0]);
      }
      redanduntNums.add("0");
      for (int i = maxNum + 1; i < 10; i++) {
        redanduntNums.add(Integer.toString(i));
      }

    } catch (NumberFormatException e) {
      System.out.println("Please insert board size square arg. For example type: \n<java BoardTest 8> ");
      return;
    }
    checkQueens(Integer.parseInt(boardStartPosition.toString()), Integer.parseInt(boardEndPosition.toString()), redanduntNums);
  }


  private static void checkQueens(int board, int lastNum, List<String> redanduntNums) {
    int quantityVariants = 0;
    while (board < (lastNum + 1)) {
      quantityVariants += check(board, quantityVariants, redanduntNums);
      board++;
    }
    System.out.println("There are " + quantityVariants + " variants found.");
  }

  private static int check(int board, int quantityVariants, List<String> redanduntNums) {
    List<String> positionsOnBoard = List.of(Long.toString(board).split("(?!^)"));
    List<String> tempForIntersection = new ArrayList<>(redanduntNums);
    if (new HashSet<>(positionsOnBoard).size() < positionsOnBoard.size()) {
      return 0;
    }
    tempForIntersection.retainAll(positionsOnBoard);
    if (!tempForIntersection.isEmpty()) {
      return 0;
    }
    if (checkDiagonal(positionsOnBoard)) {
      final char[] arrChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
      System.out.print(++quantityVariants + ": ");
      for (int i = 0; i < positionsOnBoard.size(); i++) {
        System.out.print(arrChars[i] + positionsOnBoard.get(i) + " ");
      }
      System.out.println();
      return 1;
    }
    return 0;
  }

  public static boolean checkDiagonal(List<String> board) {
    int current;
    int difference = 1;
    for (int i = 0; i < board.size() - 1; i++) {
      current = Integer.parseInt(board.get(i));
      for (int j = i + 1; j < board.size(); j++) {
        if(Math.abs(current - Integer.parseInt(board.get(j))) == difference) {
          return false;
        }
        difference++;
      }
      difference = 1;
    }
    return true;
  }

}
