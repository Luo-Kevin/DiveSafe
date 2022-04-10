
package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import ca.mcgill.ecse.divesafe.controller.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for page where we initiate and view assignments.
 * 
 * @author Siger Ma
 */
public class InitiateAndViewAssignmentPageController implements Initializable {

  private Stage stage;
  private Scene scene;
  private Parent root;
  private String error = "";
  private String memberEmail = "";

  // Button to initiate assignment
  @FXML
  private Button initiateButton;

  // Button to view assignment
  @FXML
  private Button assignmentButton;

  // Button to register members
  @FXML
  private Button memberButton;

  // Button to confirm payment
  @FXML
  private Button paymentButton;

  // Button to reset app to initial state
  @FXML
  private Button resetButton;

  // Button to manage trips
  @FXML
  private Button tripButton;

  // List of assigned members
  @FXML
  private ListView<String> listAssignedMembers;

  // List of unassigned members
  @FXML
  private ListView<String> listUnassignedMembers;

  // Deetailed information about the assignments
  @FXML
  private TreeView<String> treeAssignmentDetails;

  // Label to display error message
  @FXML
  private Label errorMessage;

  /**
   * Initialize the controller class
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    memberEmail = "";
    error = "";
    updateLists();
    setTreeItem();
    errorMessage.setText("DiveSafe");
    errorMessage.setTextFill(Color.web("#0076a3"));
  }

  /**
   * Action of the button initiateButton to initiate the assignments
   * 
   * @param event - mouse click
   */
  @FXML
  void startInitiate(MouseEvent event) {
    error = AssignmentController.initiateAssignment();
    updateLists();
    setTreeItem();
    if (error != "") {
      errorMessage.setText(error);
      errorMessage.setTextFill(Color.RED);
    } else {
      errorMessage.setText("DiveSafe");
      errorMessage.setTextFill(Color.web("#0076a3"));
    }
  }

  /**
   * Create items in the tree treeAssignmentDetails
   */
  @FXML
  public void setTreeItem() {

    TreeItem<String> root = null;

    if (memberEmail != "") {
      List<String> assignmentDetail = AssignmentController.getAssignmentDetails(memberEmail);

      String contentOne = assignmentDetail.get(0);
      String contentTwo = assignmentDetail.get(1);
      String contentThree = assignmentDetail.get(2);
      String contentFour = "";
      for (int i = 3; i < assignmentDetail.size(); i++) {
        contentFour += assignmentDetail.get(i) + "\n";
      }

      root = new TreeItem<>(memberEmail);

      TreeItem<String> itemOne = new TreeItem<>("Start Date");
      TreeItem<String> itemTwo = new TreeItem<>("End Date");
      TreeItem<String> itemThree = new TreeItem<>("Guide");
      TreeItem<String> itemFour = new TreeItem<>("Item Booking");

      TreeItem<String> itemOneContent = new TreeItem<>(contentOne);
      TreeItem<String> itemTwoContent = new TreeItem<>(contentTwo);
      TreeItem<String> itemThreeContent = new TreeItem<>(contentThree);
      TreeItem<String> itemFourContent = new TreeItem<>(contentFour.toString());

      itemOne.getChildren().add(itemOneContent);
      itemTwo.getChildren().add(itemTwoContent);
      itemThree.getChildren().add(itemThreeContent);
      itemFour.getChildren().add(itemFourContent);

      root.getChildren().addAll(itemOne, itemTwo, itemThree, itemFour);
    }

    treeAssignmentDetails.setRoot(root);
  }

  /**
   * Method to initiate the treeAssignmentDetails tree and make it interactive
   */
  @FXML
  public void selectTreeBranch() {
    treeAssignmentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    });
  }

  /**
   * Method to get the tree with the details of the assignments corresponding to
   * the selected member in the list listAssignedMembers
   */
  @FXML
  void getDetails(MouseEvent event) {
    memberEmail = listAssignedMembers.getSelectionModel().getSelectedItem();
    setTreeItem();
  }

  /**
   * Reset the assignments
   */
  @FXML
  void resetApp(MouseEvent event) {
    DiveSafeApplication.reset();
    memberEmail = "";
    error = "";
    updateLists();
    setTreeItem();
  }

  /**
   * Method to update the lists of assigned and unassigned members
   */
  @FXML
  private void updateLists() {
    listAssignedMembers.getItems().clear();
    listUnassignedMembers.getItems().clear();
    List<String> listOfAssignedMembers = MemberController.getAssignedMembers();
    for (String member : listOfAssignedMembers) {
      listAssignedMembers.getItems().add(member);
    }
    List<String> listOfUnassignedMembers = MemberController.getUnassignedMembers();
    for (String member : listOfUnassignedMembers) {
      listUnassignedMembers.getItems().add(member);
    }
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


}