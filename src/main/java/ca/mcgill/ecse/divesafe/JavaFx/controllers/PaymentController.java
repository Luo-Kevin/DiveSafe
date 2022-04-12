package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import ca.mcgill.ecse.divesafe.controller.MemberController;

/**
 * Controller for page where user gets their bill and makes their payment.
 * 
 * @author Kevin Luo
 */

public class PaymentController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private Button memberButton;

  @FXML
  private Button assignmentButton;

  @FXML
  private Button paymentButton;

  @FXML
  private Button tripButton;

  @FXML
  private Button resetButton;

  @FXML
  private TextField authorizationCode;

  @FXML
  private TextField email;

  @FXML
  private TextField password;

  @FXML
  private ListView<String> paymentDetail;

  @FXML
  private Text resultText;

  @FXML
  private TextFlow paymentSummary;

  @FXML
  private Label errorMessage;

  /**
   * Method executed when page is initialized.
   * 
   * @author everyone
   */

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    errorMessage.setTextFill(Color.web("#0076a3"));
    errorMessage.setText("DiveSafe");
  }

  /**
   * Reset the assignments.
   * 
   * @author everyone
   * @param event - mouse click
   * @throws IOException
   */

  @FXML
  void resetApp(MouseEvent event) throws IOException {
    DiveSafeApplication.reset();
    switchToPayment(event);
  }

  /**
   * Method triggered when user click the retrieve bill button.
   * 
   * @param event - mouse click
   */

  @FXML
  void retrieveBillClick(ActionEvent event) {
    String userEmail = email.getText().strip();
    boolean userFound = false;
    if (paymentDetail.getItems().isEmpty()) {
      var userBooking = AssignmentController.getUserBill(userEmail);

      for (var member : AssignmentController.getAssignments()) {
        if (member.getMemberEmail().equals(userEmail)) {
          userFound = true;

          //Display information on detailed sales to user
          String userStartDate = "Start Date: " + member.getStartDay();
          String userEndDate = "End Date: " + member.getEndDay();
          paymentDetail.getItems().add(userStartDate);
          paymentDetail.getItems().add(userEndDate);
          paymentDetail.getItems().add("GUIDE");
          String userGuideDetail = "Guide: " + member.getGuideName() + " [Contact: " + member.getGuideEmail() + "] $"
              + member.getTotalCostForGuide();
          paymentDetail.getItems().add(userGuideDetail);
          paymentDetail.getItems().add("EQUIPMENT");
          var userEquipmentDetail = AssignmentController.userBillBookedEquipmentDetails(userBooking, userEmail);
          userEquipmentDetail.forEach(equipmentDetail -> paymentDetail.getItems().add(equipmentDetail));
          paymentDetail.getItems().add("BUNDLE");
          var userBundleDetail = AssignmentController.userBillBundleDetails(userBooking, userEmail);
          userBundleDetail.forEach(bundleDetail -> paymentDetail.getItems().add(bundleDetail));
     
          //Display payment summary
          int totalEquipmentCost = member.getTotalCostForEquipment();
          Text equipmentSummary = new Text("Total Equipment Cost: $" + totalEquipmentCost + "\n");
          int totalGuideCost = member.getTotalCostForGuide();
          Text guideSummary = new Text("Total Guide Cost: $" + totalGuideCost + "\n");
          int totalCost = totalEquipmentCost + totalGuideCost;
          Text totalSummary = new Text("Total Cost: $" + totalCost);
          paymentSummary.getChildren().add(equipmentSummary);
          paymentSummary.getChildren().add(guideSummary);
          paymentSummary.getChildren().add(totalSummary);

          break;
        }
      }
    }
    //Display error message if user not found
    if (!userFound) {
      errorMessage.setText(String.format("No payment associated with ") + userEmail);
      errorMessage.setTextFill(Color.RED);
    }

  }

  /**
   * Method triggered when user click the retrieve make payment button.
   * 
   * @param event - mouse click
   */

  @FXML
  void makePayment(MouseEvent event) {
    if (!paymentDetail.getItems().isEmpty()) {
      String userEmail = email.getText().strip();
      //Getting the authorization code
      String paymentAuthorization = authorizationCode.getText().strip();
      //Attempt to make the payment
      String paymentMessage = AssignmentController.confirmPayment(userEmail, paymentAuthorization);
      //Display error message after
      if (MemberController.getMemberStatus(userEmail).equals("Paid")) {
        errorMessage.setTextFill(Color.web("#0076a3"));
      } else {
        errorMessage.setTextFill(Color.RED);
      }
      errorMessage.setText(paymentMessage);

    }
  }

  /**
   * Method to switch to the payment page.
   * 
   * @param event - mouse click
   */

  @FXML
  public void switchToPayment(MouseEvent event) throws IOException {
    String userEmail = email.getText().strip();
    root = FXMLLoader.load(getClass().getResource("../pages/PaymentPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    if (MemberController.getMemberStatus(userEmail).equals("Paid")) {
      errorMessage.setTextFill(Color.web("#0076a3"));
      errorMessage.setText("Paid");
    }

  }

  /**
   * Method to switch to assignment page.
   * 
   * @author everyone
   * @param event - mouse click
   */

  @FXML
  void switchToAssignment(MouseEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("../pages/InitiateAndViewAssignmentPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Method to switch to trip page.
   * 
   * @author everyone
   * @param event - mouse click
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
   * Method to switch to member page.
   * 
   * @author everyone
   * @param event - mouse click
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
