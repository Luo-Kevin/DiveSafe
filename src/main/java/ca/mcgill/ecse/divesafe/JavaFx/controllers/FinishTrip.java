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

public class FinishTrip implements Initializable {

  @FXML
  private Button confirm;

  @FXML
  private ChoiceBox<String> emailChoiceBox;

  @FXML
  private ChoiceBox<String> finishOrCancel;

  private String[] adminAction = new String[] {"Finish Trip", "Cancel Trip"};

  private List<String> members = this.getMembers();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    finishOrCancel.getItems().addAll(adminAction);
    emailChoiceBox.getItems().addAll(members);
    confirm.setOnAction(this::performFinishOrCancel);
  }

  public void performFinishOrCancel(ActionEvent event) {

    String errorMessage = "";

    String action = finishOrCancel.getValue();
    if (action.equals("Finish Trip")) {
      errorMessage = AssignmentController.finishTrip(emailChoiceBox.getValue())+"\n";
      if (errorMessage.equals("0")) {
        errorMessage = String.format("Finished trip successfully for %s.\n", emailChoiceBox.getValue());
      }
    } else if (action.equals("Cancel Trip")) {
      errorMessage = AssignmentController.cancelTrip(emailChoiceBox.getValue())+"\n";
      if (errorMessage.equals("50") || errorMessage.equals("10") || errorMessage.equals("")) {
        errorMessage = String.format("Cancelled trip successfully for %s.\n", emailChoiceBox.getValue());
      }
    }

    Text message = new Text(errorMessage);
    StartTripController.getTextFlow().getChildren().add(message);

  }

  public ArrayList<String> getMembers() {
    return AssignmentController.getMemberEmails();
  }

}
