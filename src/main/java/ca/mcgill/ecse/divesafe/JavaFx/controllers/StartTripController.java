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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartTripController implements Initializable {
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
    private Text startTripResult;

    // Configure spinner to choose desired week to start trips
    @FXML
    private Spinner<Integer> weekSpinner;

    //integer to store the week selected by the user using the weekSpinner spinner
    int targetWeek;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        weekSpinner.setValueFactory(valueFactory);
        targetWeek = valueFactory.getValue();

    }
    
    /**
     * 
     * @author JZ
     * Method triggered when user clicks the start button
     * 1. get all assignments and their corresponding startDay
     * 2. Check if targetWeek is the same as the startDay of the assignment
     * 3. Check if member is eligible to start trip, i.e. if status is paid, if not BAN
     * 4. If eligible, start trip
     */

    @FXML 
    void startTrip(MouseEvent event) { 
        List<TOAssignment> assignments = AssignmentController.getAssignments();
        List<String> readyForTripMembers = new ArrayList<String>();

        for (TOAssignment assignment : assignments) {
            int startDay = assignment.getStartDay();

            if(startDay == targetWeek) {
                readyForTripMembers.add(assignment.getMemberEmail());
            }
        }
        for(String memberEmail: readyForTripMembers) {
    
            if(MemberController.getMemberStatus(memberEmail).equals("Paid")) {
                startTripResult.setText("Member " + memberEmail + ": Trip started!");
            }
        
            else {
                startTripResult.setText("Member " + memberEmail + ": Banned due to not having paid.");
            }

        }

    }

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
