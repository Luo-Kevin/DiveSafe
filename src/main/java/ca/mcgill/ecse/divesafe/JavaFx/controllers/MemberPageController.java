package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ca.mcgill.ecse.divesafe.controller.BundleController;
import ca.mcgill.ecse.divesafe.controller.MemberController;
import ca.mcgill.ecse.divesafe.JavaFx.DiveSafeFxmlView;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * View controller for MemberPage
 * 
 * @author Zahra Landou
 */

public class MemberPageController implements Initializable {

  private Stage stage;
  private Scene scene;
  private Parent root;
  private String error = "";

  @FXML
  private Button addBundleUpdate;

  @FXML
  private Button addItemUpdate;

  @FXML
  private Button addRegisterItem;

  @FXML
  private Button addRegisterBundle;

  
    @FXML
    private ComboBox<String> registerItemBox;

    @FXML
    private ComboBox<String> registerBundleBox = new ComboBox<String>();
    @FXML
    private ComboBox<String> updateItemBox = new ComboBox<String>();
    @FXML
    private ComboBox<String> updateBundleBox = new ComboBox<String>();

  @FXML
  private Button assignmentButton;

  @FXML
  private Button paymentButton;

  @FXML
  private Button tripButton;

  @FXML
  private Button deleteMemberButton;

  @FXML
  private CheckBox guideRequiredRegister;

  @FXML
  private CheckBox guideRequiredUpdate;

  @FXML
  private CheckBox hotelRequiredRegister;

  @FXML
  private CheckBox hotelRequiredUpdate;

  @FXML
  private Button memberButton;

  @FXML
  private Button registerButton;

  @FXML
  private Button resetButton;

  @FXML
  private Button updateMemberButton;

  @FXML
  private TextField memberName;

  @FXML
  private TextField memberPassword;

  @FXML
  private TextField memberContact;

  @FXML
  private TextField memberEmail;

  @FXML
  private TextField memberNumDays;

  @FXML
  private TextField newMemberName;

  @FXML
  private TextField newMemberPassword;

  @FXML
  private TextField newMemberContact;

  @FXML
  private TextField newMemberEmail;

  @FXML
  private TextField currentEmail;

  @FXML
  private TextField newMemberNumDays;

  @FXML
  private TextField itemRegisterQuantity;

  @FXML
  private TextField bundleRegisterQuantity;

  @FXML
  private TextField itemUpdateQuantity;

  @FXML
  private TextField bundleUpdateQuantity;

 /* @FXML
  private TextField updateItemList;

  @FXML
  private TextField updateBundleList;*/

  //@FXML
 // private TextField addItemNameList;

 //@FXML
 // private TextField addBundleNameList;

  @FXML
  private Label errorMessage;

  private List<String> newItemNames = new ArrayList<String>();

  private List<Integer> newItemQuantities = new ArrayList<Integer>();

  private List<String> itemNames = new ArrayList<String>();

  private List<Integer> itemQuantities = new ArrayList<Integer>();

  /**
   * Reset member
   * 
   * @param event
   */

  @FXML
  void resetApp(MouseEvent event) {
    DiveSafeApplication.reset();
  }

  /**
   * register Member
   * 
   * @param event
   */

  @FXML
  void registerMember(MouseEvent event) {

    String name = memberName.getText();
    String password = memberPassword.getText();
    String email = memberEmail.getText();
    String contact = memberContact.getText();
    int numDays;
    try {
      numDays = Integer.parseInt(memberNumDays.getText());
    } catch (Exception e) {
      return;
    }

    boolean wantGuide = guideRequiredRegister.isSelected();
    boolean wantHotel = hotelRequiredRegister.isSelected();

    error = MemberController.registerMember(email, password, name, contact, numDays, wantGuide, wantHotel, itemNames,
        itemQuantities);

    itemNames.clear();
    itemQuantities.clear();
    guideRequiredRegister.setSelected(false);
    hotelRequiredRegister.setSelected(false);
    memberNumDays.setText("");
    memberContact.setText("");
    memberName.setText("");
    memberEmail.setText("");
    memberPassword.setText("");
    if (error != "") {
      errorMessage.setText(error);
      errorMessage.setTextFill(Color.RED);
    } else {
      errorMessage.setText("DiveSafe");
      errorMessage.setTextFill(Color.web("#0076a3"));
    }
    DiveSafeFxmlView.getInstance().refresh();
  }

