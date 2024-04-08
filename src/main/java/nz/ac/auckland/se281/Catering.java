package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {

  private CateringType cateringType;

  public Catering(CateringType cateringType, int attendeesCount) {
    super(attendeesCount * cateringType.getCostPerPerson());
    this.cateringType = cateringType;
  }

  public CateringType getCateringType() {
    return cateringType;
  }
}
