public class Game {
    public static final int BOARD_SIZE = 10;

    public static void main(String[] args) {
        Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
        clearBoard(board);
        tetrominoPattern(board, 5, 5);

        while (true){
            printBoard(board);
            updateStatus(board);
            updateBoard(board);
            printBoard(board);
        }
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

    public static void updateStatus(Cell[][] board) {
        for (int row = 0; row < board.length - 1; row++) {
            for (int column = 0; column < board[row].length - 1; column++) {
                board[row][column].setAliveNeighbours(0); // Reset the amount of alive neighbours of the cell
                if (row - 1 >= 0) { // Don't look at cells in row - 1 < 0
                    if (column - 1 >= 0)
                        if (board[row - 1][column - 1].isAlive())
                            board[row][column].incrementNeighbours();
                    if (board[row - 1][column].isAlive())
                        board[row][column].incrementNeighbours();
                    if (column + 1 < board[row].length)
                        if (board[row - 1][column + 1].isAlive())
                            board[row][column].incrementNeighbours();
                }

                if (row + 1 < board.length) { // Don't look at cells in row + 1 > board size
                    if (column - 1 >= 0)
                        if (board[row + 1][column - 1].isAlive())
                            board[row][column].incrementNeighbours();
                    if (board[row + 1][column].isAlive())
                        board[row][column].incrementNeighbours();
                    if (column + 1 < board[row].length)
                        if (board[row + 1][column + 1].isAlive())
                            board[row][column].incrementNeighbours();
                }

                if (column - 1 >= 0)
                    if (board[row][column - 1].isAlive())
                        board[row][column].incrementNeighbours();
                if (column + 1 < board[row].length)
                    if (board[row][column + 1].isAlive())
                        board[row][column].incrementNeighbours();
            }
        }
    }

    public static void updateBoard(Cell[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column].updateCell();
            }
        }
    }
}

