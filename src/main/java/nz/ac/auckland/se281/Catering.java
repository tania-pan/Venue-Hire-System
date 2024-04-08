package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {

  private int cateringCost;
  private String cateringName;

  public void bookCatering(CateringType cateringType, int attendeesCount) {
    this.cateringName = cateringType.getName();
    this.cateringCost = cost * attendeesCount;
  }

  public int getCateringCost() {
    return cateringCost;
  }

  public String getCateringName() {
    return cateringName;
  }
}
