public class Main {
    public static final int BOARD_SIZE = 10;

    public static void main(String[] args) {
        boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];

        clearBoard(board);
        tetrominoPattern(board, 5, 5);
        printBoard(board);
        update(board);
        printBoard(board);

    }

    public static void clearBoard(boolean[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = false;
            }
        }
    }

    public static void printBoard(boolean[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (!board[row][column])
                    System.out.print("[ ]");
                if (board[row][column])
                    System.out.print("[O]");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void tetrominoPattern(boolean[][] board, int row, int column) {
        if (row >= 0 && row + 1 < board.length && column - 2 > 0 && column < board[row].length) {
            board[row][column] = true;
            board[row][column - 1] = true;
            board[row][column - 2] = true;
            board[row + 1][column - 2] = true;
        } else
            System.out.println("Selected cell is outside of the board.");
    }

    public static void update(boolean[][] board) {
        for (int row = 0; row < board.length - 1; row++) {
            for (int column = 0; column < board[row].length - 1; column++) {
                int aliveNeighbours = 0;
                if (row - 1 >= 0) { // Don't look at cells in row - 1 < 0
                    if (column - 1 >= 0)
                        if (board[row - 1][column - 1])
                            aliveNeighbours++;
                    if (board[row - 1][column])
                        aliveNeighbours++;
                    if (column + 1 < board[row].length)
                        if (board[row - 1][column + 1])
                            aliveNeighbours++;
                }

                if (row + 1 < board.length) { // Don't look at cells in row + 1 > board size
                    if (column - 1 >= 0)
                        if (board[row + 1][column - 1])
                            aliveNeighbours++;
                    if (board[row + 1][column])
                        aliveNeighbours++;
                    if (column + 1 < board[row].length)
                        if (board[row + 1][column + 1])
                            aliveNeighbours++;
                }

                if (column - 1 >= 0)
                    if (board[row][column - 1])
                        aliveNeighbours++;
                if (column + 1 < board[row].length)
                    if (board[row][column + 1])
                        aliveNeighbours++;


                // Check if a live cell is killed
                if (board[row][column]) {
                    aliveNeighbours--;
                    if (aliveNeighbours < 2 && aliveNeighbours > 3)
                        board[row][column] = false;
                }

                // Check if a dead cell is revived
                if (!board[row][column]) {
                    if (aliveNeighbours == 3)
                        board[row][column] = true;
                }
            }
        }
    }
}
