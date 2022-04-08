package ca.mcgill.ecse.divesafe.JavaFx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.HexFormat;
import ca.mcgill.ecse.divesafe.model.Member;




public class PaymentController {
  @FXML
  private TextField authorizationCode;

  @FXML
  private TextField password;

  @FXML
  private TextField email;

  @FXML
  private Text resultText;


  public void retrieveBillClick(MouseEvent mouseEvent) throws IOException {
    String userEmail = email.getText();
    String userPassword = password.getText();

    if (!Member.hasWithEmail(userEmail)) {
      resultText.setText(String.format("Member with email address %s does not exist", userEmail));
    }

    else{
      Member member = Member.getWithEmail(userEmail);
      String checkPassword = member.getPassword();


      if(!(checkPassword.equals(userPassword))){
        resultText.setText("Wrong password");
      }
      else{
        var equip = member.getItemBookings();

        for(var q: equip){
          System.out.println(q.getItem().getName());
        }
      }
    }



  }
}
