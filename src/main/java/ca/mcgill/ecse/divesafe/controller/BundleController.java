package ca.mcgill.ecse.divesafe.controller;

import java.util.List;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Item;

public class BundleController {

  // Instance variables
  private static DiveSafe divesafe = DiveSafeApplication.getDiveSafe();

  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames, List<Integer> equipmentQuantities) {
    
    // Create bundle by SM
    EquipmentBundle aBundle = divesafe.addBundle(name, discount);

    // Add bundle items by SM
    for (int i = 0; i < equipmentNames.size(); i++) {

      Equipment aEquipment = (Equipment) Item.getWithName(equipmentNames.get(i));
      int aQuantity = equipmentQuantities.get(i);

      divesafe.addBundleItem(aQuantity, aBundle, aEquipment);
    }

    return null;
  }

  public static String updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) {
    return null;
  }

  public static void deleteEquipmentBundle(String name) {}
}
