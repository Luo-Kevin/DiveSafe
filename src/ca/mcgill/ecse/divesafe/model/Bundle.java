package ca.mcgill.ecse.divesafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 80 "DiveSafe.ump"
// line 184 "DiveSafe.ump"
// line 265 "DiveSafe.ump"
public class Bundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private int pricePerWeek;
  private double discount;

  //Bundle Associations
  private List<Registration> bundleRenters;
  private List<Equipment> piece;
  private DiveSafe diveSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(int aPricePerWeek, double aDiscount, DiveSafe aDiveSafe, Equipment... allPiece)
  {
    pricePerWeek = aPricePerWeek;
    discount = aDiscount;
    bundleRenters = new ArrayList<Registration>();
    piece = new ArrayList<Equipment>();
    boolean didAddPiece = setPiece(allPiece);
    if (!didAddPiece)
    {
      throw new RuntimeException("Unable to create Bundle, must have at least 2 piece. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDiveSafe = setDiveSafe(aDiveSafe);
    if (!didAddDiveSafe)
    {
      throw new RuntimeException("Unable to create bundle due to diveSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setDiscount(double aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }

  public double getDiscount()
  {
    return discount;
  }
  /* Code from template association_GetMany */
  public Registration getBundleRenter(int index)
  {
    Registration aBundleRenter = bundleRenters.get(index);
    return aBundleRenter;
  }

  public List<Registration> getBundleRenters()
  {
    List<Registration> newBundleRenters = Collections.unmodifiableList(bundleRenters);
    return newBundleRenters;
  }

  public int numberOfBundleRenters()
  {
    int number = bundleRenters.size();
    return number;
  }

  public boolean hasBundleRenters()
  {
    boolean has = bundleRenters.size() > 0;
    return has;
  }

  public int indexOfBundleRenter(Registration aBundleRenter)
  {
    int index = bundleRenters.indexOf(aBundleRenter);
    return index;
  }
  /* Code from template association_GetMany */
  public Equipment getPiece(int index)
  {
    Equipment aPiece = piece.get(index);
    return aPiece;
  }

  public List<Equipment> getPiece()
  {
    List<Equipment> newPiece = Collections.unmodifiableList(piece);
    return newPiece;
  }

  public int numberOfPiece()
  {
    int number = piece.size();
    return number;
  }

  public boolean hasPiece()
  {
    boolean has = piece.size() > 0;
    return has;
  }

  public int indexOfPiece(Equipment aPiece)
  {
    int index = piece.indexOf(aPiece);
    return index;
  }
  /* Code from template association_GetOne */
  public DiveSafe getDiveSafe()
  {
    return diveSafe;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundleRenters()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundleRenter(Registration aBundleRenter)
  {
    boolean wasAdded = false;
    if (bundleRenters.contains(aBundleRenter)) { return false; }
    bundleRenters.add(aBundleRenter);
    if (aBundleRenter.indexOfRentedBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundleRenter.addRentedBundle(this);
      if (!wasAdded)
      {
        bundleRenters.remove(aBundleRenter);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundleRenter(Registration aBundleRenter)
  {
    boolean wasRemoved = false;
    if (!bundleRenters.contains(aBundleRenter))
    {
      return wasRemoved;
    }

    int oldIndex = bundleRenters.indexOf(aBundleRenter);
    bundleRenters.remove(oldIndex);
    if (aBundleRenter.indexOfRentedBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundleRenter.removeRentedBundle(this);
      if (!wasRemoved)
      {
        bundleRenters.add(oldIndex,aBundleRenter);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleRenterAt(Registration aBundleRenter, int index)
  {  
    boolean wasAdded = false;
    if(addBundleRenter(aBundleRenter))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleRenters()) { index = numberOfBundleRenters() - 1; }
      bundleRenters.remove(aBundleRenter);
      bundleRenters.add(index, aBundleRenter);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleRenterAt(Registration aBundleRenter, int index)
  {
    boolean wasAdded = false;
    if(bundleRenters.contains(aBundleRenter))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleRenters()) { index = numberOfBundleRenters() - 1; }
      bundleRenters.remove(aBundleRenter);
      bundleRenters.add(index, aBundleRenter);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleRenterAt(aBundleRenter, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPieceValid()
  {
    boolean isValid = numberOfPiece() >= minimumNumberOfPiece();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPiece()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPiece(Equipment aPiece)
  {
    boolean wasAdded = false;
    if (piece.contains(aPiece)) { return false; }
    piece.add(aPiece);
    if (aPiece.indexOfPack(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPiece.addPack(this);
      if (!wasAdded)
      {
        piece.remove(aPiece);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removePiece(Equipment aPiece)
  {
    boolean wasRemoved = false;
    if (!piece.contains(aPiece))
    {
      return wasRemoved;
    }

    if (numberOfPiece() <= minimumNumberOfPiece())
    {
      return wasRemoved;
    }

    int oldIndex = piece.indexOf(aPiece);
    piece.remove(oldIndex);
    if (aPiece.indexOfPack(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPiece.removePack(this);
      if (!wasRemoved)
      {
        piece.add(oldIndex,aPiece);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setPiece(Equipment... newPiece)
  {
    boolean wasSet = false;
    ArrayList<Equipment> verifiedPiece = new ArrayList<Equipment>();
    for (Equipment aPiece : newPiece)
    {
      if (verifiedPiece.contains(aPiece))
      {
        continue;
      }
      verifiedPiece.add(aPiece);
    }

    if (verifiedPiece.size() != newPiece.length || verifiedPiece.size() < minimumNumberOfPiece())
    {
      return wasSet;
    }

    ArrayList<Equipment> oldPiece = new ArrayList<Equipment>(piece);
    piece.clear();
    for (Equipment aNewPiece : verifiedPiece)
    {
      piece.add(aNewPiece);
      if (oldPiece.contains(aNewPiece))
      {
        oldPiece.remove(aNewPiece);
      }
      else
      {
        aNewPiece.addPack(this);
      }
    }

    for (Equipment anOldPiece : oldPiece)
    {
      anOldPiece.removePack(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPieceAt(Equipment aPiece, int index)
  {  
    boolean wasAdded = false;
    if(addPiece(aPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPiece()) { index = numberOfPiece() - 1; }
      piece.remove(aPiece);
      piece.add(index, aPiece);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePieceAt(Equipment aPiece, int index)
  {
    boolean wasAdded = false;
    if(piece.contains(aPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPiece()) { index = numberOfPiece() - 1; }
      piece.remove(aPiece);
      piece.add(index, aPiece);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPieceAt(aPiece, index);
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
      existingDiveSafe.removeBundle(this);
    }
    diveSafe.addBundle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Registration> copyOfBundleRenters = new ArrayList<Registration>(bundleRenters);
    bundleRenters.clear();
    for(Registration aBundleRenter : copyOfBundleRenters)
    {
      aBundleRenter.removeRentedBundle(this);
    }
    ArrayList<Equipment> copyOfPiece = new ArrayList<Equipment>(piece);
    piece.clear();
    for(Equipment aPiece : copyOfPiece)
    {
      aPiece.removePack(this);
    }
    DiveSafe placeholderDiveSafe = diveSafe;
    this.diveSafe = null;
    if(placeholderDiveSafe != null)
    {
      placeholderDiveSafe.removeBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "," +
            "discount" + ":" + getDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "diveSafe = "+(getDiveSafe()!=null?Integer.toHexString(System.identityHashCode(getDiveSafe())):"null");
  }
}