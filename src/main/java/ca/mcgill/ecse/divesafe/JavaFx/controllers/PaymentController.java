package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import java.io.IOException;
import java.util.List;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.ItemBooking;
import ca.mcgill.ecse.divesafe.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
  void retrieveBillClick(MouseEvent event) {
    String userEmail = email.getText().strip();

    //TODO: Check email exist
    if(!Member.hasWithEmail(userEmail)){
      System.out.println("Email does not exist");
    }


    //TODO: Check Password
    else{
      Member member = Member.getWithEmail(userEmail);
      String inputPassword = password.getText();
      String userPassword = member.getPassword();

      if(inputPassword.equals(userPassword)){
        //TODO Retrieve Bill
        List<ItemBooking> userBooking = member.getItemBookings();
        for(ItemBooking itemBooked: userBooking){
          System.out.println(itemBooked);

        }
        
      }


    
    }

   

  }

  @FXML
  void makePayment(MouseEvent event) {
    System.out.println("hello");

  }

  @FXML
  void swtichToAssignment(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("../pages/InitiateAndViewAssignmentPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
