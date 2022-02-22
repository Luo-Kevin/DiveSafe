package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "DiveSafe.ump"
// line 107 "DiveSafe.ump"
// line 206 "DiveSafe.ump"
public abstract class ADProgramAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ADProgramAccount Attributes
  private String accountName;
  private String accountPassword;

  //ADProgramAccount Associations
  private DiveSafe diveSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ADProgramAccount(String aAccountName, String aAccountPassword, DiveSafe aDiveSafe)
  {
    accountName = aAccountName;
    accountPassword = aAccountPassword;
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create aDProgramAccount due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAccountName(String aAccountName)
  {
    boolean wasSet = false;
    accountName = aAccountName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAccountPassword(String aAccountPassword)
  {
    boolean wasSet = false;
    accountPassword = aAccountPassword;
    wasSet = true;
    return wasSet;
  }

  public String getAccountName()
  {
    return accountName;
  }

  public String getAccountPassword()
  {
    return accountPassword;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
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
      existingDiveSafe.removeADProgramAccount(this);
    }
    diveSafe.addADProgramAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeADProgramAccount(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "accountName" + ":" + getAccountName()+ "," +
            "accountPassword" + ":" + getAccountPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null");
  }
}