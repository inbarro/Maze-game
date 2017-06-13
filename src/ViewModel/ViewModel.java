package ViewModel;

import Model.IModel;
import Model.MyModel;
import Server.*;
import View.MazeDisplayer;
import algorithms.mazeGenerators.Maze;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent.*;



/**
 * Created by ענבר on 12/06/2017.
 */
public class ViewModel {
    public IModel model;
    private int characterPositionRow ;
    private int characterPositionColumn;
    public MazeDisplayer mazeDisplayer;
    public TextField txtfld_rowsNum;
    public TextField txtfld_columnsNum;
    public Pane center;


    public ViewModel(){
        model = new MyModel();
    }

    public void generateMaze() {
        reFresh();
        int rows = Integer.parseInt(txtfld_rowsNum.getText());
        int columns = Integer.parseInt(txtfld_columnsNum.getText());
        Maze m = model.generate(rows,columns);
        this.mazeDisplayer.setMaze(m.mainBoard);
        characterPositionRow = m.getStartPosition().getX();
        characterPositionColumn = m.getStartPosition().getY();
    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void solveMaze(ActionEvent actionEvent) {
        showAlert("Solving maze..");
    }

    public void KeyPressed(KeyEvent keyEvent) {

        int characterRow = mazeDisplayer.getCharacterPositionRow();
        int characterColumn = mazeDisplayer.getCharacterPositionColumn();

        if (keyEvent.getCode() == KeyCode.UP) {
            mazeDisplayer.setCharacterPosition(characterRow - 1, characterColumn);
        }
        if (keyEvent.getCode() == KeyCode.DOWN) {
            mazeDisplayer.setCharacterPosition(characterRow + 1, characterColumn);
        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            mazeDisplayer.setCharacterPosition(characterRow, characterColumn + 1);
        }
        if (keyEvent.getCode() == KeyCode.LEFT) {
            mazeDisplayer.setCharacterPosition(characterRow, characterColumn - 1);
        }
    }

    //region String Property for Binding
    public StringProperty CharacterRow = new SimpleStringProperty();
    public StringProperty CharacterColumn = new SimpleStringProperty();

    public String getCharacterRow() {
        return (mazeDisplayer!= null) ? mazeDisplayer.getCharacterPositionRow() + "" : "1";
    }

    public String getCharacterColumn() {
        return (mazeDisplayer!= null) ? mazeDisplayer.getCharacterPositionColumn() + "" : "1";
    }

    public void reFresh() {
        this.mazeDisplayer.reFresh(center.getHeight(), center.getWidth());
    }


    public static void startAllServers(){
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();
    }
}


/*
    public void openFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle(" open maze file");
        fc.setInitialDirectory(new File(""));
        File chosen = fc.showOpenDialog(null);
    }
}
*/