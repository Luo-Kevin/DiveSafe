/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.divesafe.model;
import java.util.*;

// line 57 "../../../../../AssignmentStates.ump"
// line 54 "../../../../../DiveSafe.ump"
// line 192 "../../../../../DiveSafe.ump"
public class Member extends NamedUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int numDays;
  private boolean guideRequired;
  private boolean hotelRequired;

  //Member State Machines
  public enum MemberStatus { Unassigned, Assigned, Paid, Started, Finished, Banned, Cancelled }
  private MemberStatus memberStatus;

  //Member Associations
  private DiveSafe diveSafe;
  private Assignment assignment;
  private List<ItemBooking> itemBookings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aEmail, String aPassword, String aName, String aEmergencyContact, int aNumDays, boolean aGuideRequired, boolean aHotelRequired, DiveSafe aDiveSafe)
  {
    super(aEmail, aPassword, aName, aEmergencyContact);
    numDays = aNumDays;
    guideRequired = aGuideRequired;
    hotelRequired = aHotelRequired;
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create member due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    itemBookings = new ArrayList<ItemBooking>();
    setMemberStatus(MemberStatus.Unassigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumDays(int aNumDays)
  {
    boolean wasSet = false;
    numDays = aNumDays;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideRequired(boolean aGuideRequired)
  {
    boolean wasSet = false;
    guideRequired = aGuideRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelRequired(boolean aHotelRequired)
  {
    boolean wasSet = false;
    hotelRequired = aHotelRequired;
    wasSet = true;
    return wasSet;
  }

  public int getNumDays()
  {
    return numDays;
  }

  public boolean getGuideRequired()
  {
    return guideRequired;
  }

  public boolean getHotelRequired()
  {
    return hotelRequired;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isGuideRequired()
  {
    return guideRequired;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHotelRequired()
  {
    return hotelRequired;
  }

  public String getMemberStatusFullName()
  {
    String answer = memberStatus.toString();
    return answer;
  }

  public MemberStatus getMemberStatus()
  {
    return memberStatus;
  }

  public boolean assign(Guide guide)
  {
    boolean wasEventProcessed = false;
    
    MemberStatus aMemberStatus = memberStatus;
    switch (aMemberStatus)
    {
      case Unassigned:
        if (doAssign(guide))
        {
          setMemberStatus(MemberStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean confirmPayment()
  {
    boolean wasEventProcessed = false;
    
    MemberStatus aMemberStatus = memberStatus;
    switch (aMemberStatus)
    {
      case Assigned:
        setMemberStatus(MemberStatus.Paid);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTrip(int day)
  {
    boolean wasEventProcessed = false;
    
    MemberStatus aMemberStatus = memberStatus;
    switch (aMemberStatus)
    {
      case Assigned:
        if (day==this.getAssignment().getStartDay())
        {
          setMemberStatus(MemberStatus.Banned);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        if (day==this.getAssignment().getStartDay())
        {
          setMemberStatus(MemberStatus.Started);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelTrip()
  {
    boolean wasEventProcessed = false;
    
    MemberStatus aMemberStatus = memberStatus;
    switch (aMemberStatus)
    {
      case Assigned:
        setMemberStatus(MemberStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        setMemberStatus(MemberStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        setMemberStatus(MemberStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    MemberStatus aMemberStatus = memberStatus;
    switch (aMemberStatus)
    {
      case Started:
        setMemberStatus(MemberStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setMemberStatus(MemberStatus aMemberStatus)
  {
    memberStatus = aMemberStatus;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
  }

  public boolean hasAssignment()
  {
    boolean has = assignment != null;
    return has;
  }
  /* Code from template association_GetMany */
  public ItemBooking getItemBooking(int index)
  {
    ItemBooking aItemBooking = itemBookings.get(index);
    return aItemBooking;
  }

  public List<ItemBooking> getItemBookings()
  {
    List<ItemBooking> newItemBookings = Collections.unmodifiableList(itemBookings);
    return newItemBookings;
  }

  public int numberOfItemBookings()
  {
    int number = itemBookings.size();
    return number;
  }

  public boolean hasItemBookings()
  {
    boolean has = itemBookings.size() > 0;
    return has;
  }

  public int indexOfItemBooking(ItemBooking aItemBooking)
  {
    int index = itemBookings.indexOf(aItemBooking);
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
      existingDiveSafe.removeMember(this);
    }
    diveSafe.addMember(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAssignment(Assignment aNewAssignment)
  {
    boolean wasSet = false;
    if (assignment != null && !assignment.equals(aNewAssignment) && equals(assignment.getMember()))
    {
      //Unable to setAssignment, as existing assignment would become an orphan
      return wasSet;
    }

    assignment = aNewAssignment;
    Member anOldMember = aNewAssignment != null ? aNewAssignment.getMember() : null;

    if (!this.equals(anOldMember))
    {
      if (anOldMember != null)
      {
        anOldMember.assignment = null;
      }
      if (assignment != null)
      {
        assignment.setMember(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItemBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ItemBooking addItemBooking(int aQuantity, DiveSafe aDiveSafe, Item aItem)
  {
    return new ItemBooking(aQuantity, aDiveSafe, this, aItem);
  }

  public boolean addItemBooking(ItemBooking aItemBooking)
  {
    boolean wasAdded = false;
    if (itemBookings.contains(aItemBooking)) { return false; }
    Member existingMember = aItemBooking.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aItemBooking.setMember(this);
    }
    else
    {
      itemBookings.add(aItemBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItemBooking(ItemBooking aItemBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aItemBooking, as it must always have a member
    if (!this.equals(aItemBooking.getMember()))
    {
      itemBookings.remove(aItemBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemBookingAt(ItemBooking aItemBooking, int index)
  {  
    boolean wasAdded = false;
    if(addItemBooking(aItemBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItemBookings()) { index = numberOfItemBookings() - 1; }
      itemBookings.remove(aItemBooking);
      itemBookings.add(index, aItemBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemBookingAt(ItemBooking aItemBooking, int index)
  {
    boolean wasAdded = false;
    if(itemBookings.contains(aItemBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItemBookings()) { index = numberOfItemBookings() - 1; }
      itemBookings.remove(aItemBooking);
      itemBookings.add(index, aItemBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemBookingAt(aItemBooking, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeMember(this);
    }
    Assignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.delete();
    }
    for(int i=itemBookings.size(); i > 0; i--)
    {
      ItemBooking aItemBooking = itemBookings.get(i - 1);
      aItemBooking.delete();
    }
    super.delete();
  }


  /**
   * Method for the members to be assigned to their schedule and to their guide if they asked for one.
   * @author Siger Ma
   * @param guide Guide to be assigned to the member if he asked for one
   */
  // line 87 "../../../../../AssignmentStates.ump"
   public boolean doAssign(Guide guide){
    int numDaysRequest = this.getNumDays();
    boolean needGuide = this.getGuideRequired();
    if (!needGuide) {
      diveSafe.addAssignment(1, numDaysRequest, this);
      return true;
    } else {
      if (numDaysRequest <= guide.availableForPeriod()) {
        Assignment assignment = diveSafe.addAssignment(guide.takenForPeriod() + 1, guide.takenForPeriod() + numDaysRequest, this);
        assignment.setGuide(guide);
        return true;
      }
    }
    return false;
  }

  // line 104 "../../../../../AssignmentStates.ump"
   public void publicSetMemberStatus(MemberStatus aMemberStatus){
    setMemberStatus(aMemberStatus);
  }

  // line 61 "../../../../../DiveSafe.ump"
   public static  Member getWithEmail(String email){
    if (User.getWithEmail(email) instanceof Member member) {
      return member;
    }
    return null;
  }

  // line 68 "../../../../../DiveSafe.ump"
   public static  boolean hasWithEmail(String email){
    return getWithEmail(email) != null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "numDays" + ":" + getNumDays()+ "," +
            "guideRequired" + ":" + getGuideRequired()+ "," +
            "hotelRequired" + ":" + getHotelRequired()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}