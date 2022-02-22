package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 62 "DiveSafe.ump"
// line 167 "DiveSafe.ump"
// line 255 "DiveSafe.ump"
public class Hotel
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum HotelType { Villa, Resort, Suite, Inn, Motel, Hotel, Others }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private HotelType type;
  private int rating;

  //Hotel Associations
  private List<Registration> hotelClients;
  private DiveSafe diveSafe;
  private List<Address> hotelAddress;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, HotelType aType, int aRating, DiveSafe aDiveSafe)
  {
    name = aName;
    type = aType;
    rating = aRating;
    hotelClients = new ArrayList<Registration>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create hotel due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    hotelAddress = new ArrayList<Address>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(HotelType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setRating(int aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public HotelType getType()
  {
    return type;
  }

  public int getRating()
  {
    return rating;
  }
  /* Code from template association_GetMany */
  public Registration getHotelClient(int index)
  {
    Registration aHotelClient = hotelClients.get(index);
    return aHotelClient;
  }

  public List<Registration> getHotelClients()
  {
    List<Registration> newHotelClients = Collections.unmodifiableList(hotelClients);
    return newHotelClients;
  }

  public int numberOfHotelClients()
  {
    int number = hotelClients.size();
    return number;
  }

  public boolean hasHotelClients()
  {
    boolean has = hotelClients.size() > 0;
    return has;
  }

  public int indexOfHotelClient(Registration aHotelClient)
  {
    int index = hotelClients.indexOf(aHotelClient);
    return index;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_GetMany */
  public Address getHotelAddress(int index)
  {
    Address aHotelAddress = hotelAddress.get(index);
    return aHotelAddress;
  }

  public List<Address> getHotelAddress()
  {
    List<Address> newHotelAddress = Collections.unmodifiableList(hotelAddress);
    return newHotelAddress;
  }

  public int numberOfHotelAddress()
  {
    int number = hotelAddress.size();
    return number;
  }

  public boolean hasHotelAddress()
  {
    boolean has = hotelAddress.size() > 0;
    return has;
  }

  public int indexOfHotelAddress(Address aHotelAddress)
  {
    int index = hotelAddress.indexOf(aHotelAddress);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotelClients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addHotelClient(Registration aHotelClient)
  {
    boolean wasAdded = false;
    if (hotelClients.contains(aHotelClient)) { return false; }
    Hotel existingBookedHotel = aHotelClient.getBookedHotel();
    if (existingBookedHotel == null)
    {
      aHotelClient.setBookedHotel(this);
    }
    else if (!this.equals(existingBookedHotel))
    {
      existingBookedHotel.removeHotelClient(aHotelClient);
      addHotelClient(aHotelClient);
    }
    else
    {
      hotelClients.add(aHotelClient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotelClient(Registration aHotelClient)
  {
    boolean wasRemoved = false;
    if (hotelClients.contains(aHotelClient))
    {
      hotelClients.remove(aHotelClient);
      aHotelClient.setBookedHotel(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelClientAt(Registration aHotelClient, int index)
  {  
    boolean wasAdded = false;
    if(addHotelClient(aHotelClient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelClients()) { index = numberOfHotelClients() - 1; }
      hotelClients.remove(aHotelClient);
      hotelClients.add(index, aHotelClient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelClientAt(Registration aHotelClient, int index)
  {
    boolean wasAdded = false;
    if(hotelClients.contains(aHotelClient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelClients()) { index = numberOfHotelClients() - 1; }
      hotelClients.remove(aHotelClient);
      hotelClients.add(index, aHotelClient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelClientAt(aHotelClient, index);
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
      existingDiveSafe.removeHotel(this);
    }
    diveSafe.addHotel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfHotelAddressValid()
  {
    boolean isValid = numberOfHotelAddress() >= minimumNumberOfHotelAddress();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotelAddress()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Address addHotelAddress(int aAddressNumber, String aStreet, String aCity, String aRegionOfIsland, DiveSafe aDiveSafe)
  {
    Address aNewHotelAddress = new Address(aAddressNumber, aStreet, aCity, aRegionOfIsland, this, aDiveSafe);
    return aNewHotelAddress;
  }

  public boolean addHotelAddress(Address aHotelAddress)
  {
    boolean wasAdded = false;
    if (hotelAddress.contains(aHotelAddress)) { return false; }
    Hotel existingHotel = aHotelAddress.getHotel();
    boolean isNewHotel = existingHotel != null && !this.equals(existingHotel);

    if (isNewHotel && existingHotel.numberOfHotelAddress() <= minimumNumberOfHotelAddress())
    {
      return wasAdded;
    }
    if (isNewHotel)
    {
      aHotelAddress.setHotel(this);
    }
    else
    {
      hotelAddress.add(aHotelAddress);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotelAddress(Address aHotelAddress)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotelAddress, as it must always have a hotel
    if (this.equals(aHotelAddress.getHotel()))
    {
      return wasRemoved;
    }

    //hotel already at minimum (1)
    if (numberOfHotelAddress() <= minimumNumberOfHotelAddress())
    {
      return wasRemoved;
    }

    hotelAddress.remove(aHotelAddress);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAddressAt(Address aHotelAddress, int index)
  {  
    boolean wasAdded = false;
    if(addHotelAddress(aHotelAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelAddress()) { index = numberOfHotelAddress() - 1; }
      hotelAddress.remove(aHotelAddress);
      hotelAddress.add(index, aHotelAddress);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAddressAt(Address aHotelAddress, int index)
  {
    boolean wasAdded = false;
    if(hotelAddress.contains(aHotelAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelAddress()) { index = numberOfHotelAddress() - 1; }
      hotelAddress.remove(aHotelAddress);
      hotelAddress.add(index, aHotelAddress);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAddressAt(aHotelAddress, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !hotelClients.isEmpty() )
    {
      hotelClients.get(0).setBookedHotel(null);
    }
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeHotel(this);
    }
    for(int i=hotelAddress.size(); i > 0; i--)
    {
      Address aHotelAddress = hotelAddress.get(i - 1);
      aHotelAddress.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "rating" + ":" + getRating()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null");
  }
}