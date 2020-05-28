package View.Controller;

import Models.DataBaseHandler;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenuForEmployee implements Initializable {
    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton addPropertyBtn, viewPropertyBtn,
            addUserBtn, viewUserBtn, transactionsBtn, statisticsBtna, ddCustomerBtn;

    @FXML
    private VBox hBoxForButtons;

    @FXML
    private Pane paneCenter;

    public MainMenuForEmployee() throws SQLException {
    }


    @FXML
    void addCustomer(ActionEvent event) {
        createNewStage("/View/addCustomerFXML.fxml", "Add Customer");
    }

    @FXML
    void addProperty(ActionEvent event) {
        switchBetweenScenes(event, "/View/selectPropertyForAddFXML.fxml");
    }

    @FXML
    void addUser(ActionEvent event) {
        switchBetweenScenes(event, "/View/SelectUserForAddForEmployee.fxml");
    }

    @FXML
    void statistics(ActionEvent event) {
        switchBetweenScenes(event, "/View/statisticsFXML.fxml");
    }

    @FXML
    void transactions(ActionEvent event) {
        createNewStage("/View/transactionsFXML.fxml", "Transactions");
    }

    @FXML
    void viewProperty(ActionEvent event) {
        switchBetweenScenes(event, "/View/selectPropertyForViewFXML.fxml");
    }

    @FXML
    void viewUser(ActionEvent event) {
        switchBetweenScenes(event, "/View/selectUserForViewFXML.fxml");
    }

    void switchBetweenScenes(ActionEvent actionEvent, String location) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource(location));
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("/Resources/Table.Css").toExternalForm());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void createNewStage(String location, String name) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(name);
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("/Resources/Table.Css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataBaseHandler = DataBaseHandler.getInstance();
        addPropertyBtn.prefWidthProperty().bind(hBoxForButtons.widthProperty());
        addPropertyBtn.prefHeightProperty().bind(hBoxForButtons.heightProperty());
        viewPropertyBtn.prefWidthProperty().bind(hBoxForButtons.widthProperty());
        viewPropertyBtn.prefHeightProperty().bind(hBoxForButtons.heightProperty());
        addUserBtn.prefWidthProperty().bind(hBoxForButtons.widthProperty());
        addUserBtn.prefHeightProperty().bind(hBoxForButtons.heightProperty());
        viewUserBtn.prefWidthProperty().bind(hBoxForButtons.widthProperty());
        viewUserBtn.prefHeightProperty().bind(hBoxForButtons.heightProperty());
        transactionsBtn.prefWidthProperty().bind(hBoxForButtons.widthProperty());
        transactionsBtn.prefHeightProperty().bind(hBoxForButtons.heightProperty());

    }
}
