package ca.mcgill.ecse.divesafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.*;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.AssignmentController;
import ca.mcgill.ecse.divesafe.controller.BundleController;
import ca.mcgill.ecse.divesafe.model.Assignment;
import ca.mcgill.ecse.divesafe.model.BundleItem;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Item;
import ca.mcgill.ecse.divesafe.model.Member;
import ca.mcgill.ecse.divesafe.model.User;
import ca.mcgill.ecse.divesafe.model.Member.MemberStatus;



public class AssignmentFeatureStepDefinitions {

  private DiveSafe diveSafe;
  private String error;

  /**
   * Making sure that the specified DiveSafe system exists, i.e. start date, number of days
   * and price of guide per day match.
   * @author Jiahao Zhao
   * @param dataTable Table that contains inputs specified in the feature files
   */

  @Given("the following DiveSafe system exists:")
  public void the_following_dive_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    // Initialize new diveSafe system
    diveSafe = DiveSafeApplication.getDiveSafe();

    // initial error message empty
    error = "";
    
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {

      // Extracting the components of the table
      Date startDate = Date.valueOf(row.get("startDate"));
      int numDays = Integer.parseInt(row.get("numDays"));
      int priceOfGuidePerDay = Integer.parseInt(row.get("priceOfGuidePerDay"));

      diveSafe.setNumDays(numDays);
      diveSafe.setStartDate(startDate);
      diveSafe.setPriceOfGuidePerDay(priceOfGuidePerDay);
    }

  }

  /**
   * Add the pieces of equipment that should be present in the system before evaluating @When clause
   *
   * @author Jiahao Zhao
   * @param dataTable Table that contains inputs specified in the feature files
   */

  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

      //Extract inputs from table, then adding equipment into diveSafe

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name");
      int weight = Integer.parseInt(row.get("weight"));
      int pricePerDay = Integer.parseInt(row.get("pricePerDay"));
      diveSafe.addEquipment(name, weight, pricePerDay);
    }
  }

  /**
   * Add the bundles that should exist in the system before evaluating @When clause
   *
   * @author Jiahao Zhao
   * @param dataTable Table that contains inputs specified in the feature files
   */

  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      // Extract inputs from table, then adding equipment bundles into diveSafe
      String name = row.get("name");
      int discount = Integer.parseInt(row.get("discount"));
      String[] items = row.get("items").split(",");
      String[] quantity = row.get("quantity").split(",");

      // Create the Bundle
      EquipmentBundle bundle = new EquipmentBundle(name, discount, diveSafe);

      // Fill the bundle
      for (int i = 0; i < quantity.length; i++) {
        var bundleItem = new BundleItem(Integer.parseInt(quantity[i]), diveSafe, bundle,
            (Equipment) Item.getWithName(items[i]));
        bundle.addBundleItem(bundleItem);
      }

      // Add bundle to system
      diveSafe.addBundle(bundle);

    }
  }

  /**
   * Add the guides that should exist in the system before evaluating @When clause
   *
   * @author Jiahao Zhao
   * @param dataTable Table that contains inputs specified in the feature files
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    //extract information about guides from table

    for (var row : rows) {
      String guideEmail = row.get("email");
      String guidePassword = row.get("password");
      String guideName = row.get("name");
      String guideEmergencyContract = row.get("emergencyContact");
      
      // add guides to divesafe
      diveSafe.addGuide(guideEmail, guidePassword, guideName, guideEmergencyContract);
    }
  }

  /**
   * Add members that should exist in diveSafe prior to evaluating the @When clause
   *
   * @author Jiahao Zhao
   * @param dataTable
   */

  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String,String>> rows = dataTable.asMaps();

    // extract member information from datatable

    for(var row : rows) {
      String memberEmail = row.get("email");
      String memberPassword = row.get("password");
      String memberName = row.get("name");
      String memberEmergencyContact = row.get("emergencyContact");
      int memberNumDays = Integer.parseInt(row.get("numDays"));
      boolean memberGuideRequired = Boolean.parseBoolean(row.get("guideRequired"));
      boolean memberHotelRequired = Boolean.parseBoolean(row.get("hotelRequired"));

      String[] memberItemBookings = row.get("itemBookings").split(",");
      String[] itemBookingQuantity = row.get("itemBookingQuantities").split(",");
      // Create member
      Member aMember = new Member(memberEmail, memberPassword, memberName, memberEmergencyContact, memberNumDays, memberGuideRequired, memberHotelRequired, diveSafe);
      diveSafe.addMember(aMember);
      //add member's item booking
      for (int i = 0; i < itemBookingQuantity.length; i++) {
        aMember.addItemBooking(Integer.parseInt(itemBookingQuantity[i]), diveSafe, Item.getWithName(memberItemBookings[i]));
      }
    }
  }

  // @author Siger Ma
  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    error = AssignmentController.initiateAssignment();
  }

  // @author Siger Ma
  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    
    // Extracting the information from the data table
    List <Map<String, String>> rows = dataTable.asMaps();
    List <String> memberEmailList = new ArrayList <String>();
    List <String> guideEmailList = new ArrayList <String>();
    List <Integer> startDayList = new ArrayList <Integer>();
    List <Integer> endDayList = new ArrayList <Integer>();
    for (var row : rows) {
      String memberEmail = row.get("memberEmail");
      String guideEmail = row.get("guideEmail");
      Integer startDay = Integer.valueOf(row.get("startDay"));
      Integer endDay = Integer.valueOf(row.get("endDay"));
      memberEmailList.add(memberEmail);
      guideEmailList.add(guideEmail);
      startDayList.add(startDay);
      endDayList.add(endDay);
    }

    // Get assignments
    List <Assignment> currentAssignmentList = diveSafe.getAssignments();

    // Check correct assignments
    for (int i = 0; i < memberEmailList.size(); i++) {
      Assignment currentAssignment = currentAssignmentList.get(i);
      assertEquals(Member.getWithEmail(memberEmailList.get(i)), currentAssignment.getMember());
      assertEquals(Guide.getWithEmail(guideEmailList.get(i)), currentAssignment.getGuide());
      assertEquals(startDayList.get(i), currentAssignment.getStartDay());
      assertEquals(endDayList.get(i), currentAssignment.getEndDay());
    }
  }

  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String email, String state) {
    Member member = Member.getWithEmail(email);
    assertEquals(state, member.getMemberStatusFullName());
  }

  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String numOfAssignments) {
    int currentNumOfAssignments = diveSafe.numberOfAssignments();
    assertEquals(Integer.parseInt(numOfAssignments), currentNumOfAssignments);
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String expectedError) {
    assertEquals(expectedError, error);
  }

  /**
   * Add assignments that should exist in the system prior to evaluating @When clause
   *
   * @author Jiahao Zhao
   * @param dataTable
   */

  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

        // Extract all information from dataTable

        List<Map<String, String>> rows = dataTable.asMaps();
        for(var row : rows) { 
          String memberEmail = row.get("memberEmail");
          String guideEmail = row.get("guideEmail");
          int startDay = Integer.parseInt(row.get("startDay"));
          int endDay = Integer.parseInt(row.get("endDay"));
          Member assignedMember = (Member) User.getWithEmail(memberEmail);
          Guide assignedGuide = (Guide) User.getWithEmail(guideEmail);
          
          //Creating new Assignment and adding start and end day, member along with their assigned guide
          Assignment newAssignment = diveSafe.addAssignment(startDay, endDay, assignedMember);
          newAssignment.setGuide(assignedGuide);
        }
  }

  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String userEmail, String authorizationCode) {
        error = AssignmentController.confirmPayment(userEmail, authorizationCode);
  }

  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String userEmail,
      String authorizationCode) {

        //make changes to the ump file to allow for authorization of code
      
  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String memberEmail) {
    // set Member's status to paid
    Member member = Member.getWithEmail(memberEmail);
    member.pay();
  
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to start the trips for day {string}")
  public void the_administrator_attempts_to_start_the_trips_for_day(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

}
