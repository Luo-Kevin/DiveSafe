package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 39 "DiveSafe.ump"
// line 147 "DiveSafe.ump"
// line 231 "DiveSafe.ump"
public class Member extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int durationOfExpedition;
  private boolean withGuide;
  private boolean withHotel;

  //Member Associations
  private Registration registration;
  private Guide assignedGuide;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aAccountName, String aAccountPassword, DiveSafe aDiveSafe, String aName, String aEmergencyContact, DivingSeason aDivingSeason, int aDurationOfExpedition, boolean aWithGuide, boolean aWithHotel, Registration aRegistration)
  {
    super(aAccountName, aAccountPassword, aDiveSafe, aName, aEmergencyContact, aDivingSeason);
    durationOfExpedition = aDurationOfExpedition;
    withGuide = aWithGuide;
    withHotel = aWithHotel;
    if (aRegistration == null || aRegistration.getAssociatedMember() != null)
    {
      throw new RuntimeException("Unable to create Member due to aRegistration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = aRegistration;
  }

  public Member(String aAccountName, String aAccountPassword, DiveSafe aDiveSafe, String aName, String aEmergencyContact, DivingSeason aDivingSeason, int aDurationOfExpedition, boolean aWithGuide, boolean aWithHotel, int aTotalCostForRegistration, DiveSafe aDiveSafeForRegistration, DivingSeason aDivingSeasonForRegistration)
  {
    super(aAccountName, aAccountPassword, aDiveSafe, aName, aEmergencyContact, aDivingSeason);
    durationOfExpedition = aDurationOfExpedition;
    withGuide = aWithGuide;
    withHotel = aWithHotel;
    registration = new Registration(aTotalCostForRegistration, aDiveSafeForRegistration, aDivingSeasonForRegistration, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDurationOfExpedition(int aDurationOfExpedition)
  {
    boolean wasSet = false;
    durationOfExpedition = aDurationOfExpedition;
    wasSet = true;
    return wasSet;
  }

  public boolean setWithGuide(boolean aWithGuide)
  {
    boolean wasSet = false;
    withGuide = aWithGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setWithHotel(boolean aWithHotel)
  {
    boolean wasSet = false;
    withHotel = aWithHotel;
    wasSet = true;
    return wasSet;
  }

  public int getDurationOfExpedition()
  {
    return durationOfExpedition;
  }

  public boolean getWithGuide()
  {
    return withGuide;
  }

  public boolean getWithHotel()
  {
    return withHotel;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWithGuide()
  {
    return withGuide;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWithHotel()
  {
    return withHotel;
  }
  /* Code from template association_GetOne */
  public Registration getRegistration()
  {
    return registration;
  }
  /* Code from template association_GetOne */
  public Guide getAssignedGuide()
  {
    return assignedGuide;
  }

  public boolean hasAssignedGuide()
  {
    boolean has = assignedGuide != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAssignedGuide(Guide aAssignedGuide)
  {
    boolean wasSet = false;
    Guide existingAssignedGuide = assignedGuide;
    assignedGuide = aAssignedGuide;
    if (existingAssignedGuide != null && !existingAssignedGuide.equals(aAssignedGuide))
    {
      existingAssignedGuide.removeAssignedMember(this);
    }
    if (aAssignedGuide != null)
    {
      aAssignedGuide.addAssignedMember(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Registration existingRegistration = registration;
    registration = null;
    if (existingRegistration != null)
    {
      existingRegistration.delete();
    }
    if (assignedGuide != null)
    {
      Guide placeholderAssignedGuide = assignedGuide;
      this.assignedGuide = null;
      placeholderAssignedGuide.removeAssignedMember(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "durationOfExpedition" + ":" + getDurationOfExpedition()+ "," +
            "withGuide" + ":" + getWithGuide()+ "," +
            "withHotel" + ":" + getWithHotel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedGuide = "+(getAssignedGuide()!=null?Integer.toHexString(System.identityHashCode(getAssignedGuide())):"null");
  }
}