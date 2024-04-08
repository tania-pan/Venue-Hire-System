package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {

  private String cateringName;

  public Catering(CateringType cateringType, int attendeesCount) {
    super(attendeesCount * cateringType.getCostPerPerson());
    this.cateringName = cateringType.getName();
  }

  public String getCateringName() {
    return cateringName;
  }
}
