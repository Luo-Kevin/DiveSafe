package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 29 "DiveSafe.ump"
// line 69 "DiveSafe.ump"
public class Guide extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private int price;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aAccountName, String aAccountPassword, String aName, Long aEmergencyContact, DivingSeason aDivingSeason, int aPrice)
  {
    super(aAccountName, aAccountPassword, aName, aEmergencyContact, aDivingSeason);
    price = aPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getPrice()
  {
    return price;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]";
  }
}