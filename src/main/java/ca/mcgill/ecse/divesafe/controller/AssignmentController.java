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
import ca.mcgill.ecse.divesafe.model.User;
import ca.mcgill.ecse.divesafe.model.Guide.AvailableStatus;

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

    String error = "";

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
      error = "Assignments could not be completed for all members";
    }

    return error;
  }

/**
 * cancelTrip : Method to cancel user trip
 * @author Zahra Landou
 * @param userEmail - user email address
 * @return error message or refund if there is one. 
 */

  public static String cancelTrip(String userEmail) {
    
    String error = "";
    if(!Member.hasWithEmail(userEmail)) return error = "Member with email address "+userEmail+" does not exist";
  
    Member member = Member.getWithEmail(userEmail) ;
    if(member.getMemberStatusFullName().equals("Banned")){
    error =  "Cannot cancel the trip due to a ban";
    } 
  
    else if(member.getMemberStatusFullName().equals("Finished")) {
    error = "Cannot cancel a trip which has finished";
    }
    else if(member.getMemberStatusFullName().equals("Paid")) 
     error = "50";

    else if(member.getMemberStatusFullName().equals("Started"))
     error = "10";

    member.cancelTrip();
   return error;  
  }

  public static String finishTrip(String userEmail) {
    return null;
  }

  /**
   * 
   * @author Jiahao Zhao
   * 
   * Method that starts the trip for all members that have paid in accordance 
   * with their schedule
   * @param day Input parameter that determines the members that leave on a particular day
   * in accordance with their schedule
   * @return
   */

  public static String startTripsForDay(int day) {
    String error = "";
    List<Member> currentMemberList = diveSafe.getMembers();
    for (Member member : currentMemberList) {
      // if(member.getAssignment().getStartDay() == day) {
      // member.startTrip();
      // }
      // }
      member.startTrip(day);
      if(member.getMemberStatusFullName().equals("Banned")){
        error =  "Cannot start the trip due to a ban";
      }
      if(member.getMemberStatusFullName().equals("Cancelled")){
        error = "Cannot start a trip which has been cancelled";
      }
      if(member.getMemberStatusFullName().equals("Finished")) {
        error = "Cannot start a trip which has finished";
      }
    }
    return error;
  }

  /**
   * Method confirms payment for user and updates their MemberStatus
   * @param userEmail - email related to payment
   * @param authorizationCode - authorization code related to payment
   * @return error message related to user input
   * @author Kevin Luo
   */

  public static String confirmPayment(String userEmail, String authorizationCode) {
    //Checks email exist
    if(!Member.hasWithEmail(userEmail)){
      return String.format("Member with email address %s does not exist", userEmail);
      // return userEmail;
    }

    //Checks authorization code validity
    if(authorizationCode.isBlank()){
      return "Invalid authorization code";
    }

    Member member =  Member.getWithEmail(userEmail);

    if(member.getMemberStatusFullName().equals("Paid")){
      return "Trip has already been paid for";
    }

    if(member.getMemberStatusFullName().equals("Started")){
      return "Trip has already been paid for";
    }
    
    if(member.getMemberStatusFullName().equals("Cancelled")){
      return "Cannot pay for a trip which has been cancelled";
    }

    if(member.getMemberStatusFullName().equals("TripFinish")){
      return "Cannot pay for a trip which has finished";
    }

    if(member.getMemberStatusFullName().equals("Banned")){
      return "Cannot pay for the trip due to a ban";
    }

    //Update user payment status
    if(User.hasWithEmail(userEmail) && !(authorizationCode.isBlank()) && member.getMemberStatusFullName().equals("Assigned")){
    member.confirmPayment();
    return authorizationCode;
    }

    return null;
  }

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
