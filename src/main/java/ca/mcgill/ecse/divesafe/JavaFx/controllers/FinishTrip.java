package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class FinishTrip implements Initializable {

    @FXML
    private Button confirm;

    @FXML
    private ChoiceBox<String> emailChoiceBox;

    @FXML
    private ChoiceBox<String> finishOrCancel;

    private String[] adminAction = new String[] {"Finish Trip", "Cancel Trip"};



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        finishOrCancel.getItems().addAll(adminAction);

    }

    public String performFinishOrCancel(ActionEvent event) {

        String errorMessage = "";

        String action = finishOrCancel.getValue();
        if (action.equals("Finish Trip")) {
            errorMessage = AssignmentController.finishTrip(emailChoiceBox.getValue());
            if (errorMessage.equals("")) {
                errorMessage = String.format("Finished trip successfully for %s", emailChoiceBox.getValue());
            }
        }
        else if (action.equals("Cancel Trip")) {
            errorMessage = AssignmentController.cancelTrip(emailChoiceBox.getValue());
            if (errorMessage.equals("")) {
                errorMessage = String.format("Finished trip successfully for %s", emailChoiceBox.getValue());
            }
        }

        return errorMessage;
    }



}
