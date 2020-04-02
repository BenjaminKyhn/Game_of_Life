public class Cell {
    private int aliveNeighbours;
    private boolean alive;

    public int getAliveNeighbours() {
        return aliveNeighbours;
    }

    public void setAliveNeighbours(int aliveNeighbours) {
        this.aliveNeighbours = aliveNeighbours;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void update(Cell[][] board) {
        for (int row = 0; row < board.length - 1; row++) {
            for (int column = 0; column < board[row].length - 1; column++) {
                aliveNeighbours = 0;
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
