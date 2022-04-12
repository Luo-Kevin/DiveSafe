package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

/**
 * Controller for the finish trip and cancel trip page.
 * 
 * @author Eric Joung
 */

public class FinishTrip implements Initializable {

  @FXML
  private Button confirm;

  @FXML
  private ChoiceBox<String> emailChoiceBox;

  @FXML
  private ChoiceBox<String> finishOrCancel;

  private String[] adminAction = new String[] {"Finish Trip", "Cancel Trip"};

  private List<String> members = this.getMembers();

  /**
   * Method executed when page is initialized.
   */

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    finishOrCancel.getItems().addAll(adminAction);
    emailChoiceBox.getItems().addAll(members);
    confirm.setOnAction(this::performFinishOrCancel);
  }

  /**
   * Method to perform finish or cancel trip.
   * 
   * @param event - mouse click
   */

  public void performFinishOrCancel(ActionEvent event) {

    String errorMessage = "";

    String action = finishOrCancel.getValue();
    if (action.equals("Finish Trip")) {
      errorMessage = AssignmentController.finishTrip(emailChoiceBox.getValue());
      if (errorMessage.length() < 3) {
        errorMessage = String.format("Finished trip successfully for %s.", emailChoiceBox.getValue());
      }
    } else if (action.equals("Cancel Trip")) {
      errorMessage = AssignmentController.cancelTrip(emailChoiceBox.getValue());
      if (errorMessage.equals("50") || errorMessage.equals("10") || errorMessage.equals("")) {
        if (errorMessage.equals("")) {
          errorMessage = "0";
        }
        String refundPercent = errorMessage;
        errorMessage = String.format("Cancelled trip successfully for %s.\n     Refund of %s %%.", emailChoiceBox.getValue(), refundPercent);
      }
    }

    Text message = new Text(errorMessage + "\n");
    StartTripController.getTextFlow().getChildren().add(message);

  }

  /**
   * Get the list of member emails from the assignment controller.
   * 
   * @return the list of members
   */

  public ArrayList<String> getMembers() {
    return AssignmentController.getMemberEmails();
  }

}
