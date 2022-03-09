package ca.mcgill.ecse.divesafe.controller;

import java.util.List;
import org.checkerframework.checker.units.qual.A;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Item;

public class BundleController {

  // Instance variables
  private static DiveSafe divesafe = DiveSafeApplication.getDiveSafe();

  /**
   * 
   * @author Jiahao Zhao, Siger Ma, Kevin Luo
   * @param name - Name of the bundle
   * @param discount - Discount applied on the bundle
   * @param equipmentNames - Name of the equipments in the bundle
   * @param equipmentQuantities - Quantities of item in the bundle
   * @return String
   */

  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) {


// Constraints JZ and KL
    var error = "";

    if (discount < 0) {
      error = "The discount must be greater than zero. ";
    }

    if (discount > 100) {
      error = "The discount must be less than one hundred. ";
    }

    if (equipmentQuantities.size() <= 0) {
      error = "The number quantity of items must be greater than zero. ";
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

    } catch (Exception e) {
      return e.getMessage();
    }
    
    return error;
  }

  public static String updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) {
    return null;
  }

  public static void deleteEquipmentBundle(String name) {}
}

