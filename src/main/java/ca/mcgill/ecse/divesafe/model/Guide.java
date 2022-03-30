/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.divesafe.model;
import java.util.*;

// line 1 "../../../../../AssignmentStates.ump"
// line 39 "../../../../../DiveSafe.ump"
// line 187 "../../../../../DiveSafe.ump"
public class Guide extends NamedUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide State Machines
  public enum AvailableStatus { Available, Taken }
  private AvailableStatus availableStatus;

  //Guide Associations
  private DiveSafe diveSafe;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aEmail, String aPassword, String aName, String aEmergencyContact, DiveSafe aDiveSafe)
  {
    super(aEmail, aPassword, aName, aEmergencyContact);
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create guide due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignments = new ArrayList<Assignment>();
    setAvailableStatus(AvailableStatus.Available);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getAvailableStatusFullName()
  {
    String answer = availableStatus.toString();
    return answer;
  }

  public AvailableStatus getAvailableStatus()
  {
    return availableStatus;
  }

  public boolean bookGuide()
  {
    boolean wasEventProcessed = false;
    
    AvailableStatus aAvailableStatus = availableStatus;
    switch (aAvailableStatus)
    {
      case Available:
        // line 4 "../../../../../AssignmentStates.ump"
        doBookGuide();
        setAvailableStatus(AvailableStatus.Available);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition1__()
  {
    boolean wasEventProcessed = false;
    
    AvailableStatus aAvailableStatus = availableStatus;
    switch (aAvailableStatus)
    {
      case Available:
        if (availableForPeriod()<=0)
        {
          setAvailableStatus(AvailableStatus.Taken);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAvailableStatus(AvailableStatus aAvailableStatus)
  {
    availableStatus = aAvailableStatus;

    // entry actions and do activities
    switch(availableStatus)
    {
      case Available:
        __autotransition1__();
        break;
    }
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
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
      existingDiveSafe.removeGuide(this);
    }
    diveSafe.addGuide(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Guide existingGuide = aAssignment.getGuide();
    if (existingGuide == null)
    {
      aAssignment.setGuide(this);
    }
    else if (!this.equals(existingGuide))
    {
      existingGuide.removeAssignment(aAssignment);
      addAssignment(aAssignment);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    if (assignments.contains(aAssignment))
    {
      assignments.remove(aAssignment);
      aAssignment.setGuide(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeGuide(this);
    }
    while( !assignments.isEmpty() )
    {
      assignments.get(0).setGuide(null);
    }
    super.delete();
  }


  /**
   * Method to count the number of days the guide is already taken
   * @author Siger Ma
   */
  // line 16 "../../../../../AssignmentStates.ump"
   public int takenForPeriod(){
    int daysTaken = 0;
      List <Assignment> currentAssignments = getAssignments();

      for (Assignment assignment : currentAssignments) {
        int startDate = assignment.getStartDay();
        int endDate = assignment.getEndDay();
        daysTaken = daysTaken + (endDate - startDate);
      }

      return daysTaken;
  }


  /**
   * Method to count the number of days the guide is still available
   * @author Siger Ma
   */
  // line 32 "../../../../../AssignmentStates.ump"
   public int availableForPeriod(){
    int numOfDaysAvailable = 0;
    int daysTaken = takenForPeriod();
    int numOfDaysInSeason = getDiveSafe().getNumDays();

    numOfDaysAvailable = numOfDaysInSeason - daysTaken;
    return numOfDaysAvailable;
  }


  /**
   * Method for the guides to be booked and be assigned to members
   * @author Siger Ma
   */
  // line 44 "../../../../../AssignmentStates.ump"
   public void doBookGuide(){
    List<Member> currentMembers = getDiveSafe().getMembers();
    for (Member member : currentMembers) {
      member.assign(this);
    }
  }

  // line 43 "../../../../../DiveSafe.ump"
   public static  Guide getWithEmail(String email){
    if (User.getWithEmail(email) instanceof Guide guide) {
      return guide;
    }
    return null;
  }

  // line 50 "../../../../../DiveSafe.ump"
   public static  boolean hasWithEmail(String email){
    return getWithEmail(email) != null;
  }

}