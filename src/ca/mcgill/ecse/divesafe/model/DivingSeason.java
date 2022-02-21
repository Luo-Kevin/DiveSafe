package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 22 "DiveSafe.ump"
// line 123 "DiveSafe.ump"
// line 207 "DiveSafe.ump"
public class DivingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DivingSeason Attributes
  private Date startDate;
  private Date endDate;

  //DivingSeason Associations
  private List<User> users;
  private List<SafeDivingDay> safeDivingDaies;
  private List<Registration> registrations;
  private DiveSafe diveSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DivingSeason(Date aStartDate, Date aEndDate, DiveSafe aDiveSafe)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    users = new ArrayList<User>();
    safeDivingDaies = new ArrayList<SafeDivingDay>();
    registrations = new ArrayList<Registration>();
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
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
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
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    DivingSeason existingDivingSeason = aUser.getDivingSeason();
    boolean isNewDivingSeason = existingDivingSeason != null && !this.equals(existingDivingSeason);
    if (isNewDivingSeason)
    {
      aUser.setDivingSeason(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a divingSeason
    if (!this.equals(aUser.getDivingSeason()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSafeDivingDaies()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SafeDivingDay addSafeDivingDay(Date aSafeDay, int aSafeDayNumber, DiveSafe aDiveSafe, Registration aRegistration)
  {
    return new SafeDivingDay(aSafeDay, aSafeDayNumber, aDiveSafe, this, aRegistration);
  }

  public boolean addSafeDivingDay(SafeDivingDay aSafeDivingDay)
  {
    boolean wasAdded = false;
    if (safeDivingDaies.contains(aSafeDivingDay)) { return false; }
    DivingSeason existingDivingSeason = aSafeDivingDay.getDivingSeason();
    boolean isNewDivingSeason = existingDivingSeason != null && !this.equals(existingDivingSeason);
    if (isNewDivingSeason)
    {
      aSafeDivingDay.setDivingSeason(this);
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
    //Unable to remove aSafeDivingDay, as it must always have a divingSeason
    if (!this.equals(aSafeDivingDay.getDivingSeason()))
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
  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Registration addRegistration(int aTotalCost, DiveSafe aDiveSafe, Member aMember)
  {
    return new Registration(aTotalCost, aDiveSafe, this, aMember);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    DivingSeason existingDivingSeason = aRegistration.getDivingSeason();
    boolean isNewDivingSeason = existingDivingSeason != null && !this.equals(existingDivingSeason);
    if (isNewDivingSeason)
    {
      aRegistration.setDivingSeason(this);
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
    //Unable to remove aRegistration, as it must always have a divingSeason
    if (!this.equals(aRegistration.getDivingSeason()))
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
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (safeDivingDaies.size() > 0)
    {
      SafeDivingDay aSafeDivingDay = safeDivingDaies.get(safeDivingDaies.size() - 1);
      aSafeDivingDay.delete();
      safeDivingDaies.remove(aSafeDivingDay);
    }
    
    while (registrations.size() > 0)
    {
      Registration aRegistration = registrations.get(registrations.size() - 1);
      aRegistration.delete();
      registrations.remove(aRegistration);
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