package ca.mcgill.ecse.divesafe.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.Assignment;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Item;
import ca.mcgill.ecse.divesafe.model.Member;
import ca.mcgill.ecse.divesafe.persistence.DiveSafePersistence;

public class AssignmentController {
  private static DiveSafe diveSafe = DiveSafeApplication.getDiveSafe();

  private AssignmentController() {
  }

  public static List<TOAssignment> getAssignments() {
    List<TOAssignment> assignments = new ArrayList<>();

    for (var assignment : diveSafe.getAssignments()) {
      var newTOAssignment = wrapAssignment(assignment);
      assignments.add(newTOAssignment);
    }

    return assignments;
  }

  /**
   * Method to initiate the assignment for the members by giving them a guide (if
   * they asked for one) and their trip's schedule
   * 
   * @author Siger Ma
   * @return error string if there is one
   */

  public static String initiateAssignment() {

    // Assign members
    List<Guide> currentGuides = diveSafe.getGuides();

    for (Guide guide : currentGuides) {
      guide.bookGuide();
    }

    // get all unassigned members and return error if not empty
    List<Member> currentMemberList = diveSafe.getMembers();
    int count = 0;
    for (Member currentMember : currentMemberList) {
      if (currentMember.getMemberStatusFullName() == "Unassigned") {
        count++;
      }
    }
    if (count != 0) {
      return "Assignments could not be completed for all members";
    }

    try {
      DiveSafePersistence.save();
    } catch (RuntimeException e) {
      e.getMessage();
    }

    return "";
  }

  /**
   * Method to cancel member trip and refund them if necessary.
   * 
   * @author Zahra Landou
   * @param userEmail - user email address
   * @return error message or refund if there is one.
   */

  public static String cancelTrip(String userEmail) {

    String error = "";
    String refund = "";

    if (!Member.hasWithEmail(userEmail))
      return error = "Member with email address " + userEmail + " does not exist";

    Member member = Member.getWithEmail(userEmail);

    //Check for invalid user status when attempting to refund
    if (member.getMemberStatusFullName().equals("Banned")) {
      error = "Cannot cancel the trip due to a ban";
    }

    else if (member.getMemberStatusFullName().equals("Finished")) {
      error = "Cannot cancel a trip which has finished";
    }

    //Check for user status when allowed to refund
    else if (member.getMemberStatusFullName().equals("Paid"))
      refund = "50";

    else if (member.getMemberStatusFullName().equals("Started"))
      refund = "10";

    if(!error.isEmpty()){
      return error.trim();
    }

     try {
       //cancel member trip
        member.cancelTrip();
        //save changes with persistence
        DiveSafePersistence.save();
     } catch (RuntimeException e) {
       return e.getMessage();
     }
    
    return refund;
  }

  /**
   * Method to finish trip for a specific member.
   * 
   * @author Eric Joung
   * @param userEmail used to indentify the member
   * @return error message if there is one, or 0 % refund if success
   */

  public static String finishTrip(String userEmail) {

    String error = "";

    Member member = Member.getWithEmail(userEmail);

    //Check if userEmail exist in system
    if (!Member.hasWithEmail(userEmail)) {
      return String.format("Member with email address %s does not exist", userEmail);
    }

    String statusMember = member.getMemberStatusFullName();

    //Check for invalid user status when finishing trip

    if (statusMember.equals("Assigned") || statusMember.equals("Paid")) {
      error = "Cannot finish a trip which has not started";
    }

    else if (statusMember.equals("Banned")) {
      error = "Cannot finish the trip due to a ban";
    }

    else if (statusMember.equals("Cancelled")) {
      error = "Cannot finish a trip which has been cancelled";
    }

    if (!error.isEmpty()){
      return error.trim();
    }

   try {
     //finish member's trip
     member.finishTrip();
     //save changes with persistence
     DiveSafePersistence.save();
   } catch (RuntimeException e) {
     
    return e.getMessage();
   } 

    return "0";  // Refund percentage if finish trip
  }

  /**
   * Method that starts the trip for all members that have paid in accordance with
   * their schedule.
   * 
   * @author Jiahao Zhao
   * @param day Input parameter that determines the members that leave on a
   *            particular day
   *            in accordance with their schedule
   * @return error message if there is one, otherwise null string
   */

  public static String startTripsForDay(int day) {

    String error = "";

    List<Member> currentMemberList = diveSafe.getMembers();

    // Checking all user status 
    for (Member member : currentMemberList) {

      // Checks for user that are not allowed to start trip
      if (member.getMemberStatusFullName().equals("Banned")) {
        error = "Cannot start the trip due to a ban";
      }

      else if (member.getMemberStatusFullName().equals("Cancelled")) {
        error = "Cannot start a trip which has been cancelled";
      }

      else if (member.getMemberStatusFullName().equals("Finished")) {
        error = "Cannot start a trip which has finished";
      }

      if (!error.isEmpty()) {
        return error.trim();
      }
  
      try {
        //start member's trip
        member.startTrip(day);
        //save changes with persistence
        DiveSafePersistence.save();
      } catch (RuntimeException e) {
       return e.getMessage();
      } 
      
    }

    return "";
  }

