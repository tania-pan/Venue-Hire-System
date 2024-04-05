package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;

  private String venueDate;

  public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getHireFee() {
    return hireFee;
  }

  public String setVenueDate(String venueDate) {
    this.venueDate = venueDate;
    return venueDate;
  }

  public String getVenueDate() {
    return venueDate;
  }
}
