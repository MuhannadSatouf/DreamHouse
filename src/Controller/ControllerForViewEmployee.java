package Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForViewEmployee implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public TableView tableOfEmployee;
    public TableColumn SSNCol;
    public TableColumn typeCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn phoneCol;
    public TableColumn emailCol;
    public TableColumn employeeIDCol;
    public HBox hbox;
    public Pane upPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfEmployee.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfEmployee.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
}
