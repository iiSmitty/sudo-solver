/*
code credit to Coding with John
subscribe to his channel for more:
https://bit.ly/3rrGzD4
 */

public class SolveMySudo {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        // get your layout here
        // https://www.sudokuweb.org/
        // 9 x 9 layout


        int[][] boardLayout = {
                {6, 1, 2, 8, 0, 5, 0, 9, 0},        // 1st row
                {8, 4, 3, 0, 2, 0, 0, 0, 6},        // 2nd row
                {7, 0, 9, 4, 6, 0, 0, 0, 0},        // 3rd row
                {0, 0, 4, 2, 0, 0, 3, 0, 9},        // 4th row
                {0, 6, 0, 0, 0, 0, 0, 2, 0},        // 5th row
                {2, 0, 0, 0, 9, 0, 0, 9, 0},        // 6th row
                {0, 9, 6, 3, 0, 0, 0, 0, 5},        // 7th row
                {4, 0, 0, 1, 8, 0, 0, 7, 3},        // 8th row
                {3, 0, 0, 6, 5, 0, 9, 1, 0},        // 9th row
        };

        System.out.println("::BOARD BEFORE IT IS SOLVED::\n");
        printBoardLayout(boardLayout);

        if (activateHacks(boardLayout)) {
            System.out.println("\n::BOARD SOLVED SUCCESSFULLY!::\n");
        } else {
            System.out.println("::UNSOLVABLE BOARD::");
            System.out.println("Please double check if your board layout is valid");
        }

        printBoardLayout(boardLayout);
    }

    private static void printBoardLayout(int[][] sudoBoard) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(sudoBoard[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumInRow(int[][] sudoBoard, int num, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (sudoBoard[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumInColumn(int[][] sudoBoard, int num, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (sudoBoard[i][column] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumInBox(int[][] sudoBoard, int num, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (sudoBoard[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] sudoBoard, int num, int row, int column) {
        return !isNumInRow(sudoBoard, num, row) &&
                !isNumInColumn(sudoBoard, num, column) &&
                !isNumInBox(sudoBoard, num, row, column);
    }

    private static boolean activateHacks(int[][] sudoBoard) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (sudoBoard[row][column] == 0) {
                    // checking for a validate number ranging from 1 - 9
                    for (int validateNum = 1; validateNum <= GRID_SIZE; validateNum++) {
                        if (isValidPlacement(sudoBoard, validateNum, row, column)) {
                            sudoBoard[row][column] = validateNum;
                            if (activateHacks(sudoBoard)) {
                                return true;
                                // rollback (traverse) to zero if a validate number cannot be found
                                // the process starts again continuing from previous value
                            } else {
                                sudoBoard[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
