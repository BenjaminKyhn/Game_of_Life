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

    public void incrementNeighbours() {
        this.aliveNeighbours++;
    }

    public void updateCell() {
        // Check if the cell is killed
        if (aliveNeighbours < 2 || aliveNeighbours > 3)
            this.alive = false;

        // Check if the cell is revived from death
        if (aliveNeighbours == 3)
            this.alive = true;
    }
}

