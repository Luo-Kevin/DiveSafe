package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 72 "DiveSafe.ump"
// line 175 "DiveSafe.ump"
// line 260 "DiveSafe.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private double weight;
  private int pricePerWeek;

  //Equipment Associations
  private List<Registration> equipmentRenters;
  private DiveSafe diveSafe;
  private List<Bundle> pack;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(double aWeight, int aPricePerWeek, DiveSafe aDiveSafe)
  {
    weight = aWeight;
    pricePerWeek = aPricePerWeek;
    equipmentRenters = new ArrayList<Registration>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create equipment due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    pack = new ArrayList<Bundle>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeight(double aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public double getWeight()
  {
    return weight;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }
  /* Code from template association_GetMany */
  public Registration getEquipmentRenter(int index)
  {
    Registration aEquipmentRenter = equipmentRenters.get(index);
    return aEquipmentRenter;
  }

  public List<Registration> getEquipmentRenters()
  {
    List<Registration> newEquipmentRenters = Collections.unmodifiableList(equipmentRenters);
    return newEquipmentRenters;
  }

  public int numberOfEquipmentRenters()
  {
    int number = equipmentRenters.size();
    return number;
  }

  public boolean hasEquipmentRenters()
  {
    boolean has = equipmentRenters.size() > 0;
    return has;
  }

  public int indexOfEquipmentRenter(Registration aEquipmentRenter)
  {
    int index = equipmentRenters.indexOf(aEquipmentRenter);
    return index;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_GetMany */
  public Bundle getPack(int index)
  {
    Bundle aPack = pack.get(index);
    return aPack;
  }

  public List<Bundle> getPack()
  {
    List<Bundle> newPack = Collections.unmodifiableList(pack);
    return newPack;
  }

  public int numberOfPack()
  {
    int number = pack.size();
    return number;
  }

  public boolean hasPack()
  {
    boolean has = pack.size() > 0;
    return has;
  }

  public int indexOfPack(Bundle aPack)
  {
    int index = pack.indexOf(aPack);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentRenters()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipmentRenter(Registration aEquipmentRenter)
  {
    boolean wasAdded = false;
    if (equipmentRenters.contains(aEquipmentRenter)) { return false; }
    equipmentRenters.add(aEquipmentRenter);
    if (aEquipmentRenter.indexOfRentedEquipment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipmentRenter.addRentedEquipment(this);
      if (!wasAdded)
      {
        equipmentRenters.remove(aEquipmentRenter);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipmentRenter(Registration aEquipmentRenter)
  {
    boolean wasRemoved = false;
    if (!equipmentRenters.contains(aEquipmentRenter))
    {
      return wasRemoved;
    }

    int oldIndex = equipmentRenters.indexOf(aEquipmentRenter);
    equipmentRenters.remove(oldIndex);
    if (aEquipmentRenter.indexOfRentedEquipment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipmentRenter.removeRentedEquipment(this);
      if (!wasRemoved)
      {
        equipmentRenters.add(oldIndex,aEquipmentRenter);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentRenterAt(Registration aEquipmentRenter, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentRenter(aEquipmentRenter))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentRenters()) { index = numberOfEquipmentRenters() - 1; }
      equipmentRenters.remove(aEquipmentRenter);
      equipmentRenters.add(index, aEquipmentRenter);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentRenterAt(Registration aEquipmentRenter, int index)
  {
    boolean wasAdded = false;
    if(equipmentRenters.contains(aEquipmentRenter))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentRenters()) { index = numberOfEquipmentRenters() - 1; }
      equipmentRenters.remove(aEquipmentRenter);
      equipmentRenters.add(index, aEquipmentRenter);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentRenterAt(aEquipmentRenter, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDiveSafe(DiveSafe aDiveSafe)
  {
    boolean wasSet = false;
    if (aDiveSafe == null)
    {
      return wasSet;
    }

    DiveSafe existingDiveSafe = diveSafe;
    diveSafe = aDiveSafe;
    if (existingDiveSafe != null && !existingDiveSafe.equals(aDiveSafe))
    {
      existingDiveSafe.removeEquipment(this);
    }
    diveSafe.addEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPack()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPack(Bundle aPack)
  {
    boolean wasAdded = false;
    if (pack.contains(aPack)) { return false; }
    pack.add(aPack);
    if (aPack.indexOfPiece(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPack.addPiece(this);
      if (!wasAdded)
      {
        pack.remove(aPack);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePack(Bundle aPack)
  {
    boolean wasRemoved = false;
    if (!pack.contains(aPack))
    {
      return wasRemoved;
    }

    int oldIndex = pack.indexOf(aPack);
    pack.remove(oldIndex);
    if (aPack.indexOfPiece(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPack.removePiece(this);
      if (!wasRemoved)
      {
        pack.add(oldIndex,aPack);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPackAt(Bundle aPack, int index)
  {  
    boolean wasAdded = false;
    if(addPack(aPack))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPack()) { index = numberOfPack() - 1; }
      pack.remove(aPack);
      pack.add(index, aPack);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePackAt(Bundle aPack, int index)
  {
    boolean wasAdded = false;
    if(pack.contains(aPack))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPack()) { index = numberOfPack() - 1; }
      pack.remove(aPack);
      pack.add(index, aPack);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPackAt(aPack, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Registration> copyOfEquipmentRenters = new ArrayList<Registration>(equipmentRenters);
    equipmentRenters.clear();
    for(Registration aEquipmentRenter : copyOfEquipmentRenters)
    {
      aEquipmentRenter.removeRentedEquipment(this);
    }
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeEquipment(this);
    }
    ArrayList<Bundle> copyOfPack = new ArrayList<Bundle>(pack);
    pack.clear();
    for(Bundle aPack : copyOfPack)
    {
      if (aPack.numberOfPiece() <= Bundle.minimumNumberOfPiece())
      {
        aPack.delete();
      }
      else
      {
        aPack.removePiece(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "," +
            "pricePerWeek" + ":" + getPricePerWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null");
  }
}