package nz.ac.auckland.se281;

public abstract class Service {

  protected int serviceCost;

  protected Service(int serviceCost) {
    this.serviceCost = serviceCost;
  }

  public int getServiceCost() {
    return serviceCost;
  }
}
