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
   * addEquipmentBundle : A method to verify if all constraints are respected before adding a bundle 
   * @author Jiahao Zhao, Siger Ma, Kevin Luo, Eric Joung, Zahra Landou
   * @param name                - Name of the bundle
   * @param discount            - Discount applied on the bundle
   * @param equipmentNames      - Name of the equipments in the bundle
   * @param equipmentQuantities - Quantities of item in the bundle
   * @return String error - Nothing if no errors encountered or error message if there is one
   */

  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) {

    // Variables
    divesafe = DiveSafeApplication.getDiveSafe();
    String error = "";

    // Constraint 1 : equiments in bundle must be of at least two distinct types  (done by JZ and KL)
    if (equipmentNames.size() <= 1) {
      error = "Equipment bundle must contain at least two distinct types of equipment";
    } else {
      String firstEquipmentName = equipmentNames.get(0);
      for (int i = 1; i < equipmentNames.size(); i++) {
        //check if the first equipment's name matches  name of another equipment in the list
        if (!(equipmentNames.get(i).equals(firstEquipmentName))) {
          error = "";
          break;
        } else {
          error = "Equipment bundle must contain at least two distinct types of equipment";
        }
      }
    }

    // Constraint 2 : Check if discount is in the range (0,100) (done by JZ )
    if (discount < 0) {
      error = "Discount must be at least 0";
    }

    if (discount > 100) {
      error = "Discount must be no more than 100";
    }

    // Constraint 3: Bundle items' quantity must be at least 1 (done by SM and KL)
    // Taking into consideration that if (equipmentNames.size <= 1) it is another error
    if ((equipmentQuantities.size() <= 0) && (equipmentNames.size() > 1)) {
      error = "Each bundle item must have quantity greater than or equal to 1";
    } else {
      for (Integer quantity : equipmentQuantities) {
        if (quantity < 1) {
          error = "Each bundle item must have quantity greater than or equal to 1";
          break;
        }
      }
    }

    // Constraint 4: Invalid name for bundle (done by KL)
    if (name.isBlank() || name == null) {
      error = "Equipment bundle name cannot be empty";
    }

    // Constraint 5: Invalid equipment  (done by KL)
    // Taking into consideration that if (equipmentNames.size <= 1) it is another error
    if (equipmentNames.size() > 1) {
      for (String equipment : equipmentNames) {
        if (!(Item.hasWithName(equipment))) {
          error = String.format("Equipment %s does not exist", equipment);
          break;
        }
      }
    }

    // Constraint 6 : Check for duplicates (done by EJ)
    List<EquipmentBundle> equipmentBundles = divesafe.getBundles();
    List<Equipment> itemNames = divesafe.getEquipments();
    for (EquipmentBundle bundle : equipmentBundles) {
      if (name.equals(bundle.getName())) {
        error = String.format("A bookable item called %s already exists", name);
        break;
      }
    }

    // Constraint JZ and KL
    for (Equipment equipment : itemNames) {
      if (name.equals(equipment.getName())) {
        error = String.format("A bookable item called %s already exists", name);
        break;
      }
    }

    // If error return KL
    if (!error.isEmpty()) {
      return error;
    }

    // Try-catch KL
    try {
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

  public static void deleteEquipmentBundle(String name) {
  }
}
