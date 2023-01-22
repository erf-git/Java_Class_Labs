package connectfour.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * A custom Button
 *
 * @author Ethan R Feldman
 */
public class Cell extends Button {

    // Stores button's row and column position
    private int rowPos;
    private int colPos;

    public Cell(int rowPos, int colPos) {
        this.rowPos = rowPos;
        this.colPos = colPos;
    }

    // You can set an image with this
    public void setImage(Image image) {
        this.setGraphic(new ImageView(image));
    }

    // You can get the cell's row and column position
    public int getRowPos() { return this.rowPos; }
    public int getColPos() { return this.colPos; }

}
