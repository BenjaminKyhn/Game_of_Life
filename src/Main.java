import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Instantiate a Game object and 2 two-dimensional arrays (these are part of the logic, but for the sake of not
        // having to call game.getBoard() a million times I chose to instantiate it here)
        Game game = new Game();
        Cell[][] board = new Cell[game.getBoardSize()][game.getBoardSize()];
        Cell[][] boardCopy = new Cell[game.getBoardSize()][game.getBoardSize()];

        // Create a GridPane and and two-dimensional array of Rectangles to be shown in the GUI
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Rectangle[][] squares = new Rectangle[game.getBoardSize()][game.getBoardSize()];

        // Print the empty grid
        showGrid(gridPane, squares);

        // Create a scene and add it to the stage
        Scene scene = new Scene(gridPane, 25 * game.getBoardSize() + 50, 25 * game.getBoardSize() + 50);
        stage.setScene(scene);
        stage.show();

        // Initiate the first pattern
        game.clearBoard(board);
        game.tetrominoPattern1(board, 10, 10);
        updateGUI(board, squares);
        game.printBoard(board);

        // Add a timeline to call the cycle method
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                e -> cycle(board, boardCopy, squares)));
        timeline.setOnFinished(e -> finishText());
        timeline.setCycleCount(3);
        timeline.play();
    }

    // Method for showing the grid in the GUI
    public void showGrid(GridPane gridPane, Rectangle[][] squares) {
        Game game = new Game();
        for (int i = 0; i < game.getBoardSize(); i++)
            for (int j = 0; j < game.getBoardSize(); j++) {
                gridPane.add(squares[i][j] = new Rectangle(25, 25, Color.WHITE), j, i);
                squares[i][j].setStroke(Color.BLACK);
            }
    }

    // Method for updating the grid in the GUI
    public void updateGUI(Cell[][] board, Rectangle[][] squares) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column].isAlive())
                    squares[row][column].setFill(Color.BLUE);
                if (!board[row][column].isAlive())
                    squares[row][column].setFill(Color.WHITE);
            }
        }
    }

    void cycle(Cell[][] board, Cell[][] boardCopy, Rectangle[][] squares) {
        Game game = new Game();

        // Make a copy of the board
        game.copyBoard(board, boardCopy);

        // Update the board status
        game.updateStatus(board);
        game.updateBoard(board);
        game.printBoard(board);

        // Update the GUI
        updateGUI(board, squares);

        // Check if the board has changed since the copy was made
        game.checkGameOver(board, boardCopy);
    }

    void finishText() {
        System.out.println("The cells have reached a stable state");
    }
}
