package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Image image = new Image("file:../ressources/diving.png");

    @FXML
    private Button assignmentButton;

    @FXML
    private Button resetButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ImageView img = new ImageView(image);
        // img.setFitHeight(100);
        // img.setFitWidth(100);
    }

    @FXML
    void resetApp(MouseEvent event) {
        DiveSafeApplication.reset();
    }
    

    /**
     * Method to switch to payment page
     */

    @FXML
    public void switchToPayment(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../pages/PaymentPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch to assignment page
     */

    @FXML
    public void switchToAssignment(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../pages/InitiateAndViewAssignmentPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    /**
     * Method to switch to trip page
     */
  

    @FXML 
    public void switchToTrip(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../pages/TripPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
