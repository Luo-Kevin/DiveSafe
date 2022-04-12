package ca.mcgill.ecse.divesafe.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.BundleItem;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Equipment;
import ca.mcgill.ecse.divesafe.model.EquipmentBundle;
import ca.mcgill.ecse.divesafe.model.Item;
import ca.mcgill.ecse.divesafe.persistence.DiveSafePersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BundleController {

  private static DiveSafe diveSafe = DiveSafeApplication.getDiveSafe();

  private static final int MAX_DISCOUNT = 100;

  private BundleController() {
  }

  /**
   * @author Runge (Karen) Fu This method creates an equipment bundle
   * @param name                the name of the new equipment bundle
   * @param discount            the discount of the new equipment bundle
   * @param equipmentNames      list of equipment names that need to be add
   * @param equipmentQuantities list of equipment quantities which need to be add
   * @throws InvalidInputException if an input exception occurred
   */
  public static String addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) {
    // test for illegal inputs
    String error = checkCommonConditions(name, discount, equipmentNames, equipmentQuantities);

    if (Item.hasWithName(name)) {
      error = "A bookable item called " + name + " already exists";
    }

    if (!error.isBlank()) {
      return error.trim();
    }

    EquipmentBundle bundle = diveSafe.addBundle(name, discount);
    for (int i = 0; i < equipmentNames.size(); i++) {
      String equipmentName = equipmentNames.get(i);
      int equipmentQuantity = equipmentQuantities.get(i);
      BundleItem bundleItem = diveSafe.addBundleItem(equipmentQuantity, bundle, Equipment.getWithName(equipmentName));
      bundle.addBundleItem(bundleItem);

      try {
        DiveSafeApplication.save(diveSafe);
        DiveSafePersistence.save();
      } catch (RuntimeException e) {
        e.getMessage();
      }
    }

    return "";
  }

  /**
   * @author Runge (Karen) Fu This method updates the equipment bundle
   * @param oldName                name of the equipment bundle which will be
   *                               updated
   * @param newName                new name for the equipment bundle which will be
   *                               updated
   * @param newDiscount            new discount for the equipment bundle
   * @param newEquipmentNames      list of equipment names that need to be updated
   * @param newEquipmentQuantities list of equipment quantities that need to be
   *                               updated
   * @throws InvalidInputException if an input exception occurred
   */
  public static String updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) {

    String error = checkCommonConditions(newName, newDiscount, newEquipmentNames, newEquipmentQuantities);
    var foundBundle = EquipmentBundle.getWithName(oldName);

    if (!oldName.equals(newName) && Item.hasWithName(newName)) {
      error = "A bookable item called " + newName + " already exists";
    }

    if (foundBundle == null) {
      error = "Equipment bundle " + oldName + " does not exist";
    }

    if (!error.isBlank()) {
      return error.trim();
    }

    while (foundBundle.getBundleItems().size() > 0) {
      foundBundle.getBundleItem(0).delete();
    }

    foundBundle.setName(newName);
    foundBundle.setDiscount(newDiscount);
    for (int i = 0; i < newEquipmentNames.size(); i++) {
      var equipment = Equipment.getWithName(newEquipmentNames.get(i));
      foundBundle.addBundleItem(newEquipmentQuantities.get(i), diveSafe, equipment);
    }

    try {
      DiveSafeApplication.save(diveSafe);
        DiveSafePersistence.save();
    } catch (RuntimeException e) {
      e.getMessage();
    }

    return "";

  }

  public static String deleteEquipmentBundle(String name) {
    var foundBundle = EquipmentBundle.getWithName(name);

    if (foundBundle != null) {
      foundBundle.delete();
      try {
        DiveSafeApplication.save(diveSafe);
        DiveSafePersistence.save();
      } catch (RuntimeException e) {
        e.getMessage();
      }
    }

    return "";

  }

  private static String checkCommonConditions(String bundleName, int discount,
      List<String> equipmentNames, List<Integer> equipmentQuantities) {
    if (discount < 0) {
      return "Discount must be at least 0";
    } else if (discount > MAX_DISCOUNT) {
      return "Discount must be no more than " + MAX_DISCOUNT;
    } else if (bundleName.isEmpty()) {
      return "Equipment bundle name cannot be empty";
    } else if (equipmentQuantities.isEmpty() || new HashSet<>(equipmentNames).size() <= 1) {
      return "Equipment bundle must contain at least two distinct types of equipment";
    }

    // check if there is any equipment with quantity less than 1
    if (Collections.min(equipmentQuantities) < 1) {
      return "Each bundle item must have quantity greater than or equal to 1";
    }

    for (String equipmentName : equipmentNames) {
      if (!Equipment.hasWithName(equipmentName)) {
        return "Equipment " + equipmentName + " does not exist";
      }
    }

    return ""; // no error
  }


  /**
   * Helper method to put the bundles'name into an ObservableList<String>
   * @return myBundles - list of bundles' name
   */
  public static ObservableList<String> getBundles(){

   List<String> myBundleList= new ArrayList<String>() ;
    List<EquipmentBundle> systemBundles = diveSafe.getBundles();
   for (EquipmentBundle bundle : systemBundles) {
     myBundleList.add(bundle.getName());
     
   }
   ObservableList<String> myBundles= FXCollections.observableList(myBundleList);
   return myBundles;
  }
 
  /**
   * Helper method to put the items'name into an ObservableList<String>
   * @return myItems - list of items' name
   */
  public static ObservableList<String> getItems(){

    List<String> myItemList= new ArrayList<String>() ;
     List<Equipment> systemItem = diveSafe.getEquipments();
    for (Equipment item : systemItem) {
      myItemList.add(item.getName());    
    }
    ObservableList<String> myItems= FXCollections.observableList(myItemList);
    return myItems;
   }












}
