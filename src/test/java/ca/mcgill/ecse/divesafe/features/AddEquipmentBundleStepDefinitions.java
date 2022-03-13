package ca.mcgill.ecse.divesafe.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.*;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.BundleController;
import ca.mcgill.ecse.divesafe.model.BundleItem;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;

public class AddEquipmentBundleStepDefinitions {

  // Instance Variables
  private DiveSafe divesafe;
  private String error;

  /**
   * @author Siger Ma
   */

  @Given("the following DiveSafe system exists: \\(p2)")
  public void the_following_dive_safe_system_exists_p2(io.cucumber.datatable.DataTable dataTable) {

    // Get inputs from the data table
    List<Map<String, String>> rows = dataTable.asMaps();

    Date aStartDate = (Date) Date.valueOf(rows.get(0).get("startDate"));
    int aNumDays = Integer.parseInt(rows.get(0).get("numDays"));
    int aPriceOfGuidePerDay = Integer.parseInt(rows.get(0).get("priceOfGuidePerDay"));

    // Create instance of Dive Safe in the application
    DiveSafeApplication.setDiveSafe(aStartDate, aNumDays, aPriceOfGuidePerDay);
    divesafe = DiveSafeApplication.getDiveSafe();

    // Initialize error message
    error = "";

  }

  /**
   * @author Siger Ma
   */

  @Given("the following equipment exists in the system: \\(p2)")
  public void the_following_equipment_exists_in_the_system_p2(io.cucumber.datatable.DataTable dataTable) {

    // Get inputs from the data table
    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      String aName = row.get("name");
      int aWeight = Integer.parseInt(row.get("weight"));
      int aPricePerDay = Integer.parseInt(row.get("pricePerDay"));

      // Create the equipment instances
      divesafe.addEquipment(aName, aWeight, aPricePerDay);

    }

  }

  /**
   * @author Eric Joung
   */

  @When("the administrator attempts to add an equipment bundle with name {string}, discount {string}, items {string}, and quantities {string} \\(p2)")
  public void the_administrator_attempts_to_add_an_equipment_bundle_with_name_discount_items_and_quantities_p2(
      String bundleName, String bundleDiscount, String bundleItem, String itemQuantities) {

    // Convert input of @Given into acceptable inputs of addEquipmentBundle
    List<String> itemList = new ArrayList<String>(Arrays.asList(bundleItem.split(",")));
    String[] quantitiesAsArray = itemQuantities.split(",");
    List<Integer> quantitiesList = new ArrayList<Integer>();

    // Catch Number Format Exception for empty input of itemQuantities
    try {
      for (int i = 0; i < quantitiesAsArray.length; i++) {
        Integer quantitiesAsInt = Integer.valueOf(quantitiesAsArray[i]);
        quantitiesList.add(quantitiesAsInt);
      }
    } catch (NumberFormatException ex) {

    }

    // Create equipment bundle instances and update error message
    error = BundleController.addEquipmentBundle(bundleName, Integer.parseInt(bundleDiscount), itemList, quantitiesList);

  }

  /**
   * @author Kevin Luo
   */

  @Then("the number of equipment bundles in the system shall be {string} \\(p2)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p2(String numberOfBundles) {
    assertEquals(Integer.parseInt(numberOfBundles), divesafe.numberOfBundles());
  }

  /**
   * @author Jiahao Zhao
   */

  @Then("the equipment bundle {string} shall exist in the system \\(p2)")
  public void the_equipment_bundle_shall_exist_in_the_system_p2(String bundleName) {
    assertTrue(EquipmentBundle.hasWithName(bundleName));
  }

  /**
   * @author Kevin Luo, Siger Ma
   */

  @Then("the equipment bundle {string} shall contain the items {string} with quantities {string} \\(p2)")
  public void the_equipment_bundle_shall_contain_the_items_with_quantities_p2(String bundleName, String bundleItems,
      String equipmentQuantities) {

    // Get the information of the existing budle that was created
    EquipmentBundle actualBundle = (EquipmentBundle) EquipmentBundle.getWithName(bundleName);
    List<BundleItem> actualBundleItemsList = actualBundle.getBundleItems();

    String actualBundleItems = "";
    String actualEquipmentQuantities = "";

    // Put the bundle items and their quantities in a string to compare
    for (int i = 0; i < actualBundleItemsList.size(); i++) {

      actualBundleItems = actualBundleItems + actualBundleItemsList.get(i).getEquipment().getName();
      actualEquipmentQuantities = actualEquipmentQuantities
          + String.valueOf(actualBundleItemsList.get(i).getQuantity());

      if (i < actualBundleItemsList.size() - 1) {

        actualBundleItems = actualBundleItems + ",";
        actualEquipmentQuantities = actualEquipmentQuantities + ",";

      }

    }

    assertEquals(bundleItems, actualBundleItems);
    assertEquals(equipmentQuantities, actualEquipmentQuantities);

  }

  /**
   * @author Jiahao Zhao
   */

  @Then("the equipment bundle {string} shall have a discount of {string} \\(p2)")
  public void the_equipment_bundle_shall_have_a_discount_of_p2(String bundleName, String bundleDiscount) {
    EquipmentBundle bundle = (EquipmentBundle) EquipmentBundle.getWithName(bundleName);
    assertEquals(Integer.parseInt(bundleDiscount), bundle.getDiscount());
  }

  /**
   * @author Jiahao Zhao
   */

  @Then("the error {string} shall be raised \\(p2)")
  public void the_error_shall_be_raised_p2(String errorMsg) {
    assertEquals(errorMsg, error);
  }

  /**
   * @author Siger Ma
   */

  @Given("the following equipment bundles exist in the system: \\(p2)")
  public void the_following_equipment_bundles_exist_in_the_system_p2(io.cucumber.datatable.DataTable dataTable) {

    // Get variables from data table
    List<Map<String, String>> rows = dataTable.asMaps();

    String aName = rows.get(0).get("name");
    int aDiscount = Integer.parseInt(rows.get(0).get("discount"));
    String items = rows.get(0).get("items");
    String quantities = rows.get(0).get("quantities");

    List<String> equipmentList = new ArrayList<String>(Arrays.asList(items.split(",")));
    String[] quantityArray = quantities.split(",");
    List<Integer> quantityList = new ArrayList<Integer>();

    for (int i = 0; i < quantityArray.length; i++) {
      Integer aQuantity = Integer.valueOf(quantityArray[i]);
      quantityList.add(aQuantity);
    }

    // Create bundle
    BundleController.addEquipmentBundle(aName, aDiscount, equipmentList, quantityList);

  }

  /**
   * @author Siger Ma
   */

  @After
  public void tearDown() {
    divesafe.delete();
  }

}
