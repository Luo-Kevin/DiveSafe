package ca.mcgill.ecse.divesafe.features;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.BundleController;
import ca.mcgill.ecse.divesafe.model.BundleItem;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Item;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteEquipmentBundleStepDefinitions {
  // Instance variables
private DiveSafe divesafe;
private String error;

/**
   * @author Zahra Landou
   */

  @Given("the following DiveSafe system exists: \\(p6)")
  public void the_following_dive_safe_system_exists_p6(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();

    Date aStartDate = (Date)Date.valueOf(rows.get(0).get("startDate"));
    int aNumDays = Integer.parseInt(rows.get(0).get("numDays"));
    int aPriceOfGuidePerDay = Integer.parseInt(rows.get(0).get("priceOfGuidePerDay"));

    DiveSafeApplication.setDiveSafe(aStartDate, aNumDays, aPriceOfGuidePerDay);
    divesafe = DiveSafeApplication.getDiveSafe();
    error = "";
   // throw new io.cucumber.java.PendingException();
  }

/**
 * 
 * @author Zahra Landou
 */

  @Given("the following equipment exists in the system: \\(p6)")
  public void the_following_equipment_exists_in_the_system_p6(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String,String>> rows = dataTable.asMaps();
    for (var row : rows) {

      String aName = row.get("name");
      int aWeight = Integer.parseInt(row.get("weight"));
      int aPricePerDay = Integer.parseInt(row.get("pricePerDay"));

      divesafe.addEquipment(aName, aWeight, aPricePerDay);

    }
    
  //  throw new io.cucumber.java.PendingException();
  }
   /**
    * 
    * @author Zahra Landou
    */

  @Given("the following equipment bundles exist in the system: \\(p6)")
  public void the_following_equipment_bundles_exist_in_the_system_p6(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();

  


    //throw new io.cucumber.java.PendingException();
  }
  /**
   * 
   * @author Zahra Landou
   */


  @Given("the following members exist in the system: \\(p6)")
  public void the_following_members_exist_in_the_system_p6(
      io.cucumber.datatable.DataTable dataTable) {
    
    List<Map<String, String>> rows = dataTable.asMaps();
    String aEmail = rows.get(0).get("email");
    String aPassword = rows.get(0).get("password");
    String aName = rows.get(0).get("name");
    String aEmergencyContact =  rows.get(0).get("emergencyContact");
    int aNumDays = Integer.parseInt(rows.get(0).get("numDays"));
    boolean aGuideRequired = Boolean.parseBoolean(rows.get(0).get("guideRequired"));
    boolean aHotelRequired = Boolean.parseBoolean(rows.get(0).get("hotelRequired"));
   //create a member
    divesafe.addMember(aEmail, aPassword, aName, aEmergencyContact, aNumDays, aGuideRequired, aHotelRequired);
    
    
    //arraylist with itemBooking
     String [] itemforBookingAsString = rows.get(0).get("itemBookings").split(",");
    List<Item> itemforBookings = new ArrayList<Item>();
    try {
      for (int i=0; i< itemforBookingAsString.length; i++){
        itemforBookings.add(Item.getWithName(itemforBookingAsString[i]));
      }
    }
    catch(Exception e){
    
  }
     

    //create an arraylist with quantity of each itemBooking
    String [] quantityString = rows.get(0).get("quantity").split(",");
    List<Integer> quantityList = new ArrayList<Integer>(); 
    try{

      for (int i= 0; i<quantityString.length;i++){
      Integer quantityAsInt = Integer.valueOf(quantityString[i]);
      quantityList.add(quantityAsInt);
      
      }
    }
      catch(NumberFormatException exception){

      }
      for(int i=0; i< itemforBookings.size();i++) {
        divesafe.getMember(0).addItemBooking(quantityList.get(i), divesafe, itemforBookings.get(i));
  
      }


  }
  /**
   * @author Eric Joung
   */
  @When("the administrator attempts to delete the equipment bundle {string} \\(p6)")
  public void the_administrator_attempts_to_delete_the_equipment_bundle_p6(String string) {
    // BundleController calls the deleteEquipmentBundle(string) method 
    // method returns void so simply call method
    BundleController.deleteEquipmentBundle(string);
  }

  /**
   * 
   * @author Eric Joung
   */
  @Then("the number of equipment bundles in the system shall be {string} \\(p6)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p6(String string) {
    Integer numberOfBundles = divesafe.getBundles().size();
    String numberOfBundlesSTR = numberOfBundles.toString();
    assertEquals(numberOfBundlesSTR, string);
  }

  /**
   * 
   * @author Eric Joung
   */
  @Then("the equipment bundle {string} shall not exist in the system \\(p6)")
  public void the_equipment_bundle_shall_not_exist_in_the_system_p6(String string) {
    // IDK if this works --> Item.hasWithName is a static method
    boolean doesEquipmentBundleSTRexist = EquipmentBundle.hasWithName(string); 
    assertFalse(doesEquipmentBundleSTRexist);
    }
     
  

  @Then("the equipment bundle {string} shall preserve the following properties: \\(p6)")
  public void the_equipment_bundle_shall_preserve_the_following_properties_p6(String string,
      io.cucumber.datatable.DataTable dataTable) {
        EquipmentBundle equipmentBundle = (EquipmentBundle) EquipmentBundle.getWithName(string);

      List<Map<String, String>> rows = dataTable.asMaps();
      for(Map<String, String> rowOfEquipmentBundle : rows) {
        if(rowOfEquipmentBundle.get("name").equals(string)) {
          // properties of bundle
          //discount
          int discount = Integer.parseInt(rowOfEquipmentBundle.get("discount"));
          // items
          String itemsAsSTR = rowOfEquipmentBundle.get("items");
          String[] itemsAsArray = itemsAsSTR.split(",");
          List<BundleItem> bundleItemList = new ArrayList<BundleItem>();
          for(String item : itemsAsArray) {
            Equipment aEquipment = (Equipment) Equipment.getWithName(item);
            BundleItem equipmentAsBundleItem = 
            bundleItemList.add
            
          }
          // quantities
          String quantitiesAsSTR = rowOfEquipmentBundle.get("quantities");
          String[] quantitiesAsArray = quantitiesAsSTR.split(",");
          List<Integer> quantitiesList = new ArrayList<Integer>();
          for(String quantity : quantitiesAsArray) {
            Integer quantityAsInt = Integer.valueOf(quantity);
            quantitiesList.add(quantityAsInt);
          }
          // Assert equals test for properties
          assertEquals(equipmentBundle.getDiscount(), discount);
          assertEquals(equipmentBundle.getBundleItems());
          assertEquals(equipmentBundle.getBundleItems(), actual);

        }
      } 

  }

  @Then("the member {string} shall have the following bookable items: \\(p6)")
  public void the_member_shall_have_the_following_bookable_items_p6(String string,
      io.cucumber.datatable.DataTable dataTable) {
   

    //throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of equipment in the system shall be {string} \\(p6)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p6(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
