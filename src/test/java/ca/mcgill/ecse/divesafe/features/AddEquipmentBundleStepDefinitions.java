package ca.mcgill.ecse.divesafe.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.*;

import org.checkerframework.checker.units.qual.C;

import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Item;

public class AddEquipmentBundleStepDefinitions {

  // Instance Variables
  private DiveSafe divesafe;
  
  /**
   * @author Siger Ma
   */
  @Given("the following DiveSafe system exists: \\(p2)")
  public void the_following_dive_safe_system_exists_p2 (io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    
    Date aStartDate = (Date) Date.valueOf(rows.get(0).get("startDate"));
    int aNumDays = Integer.parseInt(rows.get(0).get("numDays"));
    int aPriceOfGuidePerDay = Integer.parseInt(rows.get(0).get("priceOfGuidePerDay"));

    divesafe = new DiveSafe(aStartDate, aNumDays, aPriceOfGuidePerDay);
    
  }

  /**
   * @author Siger Ma
   */
  @Given("the following equipment exists in the system: \\(p2)")
  public void the_following_equipment_exists_in_the_system_p2 (io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row:rows) {
      
      String aName = row.get("name");
      int aWeight = Integer.parseInt(row.get("weight"));
      int aPricePerDay = Integer.parseInt(row.get("pricePerDay"));

      divesafe.addEquipment(aName, aWeight, aPricePerDay);

    }

  }

  @When("the administrator attempts to add an equipment bundle with name {string}, discount {string}, items {string}, and quantities {string} \\(p2)")
  public void the_administrator_attempts_to_add_an_equipment_bundle_with_name_discount_items_and_quantities_p2(
      String string, String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of equipment bundles in the system shall be {string} \\(p2)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall exist in the system \\(p2)")
  public void the_equipment_bundle_shall_exist_in_the_system_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall contain the items {string} with quantities {string} \\(p2)")
  public void the_equipment_bundle_shall_contain_the_items_with_quantities_p2(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall have a discount of {string} \\(p2)")
  public void the_equipment_bundle_shall_have_a_discount_of_p2(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised \\(p2)")
  public void the_error_shall_be_raised_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Siger Ma
   */
  @Given("the following equipment bundles exist in the system: \\(p2)")
  public void the_following_equipment_bundles_exist_in_the_system_p2 (io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    
    // Create bundle

    String aName = rows.get(0).get("name");
    int aDiscount = Integer.parseInt(rows.get(0).get("discount"));
    
    EquipmentBundle aBundle = divesafe.addBundle(aName, aDiscount);

    // Fill bundle

    String Items = rows.get(0).get("items");
    String Quantities = rows.get(0).get("quantities");
    String[] EquipmentArrayToAdd = Items.split(",");
    String[] QuantityArray = Quantities.split(",");

    for (int i = 0; i < EquipmentArrayToAdd.length; i++) {

      Equipment aEquipment = (Equipment) Item.getWithName(EquipmentArrayToAdd[i]);
      int aQuantity = Integer.parseInt(QuantityArray[i]);

      divesafe.addBundleItem(aQuantity, aBundle, aEquipment);

    }

  }

  /**
   * @author Siger Ma
   */
  @After
  public void tearDown() {
  divesafe.delete();
  }
  
}
