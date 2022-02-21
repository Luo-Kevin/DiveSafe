package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 14 "DiveSafe.ump"
// line 118 "DiveSafe.ump"
// line 202 "DiveSafe.ump"
public abstract class User extends ADProgramAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private String emergencyContact;

  //User Associations
  private DivingSeason divingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aAccountName, String aAccountPassword, DiveSafe aDiveSafe, String aName, String aEmergencyContact, DivingSeason aDivingSeason)
  {
    super(aAccountName, aAccountPassword, aDiveSafe);
    name = aName;
    emergencyContact = aEmergencyContact;
    boolean didAddDivingSeason = setDivingSeason(aDivingSeason);
    if (!didAddDivingSeason)
    {
      throw new RuntimeException("Unable to create user due to divingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public DivingSeason getDivingSeason()
  {
    return divingSeason;
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
      existingDivingSeason.removeUser(this);
    }
    divingSeason.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    DivingSeason placeholderDivingSeason = divingSeason;
    this.divingSeason = null;
    if(placeholderDivingSeason != null)
    {
      placeholderDivingSeason.removeUser(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "divingSeason = "+(getDivingSeason()!=null?Integer.toHexString(System.identityHashCode(getDivingSeason())):"null");
  }
}