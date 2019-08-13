import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Game
 */
public class Game {
    int[][] board;
    int player;
    int turn;
    int row, col;
    Scanner scanner;

    public Game() {
        player = 1;
        turn = 0;
        row = 10;
        col = 12;
        board = generateBoard(row, col);
        scanner = new Scanner(System.in);
    }

    public void turn() {
        int[] roll = DiceRoll();
        displayBoard();
        System.out.println("Turn " + this.turn + " - Player " + player + " - Dice: (" + roll[0] + ", " + roll[1] + ")");
        System.out.println();
        InputCoordinates(roll);

        nextTurn();
    }

    /**
     * Logic from Jeremie
     * 
     * @param row
     * @param col
     * @return
     */
    public int[][] generateBoard(int row, int col) {
        row += 2;
        col += 2;

        int arr[][] = new int[row][col];

        for (int i = 0; i < row; i++) { // Can be optimised
            for (int j = 0; j < col; j++) {
                arr[i][j] = 0;
                arr[0][j] = 7;
                arr[row - 1][j] = 7;
                arr[i][0] = 7;
                arr[i][col - 1] = 7;
            }
        }

        arr[0][1] = 1;
        arr[row - 1][col - 2] = 2;

        return arr;
    }

    public void displayBoard() {
        System.out.println();
        System.out.print("   ");
        for (int j = 1; j <= col; j++) {
            System.out.print(String.format("%02d", j) + " ");
        }
        System.out.println();
        System.out.println();
        for (int i = 1; i <= row; i++) {
            // for (int i = 0; i < row + 2; i++) {
            System.out.print(String.format("%02d", i) + "  ");
            for (int j = 1; j <= col; j++) {
                // for (int j = 0; j < col + 2; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void nextTurn() {
        this.turn++;
        this.player = this.player == 1 ? 2 : 1;
    }

    public void displayTurn() {
        System.out.println(player);
    }

    /**
     * Original Logic taken from jeremie
     * 
     * @return Array of two integers representing the dice roll
     */
    public int[] DiceRoll() {
        Random dices = new Random();
        int dice1 = dices.nextInt(5) + 1;
        int dice2 = dices.nextInt(5) + 1;
        int roll[] = { dice1, dice2 };

        return roll;
    }

    public void InputCoordinates(int[] diceThrow) {
        ArrayList<int[]> startPoints = GenerateStartingPoints();
        ArrayList<ArrayList<int[]>> endPoints = new ArrayList<ArrayList<int[]>>();

        int[] sp, ep;
        int spIndex;
        // Get only starting points which have endpoints
        for (int i = 0; i < startPoints.size(); i++) {
            int[] startPoint = startPoints.get(i);
            ArrayList<int[]> thisEndPoints = EndPoint(diceThrow, startPoint);

            if (thisEndPoints.size() > 0) {
                endPoints.add(thisEndPoints);
            } else {
                startPoints.remove(i);
            }
        }

        // Get start point
        spIndex = inputStartingPoints(startPoints);
        sp = startPoints.get(spIndex);
        System.out.println();
        ep = inputEndingPoints(endPoints.get(spIndex));
        fillBoard(sp, ep);
    }

    /**
     * Logic from Yoveer
     * 
     * @return
     */
    public ArrayList<int[]> GenerateStartingPoints() {

        ArrayList<int[]> StartPoints = new ArrayList<int[]>();
        int[] sp;

        if (turn == 0) {
            sp = new int[] { 1, 1 };
            StartPoints.add(sp);
        } else if (turn == 1) {
            sp = new int[] { col, row };
            StartPoints.add(sp);
        } else {
            for (int i = 1; i < board.length; i++) {// passing through the rows
                for (int j = 1; j < board[1].length; j++) {// passing through columns
                    if (board[i][j] == player) {
                        if (board[i][j + 1] == 0) {
                            sp = new int[] { i, j + 1 };
                            StartPoints.add(sp);
                        }
                        if (board[i][j - 1] == 0) {
                            sp = new int[] { i, j - 1 };
                            StartPoints.add(sp);
                        }
                        if (board[i + 1][j] == 0) {
                            sp = new int[] { i + 1, j };
                            StartPoints.add(sp);
                        }
                        if (board[i - 1][j] == 0) {
                            sp = new int[] { i - 1, j };
                            StartPoints.add(sp);
                        }
                    }
                }
            }

        }
        return StartPoints;
    }

    public int inputStartingPoints(ArrayList<int[]> startingPoints) {
        int index;
        for (int i = 0; i < startingPoints.size(); i++) {
            int[] coord = startingPoints.get(i);
            System.out.println(i + ": (" + coord[0] + ", " + coord[1] + ")");
        }

        System.out.print("Starting Point: ");
        index = scanner.nextInt(); // TODO: Add error check
        return index;
        // return 0;
    }

    public int[] inputEndingPoints(ArrayList<int[]> endingPoints) {
        int index;
        for (int i = 0; i < endingPoints.size(); i++) {
            int[] coord = endingPoints.get(i);
            System.out.println(i + ": (" + coord[0] + ", " + coord[1] + ")");
        }

        System.out.print("Ending Point: ");
        index = scanner.nextInt(); // TODO: Add error check
        return endingPoints.get(index);
    }

    public void fillBoard(int[] startingPoint, int[] endingPoint) {
        try {
            for (int i = startingPoint[0]; i <= endingPoint[0]; i++) {
                for (int j = startingPoint[1]; j <= endingPoint[1]; j++) {
                    board[j][i] = player;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            for (int i = startingPoint[0]; i <= endingPoint[0]; i++) {
                for (int j = startingPoint[1]; j >= endingPoint[1]; j--) {
                    board[j][i] = player;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            for (int i = startingPoint[0]; i >= endingPoint[0]; i--) {
                for (int j = startingPoint[1]; j <= endingPoint[1]; j++) {
                    board[j][i] = player;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            for (int i = startingPoint[0]; i >= endingPoint[0]; i--) {
                for (int j = startingPoint[1]; j >= endingPoint[1]; j--) {
                    board[j][i] = player;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public ArrayList<int[]> ConcatEndPoints(ArrayList<int[]> EndPoints, int row, int column, int[] point) {
        // row and column are for the possible endpoints to be checked
        Boolean check = true;
        try {
            int rowstart, colstart, rowend, colend;
            if (row > point[0]) {
                rowstart = point[0];
                rowend = row + 1;
            } else {
                rowstart = row - 1;
                rowend = point[0];
            }

            if (column > point[1]) {
                colstart = point[1];
                colend = column + 1;
            } else {
                colstart = column - 1;
                colend = point[1];
            }
            if (board[row][column] == 0) {
                for (int i = rowstart; i < rowend - 1; i++) {
                    for (int j = colstart; j < colend - 1; j++) {
                        if (board[i][j] != 0) {
                            check = false;
                        }
                    }
                }
                if (check == true) {
                    int[] EndP = { row, column };
                    EndPoints.add(EndP);
                }
            }
        } catch (Exception e) {
            // do nothing
        }

        return EndPoints;
    }

    public ArrayList<int[]> EndPoint(int[] dice, int[] point) {
        ArrayList<int[]> EP = new ArrayList<int[]>();
        dice[0] -= 1;
        dice[1] -= 1;
        int OoB = 0, row, column;

        for (int j = 0; j < 2; j++) {
            try {
                row = dice[0] + point[0];
                column = dice[1] + point[1];
                ConcatEndPoints(EP, row, column, point);

                row = point[0] - dice[0];
                column = point[1] - dice[1];
                ConcatEndPoints(EP, row, column, point);

                row = point[0] + dice[0];
                column = point[1] - dice[1];
                ConcatEndPoints(EP, row, column, point);

                row = point[0] - dice[0];
                column = point[1] + dice[1];
                ConcatEndPoints(EP, row, column, point);
            } catch (Exception e) {
                OoB++;
            }

            // swapping dice values
            int temp;
            temp = dice[0];
            dice[0] = dice[1];
            dice[1] = temp;
        }
        // TODO: Filter duplicates
        return EP;
    }

}