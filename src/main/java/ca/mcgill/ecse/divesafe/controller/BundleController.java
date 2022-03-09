package ca.mcgill.ecse.divesafe.controller;

import java.util.*;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Item;

public class BundleController {

  // Instance variables
  private static DiveSafe divesafe;

  /**
   * 
   * @author Jiahao Zhao, Siger Ma, Kevin Luo
   * @param name - Name of the bundle
   * @param discount - Discount applied on the bundle
   * @param equipmentNames - Name of the equipments in the bundle
   * @param equipmentQuantities - Quantities of item in the bundle
   * @return String - Nothing if no errors or error message if there is one
   */

  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames, List<Integer> equipmentQuantities) {

    // Variables
    divesafe = DiveSafeApplication.getDiveSafe();
    String error = "";
    
    // Constraints JZ and KL
    
    if (discount < 0) {
      error = "The discount must be greater than zero. ";
    }

    if (discount > 100) {
      error = "The discount must be less than one hundred. ";
    }

    if (equipmentQuantities.size() <= 0) {
      error = "The number quantity of items must be greater than zero. ";
    }

    if(name.isBlank()) {
      error = "Equipment bundle name cannot be empty";
    }

    if (equipmentNames.size() <= 1) {
      error = "A bundle must contain at least two different kinds of equipment. ";
    } else {
      String firstEquipmentName = equipmentNames.get(0);
      for(int i = 0; i < equipmentNames.size(); i++){
        if(!(equipmentNames.get(i).equals(firstEquipmentName))) { 
          error = "";
          break;
        } else {
          error = "A bundle must contain at least two different kinds of equipment. ";
        }
      }
    }

    for(String equipment: equipmentNames){
      if(!(Item.hasWithName(equipment))){
        error = String.format("Equipment %s does not exist", name);
      }
   
      
    if (!error.isEmpty()) {
      return error.trim();
    }

    try {
      // TO REVIEW!

      // Create bundle by SM
      EquipmentBundle aBundle = divesafe.addBundle(name, discount);

      // Add bundle items by SM
      for (int i = 0; i < equipmentNames.size(); i++) {

        Equipment aEquipment = (Equipment) Item.getWithName(equipmentNames.get(i));
        int aQuantity = equipmentQuantities.get(i);
          
        divesafe.addBundleItem(aQuantity, aBundle, aEquipment);
      }

      return error;

    } catch (Exception e) {
      return e.getMessage();
    }
    
  }

  public static String updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) {
    return null;
  }

  public static void deleteEquipmentBundle(String name) {}
}

