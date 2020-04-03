import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game();
        Cell[][] board = new Cell[game.getBoardSize()][game.getBoardSize()];
        Cell[][] boardCopy = new Cell[game.getBoardSize()][game.getBoardSize()];

        game.clearBoard(board);
        game.tetrominoPattern1(board, 10, 10);
        game.printBoard(board);

        do {
            // Make a copy of the board
            game.copyBoard(board, boardCopy);

            // Update the board status
            game.updateStatus(board);
            game.updateBoard(board);
            game.printBoard(board);

            // Check if the board has changed since the copy was made
            game.checkGameOver(board, boardCopy);

        } while (!game.isGameOver()); // End game if the cells have stopped changing

        System.out.println("The cells have reached a stable state");
    }
}
