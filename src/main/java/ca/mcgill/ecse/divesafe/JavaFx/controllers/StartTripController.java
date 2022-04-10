package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartTripController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button assignmentButton;

    @FXML
    private Button member;

    @FXML
    private Button resetButton;

    @FXML
    void resetApp(MouseEvent event) {
        DiveSafeApplication.reset();
    }

    @FXML
    void switchToAssignment(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../pages/InitiateAndViewAssignmentPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToPayment(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../pages/PaymentPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
