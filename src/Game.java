public class Game {
    public static final int BOARD_SIZE = 10;

    public static void main(String[] args) {
        Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
        clearBoard(board);
        tetrominoPattern(board, 5, 5);
        printBoard(board);
    }

    public static void clearBoard(Cell[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = new Cell();
                board[row][column].setAlive(false);
            }
        }
    }

    public static void printBoard(Cell[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (!board[row][column].isAlive())
                    System.out.print("[ ]");
                if (board[row][column].isAlive())
                    System.out.print("[O]");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void tetrominoPattern(Cell[][] board, int row, int column) {
        if (row >= 0 && row + 1 < board.length && column - 2 > 0 && column < board[row].length) {
            board[row][column].setAlive(true);
            board[row][column - 1].setAlive(true);
            board[row][column - 2].setAlive(true);
            board[row + 1][column - 2].setAlive(true);
        } else
            System.out.println("Selected cell is outside of the board.");
    }

    public static void update(Cell[][] board){
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column].update(board);
            }
        }
    }
}
