package ca.mcgill.ecse.divesafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.Assignment;
import ca.mcgill.ecse.divesafe.model.BundleItem;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Item;
import ca.mcgill.ecse.divesafe.model.ItemBooking;
import ca.mcgill.ecse.divesafe.model.Member;
import ca.mcgill.ecse.divesafe.persistence.DiveSafePersistence;

public class AssignmentController {
  private static DiveSafe diveSafe = DiveSafeApplication.getDiveSafe();

  private AssignmentController() {
  }

  public static List<TOAssignment> getAssignments() {
    List<TOAssignment> assignments = new ArrayList<>();
    List<Assignment> diveSafeAssignments = DiveSafeApplication.getDiveSafe().getAssignments();

    for (var assignment : diveSafeAssignments) {
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
    List<Guide> currentGuides = DiveSafeApplication.getDiveSafe().getGuides();

    for (Guide guide : currentGuides) {
      guide.bookGuide();

    }

    // get all unassigned members and return error if not empty
    List<Member> currentMemberList = DiveSafeApplication.getDiveSafe().getMembers();
    int count = 0;
    for (Member currentMember : currentMemberList) {
      if (!currentMember.getMemberStatusFullName().equals("Assigned")) {
        count++;
      }
    }
    if (count != 0) {
      return "Assignments could not be completed for all members";
    }

    try {
      DiveSafeApplication.save(DiveSafeApplication.getDiveSafe());
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
    String refundPercentage = "";

    // Check if userEmail exist in system
    if (!Member.hasWithEmail(userEmail))
      return error = "Member with email address " + userEmail + " does not exist.\n";

    Member member = Member.getWithEmail(userEmail);

    // Check for invalid user status when attempting to refund
    if (member.getMemberStatusFullName().equals("Banned")) {
      return error = "Cannot cancel the trip due to a ban.\n";
    }

    if (member.getMemberStatusFullName().equals("Unassigned")) {
      return error = "Cannot cancel a trip for an unassigned member.\n";
    }

    else if (member.getMemberStatusFullName().equals("Finished")) {
      return error = "Cannot cancel a trip which has finished.\n";
    }

    // Check for user status when allowed to refund
    else if (member.getMemberStatusFullName().equals("Paid"))
      refundPercentage = "50";

    else if (member.getMemberStatusFullName().equals("Started"))
      refundPercentage = "10";

    if (!error.isEmpty()) {
      return error.trim();
    }

    try {
      // cancel member trip
      member.cancelTrip();
      // save changes with persistence
      DiveSafeApplication.save(diveSafe);
      DiveSafePersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }

    return refundPercentage;
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

    // Check if userEmail exist in system
    if (!Member.hasWithEmail(userEmail)) {
      return String.format("Member with email address %s does not exist.\n", userEmail);
    }

    String statusMember = member.getMemberStatusFullName();

    // Check for invalid user status when finishing trip
    if (statusMember.equals("Assigned") || statusMember.equals("Paid")) {
      return error = "Cannot finish a trip which has not started.\n";
    }

    else if (statusMember.equals("Banned")) {
      return error = "Cannot finish the trip due to a ban.\n";
    }

    if (statusMember.equals("Unassigned")) {
      return error = "Cannot finish a trip for an unassigned member.\n";
    }

    else if (statusMember.equals("Cancelled")) {
      return error = "Cannot finish a trip which has been cancelled.\n";
    }

    if (!error.isEmpty()) {
      return error.trim();
    }

    try {
      // finish member's trip
      member.finishTrip();
      // save changes with persistence
      DiveSafeApplication.save(diveSafe);
      DiveSafePersistence.save();
    } catch (RuntimeException e) {

      return e.getMessage();
    }

    return "0"; // Refund percentage if finish trip
  }

  /**
   * Method that starts the trip for all members that have paid in accordance with
   * their schedule.
   * 
   * @author Jiahao Zhao
   * @param day Input parameter that determines the members that leave on a
   *            particular day
   *            in accordance with their schedule
   * @return error message if there is one, otherwise empty string
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
        // start member's trip
        member.startTrip(day);
        // save changes with persistence
        DiveSafeApplication.save(diveSafe);
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
   * @return error message related to user input, otherwise return authorization
   *         code of user
   */

  public static String confirmPayment(String userEmail, String authorizationCode) {
    String error = "";
    boolean success;

    // Checks email exist
    if (!Member.hasWithEmail(userEmail)) {
      return String.format("Member with email address %s does not exist", userEmail);
    }

    // Checks authorization code validity
    else if (authorizationCode.isBlank()) {
      error = "Invalid authorization code";
    }
    var divesafeMember = DiveSafeApplication.getDiveSafe().getMembers();

    Member member = null;
    for (Member checkMember : divesafeMember) {
      if (checkMember.getEmail().equals(userEmail)) {
        member = checkMember;
      }
    }

    // Checking for invalid user status when confirming payment
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

    if (!error.isEmpty()) {
      return error.trim();
    }

    try {
      success = member.confirmPayment();
      DiveSafeApplication.save(DiveSafeApplication.getDiveSafe());
      DiveSafePersistence.save();
    } catch (Exception e) {
      return e.getMessage();
    }

    if (success) {
      return authorizationCode;
    } else {
      return "";
    }

  }

  /**
   * We did not need this method for our implementation because of how our state
   * machine is implemented.
   */

  public static String toggleBan(String userEmail) {
    return null;
  }

  /**
   * Method that gets all member's emails in diveSafe
   * 
   * @author Eric Joung
   * @return List<Member> List of all members
   */
  public static ArrayList<String> getMemberEmails() {
    ArrayList<String> memberEmails = new ArrayList<>();
    List<Member> members = diveSafe.getMembers();
    for (Member member : members) {
      memberEmails.add(member.getEmail());
    }
    return memberEmails;
  }

  /**
   * Method to get the assignment details of a member
   * 
   * @author Siger Ma
   * @param email - Email of member
   * @return List with assignment details
   */
  public static List<String> getAssignmentDetails(String email) {
    List<String> assignmentDetails = new ArrayList<String>();
    Member member = Member.getWithEmail(email);
    Assignment assignment = member.getAssignment();
    List<ItemBooking> itemBookings = member.getItemBookings();

    assignmentDetails.add(String.valueOf(assignment.getStartDay()));
    assignmentDetails.add(String.valueOf(assignment.getEndDay()));
    if (member.getGuideRequired()) {
      assignmentDetails.add(assignment.getGuide().getEmail());
    } else {
      assignmentDetails.add("No guide required");
    }
    if (itemBookings == null || itemBookings.size() == 0) {
      assignmentDetails.add("No items required");
    } else {
      for (ItemBooking itemBooking : itemBookings) {
        assignmentDetails.add(itemBooking.getItem().getName() + ": " + itemBooking.getQuantity());
      }
    }

    return assignmentDetails;
  }

  /**
   * Method which gets the billable bookings of the member
   * 
   * @param email - String which representing the member's email
   * @return List of the billable equipment
   * @author Kevin Luo
   */

  public static List<ItemBooking> getUserBill(String email) {
    if (!Member.hasWithEmail(email)) {
      return null;
    } else {
      Member member = Member.getWithEmail(email);
      List<ItemBooking> userBooking = member.getItemBookings();
      return userBooking;
    }
  }

  /**
   * Method which gets the user's billable equipments
   * 
   * @param userBooking - List of billable equipment
   * @param email       - user's email
   * @return List of String representing the detailed information of user's bill
   *         for equipments booked
   * @author Kevin Luo
   */
  public static List<String> userBillBookedEquipmentDetails(List<ItemBooking> userBooking, String email) {
    List<String> bookingBill = new ArrayList<String>();
    Member member = Member.getWithEmail(email);
    int daysBooked = member.getNumDays();

    for (ItemBooking item : userBooking) {
      Item itemBooked = item.getItem();
      if (itemBooked instanceof Equipment) {
        Equipment itemEquipment = (Equipment) itemBooked;
        String itemName = itemBooked.getName();
        int itemQuantity = item.getQuantity();
        int itemPrice = itemEquipment.getPricePerDay() * itemQuantity * daysBooked;
        String billingDetail = itemName + " [Quantity: " + itemQuantity + "]" + " $" + itemPrice;
        bookingBill.add(billingDetail);

      }
    }
    return bookingBill;
  }

  /**
   * Method which gets the user's billable bundle
   * 
   * @param userBooking - List of billable equipment
   * @param email       - user's email
   * @return List of String representing the detailed information of user's bill
   *         for bundle booked
   * @author Kevin Luo
   */

  public static List<String> userBillBundleDetails(List<ItemBooking> userBooking, String email) {
    List<String> bookingBill = new ArrayList<String>();

    Member member = Member.getWithEmail(email);
    int daysBooked = member.getNumDays();
    if (member.getAssignment() == null) {
      return bookingBill;
    }

    for (ItemBooking item : userBooking) {
      Item itemBooked = item.getItem();

      if (itemBooked instanceof EquipmentBundle) {
        EquipmentBundle bundleBooked = (EquipmentBundle) itemBooked;
        List<BundleItem> itemInBundle = bundleBooked.getBundleItems();
        Integer bundlePrice = 0;
        for (BundleItem bundleEquipment : itemInBundle) {
          Integer bundleItemPrice = bundleEquipment.getEquipment().getPricePerDay() * bundleEquipment.getQuantity(); // bundle
                                                                                                                     // rented
                                                                                                                     // per
                                                                                                                     // week
          bundlePrice += bundleItemPrice;
        }

        if (member.getAssignment().hasGuide()) {
          double discount = (double) (100 - bundleBooked.getDiscount()) / 100;
          bundlePrice = (int) (bundlePrice * discount);
        }
        String bundleName = bundleBooked.getName();
        int bundleQuantity = item.getQuantity();
        bundlePrice = bundlePrice * daysBooked * bundleQuantity;
        String billingDetail = bundleName + " [Quantity: " + bundleQuantity + "]" + " $" + bundlePrice;
        bookingBill.add(billingDetail);
      }
    }
    return bookingBill;
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
