package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  // Image image = new Image("file:../ressources/diving.png");

  @FXML
  private Button memberButton;

  @FXML
  private Button assignmentButton;

  @FXML
  private Button resetButton;

  @FXML
  private Label errorMessage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    errorMessage.setTextFill(Color.web("#0076a3"));
    errorMessage.setText("DiveSafe");
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

  /**
   * Method to switch to member page
   */

  @FXML
  void switchToMember(MouseEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("../pages/MemberPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
