package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "DiveSafe.ump"
// line 47 "DiveSafe.ump"
public abstract class ADProgramAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ADProgramAccount Attributes
  private String accountName;
  private String accountPassword;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ADProgramAccount(String aAccountName, String aAccountPassword)
  {
    accountName = aAccountName;
    accountPassword = aAccountPassword;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "accountName" + ":" + getAccountName()+ "," +
            "accountPassword" + ":" + getAccountPassword()+ "]";
  }
}