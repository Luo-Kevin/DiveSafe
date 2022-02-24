package ca.mcgill.ecse.divesafe.controller;

import java.util.List;

public class MemberController {
  public static String registerMember(String email, String password, String name,
      String emergencyContact, int nrDays, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) {
    return null;
  }

  public static String updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrDays, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities){
      return null;
  }

  public static void deleteMember(String email) {}
}
