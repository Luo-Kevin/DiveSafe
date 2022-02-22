package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 48 "DiveSafe.ump"
// line 156 "DiveSafe.ump"
// line 236 "DiveSafe.ump"
public class Registration
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Attributes
  private int totalCost;

  //Registration Associations
  private List<SafeDivingDay> safeDivingDays;
  private DiveSafe diveSafe;
  private DivingSeason divingSeason;
  private Member associatedMember;
  private Hotel bookedHotel;
  private List<Equipment> rentedEquipment;
  private List<Bundle> rentedBundle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(int aTotalCost, DiveSafe aDiveSafe, DivingSeason aDivingSeason, Member aAssociatedMember)
  {
    totalCost = aTotalCost;
    safeDivingDays = new ArrayList<SafeDivingDay>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create registration due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDivingSeason = setDivingSeason(aDivingSeason);
    if (!didAddDivingSeason)
    {
      throw new RuntimeException("Unable to create correspondingRegistration due to divingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aAssociatedMember == null || aAssociatedMember.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create Registration due to aAssociatedMember. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    associatedMember = aAssociatedMember;
    rentedEquipment = new ArrayList<Equipment>();
    rentedBundle = new ArrayList<Bundle>();
  }

  public Registration(int aTotalCost, DiveSafe aDiveSafe, DivingSeason aDivingSeason, String aAccountNameForAssociatedMember, String aAccountPasswordForAssociatedMember, DiveSafe aDiveSafeForAssociatedMember, String aNameForAssociatedMember, String aEmergencyContactForAssociatedMember, DivingSeason aDivingSeasonForAssociatedMember, int aDurationOfExpeditionForAssociatedMember, boolean aWithGuideForAssociatedMember, boolean aWithHotelForAssociatedMember)
  {
    totalCost = aTotalCost;
    safeDivingDays = new ArrayList<SafeDivingDay>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create registration due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDivingSeason = setDivingSeason(aDivingSeason);
    if (!didAddDivingSeason)
    {
      throw new RuntimeException("Unable to create correspondingRegistration due to divingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    associatedMember = new Member(aAccountNameForAssociatedMember, aAccountPasswordForAssociatedMember, aDiveSafeForAssociatedMember, aNameForAssociatedMember, aEmergencyContactForAssociatedMember, aDivingSeasonForAssociatedMember, aDurationOfExpeditionForAssociatedMember, aWithGuideForAssociatedMember, aWithHotelForAssociatedMember, this);
    rentedEquipment = new ArrayList<Equipment>();
    rentedBundle = new ArrayList<Bundle>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalCost(int aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }

  public int getTotalCost()
  {
    return totalCost;
  }
  /* Code from template association_GetMany */
  public SafeDivingDay getSafeDivingDay(int index)
  {
    SafeDivingDay aSafeDivingDay = safeDivingDays.get(index);
    return aSafeDivingDay;
  }

  public List<SafeDivingDay> getSafeDivingDays()
  {
    List<SafeDivingDay> newSafeDivingDays = Collections.unmodifiableList(safeDivingDays);
    return newSafeDivingDays;
  }

  public int numberOfSafeDivingDays()
  {
    int number = safeDivingDays.size();
    return number;
  }

  public boolean hasSafeDivingDays()
  {
    boolean has = safeDivingDays.size() > 0;
    return has;
  }

  public int indexOfSafeDivingDay(SafeDivingDay aSafeDivingDay)
  {
    int index = safeDivingDays.indexOf(aSafeDivingDay);
    return index;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_GetOne */
  public DivingSeason getDivingSeason()
  {
    return divingSeason;
  }
  /* Code from template association_GetOne */
  public Member getAssociatedMember()
  {
    return associatedMember;
  }
  /* Code from template association_GetOne */
  public Hotel getBookedHotel()
  {
    return bookedHotel;
  }

  public boolean hasBookedHotel()
  {
    boolean has = bookedHotel != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Equipment getRentedEquipment(int index)
  {
    Equipment aRentedEquipment = rentedEquipment.get(index);
    return aRentedEquipment;
  }

  public List<Equipment> getRentedEquipment()
  {
    List<Equipment> newRentedEquipment = Collections.unmodifiableList(rentedEquipment);
    return newRentedEquipment;
  }

  public int numberOfRentedEquipment()
  {
    int number = rentedEquipment.size();
    return number;
  }

  public boolean hasRentedEquipment()
  {
    boolean has = rentedEquipment.size() > 0;
    return has;
  }

  public int indexOfRentedEquipment(Equipment aRentedEquipment)
  {
    int index = rentedEquipment.indexOf(aRentedEquipment);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getRentedBundle(int index)
  {
    Bundle aRentedBundle = rentedBundle.get(index);
    return aRentedBundle;
  }

  public List<Bundle> getRentedBundle()
  {
    List<Bundle> newRentedBundle = Collections.unmodifiableList(rentedBundle);
    return newRentedBundle;
  }

  public int numberOfRentedBundle()
  {
    int number = rentedBundle.size();
    return number;
  }

  public boolean hasRentedBundle()
  {
    boolean has = rentedBundle.size() > 0;
    return has;
  }

  public int indexOfRentedBundle(Bundle aRentedBundle)
  {
    int index = rentedBundle.indexOf(aRentedBundle);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSafeDivingDays()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SafeDivingDay addSafeDivingDay(Date aSafeDay, int aSafeDayNumber, DiveSafe aDiveSafe, DivingSeason aDivingSeason)
  {
    return new SafeDivingDay(aSafeDay, aSafeDayNumber, aDiveSafe, aDivingSeason, this);
  }

  public boolean addSafeDivingDay(SafeDivingDay aSafeDivingDay)
  {
    boolean wasAdded = false;
    if (safeDivingDays.contains(aSafeDivingDay)) { return false; }
    Registration existingRegistration = aSafeDivingDay.getRegistration();
    boolean isNewRegistration = existingRegistration != null && !this.equals(existingRegistration);
    if (isNewRegistration)
    {
      aSafeDivingDay.setRegistration(this);
    }
    else
    {
      safeDivingDays.add(aSafeDivingDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSafeDivingDay(SafeDivingDay aSafeDivingDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aSafeDivingDay, as it must always have a registration
    if (!this.equals(aSafeDivingDay.getRegistration()))
    {
      safeDivingDays.remove(aSafeDivingDay);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSafeDivingDayAt(SafeDivingDay aSafeDivingDay, int index)
  {  
    boolean wasAdded = false;
    if(addSafeDivingDay(aSafeDivingDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSafeDivingDays()) { index = numberOfSafeDivingDays() - 1; }
      safeDivingDays.remove(aSafeDivingDay);
      safeDivingDays.add(index, aSafeDivingDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSafeDivingDayAt(SafeDivingDay aSafeDivingDay, int index)
  {
    boolean wasAdded = false;
    if(safeDivingDays.contains(aSafeDivingDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSafeDivingDays()) { index = numberOfSafeDivingDays() - 1; }
      safeDivingDays.remove(aSafeDivingDay);
      safeDivingDays.add(index, aSafeDivingDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSafeDivingDayAt(aSafeDivingDay, index);
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
      existingDiveSafe.removeRegistration(this);
    }
    diveSafe.addRegistration(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDivingSeason(DivingSeason aDivingSeason)
  {
    boolean wasSet = false;
    if (aDivingSeason == null)
    {
      return wasSet;
    }

    DivingSeason existingDivingSeason = divingSeason;
    divingSeason = aDivingSeason;
    if (existingDivingSeason != null && !existingDivingSeason.equals(aDivingSeason))
    {
      existingDivingSeason.removeCorrespondingRegistration(this);
    }
    divingSeason.addCorrespondingRegistration(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setBookedHotel(Hotel aBookedHotel)
  {
    boolean wasSet = false;
    Hotel existingBookedHotel = bookedHotel;
    bookedHotel = aBookedHotel;
    if (existingBookedHotel != null && !existingBookedHotel.equals(aBookedHotel))
    {
      existingBookedHotel.removeHotelClient(this);
    }
    if (aBookedHotel != null)
    {
      aBookedHotel.addHotelClient(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRentedEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRentedEquipment(Equipment aRentedEquipment)
  {
    boolean wasAdded = false;
    if (rentedEquipment.contains(aRentedEquipment)) { return false; }
    rentedEquipment.add(aRentedEquipment);
    if (aRentedEquipment.indexOfEquipmentRenter(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRentedEquipment.addEquipmentRenter(this);
      if (!wasAdded)
      {
        rentedEquipment.remove(aRentedEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRentedEquipment(Equipment aRentedEquipment)
  {
    boolean wasRemoved = false;
    if (!rentedEquipment.contains(aRentedEquipment))
    {
      return wasRemoved;
    }

    int oldIndex = rentedEquipment.indexOf(aRentedEquipment);
    rentedEquipment.remove(oldIndex);
    if (aRentedEquipment.indexOfEquipmentRenter(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRentedEquipment.removeEquipmentRenter(this);
      if (!wasRemoved)
      {
        rentedEquipment.add(oldIndex,aRentedEquipment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRentedEquipmentAt(Equipment aRentedEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addRentedEquipment(aRentedEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRentedEquipment()) { index = numberOfRentedEquipment() - 1; }
      rentedEquipment.remove(aRentedEquipment);
      rentedEquipment.add(index, aRentedEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRentedEquipmentAt(Equipment aRentedEquipment, int index)
  {
    boolean wasAdded = false;
    if(rentedEquipment.contains(aRentedEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRentedEquipment()) { index = numberOfRentedEquipment() - 1; }
      rentedEquipment.remove(aRentedEquipment);
      rentedEquipment.add(index, aRentedEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRentedEquipmentAt(aRentedEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRentedBundle()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRentedBundle(Bundle aRentedBundle)
  {
    boolean wasAdded = false;
    if (rentedBundle.contains(aRentedBundle)) { return false; }
    rentedBundle.add(aRentedBundle);
    if (aRentedBundle.indexOfBundleRenter(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRentedBundle.addBundleRenter(this);
      if (!wasAdded)
      {
        rentedBundle.remove(aRentedBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRentedBundle(Bundle aRentedBundle)
  {
    boolean wasRemoved = false;
    if (!rentedBundle.contains(aRentedBundle))
    {
      return wasRemoved;
    }

    int oldIndex = rentedBundle.indexOf(aRentedBundle);
    rentedBundle.remove(oldIndex);
    if (aRentedBundle.indexOfBundleRenter(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRentedBundle.removeBundleRenter(this);
      if (!wasRemoved)
      {
        rentedBundle.add(oldIndex,aRentedBundle);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRentedBundleAt(Bundle aRentedBundle, int index)
  {  
    boolean wasAdded = false;
    if(addRentedBundle(aRentedBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRentedBundle()) { index = numberOfRentedBundle() - 1; }
      rentedBundle.remove(aRentedBundle);
      rentedBundle.add(index, aRentedBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRentedBundleAt(Bundle aRentedBundle, int index)
  {
    boolean wasAdded = false;
    if(rentedBundle.contains(aRentedBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRentedBundle()) { index = numberOfRentedBundle() - 1; }
      rentedBundle.remove(aRentedBundle);
      rentedBundle.add(index, aRentedBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRentedBundleAt(aRentedBundle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=safeDivingDays.size(); i > 0; i--)
    {
      SafeDivingDay aSafeDivingDay = safeDivingDays.get(i - 1);
      aSafeDivingDay.delete();
    }
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeRegistration(this);
    }
    DivingSeason placeholderDivingSeason = divingSeason;
    this.divingSeason = null;
    if(placeholderDivingSeason != null)
    {
      placeholderDivingSeason.removeCorrespondingRegistration(this);
    }
    Member existingAssociatedMember = associatedMember;
    associatedMember = null;
    if (existingAssociatedMember != null)
    {
      existingAssociatedMember.delete();
    }
    if (bookedHotel != null)
    {
      Hotel placeholderBookedHotel = bookedHotel;
      this.bookedHotel = null;
      placeholderBookedHotel.removeHotelClient(this);
    }
    ArrayList<Equipment> copyOfRentedEquipment = new ArrayList<Equipment>(rentedEquipment);
    rentedEquipment.clear();
    for(Equipment aRentedEquipment : copyOfRentedEquipment)
    {
      aRentedEquipment.removeEquipmentRenter(this);
    }
    ArrayList<Bundle> copyOfRentedBundle = new ArrayList<Bundle>(rentedBundle);
    rentedBundle.clear();
    for(Bundle aRentedBundle : copyOfRentedBundle)
    {
      aRentedBundle.removeBundleRenter(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "divingSeason = "+(getDivingSeason()!=null?Integer.toHexString(System.identityHashCode(getDivingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "associatedMember = "+(getAssociatedMember()!=null?Integer.toHexString(System.identityHashCode(getAssociatedMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bookedHotel = "+(getBookedHotel()!=null?Integer.toHexString(System.identityHashCode(getBookedHotel())):"null");
  }
}