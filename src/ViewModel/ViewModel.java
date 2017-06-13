package ViewModel;

import Model.IModel;
import Model.MyModel;
import View.MazeDisplayer;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by ענבר on 12/06/2017.
 */
public class ViewModel {
    public IModel model;
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

        this.mazeDisplayer.setMaze(model.generate(rows,columns).mainBoard);
    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void reFresh() {
        this.mazeDisplayer.reFresh(center.getHeight(), center.getWidth());
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