  @FXML
  void updateMember(MouseEvent event) {

    String newName = newMemberName.getText();
    String newPassword = newMemberPassword.getText();
    String Email = newMemberEmail.getText();
    String newContact = newMemberContact.getText();
    int newNumDays;
    try {
      newNumDays = Integer.parseInt(newMemberNumDays.getText());
    } catch (Exception e) {
      return;
    }
    boolean wantGuide = guideRequiredUpdate.isSelected();
    boolean wantHotel = hotelRequiredUpdate.isSelected();

    error = MemberController.updateMember(Email, newPassword, newName, newContact, newNumDays, wantGuide, wantHotel,
        newItemNames, newItemQuantities);

    newItemNames.clear();
    newItemQuantities.clear();
    hotelRequiredUpdate.setSelected(false);
    guideRequiredUpdate.setSelected(false);
    newMemberName.setText("");
    newMemberPassword.setText("");
    newMemberEmail.setText("");
    newMemberContact.setText("");
    newMemberNumDays.setText("");
    if (error != "") {
      errorMessage.setText(error);
      errorMessage.setTextFill(Color.RED);
    } else {
      errorMessage.setText("DiveSafe");
      errorMessage.setTextFill(Color.web("#0076a3"));
    }
    DiveSafeFxmlView.getInstance().refresh(); // check

    
  }

  /**
   * Method to delete member
   * 
   * @param event
   */

  @FXML
  void deleteMember(MouseEvent event) {

    String userEmail = currentEmail.getText();
    MemberController.deleteMember(userEmail);

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    errorMessage.setText("DiveSafe");
    errorMessage.setTextFill(Color.web("#0076a3"));
    registerItemBox.addEventHandler(DiveSafeFxmlView.REFRESH_EVENT, e -> { registerItemBox.setItems(BundleController.getItems()); registerItemBox.setValue(null);});
    registerBundleBox.addEventHandler(DiveSafeFxmlView.REFRESH_EVENT, e -> { registerBundleBox.setItems(BundleController.getBundles());  registerBundleBox.setValue(null);});
    updateItemBox.addEventHandler(DiveSafeFxmlView.REFRESH_EVENT, e ->{updateItemBox.setItems(BundleController.getItems()); updateItemBox.setValue(null);});
    updateBundleBox.addEventHandler(DiveSafeFxmlView.REFRESH_EVENT, e ->{ updateBundleBox.setItems(BundleController.getBundles());updateBundleBox.setValue(null);});
  }

  /**
   * Action of the button Add Item on register Page
   * 
   * @param event
   */

  @FXML
  void registerItem(MouseEvent event) {

    int itemQuantity = 0;
    try {
      itemQuantity = Integer.parseInt(itemRegisterQuantity.getText());
    } catch (NumberFormatException e) {
      return;
    }
    itemQuantities.add(itemQuantity);

    String itemName = registerItemBox.getValue();
    itemNames.add(itemName);
    itemRegisterQuantity.setText("");
    registerItemBox.setValue(null);

  }

  /**
   * 
   * @param event
   */

  @FXML
  void updateItem(MouseEvent event) {

    int itemQuantity = 0;

    try {
      itemQuantity = Integer.parseInt(itemUpdateQuantity.getText());
    } catch (NumberFormatException e) {
      // TODO: handle exception
    }
    newItemQuantities.add(itemQuantity);
    String itemName = updateItemBox.getValue();

    newItemNames.add(itemName);
    itemUpdateQuantity.setText("");
    updateItemBox.setValue(null);

  }

  /**
   * Action of the button Add Bundle on register page
   * 
   * @param event
   */

  @FXML
  void registerBundle(MouseEvent event) {

    int bundleQuantity = 0;
    try {
      bundleQuantity = Integer.parseInt(bundleRegisterQuantity.getText());
    } catch (NumberFormatException e) {
      return;
    }
    itemQuantities.add(bundleQuantity);

    String bundleName = registerBundleBox.getValue();
    itemNames.add(bundleName);

    bundleRegisterQuantity.setText("");
   registerBundleBox.setValue(null);
  }

  /**
   * Action of the button Add Bundle on update page
   * 
   * @param event
   */

  @FXML
  void updateBundle(MouseEvent event) {

    String bundleName = updateBundleBox.getValue();
    int bundleQuantity = 0;

    try {
      bundleQuantity = Integer.parseInt(bundleUpdateQuantity.getText());
    } catch (NumberFormatException e) {
      // TODO: handle exception
    }
    newItemQuantities.add(bundleQuantity);
    newItemNames.add(bundleName);

    bundleUpdateQuantity.setText("");
    updateBundleBox.setValue(null);

  }

    /**
   * Method to switch to assignment page
   */

  @FXML
  void switchToAssignment(MouseEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("../pages/InitiateAndViewAssignmentPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Method to switch to payment page
   */

  @FXML
  void switchToPayment(MouseEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("../pages/PaymentPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

  }

  /**
   * Method to switch to trip page
   */

  @FXML
  void switchToTrip(MouseEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("../pages/TripPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
