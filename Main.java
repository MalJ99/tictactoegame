import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

class Main {
  //creating the array lists that collect all positions played to check if play made is legal or check for win
  static ArrayList<Integer> humPositions = new ArrayList<Integer>();
  static ArrayList<Integer> comPositions = new ArrayList<Integer>();

  public static void main(String[] args) {
    //the board
    char[][] board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', ' ', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
        { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
    printBoard(board);
  //asks user for their move
    while (true) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Which grid number? (1-9):");
      int humPosition = scan.nextInt();
      //checks if move is legal
      while (humPositions.contains(humPosition) || comPositions.contains(humPosition)) {
        System.out.println("This play is not avaible please enter a different one");
        humPosition = scan.nextInt();
      }
      //makes the play
      play(board, humPosition, "human");
      String winResult = checkWin();
      if (winResult.length() > 0) {
        System.out.println(winResult);
        break;
      }
    //computer decides move
      Random rand = new Random();

      int comPosition = rand.nextInt(9) + 1;
      //checks if move is legal
      while (humPositions.contains(comPosition) || comPositions.contains(comPosition)) {
        comPosition = scan.nextInt();
      }
      //makes move
      play(board, comPosition, "computer");

      printBoard(board);
      //checks for a win
      winResult = checkWin();
      if (winResult.length() > 0) {
        System.out.println(winResult);
        break;
      }

    }
  }
//printing the board method
  public static void printBoard(char[][] board) {
    for (char[] row : board) {
      for (char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }
//making move
  public static void play(char[][] board, int position, String player) {
    //sets the symbols
    char symbol = ' ';
    if (player.equals("human")) {
      symbol = 'X';
      humPositions.add(position);
    } else if (player.equals("computer")) {
      symbol = 'O';
      comPositions.add(position);
    }
    //uses the board indexes to change the grid text
    switch (position) {
    case 1:
      board[0][0] = symbol;
      break;
    case 2:
      board[0][2] = symbol;
      break;
    case 3:
      board[0][4] = symbol;
      break;
    case 4:
      board[2][0] = symbol;
      break;
    case 5:
      board[2][2] = symbol;
      break;
    case 6:
      board[2][4] = symbol;
      break;
    case 7:
      board[4][0] = symbol;
      break;
    case 8:
      board[4][2] = symbol;
      break;
    case 9:
      board[4][4] = symbol;
      break;
    default:
      break;
    }
  }
//possible win conditions
  public static String checkWin() {
    List topRow = Arrays.asList(1, 2, 3);
    List midRow = Arrays.asList(4, 5, 6);
    List botRow = Arrays.asList(7, 8, 9);
    List leftCol = Arrays.asList(1, 4, 7);
    List midCol = Arrays.asList(2, 5, 8);
    List rightCol = Arrays.asList(3, 6, 9);
    List diag1 = Arrays.asList(1, 5, 9);
    List diag2 = Arrays.asList(7, 5, 3);
    //comparing for a win
    List<List> winningCon = new ArrayList<List>();
    winningCon.add(topRow);
    winningCon.add(midRow);
    winningCon.add(botRow);
    winningCon.add(leftCol);
    winningCon.add(midCol);
    winningCon.add(rightCol);
    winningCon.add(diag1);
    winningCon.add(diag2);
  //win results
    for (List l : winningCon) {
      if (humPositions.containsAll(l)) {
        return "You Have Won!";
      } else if (comPositions.contains(l)) {
        return "Computer has won! Try again next time!";
      } else if (humPositions.size() + comPositions.size() == 9) {
        return "It's a tie!";
      }
    }
    return "";
  }
}