
package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import ca.mcgill.ecse.divesafe.controller.GuideController;
import ca.mcgill.ecse.divesafe.controller.TOAssignment;
import ca.mcgill.ecse.divesafe.model.Assignment;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Member;
import ca.mcgill.ecse.divesafe.model.Guide.AvailableStatus;
import ca.mcgill.ecse.divesafe.model.Member.MemberStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Controller for page where we initiate and view assignments.
 * 
 * @author Siger Ma
 */
public class InitiateAndViewAssignmentPageController implements Initializable {

  // Button to initiate assignment
  @FXML
  private Button initiateButton;

  // List of assigned members
  @FXML
  private ListView<String> listAssignedMembers;

  // Deetailed information about the assignments
  @FXML
  private TreeView<String> treeAssignmentDetails;

  @FXML
  private Font x1;

  @FXML
  private Font x3;

  @FXML
  private Color x4;

  /**
   * Initialize the controller class
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<TOAssignment> listOfAssignments = AssignmentController.getAssignments();
    for (TOAssignment assignment : listOfAssignments) {
      listAssignedMembers.getItems().add(assignment.getMemberName());
    }
    setTreeItem();
  }

  /**
   * Action of the button initiateButton to initiate the assignments
   * 
   * @param event - mouse click
   */
  @FXML
  void startInitiate(ActionEvent event) {
    AssignmentController.initiateAssignment();
    List<TOAssignment> listOfAssignments = AssignmentController.getAssignments();
    for (TOAssignment assignment : listOfAssignments) {
      listAssignedMembers.getItems().add(assignment.getMemberName());
    }
  }

  /**
   * Create items in the tree treeAssignmentDetails
   */
  @FXML
  public void setTreeItem() {

    String contentOne = "";
    String contentTwo = "";
    String contentThree = "";

    TreeItem<String> root = new TreeItem<>("Details");

    TreeItem<String> itemOne = new TreeItem<>("Start Date");
    TreeItem<String> itemTwo = new TreeItem<>("End Date");
    TreeItem<String> itemThree = new TreeItem<>("Guide");

    TreeItem<String> itemOneContent = new TreeItem<>(contentOne);
    TreeItem<String> itemTwoContent = new TreeItem<>(contentTwo);
    TreeItem<String> itemThreeContent = new TreeItem<>(contentThree);

    itemOne.getChildren().add(itemOneContent);
    itemTwo.getChildren().add(itemTwoContent);
    itemThree.getChildren().add(itemThreeContent);

    root.getChildren().addAll(itemOne, itemTwo, itemThree);
    treeAssignmentDetails.setRoot(root);
  }

  /**
   * Method to initiate the treeAssignmentDetails tree and make it interactive
   */
  @FXML
  public void selectTreeBranch() {
    treeAssignmentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        if (newValue.getValue().equals("Start Date")) {
          System.out.println("Start Date");
        } else if (newValue.getValue().equals("End Date")) {
          System.out.println("End Date");
        } else if (newValue.getValue().equals("Guide")) {
          System.out.println("Guide");
        }
      }
    });
  }

  /**
   * Method to get the tree with the details of the assignments corresponding to
   * the selected member in the list listAssignedMembers
   */
  @FXML
  void getDetails(ContextMenuEvent event) {

  }

}