  /**
   * Method confirms payment for user and updates their MemberStatus.
   * 
   * @author Kevin Luo
   * @param userEmail         - email related to payment
   * @param authorizationCode - authorization code related to payment
   * @return error message related to user input, otherwise return authorization code of user
   */

  public static String confirmPayment(String userEmail, String authorizationCode) {
    String error = "";

    // Checks email exist
    if (!Member.hasWithEmail(userEmail)) {
      return String.format("Member with email address %s does not exist", userEmail);
    }

    // Checks authorization code validity
    else if (authorizationCode.isBlank()) {
      error = "Invalid authorization code";
    }

    Member member = Member.getWithEmail(userEmail);

    //Checking user status and returning appropriate message if encounter error
    if (member.getMemberStatusFullName().equals("Paid")) {
      error = "Trip has already been paid for";
    }

    else if (member.getMemberStatusFullName().equals("Started")) {
      error = "Trip has already been paid for";

    }

    else if (member.getMemberStatusFullName().equals("Cancelled")) {
      error = "Cannot pay for a trip which has been cancelled";
    }

    else if (member.getMemberStatusFullName().equals("Finished")) {
      error = "Cannot pay for a trip which has finished";
    }

    else if (member.getMemberStatusFullName().equals("Banned")) {
      error = "Cannot pay for the trip due to a ban";
    }

    if (!error.isEmpty()){
      return error.trim();
    }

    // Update user payment status
    else if (Member.hasWithEmail(userEmail) && !(authorizationCode.isBlank())
        && member.getMemberStatusFullName().equals("Assigned")) {
      member.confirmPayment();
    }

    try {
      DiveSafePersistence.save(); 
    } catch (Exception e) {
      return e.getMessage();
    }
   
    return authorizationCode;
  }

  /**
   * We did not need this method for our implementation because of how our state
   * machine is implemented.
   */

  public static String toggleBan(String userEmail) {
    return null;
  }

  /**
   * Helper method used to wrap the information in an <code>Assignment</code>
   * instance in an
   * instance of <code>TOAssignment</code>.
   *
   * @author Harrison Wang Oct 19, 2021
   * @param assignment - The <code>Assignment</code> instance to transfer the
   *                   information from.
   * @return A <code>TOAssignment</code> instance containing the information in
   *         the
   *         <code>Assignment</code> parameter.
   */
  private static TOAssignment wrapAssignment(Assignment assignment) {
    var member = assignment.getMember();

    // Initialize values for all necessary parameters.
    String memberEmail = member.getEmail();
    String memberName = member.getName();
    String guideEmail = assignment.hasGuide() ? assignment.getGuide().getEmail() : "";
    String guideName = assignment.hasGuide() ? assignment.getGuide().getName() : "";
    String hotelName = assignment.hasHotel() ? assignment.getHotel().getName() : "";

    int numDays = member.getNumDays();
    int startDay = assignment.getStartDay();
    int endDay = assignment.getEndDay();
    int totalCostForGuide = assignment.hasGuide() ? numDays * diveSafe.getPriceOfGuidePerDay() : 0;
    /*
     * Calculate the totalCostForEquipment.
     *
     * Sum the costs of all booked items depending on if they are an Equipment or
     * EquipmentBundle
     * instance to get the equipmentCostPerDay for this assignment.
     *
     * Multiply equipmentCostPerDay by nrOfDays to get totalCostForEquipment.
     */
    int equipmentCostPerDay = 0;
    for (var bookedItem : member.getItemBookings()) {
      Item item = bookedItem.getItem();
      if (item instanceof Equipment equipment) {
        equipmentCostPerDay += equipment.getPricePerDay() * bookedItem.getQuantity();
      } else if (item instanceof EquipmentBundle bundle) {
        int bundleCost = 0;
        for (var bundledItem : bundle.getBundleItems()) {
          bundleCost += bundledItem.getEquipment().getPricePerDay() * bundledItem.getQuantity();
        }
        // Discount only applicable if assignment includes guide, so check for that
        // before applying discount
        if (assignment.hasGuide()) {
          bundleCost = (int) (bundleCost * ((100.0 - bundle.getDiscount()) / 100.0));
        }
        equipmentCostPerDay += bundleCost * bookedItem.getQuantity();
      }
    }
    int totalCostForEquipment = equipmentCostPerDay * numDays;

    return new TOAssignment(memberEmail, memberName, guideEmail, guideName, hotelName, startDay,
        endDay, totalCostForGuide, totalCostForEquipment);
  }

}
