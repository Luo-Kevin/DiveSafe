package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 22 "DiveSafe.ump"
// line 123 "DiveSafe.ump"
// line 221 "DiveSafe.ump"
public class DivingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DivingSeason Attributes
  private Date startDate;
  private Date endDate;

  //DivingSeason Associations
  private List<User> userBase;
  private List<SafeDivingDay> safeDivindDays;
  private List<Registration> correspondingRegistrations;
  private DiveSafe diveSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DivingSeason(Date aStartDate, Date aEndDate, DiveSafe aDiveSafe)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    userBase = new ArrayList<User>();
    safeDivindDays = new ArrayList<SafeDivingDay>();
    correspondingRegistrations = new ArrayList<Registration>();
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create divingSeason due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetMany */
  public User getUserBase(int index)
  {
    User aUserBase = userBase.get(index);
    return aUserBase;
  }

  public List<User> getUserBase()
  {
    List<User> newUserBase = Collections.unmodifiableList(userBase);
    return newUserBase;
  }

  public int numberOfUserBase()
  {
    int number = userBase.size();
    return number;
  }

  public boolean hasUserBase()
  {
    boolean has = userBase.size() > 0;
    return has;
  }

  public int indexOfUserBase(User aUserBase)
  {
    int index = userBase.indexOf(aUserBase);
    return index;
  }
  /* Code from template association_GetMany */
  public SafeDivingDay getSafeDivindDay(int index)
  {
    SafeDivingDay aSafeDivindDay = safeDivindDays.get(index);
    return aSafeDivindDay;
  }

  public List<SafeDivingDay> getSafeDivindDays()
  {
    List<SafeDivingDay> newSafeDivindDays = Collections.unmodifiableList(safeDivindDays);
    return newSafeDivindDays;
  }

  public int numberOfSafeDivindDays()
  {
    int number = safeDivindDays.size();
    return number;
  }

  public boolean hasSafeDivindDays()
  {
    boolean has = safeDivindDays.size() > 0;
    return has;
  }

  public int indexOfSafeDivindDay(SafeDivingDay aSafeDivindDay)
  {
    int index = safeDivindDays.indexOf(aSafeDivindDay);
    return index;
  }
  /* Code from template association_GetMany */
  public Registration getCorrespondingRegistration(int index)
  {
    Registration aCorrespondingRegistration = correspondingRegistrations.get(index);
    return aCorrespondingRegistration;
  }

  public List<Registration> getCorrespondingRegistrations()
  {
    List<Registration> newCorrespondingRegistrations = Collections.unmodifiableList(correspondingRegistrations);
    return newCorrespondingRegistrations;
  }

  public int numberOfCorrespondingRegistrations()
  {
    int number = correspondingRegistrations.size();
    return number;
  }

  public boolean hasCorrespondingRegistrations()
  {
    boolean has = correspondingRegistrations.size() > 0;
    return has;
  }

  public int indexOfCorrespondingRegistration(Registration aCorrespondingRegistration)
  {
    int index = correspondingRegistrations.indexOf(aCorrespondingRegistration);
    return index;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserBase()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUserBase(User aUserBase)
  {
    boolean wasAdded = false;
    if (userBase.contains(aUserBase)) { return false; }
    DivingSeason existingDivingSeason = aUserBase.getDivingSeason();
    boolean isNewDivingSeason = existingDivingSeason != null && !this.equals(existingDivingSeason);
    if (isNewDivingSeason)
    {
      aUserBase.setDivingSeason(this);
    }
    else
    {
      userBase.add(aUserBase);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserBase(User aUserBase)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserBase, as it must always have a divingSeason
    if (!this.equals(aUserBase.getDivingSeason()))
    {
      userBase.remove(aUserBase);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserBaseAt(User aUserBase, int index)
  {  
    boolean wasAdded = false;
    if(addUserBase(aUserBase))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserBase()) { index = numberOfUserBase() - 1; }
      userBase.remove(aUserBase);
      userBase.add(index, aUserBase);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserBaseAt(User aUserBase, int index)
  {
    boolean wasAdded = false;
    if(userBase.contains(aUserBase))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserBase()) { index = numberOfUserBase() - 1; }
      userBase.remove(aUserBase);
      userBase.add(index, aUserBase);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserBaseAt(aUserBase, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSafeDivindDays()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SafeDivingDay addSafeDivindDay(Date aSafeDay, int aSafeDayNumber, DiveSafe aDiveSafe, Registration aRegistration)
  {
    return new SafeDivingDay(aSafeDay, aSafeDayNumber, aDiveSafe, this, aRegistration);
  }

  public boolean addSafeDivindDay(SafeDivingDay aSafeDivindDay)
  {
    boolean wasAdded = false;
    if (safeDivindDays.contains(aSafeDivindDay)) { return false; }
    DivingSeason existingDivingSeason = aSafeDivindDay.getDivingSeason();
    boolean isNewDivingSeason = existingDivingSeason != null && !this.equals(existingDivingSeason);
    if (isNewDivingSeason)
    {
      aSafeDivindDay.setDivingSeason(this);
    }
    else
    {
      safeDivindDays.add(aSafeDivindDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSafeDivindDay(SafeDivingDay aSafeDivindDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aSafeDivindDay, as it must always have a divingSeason
    if (!this.equals(aSafeDivindDay.getDivingSeason()))
    {
      safeDivindDays.remove(aSafeDivindDay);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSafeDivindDayAt(SafeDivingDay aSafeDivindDay, int index)
  {  
    boolean wasAdded = false;
    if(addSafeDivindDay(aSafeDivindDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSafeDivindDays()) { index = numberOfSafeDivindDays() - 1; }
      safeDivindDays.remove(aSafeDivindDay);
      safeDivindDays.add(index, aSafeDivindDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSafeDivindDayAt(SafeDivingDay aSafeDivindDay, int index)
  {
    boolean wasAdded = false;
    if(safeDivindDays.contains(aSafeDivindDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSafeDivindDays()) { index = numberOfSafeDivindDays() - 1; }
      safeDivindDays.remove(aSafeDivindDay);
      safeDivindDays.add(index, aSafeDivindDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSafeDivindDayAt(aSafeDivindDay, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCorrespondingRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Registration addCorrespondingRegistration(int aTotalCost, DiveSafe aDiveSafe, Member aAssociatedMember)
  {
    return new Registration(aTotalCost, aDiveSafe, this, aAssociatedMember);
  }

  public boolean addCorrespondingRegistration(Registration aCorrespondingRegistration)
  {
    boolean wasAdded = false;
    if (correspondingRegistrations.contains(aCorrespondingRegistration)) { return false; }
    DivingSeason existingDivingSeason = aCorrespondingRegistration.getDivingSeason();
    boolean isNewDivingSeason = existingDivingSeason != null && !this.equals(existingDivingSeason);
    if (isNewDivingSeason)
    {
      aCorrespondingRegistration.setDivingSeason(this);
    }
    else
    {
      correspondingRegistrations.add(aCorrespondingRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCorrespondingRegistration(Registration aCorrespondingRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aCorrespondingRegistration, as it must always have a divingSeason
    if (!this.equals(aCorrespondingRegistration.getDivingSeason()))
    {
      correspondingRegistrations.remove(aCorrespondingRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCorrespondingRegistrationAt(Registration aCorrespondingRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addCorrespondingRegistration(aCorrespondingRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCorrespondingRegistrations()) { index = numberOfCorrespondingRegistrations() - 1; }
      correspondingRegistrations.remove(aCorrespondingRegistration);
      correspondingRegistrations.add(index, aCorrespondingRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCorrespondingRegistrationAt(Registration aCorrespondingRegistration, int index)
  {
    boolean wasAdded = false;
    if(correspondingRegistrations.contains(aCorrespondingRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCorrespondingRegistrations()) { index = numberOfCorrespondingRegistrations() - 1; }
      correspondingRegistrations.remove(aCorrespondingRegistration);
      correspondingRegistrations.add(index, aCorrespondingRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCorrespondingRegistrationAt(aCorrespondingRegistration, index);
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
      existingDiveSafe.removeDivingSeason(this);
    }
    diveSafe.addDivingSeason(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (userBase.size() > 0)
    {
      User aUserBase = userBase.get(userBase.size() - 1);
      aUserBase.delete();
      userBase.remove(aUserBase);
    }
    
    while (safeDivindDays.size() > 0)
    {
      SafeDivingDay aSafeDivindDay = safeDivindDays.get(safeDivindDays.size() - 1);
      aSafeDivindDay.delete();
      safeDivindDays.remove(aSafeDivindDay);
    }
    
    while (correspondingRegistrations.size() > 0)
    {
      Registration aCorrespondingRegistration = correspondingRegistrations.get(correspondingRegistrations.size() - 1);
      aCorrespondingRegistration.delete();
      correspondingRegistrations.remove(aCorrespondingRegistration);
    }
    
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeDivingSeason(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null");
  }
}