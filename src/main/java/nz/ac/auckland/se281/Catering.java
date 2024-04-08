package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {

  private int cateringCost;
  private String cateringName;

  public void selectCatering(CateringType cateringType) {

    switch (cateringType) {
      case BREAKFAST:
        this.cateringCost = CateringType.BREAKFAST.getCostPerPerson();
        this.cateringName = CateringType.BREAKFAST.getName();
        break;
      case LUNCH:
        this.cateringCost = CateringType.LUNCH.getCostPerPerson();
        this.cateringName = CateringType.LUNCH.getName();
        break;
      case DINNER:
        this.cateringCost = CateringType.DINNER.getCostPerPerson();
        this.cateringName = CateringType.DINNER.getName();
        break;
      case DRINKS:
        this.cateringCost = CateringType.DRINKS.getCostPerPerson();
        this.cateringName = CateringType.DRINKS.getName();
        break;
      case TWO_COURSE_BL:
        this.cateringCost = CateringType.TWO_COURSE_BL.getCostPerPerson();
        this.cateringName = CateringType.TWO_COURSE_BL.getName();
        break;
      case TWO_COURSE_LD:
        this.cateringCost = CateringType.TWO_COURSE_LD.getCostPerPerson();
        this.cateringName = CateringType.TWO_COURSE_LD.getName();
        break;
      case THREE_COURSE:
        this.cateringCost = CateringType.THREE_COURSE.getCostPerPerson();
        this.cateringName = CateringType.THREE_COURSE.getName();
        break;
    }
  }

  public int getCateringCost() {
    return cateringCost;
  }

  public String getCateringName() {
    return cateringName;
  }
}
