package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.util.List;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import ca.mcgill.ecse.divesafe.controller.MemberController;
import ca.mcgill.ecse.divesafe.controller.TOAssignment;



public class PaymentController {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private TextField authorizationCode;

  @FXML
  private TextField email;

  @FXML
  private TextField password;

  @FXML
  private Text resultText;

  @FXML
  void retrieveBillClick(ActionEvent event) {
    String userEmail = email.getText().strip();
    var userBooking = MemberController.getUserBill(userEmail);

    if(userBooking == null){
      System.out.println("Email does not exist");
    }

    else{

      var userBill = MemberController.userBillToString(userBooking, userEmail);
      System.out.println(userBill);
    }

  }

  @FXML
  void makePayment(ActionEvent event) {
    System.out.println("hello");

  }

  @FXML
  void switchToAssignment(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("../pages/InitiateAndViewAssignmentPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
