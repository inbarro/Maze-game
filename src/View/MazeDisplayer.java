package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Aviadjo on 3/9/2017.
 */
public class MazeDisplayer extends Canvas {

    private int[][] maze;
    private int characterPositionRow = 1;
    private int characterPositionColumn = 1;
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();

    public void setMaze(int[][] maze) {
        this.maze = maze;
        redraw();
    }

    public void setCharacterPosition(int row, int column) {
        characterPositionRow = row;
        characterPositionColumn = column;
        redraw();
    }

    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }

    public void redraw() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = (canvasHeight / maze.length);
            double cellWidth = (canvasWidth / maze[0].length);

            //  Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
            //    Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.clearRect(0, 0, getWidth(), getHeight());

            //Draw Maze
            for (int i = maze.length-1; i >0; i--) {
                for (int j =  maze[0].length-1; j > 0; j--) {
                    if (maze[i][j] == 1 ) {
                        gc.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                        // gc.drawImage(wallImage, i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                    }

                }
            }
            //Draw Character
            gc.setFill(Color.RED);

            gc.fillOval(cellHeight*characterPositionColumn, characterPositionRow * cellWidth, cellHeight, cellWidth);
            //  gc.drawImage(characterImage, characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);

        }
    }

    //region Properties


    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }

    public void reFresh(double height, double width) {
        this.setWidth(400);
        this.setHeight(400);
    }
    //endregion

}