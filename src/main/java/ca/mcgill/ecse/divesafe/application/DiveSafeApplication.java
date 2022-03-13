/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.divesafe.application;

import java.sql.Date;
import ca.mcgill.ecse.divesafe.model.DiveSafe;

public class DiveSafeApplication {
  private static DiveSafe diveSafe;

  public static void main(String[] args) {
    // Launch UI here (For Iteration 4)
  }

  public static DiveSafe getDiveSafe() {
    if (diveSafe == null) {
      // these attributes are default, you should set them later with the setters
      diveSafe = new DiveSafe(new Date(0), 0, 0);
    }
    
    return diveSafe;
  }

  /**
   * This is the setter to create a new Dive Safe season in the application with the required parameters.
   * 
   * @author Siger Ma
   * @param aStartDate - Start date of the season
   * @param aNumDays - Duration of the season
   * @param aPriceOfGuidePerDay - Price of a guide for the season
   */

  public static void setDiveSafe(Date aStartDate, int aNumDays, int aPriceOfGuidePerDay) {
    diveSafe = new DiveSafe(aStartDate, aNumDays, aPriceOfGuidePerDay);
  }
}
