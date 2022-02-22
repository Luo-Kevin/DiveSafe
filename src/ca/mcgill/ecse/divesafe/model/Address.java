package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 89 "DiveSafe.ump"
// line 197 "DiveSafe.ump"
// line 270 "DiveSafe.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private int addressNumber;
  private String street;
  private String city;
  private String regionOfIsland;

  //Address Associations
  private Hotel hotel;
  private DiveSafe diveSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(int aAddressNumber, String aStreet, String aCity, String aRegionOfIsland, Hotel aHotel, DiveSafe aDiveSafe)
  {
    addressNumber = aAddressNumber;
    street = aStreet;
    city = aCity;
    regionOfIsland = aRegionOfIsland;
    boolean didAddHotel = setHotel(aHotel);
    if (!didAddHotel)
    {
      throw new RuntimeException("Unable to create hotelAddress due to hotel. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create address due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddressNumber(int aAddressNumber)
  {
    boolean wasSet = false;
    addressNumber = aAddressNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreet(String aStreet)
  {
    boolean wasSet = false;
    street = aStreet;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setRegionOfIsland(String aRegionOfIsland)
  {
    boolean wasSet = false;
    regionOfIsland = aRegionOfIsland;
    wasSet = true;
    return wasSet;
  }

  public int getAddressNumber()
  {
    return addressNumber;
  }

  public String getStreet()
  {
    return street;
  }

  public String getCity()
  {
    return city;
  }

  public String getRegionOfIsland()
  {
    return regionOfIsland;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    //Must provide hotel to hotelAddress
    if (aHotel == null)
    {
      return wasSet;
    }

    if (hotel != null && hotel.numberOfHotelAddress() <= Hotel.minimumNumberOfHotelAddress())
    {
      return wasSet;
    }

    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      boolean didRemove = existingHotel.removeHotelAddress(this);
      if (!didRemove)
      {
        hotel = existingHotel;
        return wasSet;
      }
    }
    hotel.addHotelAddress(this);
    wasSet = true;
    return wasSet;
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
      existingDiveSafe.removeAddress(this);
    }
    diveSafe.addAddress(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Hotel placeholderHotel = hotel;
    this.hotel = null;
    if(placeholderHotel != null)
    {
      placeholderHotel.removeHotelAddress(this);
    }
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeAddress(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "addressNumber" + ":" + getAddressNumber()+ "," +
            "street" + ":" + getStreet()+ "," +
            "city" + ":" + getCity()+ "," +
            "regionOfIsland" + ":" + getRegionOfIsland()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null");
  }
}