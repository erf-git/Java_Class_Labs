package connectfour.gui;

import connectfour.model.ConnectFourBoard;
import connectfour.model.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;

/**
 * A JavaFX GUI for the networked Connect Four game.
 *
 * @author RIT CS
 * @author Ethan R Feldman
 */
public class ConnectFourGUI extends Application implements Observer<ConnectFourBoard> {

    // Define some GUI nodes beforehand so that we can easily update them later
    private ConnectFourBoard model;
    private GridPane board;
    private Label movesMadeDisplay;
    private Label currentPlayerDisplay;
    private Label statusDisplay;

    // Images defined
    private final Image emptyCell = new Image(Objects.requireNonNull(getClass().getResourceAsStream("empty.png")));
    private final Image p1Cell = new Image(Objects.requireNonNull(getClass().getResourceAsStream("p1black.png")));
    private final Image p2Cell = new Image(Objects.requireNonNull(getClass().getResourceAsStream("p2red.png")));

    @Override
    public void init() {
        // TODO

        // create the model and add ourselves as an observer
        this.model = new ConnectFourBoard();
        model.addObserver(this);
    }

    /**
     * Construct the layout for the game.
     *
     * @param stage container (window) in which to render the GUI
     * @throws Exception if there is a problem
     */
    public void start( Stage stage ) throws Exception {
        // TODO

        // Everything in a boarder pane
        BorderPane pane = new BorderPane();

        // The board is in a grid pane
        board = new GridPane();
        board.setAlignment(Pos.CENTER);

        // The bottom text is in a flow pane
        FlowPane bottom = new FlowPane();
        bottom.setAlignment(Pos.CENTER);
        bottom.setHgap(100);

        // Adds buttons and sets their images
        for (int r=0; r<model.getRows(); r++) {
            for (int c=0; c<model.getCols(); c++) {

                // I made a special button class for this that will store it's row and column location when initiated
                Cell button = new Cell(r,c);

                // Set empty image on initiation
                button.setImage(emptyCell);

                // Gives the model.makeMove() action to the buttons
                int finalC = c;
                button.setOnAction(event -> {
                    model.makeMove(finalC);
                });

                // Adds the new button to the board
                board.add(button, c, r);
            }
        }
        pane.setCenter(board);

        // Labels for the bottom text row
        movesMadeDisplay = new Label("Moves Made: " + String.valueOf(model.getMovesMade()));
        currentPlayerDisplay = new Label("Current Player: " + model.getCurrentPlayer());
        statusDisplay = new Label("Status: " + model.getGameStatus());

        // Adds bottom row information
        bottom.getChildren().addAll(movesMadeDisplay, currentPlayerDisplay, statusDisplay);
        pane.setBottom(bottom);

        // Sets the scene
        stage.setScene(new Scene(pane));
        stage.setTitle("Connect Four");

        stage.show();
    }

    /**
     * The buttons update in this method by getting.
     *
     * @param connectFourBoard
     */
    private void refresh( ConnectFourBoard connectFourBoard ) {

        // Updates bottom labels
        movesMadeDisplay.setText("Moves Made: " + String.valueOf(connectFourBoard.getMovesMade()));
        currentPlayerDisplay.setText("Current Player: " + connectFourBoard.getCurrentPlayer());
        statusDisplay.setText("Status: " + connectFourBoard.getGameStatus());

        /**
         * It will look at all the children of the board
         * Typecast the nodes to Cells because they were all make originally as Cell.java
         * Sees if the cell is in the same location as lastRow and lastCol from the model
         * Sets the player images respectively
         */
        for (Node n : board.getChildren()) {
            Cell thisCell = (Cell) n;

            if (thisCell.getRowPos() == connectFourBoard.getLastRow() && thisCell.getColPos() == connectFourBoard.getLastCol()) {
                if (connectFourBoard.getCurrentPlayer() == ConnectFourBoard.Player.P1) {
                    thisCell.setImage(p1Cell);
                    //System.out.println(connectFourBoard.getCurrentPlayer() + " at {row:" + connectFourBoard.getLastRow() + ", col:" + connectFourBoard.getLastCol() + "}");
                } else if (connectFourBoard.getCurrentPlayer() == ConnectFourBoard.Player.P2) {
                    thisCell.setImage(p2Cell);
                    //System.out.println(connectFourBoard.getCurrentPlayer() + " at {row:" + connectFourBoard.getLastRow() + ", col:" + connectFourBoard.getLastCol() + "}");
                }
            }

            // It will disable all buttons if the game has ended (hasWonGame() or tied)
            if (connectFourBoard.hasWonGame() || connectFourBoard.getMovesMade() == connectFourBoard.getRows()*connectFourBoard.getCols()) {
                thisCell.setDisable(true);
            }
        }
    }

    /**
     * Called by the model, model.ConnectFourBoard, whenever there is a state change
     * that needs to be updated by the GUI.
     *
     * @param connectFourBoard the board
     */
    @Override
    public void update(ConnectFourBoard connectFourBoard) {
        // TODO

        if ( Platform.isFxApplicationThread() ) {
            this.refresh(connectFourBoard);
        }
        else {
            Platform.runLater( () -> this.refresh(connectFourBoard) );
        }

    }

    /**
     * The main method expects the host and port.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}