package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 32 "DiveSafe.ump"
// line 138 "DiveSafe.ump"
// line 226 "DiveSafe.ump"
public class Guide extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private int pricePerWeek;

  //Guide Associations
  private List<Member> assignedMember;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aAccountName, String aAccountPassword, DiveSafe aDiveSafe, String aName, String aEmergencyContact, DivingSeason aDivingSeason, int aPricePerWeek)
  {
    super(aAccountName, aAccountPassword, aDiveSafe, aName, aEmergencyContact, aDivingSeason);
    pricePerWeek = aPricePerWeek;
    assignedMember = new ArrayList<Member>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }
  /* Code from template association_GetMany */
  public Member getAssignedMember(int index)
  {
    Member aAssignedMember = assignedMember.get(index);
    return aAssignedMember;
  }

  public List<Member> getAssignedMember()
  {
    List<Member> newAssignedMember = Collections.unmodifiableList(assignedMember);
    return newAssignedMember;
  }

  public int numberOfAssignedMember()
  {
    int number = assignedMember.size();
    return number;
  }

  public boolean hasAssignedMember()
  {
    boolean has = assignedMember.size() > 0;
    return has;
  }

  public int indexOfAssignedMember(Member aAssignedMember)
  {
    int index = assignedMember.indexOf(aAssignedMember);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignedMember()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAssignedMember(Member aAssignedMember)
  {
    boolean wasAdded = false;
    if (assignedMember.contains(aAssignedMember)) { return false; }
    Guide existingAssignedGuide = aAssignedMember.getAssignedGuide();
    if (existingAssignedGuide == null)
    {
      aAssignedMember.setAssignedGuide(this);
    }
    else if (!this.equals(existingAssignedGuide))
    {
      existingAssignedGuide.removeAssignedMember(aAssignedMember);
      addAssignedMember(aAssignedMember);
    }
    else
    {
      assignedMember.add(aAssignedMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignedMember(Member aAssignedMember)
  {
    boolean wasRemoved = false;
    if (assignedMember.contains(aAssignedMember))
    {
      assignedMember.remove(aAssignedMember);
      aAssignedMember.setAssignedGuide(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignedMemberAt(Member aAssignedMember, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedMember(aAssignedMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedMember()) { index = numberOfAssignedMember() - 1; }
      assignedMember.remove(aAssignedMember);
      assignedMember.add(index, aAssignedMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedMemberAt(Member aAssignedMember, int index)
  {
    boolean wasAdded = false;
    if(assignedMember.contains(aAssignedMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedMember()) { index = numberOfAssignedMember() - 1; }
      assignedMember.remove(aAssignedMember);
      assignedMember.add(index, aAssignedMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedMemberAt(aAssignedMember, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !assignedMember.isEmpty() )
    {
      assignedMember.get(0).setAssignedGuide(null);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "]";
  }
}