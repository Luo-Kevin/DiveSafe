package ca.mcgill.ecse.divesafe.controller;

import java.util.List;
import build.ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;

public class BundleController {
  private DiveSafeApplication divesafe;
  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) {


        var error = "";

        if(equipmentQuantities.size() <= 0){
          error = "The number of a route must be greater than zero. ";
        }

        if(!error.isEmpty()){
          return error.trim();
        }

        try {
       
          // DiveSafeApplication.getDiveSafe().add
          
        } catch (RuntimeException e) {
          //TODO: handle exception
          return e.getMessage();
          }
        
    return "";
  }

  public static String updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) {
    return null;
  }

  public static void deleteEquipmentBundle(String name) {}
}
