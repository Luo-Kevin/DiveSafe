package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 35 "DiveSafe.ump"
// line 74 "DiveSafe.ump"
public class Member extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int durationOfExpedition;
  private boolean withGuide;
  private boolean withHotel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aAccountName, String aAccountPassword, String aName, Long aEmergencyContact, DivingSeason aDivingSeason, int aDurationOfExpedition, boolean aWithGuide, boolean aWithHotel)
  {
    super(aAccountName, aAccountPassword, aName, aEmergencyContact, aDivingSeason);
    durationOfExpedition = aDurationOfExpedition;
    withGuide = aWithGuide;
    withHotel = aWithHotel;
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

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "durationOfExpedition" + ":" + getDurationOfExpedition()+ "," +
            "withGuide" + ":" + getWithGuide()+ "," +
            "withHotel" + ":" + getWithHotel()+ "]";
  }
}