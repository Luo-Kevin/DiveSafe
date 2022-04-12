package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import ca.mcgill.ecse.divesafe.controller.MemberController;
import ca.mcgill.ecse.divesafe.controller.TOAssignment;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller method for the start trip page.
 * 
 * @author Jiahao Zhao
 */

public class StartTripController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private Button assignmentButton;

  @FXML
  private Button memberButton;

  @FXML
  private Button paymentButton;

  @FXML
  private Button resetButton;

  @FXML
  private Label errorMessage;

  @FXML
  private TextFlow startTripResult;

  @FXML
  private static TextFlow startTripResult_static;

  public static TextFlow getTextFlow() {
    return startTripResult_static;
  }

  // Configure spinner to choose desired day to start trips
  @FXML
  private Spinner<Integer> daySpinner;

  // integer to store the day selected by the user using the daySpinner spinner
  int targetDay;

  // List of assignments from the assignment controller
  List<TOAssignment> assignments = AssignmentController.getAssignments();

  /**
   * Method executed when page is initialized.
   */

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
    daySpinner.setValueFactory(valueFactory);
    // Set the targetDay to the dayspinner
    targetDay = valueFactory.getValue();

    daySpinner.valueProperty().addListener(new ChangeListener<Integer>() {
      @Override
      public void changed(javafx.beans.value.ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
        daySpinner.setValueFactory(valueFactory);
        targetDay = valueFactory.getValue();
     }
    });

    startTripResult_static = startTripResult;

    errorMessage.setTextFill(Color.web("#0076a3"));
    errorMessage.setText("DiveSafe");
  }

  /**
   * Method triggered when user clicks the start button
   *  1. get all assignments and their corresponding startDay
   *  2. Check if targetDay is the same as the startDay of the assignment
   *  3. Check if member is eligible to start trip, i.e. if status is paid,
   *     if not BAN
   *  4. If eligible, start trip
   * 
   * @param event - mouse click
   */

  @FXML
  void startTrip(MouseEvent event) {

    // System.out.println("Contains this : " + assignments);

    List<String> readyForTripMembers = new ArrayList<String>();
    // make sure there are assignmnents to start trips

    if (assignments.isEmpty()) {
      // System.out.println("No assignments");

      Text noAssignment = new Text("No assignments.\n");
      startTripResult.getChildren().add(noAssignment);
    } else {

      for (TOAssignment assignment : assignments) {
        int startDay = assignment.getStartDay();

        if (startDay == targetDay) {
          readyForTripMembers.add(assignment.getMemberEmail());
        }
      }

      if (readyForTripMembers.isEmpty()) {
        Text noMemberReady = new Text("No assigned member on chosen day.\n");
        startTripResult.getChildren().add(noMemberReady);

      } else {
        
        String message = AssignmentController.startTripsForDay(targetDay);
        
        for (String memberEmail : readyForTripMembers) {

          if (MemberController.getMemberStatus(memberEmail).equals("Started")) {
            Text tripStarted = new Text("Member " + memberEmail + ": Trip started!\n");
            startTripResult.getChildren().add(tripStarted);
          }

          else if (MemberController.getMemberStatus(memberEmail).equals("Banned")) {
            Text banMember = new Text("Member " + memberEmail + ": Banned.\n");
            startTripResult.getChildren().add(banMember);
          }

          else {
            Text error = new Text("Member " + memberEmail + ": " + message + ".\n");
            startTripResult.getChildren().add(error);
          }
        }
      }
    }
    
  }

  /**
   * Reset the assignments.
   * 
   * @author everyone
   * @param event - mouse click
   * @throws IOException
   */

  @FXML
  void resetApp(MouseEvent event) {
    DiveSafeApplication.reset();
    // Clearing assignments list instance upon clicking reset button, the above
    // isn't enough
    assignments.clear();
    startTripResult.getChildren().clear();
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
   * Method to switch to the payment page.
   * 
   * @author everyone
   * @param event - mouse click
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
