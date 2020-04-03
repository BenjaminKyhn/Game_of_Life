public class Game {
    private static final int BOARD_SIZE = 20;
    private boolean gameOver = false;

    public void clearBoard(Cell[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = new Cell(false);
            }
        }
    }

    public void printBoard(Cell[][] board) {
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

    public void copyBoard(Cell[][] board, Cell[][] boardCopy) {
        for (int i = 0; i < boardCopy.length; i++)
            for (int j = 0; j < boardCopy[i].length; j++)
                boardCopy[i][j] = new Cell(board[i][j].isAlive());
    }

    public void updateStatus(Cell[][] board) {
        for (int row = 0; row < board.length - 1; row++) {
            for (int column = 0; column < board[row].length - 1; column++) {
                board[row][column].setAliveNeighbours(0); // Reset the amount of living neighbours of the cell
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

    public void updateBoard(Cell[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column].updateCell();
            }
        }
    }

    public void checkGameOver(Cell[][] board, Cell[][] boardCopy){
        outerloop:
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column].isAlive() != boardCopy[row][column].isAlive()) {
                    gameOver = false;
                    break outerloop;
                }
                if (board[row][column].isAlive() == boardCopy[row][column].isAlive()) {
                    gameOver = true;
                }
            }
        }
    }

    public void triminoPattern1(Cell[][] board, int row, int column) {
        if (row >= 0 && row + 1 < board.length && column - 2 > 0 && column < board[row].length) {
            board[row][column].setAlive(true);
            board[row - 1][column + 1].setAlive(true);
            board[row + 1][column - 1].setAlive(true);
        } else
            System.out.println("Selected cell is outside of the board.");
    }

    public void tetrominoPattern1(Cell[][] board, int row, int column) {
        if (row >= 0 && row + 1 < board.length && column - 2 > 0 && column < board[row].length) {
            board[row][column].setAlive(true);
            board[row][column - 1].setAlive(true);
            board[row][column - 2].setAlive(true);
            board[row + 1][column - 2].setAlive(true);
        } else
            System.out.println("Selected cell is outside of the board.");
    }

    public void tetrominoPattern2(Cell[][] board, int row, int column) {
        if (row >= 0 && row + 1 < board.length && column - 2 > 0 && column < board[row].length) {
            board[row][column].setAlive(true);
            board[row + 1][column].setAlive(true);
            board[row + 2][column].setAlive(true);
            board[row - 1][column].setAlive(true);
        } else
            System.out.println("Selected cell is outside of the board.");
    }

    public void tetrominoPattern3(Cell[][] board, int row, int column) {
        if (row >= 0 && row + 1 < board.length && column - 2 > 0 && column < board[row].length) {
            board[row][column].setAlive(true);
            board[row + 1][column].setAlive(true);
            board[row + 1][column - 1].setAlive(true);
            board[row + 1][column + 1].setAlive(true);
        } else
            System.out.println("Selected cell is outside of the board.");
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }
}

