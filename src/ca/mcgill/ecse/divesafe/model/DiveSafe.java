package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 99 "DiveSafe.ump"
// line 259 "DiveSafe.ump"
public class DiveSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DiveSafe Associations
  private List<ADProgramAccount> aDProgramAccounts;
  private List<DivingSeason> divingSeasons;
  private List<Registration> registrations;
  private List<SafeDivingDay> safeDivingDaies;
  private List<Hotel> hotels;
  private List<Equipment> equipment;
  private List<Bundle> bundles;
  private List<Address> addresses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DiveSafe()
  {
    aDProgramAccounts = new ArrayList<ADProgramAccount>();
    divingSeasons = new ArrayList<DivingSeason>();
    registrations = new ArrayList<Registration>();
    safeDivingDaies = new ArrayList<SafeDivingDay>();
    hotels = new ArrayList<Hotel>();
    equipment = new ArrayList<Equipment>();
    bundles = new ArrayList<Bundle>();
    addresses = new ArrayList<Address>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public ADProgramAccount getADProgramAccount(int index)
  {
    ADProgramAccount aADProgramAccount = aDProgramAccounts.get(index);
    return aADProgramAccount;
  }

  public List<ADProgramAccount> getADProgramAccounts()
  {
    List<ADProgramAccount> newADProgramAccounts = Collections.unmodifiableList(aDProgramAccounts);
    return newADProgramAccounts;
  }

  public int numberOfADProgramAccounts()
  {
    int number = aDProgramAccounts.size();
    return number;
  }

  public boolean hasADProgramAccounts()
  {
    boolean has = aDProgramAccounts.size() > 0;
    return has;
  }

  public int indexOfADProgramAccount(ADProgramAccount aADProgramAccount)
  {
    int index = aDProgramAccounts.indexOf(aADProgramAccount);
    return index;
  }
  /* Code from template association_GetMany */
  public DivingSeason getDivingSeason(int index)
  {
    DivingSeason aDivingSeason = divingSeasons.get(index);
    return aDivingSeason;
  }

  public List<DivingSeason> getDivingSeasons()
  {
    List<DivingSeason> newDivingSeasons = Collections.unmodifiableList(divingSeasons);
    return newDivingSeasons;
  }

  public int numberOfDivingSeasons()
  {
    int number = divingSeasons.size();
    return number;
  }

  public boolean hasDivingSeasons()
  {
    boolean has = divingSeasons.size() > 0;
    return has;
  }

  public int indexOfDivingSeason(DivingSeason aDivingSeason)
  {
    int index = divingSeasons.indexOf(aDivingSeason);
    return index;
  }
  /* Code from template association_GetMany */
  public Registration getRegistration(int index)
  {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations()
  {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations()
  {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registrations.indexOf(aRegistration);
    return index;
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
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
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
  /* Code from template association_GetMany */
  public Address getAddress(int index)
  {
    Address aAddress = addresses.get(index);
    return aAddress;
  }

  public List<Address> getAddresses()
  {
    List<Address> newAddresses = Collections.unmodifiableList(addresses);
    return newAddresses;
  }

  public int numberOfAddresses()
  {
    int number = addresses.size();
    return number;
  }

  public boolean hasAddresses()
  {
    boolean has = addresses.size() > 0;
    return has;
  }

  public int indexOfAddress(Address aAddress)
  {
    int index = addresses.indexOf(aAddress);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfADProgramAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addADProgramAccount(ADProgramAccount aADProgramAccount)
  {
    boolean wasAdded = false;
    if (aDProgramAccounts.contains(aADProgramAccount)) { return false; }
    DiveSafe existingDiveSafe = aADProgramAccount.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aADProgramAccount.setDiveSafe(this);
    }
    else
    {
      aDProgramAccounts.add(aADProgramAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeADProgramAccount(ADProgramAccount aADProgramAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aADProgramAccount, as it must always have a diveSafe
    if (!this.equals(aADProgramAccount.getDiveSafe()))
    {
      aDProgramAccounts.remove(aADProgramAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addADProgramAccountAt(ADProgramAccount aADProgramAccount, int index)
  {  
    boolean wasAdded = false;
    if(addADProgramAccount(aADProgramAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfADProgramAccounts()) { index = numberOfADProgramAccounts() - 1; }
      aDProgramAccounts.remove(aADProgramAccount);
      aDProgramAccounts.add(index, aADProgramAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveADProgramAccountAt(ADProgramAccount aADProgramAccount, int index)
  {
    boolean wasAdded = false;
    if(aDProgramAccounts.contains(aADProgramAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfADProgramAccounts()) { index = numberOfADProgramAccounts() - 1; }
      aDProgramAccounts.remove(aADProgramAccount);
      aDProgramAccounts.add(index, aADProgramAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addADProgramAccountAt(aADProgramAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDivingSeasons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DivingSeason addDivingSeason(Date aStartDate, Date aEndDate)
  {
    return new DivingSeason(aStartDate, aEndDate, this);
  }

  public boolean addDivingSeason(DivingSeason aDivingSeason)
  {
    boolean wasAdded = false;
    if (divingSeasons.contains(aDivingSeason)) { return false; }
    DiveSafe existingDiveSafe = aDivingSeason.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aDivingSeason.setDiveSafe(this);
    }
    else
    {
      divingSeasons.add(aDivingSeason);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDivingSeason(DivingSeason aDivingSeason)
  {
    boolean wasRemoved = false;
    //Unable to remove aDivingSeason, as it must always have a diveSafe
    if (!this.equals(aDivingSeason.getDiveSafe()))
    {
      divingSeasons.remove(aDivingSeason);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDivingSeasonAt(DivingSeason aDivingSeason, int index)
  {  
    boolean wasAdded = false;
    if(addDivingSeason(aDivingSeason))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDivingSeasons()) { index = numberOfDivingSeasons() - 1; }
      divingSeasons.remove(aDivingSeason);
      divingSeasons.add(index, aDivingSeason);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDivingSeasonAt(DivingSeason aDivingSeason, int index)
  {
    boolean wasAdded = false;
    if(divingSeasons.contains(aDivingSeason))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDivingSeasons()) { index = numberOfDivingSeasons() - 1; }
      divingSeasons.remove(aDivingSeason);
      divingSeasons.add(index, aDivingSeason);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDivingSeasonAt(aDivingSeason, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Registration addRegistration(int aTotalCost, DivingSeason aDivingSeason, Member aMember)
  {
    return new Registration(aTotalCost, this, aDivingSeason, aMember);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    DiveSafe existingDiveSafe = aRegistration.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aRegistration.setDiveSafe(this);
    }
    else
    {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistration, as it must always have a diveSafe
    if (!this.equals(aRegistration.getDiveSafe()))
    {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRegistrationAt(Registration aRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addRegistration(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registrations.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSafeDivingDaies()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SafeDivingDay addSafeDivingDay(Date aSafeDay, int aSafeDayNumber, DivingSeason aDivingSeason, Registration aRegistration)
  {
    return new SafeDivingDay(aSafeDay, aSafeDayNumber, this, aDivingSeason, aRegistration);
  }

  public boolean addSafeDivingDay(SafeDivingDay aSafeDivingDay)
  {
    boolean wasAdded = false;
    if (safeDivingDaies.contains(aSafeDivingDay)) { return false; }
    DiveSafe existingDiveSafe = aSafeDivingDay.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aSafeDivingDay.setDiveSafe(this);
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
    //Unable to remove aSafeDivingDay, as it must always have a diveSafe
    if (!this.equals(aSafeDivingDay.getDiveSafe()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, Hotel.HotelType aType, int aRating)
  {
    return new Hotel(aName, aType, aRating, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    DiveSafe existingDiveSafe = aHotel.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aHotel.setDiveSafe(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a diveSafe
    if (!this.equals(aHotel.getDiveSafe()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(double aWeight, int aPricePerWeek)
  {
    return new Equipment(aWeight, aPricePerWeek, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    DiveSafe existingDiveSafe = aEquipment.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aEquipment.setDiveSafe(this);
    }
    else
    {
      equipment.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a diveSafe
    if (!this.equals(aEquipment.getDiveSafe()))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
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
  /* Code from template association_AddManyToOne */
  public Bundle addBundle(int aPricePerWeek, double aDiscount, Equipment... allEquipment)
  {
    return new Bundle(aPricePerWeek, aDiscount, this, allEquipment);
  }

  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    DiveSafe existingDiveSafe = aBundle.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aBundle.setDiveSafe(this);
    }
    else
    {
      bundles.add(aBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundle, as it must always have a diveSafe
    if (!this.equals(aBundle.getDiveSafe()))
    {
      bundles.remove(aBundle);
      wasRemoved = true;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAddresses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Address addAddress(int aAddressNumber, String aStreet, String aCity, String aRegionOfIsland, Hotel aHotel)
  {
    return new Address(aAddressNumber, aStreet, aCity, aRegionOfIsland, aHotel, this);
  }

  public boolean addAddress(Address aAddress)
  {
    boolean wasAdded = false;
    if (addresses.contains(aAddress)) { return false; }
    DiveSafe existingDiveSafe = aAddress.getDiveSafe();
    boolean isNewDiveSafe = existingDiveSafe != null && !this.equals(existingDiveSafe);
    if (isNewDiveSafe)
    {
      aAddress.setDiveSafe(this);
    }
    else
    {
      addresses.add(aAddress);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAddress(Address aAddress)
  {
    boolean wasRemoved = false;
    //Unable to remove aAddress, as it must always have a diveSafe
    if (!this.equals(aAddress.getDiveSafe()))
    {
      addresses.remove(aAddress);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAddressAt(Address aAddress, int index)
  {  
    boolean wasAdded = false;
    if(addAddress(aAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAddresses()) { index = numberOfAddresses() - 1; }
      addresses.remove(aAddress);
      addresses.add(index, aAddress);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAddressAt(Address aAddress, int index)
  {
    boolean wasAdded = false;
    if(addresses.contains(aAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAddresses()) { index = numberOfAddresses() - 1; }
      addresses.remove(aAddress);
      addresses.add(index, aAddress);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAddressAt(aAddress, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (aDProgramAccounts.size() > 0)
    {
      ADProgramAccount aADProgramAccount = aDProgramAccounts.get(aDProgramAccounts.size() - 1);
      aADProgramAccount.delete();
      aDProgramAccounts.remove(aADProgramAccount);
    }
    
    while (divingSeasons.size() > 0)
    {
      DivingSeason aDivingSeason = divingSeasons.get(divingSeasons.size() - 1);
      aDivingSeason.delete();
      divingSeasons.remove(aDivingSeason);
    }
    
    while (registrations.size() > 0)
    {
      Registration aRegistration = registrations.get(registrations.size() - 1);
      aRegistration.delete();
      registrations.remove(aRegistration);
    }
    
    while (safeDivingDaies.size() > 0)
    {
      SafeDivingDay aSafeDivingDay = safeDivingDaies.get(safeDivingDaies.size() - 1);
      aSafeDivingDay.delete();
      safeDivingDaies.remove(aSafeDivingDay);
    }
    
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    while (equipment.size() > 0)
    {
      Equipment aEquipment = equipment.get(equipment.size() - 1);
      aEquipment.delete();
      equipment.remove(aEquipment);
    }
    
    while (bundles.size() > 0)
    {
      Bundle aBundle = bundles.get(bundles.size() - 1);
      aBundle.delete();
      bundles.remove(aBundle);
    }
    
    while (addresses.size() > 0)
    {
      Address aAddress = addresses.get(addresses.size() - 1);
      aAddress.delete();
      addresses.remove(aAddress);
    }
    
  }

}