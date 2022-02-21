package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 55 "DiveSafe.ump"
// line 158 "DiveSafe.ump"
// line 231 "DiveSafe.ump"
public class SafeDivingDay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SafeDivingDay Attributes
  private Date safeDay;
  private int safeDayNumber;

  //SafeDivingDay Associations
  private DiveSafe diveSafe;
  private DivingSeason divingSeason;
  private Registration registration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SafeDivingDay(Date aSafeDay, int aSafeDayNumber, DiveSafe aDiveSafe, DivingSeason aDivingSeason, Registration aRegistration)
  {
    safeDay = aSafeDay;
    safeDayNumber = aSafeDayNumber;
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create safeDivingDay due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDivingSeason = setDivingSeason(aDivingSeason);
    if (!didAddDivingSeason)
    {
      throw new RuntimeException("Unable to create safeDivingDay due to divingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddRegistration = setRegistration(aRegistration);
    if (!didAddRegistration)
    {
      throw new RuntimeException("Unable to create safeDivingDay due to registration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSafeDay(Date aSafeDay)
  {
    boolean wasSet = false;
    safeDay = aSafeDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setSafeDayNumber(int aSafeDayNumber)
  {
    boolean wasSet = false;
    safeDayNumber = aSafeDayNumber;
    wasSet = true;
    return wasSet;
  }

  public Date getSafeDay()
  {
    return safeDay;
  }

  public int getSafeDayNumber()
  {
    return safeDayNumber;
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
  public Registration getRegistration()
  {
    return registration;
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
      existingDiveSafe.removeSafeDivingDay(this);
    }
    diveSafe.addSafeDivingDay(this);
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
      existingDivingSeason.removeSafeDivingDay(this);
    }
    divingSeason.addSafeDivingDay(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRegistration(Registration aRegistration)
  {
    boolean wasSet = false;
    if (aRegistration == null)
    {
      return wasSet;
    }

    Registration existingRegistration = registration;
    registration = aRegistration;
    if (existingRegistration != null && !existingRegistration.equals(aRegistration))
    {
      existingRegistration.removeSafeDivingDay(this);
    }
    registration.addSafeDivingDay(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeSafeDivingDay(this);
    }
    DivingSeason placeholderDivingSeason = divingSeason;
    this.divingSeason = null;
    if(placeholderDivingSeason != null)
    {
      placeholderDivingSeason.removeSafeDivingDay(this);
    }
    Registration placeholderRegistration = registration;
    this.registration = null;
    if(placeholderRegistration != null)
    {
      placeholderRegistration.removeSafeDivingDay(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "safeDayNumber" + ":" + getSafeDayNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "safeDay" + "=" + (getSafeDay() != null ? !getSafeDay().equals(this)  ? getSafeDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "divingSeason = "+(getDivingSeason()!=null?Integer.toHexString(System.identityHashCode(getDivingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null");
  }
}