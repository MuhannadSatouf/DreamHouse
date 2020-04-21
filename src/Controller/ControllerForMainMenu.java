package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class ControllerForMainMenu implements Initializable {
    public JFXButton addPropertyBtn;
    public JFXButton viewPropertyBtn;
    public JFXButton addUserBtn;
    public JFXButton viewUserBtn;
    public JFXButton transactionsBtn;
    public Pane startPane;
    public BorderPane borderPane;
    public VBox hBoxForButtons;
    public Pane paneCenter;
    public JFXButton statisticsBtn;
    public JFXButton selectHouseBtn;
    public JFXButton selectApartmentBtn;
    public JFXButton selectLandBtn;
    public JFXButton selectCommercialBtn;
    public JFXButton houseSaleBtn;
    public JFXButton HouseRentBtn;
    public JFXButton apartmentSaleBtn;
    public JFXButton apartmentRentBtn;
    public JFXButton commercialSaleBtn;
    public JFXButton commercialRentBtn;
    public JFXButton landSaleBtn;
    public JFXButton landRentBtn;
    public JFXButton addEmployeeBtn;
    public JFXButton addCustomerBtn;
    public JFXButton viewApartmentSaleBtn;
    public JFXButton viewApartmentRentBtn;
    public JFXButton viewCommercialSaleBtn;
    public JFXButton viewCommercialRentBtn;
    public JFXButton viewHouseSaleBtn;
    public JFXButton viewHouseRentBtn;
    public JFXButton viewLandSaleBtn;
    public JFXButton viewLandRentBtn;
    public JFXButton selectHouseForViewBtn;
    public JFXButton selectApartmentForViewBtn;
    public JFXButton selectLandForViewBtn;
    public JFXButton selectCommercialForViewBtn;
    public JFXButton viewEmployeeBtn;
    public JFXButton viewCustomerBtn;


    public void backToRegister(ActionEvent actionEvent) {
        createNewStage ("/View/registerFXML.fxml", "Register");


    }


    public void addProperty(ActionEvent actionEvent) throws IOException {

        switchBetweenScenes (actionEvent, "/View/selectPropertyForAddFXML.fxml");


    }

    public void viewProperty(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectPropertyForViewFXML.fxml");

    }

    public void addUser(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectUserForAddFXML.fxml");
        
    }

    public void viewUser(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectUserForViewFXML.fxml");
    }

    public void transactions(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        addPropertyBtn.prefWidthProperty ().bind (hBoxForButtons.widthProperty ());
        addPropertyBtn.prefHeightProperty ().bind (hBoxForButtons.heightProperty ());
        viewPropertyBtn.prefWidthProperty ().bind (hBoxForButtons.widthProperty ());
        viewPropertyBtn.prefHeightProperty ().bind (hBoxForButtons.heightProperty ());
        addUserBtn.prefWidthProperty ().bind (hBoxForButtons.widthProperty ());
        addUserBtn.prefHeightProperty ().bind (hBoxForButtons.heightProperty ());
        viewUserBtn.prefWidthProperty ().bind (hBoxForButtons.widthProperty ());
        viewUserBtn.prefHeightProperty ().bind (hBoxForButtons.heightProperty ());
        transactionsBtn.prefWidthProperty ().bind (hBoxForButtons.widthProperty ());
        transactionsBtn.prefHeightProperty ().bind (hBoxForButtons.heightProperty ());
        statisticsBtn.prefWidthProperty ().bind (hBoxForButtons.widthProperty ());
        statisticsBtn.prefHeightProperty ().bind (hBoxForButtons.heightProperty ());


      //  borderPane.prefWidthProperty ().bind (startPane.widthProperty ());
        // borderPane.prefHeightProperty ().bind (startPane.heightProperty ());


    }


    public void statistics(ActionEvent actionEvent) {
    }


    public void selectHouse(ActionEvent actionEvent) throws IOException {

        switchBetweenScenes (actionEvent, "/View/selectPropertyHouseForAddFXML.fxml");

    }

    public void selectApartment(ActionEvent actionEvent) throws IOException {

        switchBetweenScenes (actionEvent, "/View/selectPropertyApartmentForAddFXML.fxml");
    }

    public void selectLand(ActionEvent actionEvent) throws IOException {

        switchBetweenScenes (actionEvent, "/View/selectPropertyLandForAddFXML.fxml");
    }

    public void selectCommercial(ActionEvent actionEvent) throws IOException {

        switchBetweenScenes (actionEvent, "/View/selectPropertyCommercialForAddFXML.fxml");
    }

    public void addHouseForSale(ActionEvent actionEvent) {
        createNewStage ("/View/addHouseForSaleFXML.fxml","House For Sale");


    }

    public void addHouseForRent(ActionEvent actionEvent) {
        createNewStage ("/View/addHouseForRentFXML.fxml","House For Rent");
    }

    public void addApartmentForSale(ActionEvent actionEvent) {
        createNewStage ("/View/addApartmentForSaleFXML.fxml","Apartment For Sale");


    }

    public void addApartmentForRent(ActionEvent actionEvent) {
        createNewStage ("/View/addApartmentForRentFXML.fxml","Apartment For Rent");
    }

    public void addCommercialForSale(ActionEvent actionEvent) {
        createNewStage ("/View/addCommercialForSaleFXML.fxml","Commercial Properties For Sale");
    }

    public void addCommercialForRent(ActionEvent actionEvent) {
        createNewStage ("/View/addCommercialForRentFXML.fxml","Commercial Properties For Rent");

    }

    public void addLandForSale(ActionEvent actionEvent) {
        createNewStage ("/View/addLandForSaleFXML.fxml","Land For Sale");
    }

    public void addLandForRent(ActionEvent actionEvent) {
        createNewStage ("/View/addLandForRentFXML.fxml","Land For Rent");
    }

    void createNewStage(String location, String name) {
        try {
            Parent parent = FXMLLoader.load (getClass ().getResource (location));
            Stage stage = new Stage (StageStyle.DECORATED);
            stage.setTitle (name);
            stage.setScene (new Scene (parent));
            stage.show ();


        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


    void switchBetweenScenes(ActionEvent actionEvent, String location) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load (getClass ().getResource (location));
            Scene scene = new Scene (parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource ()).getScene ().getWindow ();
            stage.setScene (scene);
            stage.show ();
        } catch (IOException e) {
            e.printStackTrace ();
        }


    }


    public void addEmployee(ActionEvent actionEvent) {
        createNewStage ("/View/addEmployeeFXML.fxml","Add Employee");
    }

    public void addCustomer(ActionEvent actionEvent) {
        createNewStage ("/View/addCustomerFXML.fxml","Add Customer");
    }

    public void viewApartmentForSale(ActionEvent actionEvent) {
        createNewStage ("/View/viewApartmentForSale.fxml","Apartment For Sale");

    }

    public void viewApartmentForRent(ActionEvent actionEvent) {
        createNewStage ("/View/viewApartmentForRent.fxml","Apartment For Rent");
    }

    public void viewCommercialForSale(ActionEvent actionEvent) {
        createNewStage ("/View/viewCommercialForSale.fxml","Commercial Properties For Sale");
    }

    public void viewCommercialForRent(ActionEvent actionEvent) {
        createNewStage ("/View/viewCommercialForRent.fxml","Commercial Properties For Sale");
    }

    public void viewHouseForSale(ActionEvent actionEvent) {
        createNewStage ("/View/viewHouseForSale.fxml","House For Sale");
    }

    public void viewHouseForRent(ActionEvent actionEvent) {
        createNewStage ("/View/viewHouseForRent.fxml","House For Rent");
    }

    public void viewLandForRent(ActionEvent actionEvent) {
        createNewStage ("/View/viewLandForRent.fxml","Land For Rent");
    }

    public void viewLandForSale(ActionEvent actionEvent) {
        createNewStage ("/View/viewLandForSale.fxml","Land For Sale");
    }

    public void selectHouseForView(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectPropertyHouseForViewFXML.fxml");
    }

    public void selectApartmentForView(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectPropertyApartmentForViewFXML.fxml");
    }

    public void selectLandForView(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectPropertyLandForViewFXML.fxml");
    }

    public void selectCommercialForView(ActionEvent actionEvent) {
        switchBetweenScenes (actionEvent, "/View/selectPropertyCommercialForViewFXML.fxml");
    }

    public void viewEmployee(ActionEvent actionEvent) {
        createNewStage ("/View/viewEmployee.fxml","Employees");
    }

    public void ViewCustomer(ActionEvent actionEvent) {
        createNewStage ("/View/viewCustomer.fxml","Customers");
    }
}



