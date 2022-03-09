package ca.mcgill.ecse.divesafe.controller;

import java.util.List;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;

public class BundleController {
  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) {
        var error = "";
      
        if(discount < 0) {
          error = "The discount must be greater than zero. "; 
        }

        if(discount > 100) {
          error = "The discount must be less than one hundred. ";
        }

        if(!error.isEmpty()) {
          return error.trim();
        }


    return null;
  }

  public static String updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) {
    return null;
  }

  public static void deleteEquipmentBundle(String name) {}
}
