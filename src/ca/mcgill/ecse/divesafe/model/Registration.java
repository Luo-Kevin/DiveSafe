package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 48 "DiveSafe.ump"
// line 152 "DiveSafe.ump"
// line 222 "DiveSafe.ump"
public class Registration
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Attributes
  private int totalCost;

  //Registration Associations
  private List<SafeDivingDay> safeDivingDaies;
  private DiveSafe diveSafe;
  private DivingSeason divingSeason;
  private Member member;
  private Hotel hotel;
  private List<Equipment> equipment;
  private List<Bundle> bundles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(int aTotalCost, DiveSafe aDiveSafe, DivingSeason aDivingSeason, Member aMember)
  {
    totalCost = aTotalCost;
    safeDivingDaies = new ArrayList<SafeDivingDay>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create registration due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDivingSeason = setDivingSeason(aDivingSeason);
    if (!didAddDivingSeason)
    {
      throw new RuntimeException("Unable to create registration due to divingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aMember == null || aMember.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create Registration due to aMember. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    member = aMember;
    equipment = new ArrayList<Equipment>();
    bundles = new ArrayList<Bundle>();
  }

  public Registration(int aTotalCost, DiveSafe aDiveSafe, DivingSeason aDivingSeason, String aAccountNameForMember, String aAccountPasswordForMember, DiveSafe aDiveSafeForMember, String aNameForMember, String aEmergencyContactForMember, DivingSeason aDivingSeasonForMember, int aDurationOfExpeditionForMember, boolean aWithGuideForMember, boolean aWithHotelForMember)
  {
    totalCost = aTotalCost;
    safeDivingDaies = new ArrayList<SafeDivingDay>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create registration due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDivingSeason = setDivingSeason(aDivingSeason);
    if (!didAddDivingSeason)
    {
      throw new RuntimeException("Unable to create registration due to divingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    member = new Member(aAccountNameForMember, aAccountPasswordForMember, aDiveSafeForMember, aNameForMember, aEmergencyContactForMember, aDivingSeasonForMember, aDurationOfExpeditionForMember, aWithGuideForMember, aWithHotelForMember, this);
    equipment = new ArrayList<Equipment>();
    bundles = new ArrayList<Bundle>();
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
    SafeDivingDay aSafeDivingDay = safeDivingDaies.get(index);
    return aSafeDivingDay;
  }

  public List<SafeDivingDay> getSafeDivingDaies()
  {
    List<SafeDivingDay> newSafeDivingDaies = Collections.unmodifiableList(safeDivingDaies);
    return newSafeDivingDaies;
  }

  public int numberOfSafeDivingDaies()
  {
    int number = safeDivingDaies.size();
    return number;
  }

  public boolean hasSafeDivingDaies()
  {
    boolean has = safeDivingDaies.size() > 0;
    return has;
  }

  public int indexOfSafeDivingDay(SafeDivingDay aSafeDivingDay)
  {
    int index = safeDivingDaies.indexOf(aSafeDivingDay);
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
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSafeDivingDaies()
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
    if (safeDivingDaies.contains(aSafeDivingDay)) { return false; }
    Registration existingRegistration = aSafeDivingDay.getRegistration();
    boolean isNewRegistration = existingRegistration != null && !this.equals(existingRegistration);
    if (isNewRegistration)
    {
      aSafeDivingDay.setRegistration(this);
    }
    else
    {
      safeDivingDaies.add(aSafeDivingDay);
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
      safeDivingDaies.remove(aSafeDivingDay);
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
      if(index > numberOfSafeDivingDaies()) { index = numberOfSafeDivingDaies() - 1; }
      safeDivingDaies.remove(aSafeDivingDay);
      safeDivingDaies.add(index, aSafeDivingDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSafeDivingDayAt(SafeDivingDay aSafeDivingDay, int index)
  {
    boolean wasAdded = false;
    if(safeDivingDaies.contains(aSafeDivingDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSafeDivingDaies()) { index = numberOfSafeDivingDaies() - 1; }
      safeDivingDaies.remove(aSafeDivingDay);
      safeDivingDaies.add(index, aSafeDivingDay);
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
      existingDivingSeason.removeRegistration(this);
    }
    divingSeason.addRegistration(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeRegistration(this);
    }
    if (aHotel != null)
    {
      aHotel.addRegistration(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    if (aEquipment.indexOfRegistration(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipment.addRegistration(this);
      if (!wasAdded)
      {
        equipment.remove(aEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (!equipment.contains(aEquipment))
    {
      return wasRemoved;
    }

    int oldIndex = equipment.indexOf(aEquipment);
    equipment.remove(oldIndex);
    if (aEquipment.indexOfRegistration(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipment.removeRegistration(this);
      if (!wasRemoved)
      {
        equipment.add(oldIndex,aEquipment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    bundles.add(aBundle);
    if (aBundle.indexOfRegistration(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addRegistration(this);
      if (!wasAdded)
      {
        bundles.remove(aBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    if (!bundles.contains(aBundle))
    {
      return wasRemoved;
    }

    int oldIndex = bundles.indexOf(aBundle);
    bundles.remove(oldIndex);
    if (aBundle.indexOfRegistration(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeRegistration(this);
      if (!wasRemoved)
      {
        bundles.add(oldIndex,aBundle);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=safeDivingDaies.size(); i > 0; i--)
    {
      SafeDivingDay aSafeDivingDay = safeDivingDaies.get(i - 1);
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
      placeholderDivingSeason.removeRegistration(this);
    }
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.delete();
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeRegistration(this);
    }
    ArrayList<Equipment> copyOfEquipment = new ArrayList<Equipment>(equipment);
    equipment.clear();
    for(Equipment aEquipment : copyOfEquipment)
    {
      aEquipment.removeRegistration(this);
    }
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      aBundle.removeRegistration(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "divingSeason = "+(getDivingSeason()!=null?Integer.toHexString(System.identityHashCode(getDivingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null");
  }
}