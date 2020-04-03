import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game();
        Cell[][] board = new Cell[game.getBoardSize()][game.getBoardSize()];
        Cell[][] boardCopy = new Cell[game.getBoardSize()][game.getBoardSize()];

        game.clearBoard(board);
        game.tetrominoPattern1(board, 10, 10);
        game.printBoard(board);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Rectangle[][] squares = new Rectangle[game.getBoardSize()][game.getBoardSize()];
        for (int i = 0; i < game.getBoardSize(); i++)
            for (int j = 0; j < game.getBoardSize(); j++) {
                gridPane.add(squares[i][j] = new Rectangle(25, 25, Color.WHITE), j, i);
                squares[i][j].setStroke(Color.BLACK);
            }

        Scene scene = new Scene(gridPane, 25 * game.getBoardSize() + 50, 25 * game.getBoardSize() + 50);
        stage.setScene(scene);
        stage.show();

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
